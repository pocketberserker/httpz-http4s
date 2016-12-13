import sbt._
import Keys._
import Common._

object build {

  final val blazeName = "httpz-http4s-blaze"
  final val allName = "httpz-http4s"

  val testSetting = TaskKey[Unit]("runTests") := (run in Test).toTask("").value

  def module(id: String) =
    Project(id, file(id)).settings(commonSettings)

  val modules: List[String] = (
    blazeName ::
    allName ::
    Nil
  )
}
