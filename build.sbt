import Dependencies._
import sbt.Keys._
import sbt._

lazy val root = (project in file("."))
  .enablePlugins(PlayScala, AssemblyPlugin)
  .settings(
    name := "DockerPlayExample",
    version := "1.0",
    scalaVersion := "2.13.6",
    scalacOptions += "-target:11",
    initialize := {
      val _ = initialize.value
      val required = "11"
      val current = sys.props("java.specification.version")
      assert(current == required, s"Unsupported JDK: java.specification.version $current != $required")
    },
    scalastyleFailOnWarning := true,
    mainClass in assembly := Some("play.core.server.ProdServerStart"),
    fullClasspath in assembly += Attributed.blank(PlayKeys.playPackageAssets.value),
    assemblyJarName in assembly := "docker-play-example.jar",
    assemblyJarName in assemblyPackageDependency := "docker-play-example-deps.jar",
    assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false, includeDependency = false),
    test in assembly := {},
    resolvers ++= Seq(
      "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases",
      "Akka Snapshot Repository" at "https://repo.akka.io/snapshots/"
    ),
    libraryDependencies ++= Seq(ws, specs2 % Test, guice) ++ db ++ cache
  )

