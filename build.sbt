
ThisBuild / name := "ScalafixSandbox"
ThisBuild / version := "0.1"
ThisBuild / scalaVersion := "2.13.2"


/*
  In case you cannot afford compilation time overhead due to semanticdb-scalac,
  use this setting and call `scalafixEnable` command before you call `scalafix`.
 */
lazy val myProject = (project in file("."))

//For refactoring-purpose, semanticdb-scalac compiler plugin is always enabled
lazy val refactor = project
  .shadow(myProject)
  .settings(ScalafixSettings.permanent: _*)
  .light

