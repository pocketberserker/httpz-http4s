import build._

lazy val tests = Project("tests", file("tests")).settings(
  Common.commonSettings : _*
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
  Common.commonSettings
).settings(
  name := allName,
  artifacts := Nil,
  packagedArtifacts := Map.empty
).aggregate(
  blaze, tests
)
