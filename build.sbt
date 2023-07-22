ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.11"

lazy val root = (project in file("."))
  .settings(
    name := "kata-maxibon-scala",
  )

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.2.16" % "test",
  "org.scalatestplus" %% "scalacheck-1-17" % "3.2.16.0" % "test",
  "io.github.etspaceman" %% "scalacheck-faker" % "7.0.0" % "test"
)
