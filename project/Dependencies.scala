import sbt._

object Dependencies {

  private val slickVersion = "5.0.0"

  lazy val slick = "com.typesafe.play" %% "play-slick" % slickVersion
  lazy val slickEvolution = "com.typesafe.play" %% "play-slick-evolutions" % slickVersion
  lazy val flyway = "org.flywaydb" %% "flyway-play" % "7.15.0"
  lazy val postgres = "org.postgresql" % "postgresql" % "42.3.1"
  lazy val redis = "com.github.karelcemus" %% "play-redis" % "2.6.1"
  lazy val db = Seq(
    slick,
    slickEvolution,
    postgres,
    flyway
  )
  lazy val cache = Seq(
    redis,
    play.sbt.PlayImport.cacheApi
  )
}
