package controllers

import dao.PostgresDao
import javax.inject._
import play.api.Environment
import play.api.cache._
import play.api.cache.redis.CacheAsyncApi
import play.api.mvc._

import scala.concurrent.ExecutionContext

@Singleton
class HomeController @Inject()(
    @NamedCache("redis") redis: CacheAsyncApi,
    env: Environment,
    dao: PostgresDao,
    cc: ControllerComponents)(implicit ec: ExecutionContext)
    extends AbstractController(cc) {

  def index: Action[AnyContent] = Action {
    Ok(views.html.index(mode = env.mode.toString))
  }

  def healthCheck: Action[AnyContent] = Action.async {
    for {
      _ <- dao.dbHealthCheck()
      _ <- redis.set("healthcheck", "ok")
    } yield {
      Ok
    }
  }
}
