lazy val common: Seq[Setting[_]] = Seq(
  name := "ScalafixSandbox",
  version := "0.1",
  scalaVersion := "2.13.1"
)

/*
  In case you cannot afford compilation time overhead due to semanticdb-scalac,
  use this setting and call `scalafixEnable` command before you call `scalafix`.
 */
lazy val myProject = (project in file("."))
  .settings(common)
  .settings(ScalafixSettings.adhoc)

//For refactoring-purpose, semanticdb-scalac compiler plugin is always enabled
lazy val refactor = project
  .settings(common)
  .settings(
    for {
      cfg <- Seq(Compile, Test)
      ky <- Seq(
        sourceDirectory,
        resourceDirectory
      ) // You may need to include managed or unmanaged directories
    } yield cfg / ky := (myProject / cfg / ky).value
  )
  .settings(ScalafixSettings.permanentlyAdded)
