import sbt._
import Keys._
import Common._

object build extends Build {

  private[this] val blazeName = "httpz-http4s-blaze"
  private[this] val allName = "httpz-http4s"

  val testSetting = TaskKey[Unit]("runTests") := (run in Test).toTask("").value

  private[this] def module(id: String) =
    Project(id, file(id)).settings(commonSettings)

  val modules: List[String] = (
    blazeName ::
    allName ::
    Nil
  )

  lazy val tests = Project("tests", file("tests")).settings(
    commonSettings : _*
  ).settings(
    libraryDependencies ++= ("filter" :: "jetty" :: Nil).map(m =>
      "net.databinder" %% s"unfiltered-$m" % "0.8.2"
    ) ++ Seq(Dependencies.httpz),
    publishArtifact := false,
    publish := {},
    publishLocal := {}
  )

  lazy val blaze = module("blaze").settings(
    name := blazeName,
    libraryDependencies ++= Seq(
      Dependencies.httpz,
      Dependencies.blaze
    ),
    testSetting
  ).dependsOn(tests % "test")

  val root = Project("root", file(".")).settings(
    commonSettings
  ).settings(
    name := allName,
    artifacts := Nil,
    packagedArtifacts := Map.empty
  ).aggregate(
    blaze, tests
  )
}
