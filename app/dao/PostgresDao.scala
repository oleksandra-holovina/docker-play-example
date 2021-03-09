package dao

import com.google.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.PostgresProfile
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class PostgresDao @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {

  private val dbConfig = dbConfigProvider.get[PostgresProfile]

  def dbHealthCheck(): Future[Vector[Int]] = {
    dbConfig.db.run(sql"SELECT 1".as[Int])
  }
}
