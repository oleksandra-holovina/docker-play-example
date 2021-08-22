FROM node:14.17.5-bullseye-slim AS webpack
LABEL maintainer_nick="Nick Janetakis <nick.janetakis@gmail.com>" \
    maintainer_lexie="Oleksandra Holovina <oleksandra.holovina@gmail.com>"

WORKDIR /app/assets

RUN rm -rf /var/lib/apt/lists/* /usr/share/doc /usr/share/man \
  && mkdir -p /node_modules && chown node:node -R /node_modules /app

USER node

COPY --chown=node:node assets/package.json assets/*yarn* ./

RUN yarn install

ARG NODE_ENV="production"
ENV NODE_ENV="${NODE_ENV}" \
    USER="node"

COPY --chown=node:node assets .

# We need to copy the main web app so that PurgeCSS can find our HTML templates
# at build time so it knows what to purge / keep in the final CSS bundle.
#
# This doesn't bloat anything in the end because only the final assets get
# copied over in another build stage. Yay for multi-stage builds!
COPY --chown=node:node app/views /app/app/views

RUN if [ "${NODE_ENV}" != "development" ]; then \
  yarn run build; else mkdir -p /app/public; fi

CMD ["bash"]

################################################################################

FROM openjdk:11.0.11-slim-buster AS app_assembly
LABEL maintainer_nick="Nick Janetakis <nick.janetakis@gmail.com>" \
    maintainer_lexie="Oleksandra Holovina <oleksandra.holovina@gmail.com>"

WORKDIR /app

RUN apt-get update \
  && apt-get install -y gnupg curl --no-install-recommends \
  && echo "deb https://repo.scala-sbt.org/scalasbt/debian /" | tee -a /etc/apt/sources.list.d/sbt.list \
  && curl -sL "https://keyserver.ubuntu.com/pks/lookup?op=get&search=0x2EE0EA64E40A89B84B2DF73499E82A75642AC823" | apt-key add \
  && apt-get update \
  && apt-get install -y sbt --no-install-recommends \
  && rm -rf /var/lib/apt/lists/* /usr/share/doc /usr/share/man \
  && apt-get clean \
  && useradd --create-home java \
  && chown java:java -R /app

USER java

COPY --chown=java:java build.sbt build.sbt
COPY --chown=java:java project project

RUN sbt assemblyPackageDependency

COPY --chown=java:java . ./
COPY --chown=java:java --from=webpack /app/public /public

RUN ln -s /public /app/public && sbt assembly && rm -rf /app/public

EXPOSE 8000

ENTRYPOINT ["/app/bin/docker-entrypoint-web"]

CMD sbt run

################################################################################

FROM openjdk:11.0.11-jre-slim-buster AS app
LABEL maintainer_nick="Nick Janetakis <nick.janetakis@gmail.com>" \
    maintainer_lexie="Oleksandra Holovina <oleksandra.holovina@gmail.com>"

WORKDIR /app

RUN apt-get update \
  && apt-get install -y curl --no-install-recommends \
  && rm -rf /var/lib/apt/lists/* /usr/share/doc /usr/share/man \
  && apt-get clean \
  && useradd --create-home java \
  && chown java:java -R /app

USER java

COPY --chown=java:java --from=app_assembly /app/target/scala-2.13/docker-play-example-deps.jar docker-play-example-deps.jar
COPY --chown=java:java --from=app_assembly /app/target/scala-2.13/dockerplayexample_2.13-1.0-web-assets.jar dockerplayexample_web-assets.jar
COPY --chown=java:java --from=app_assembly /app/target/scala-2.13/docker-play-example.jar docker-play-example.jar
COPY --chown=java:java --from=webpack /app/public /public
COPY --chown=java:java bin/ ./bin

EXPOSE 8000

ENTRYPOINT ["/app/bin/docker-entrypoint-web"]

CMD ["java", "-cp", "dockerplayexample_web-assets.jar:docker-play-example-deps.jar:docker-play-example.jar", "play.core.server.ProdServerStart"]
