import sbt._, Keys._

object Dependencies {

  object Version {
    val http4s = "0.11.3"
    val httpz = "0.3.0"
  }

  val httpz = "com.github.xuwei-k" %% "httpz" % Version.httpz
  val blaze = "org.http4s" %% "http4s-blaze-client" % Version.http4s
}
