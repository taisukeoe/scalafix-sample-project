import sbt.Keys._
import sbt._
import scalafix.sbt.ScalafixPlugin.autoImport.{
  scalafixDependencies,
  scalafixSemanticdb
}

object ScalafixSettings {
  // Required for RemoveUnused Rule
  private val unused = "-Ywarn-unused"

  // This settings are not completed for Scalafix SemanticRule.
  // Call scalafixEnable before you invoke `scalafix` task
  lazy val adhoc: Seq[Setting[_]] = Seq(
    scalacOptions += unused,
    //Add external Scalafix Rules here.
    scalafixDependencies in ThisBuild ++= Seq(
      "com.github.vovapolu" %% "scaluzzi" % "0.1.4",
      "com.nequissimus" %% "sort-imports" % "0.3.2"
    )
  ) ++ Seq(Compile, Test).map(_ / console / scalacOptions -= unused)

  //These settings require additional compilation time, depending on project codebase.
  lazy val permanentlyAdded: Seq[Setting[_]] =
    adhoc ++ Seq(
      scalacOptions += "-Yrangepos",
      addCompilerPlugin(scalafixSemanticdb)
    )
}
