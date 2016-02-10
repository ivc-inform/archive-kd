package ru.simplesys.dmprocessing.sbtbuild

import sbt._
import Keys._

trait DbObjects {
  self: Build =>

  import ru.simplesys.plugins.sourcegen.DevPlugin


  lazy val dbObjects = Project(id = "db-objects", base = file("db-objects")).enablePlugins(DevPlugin).settings(
    libraryDependencies ++= Seq(
      CommonDeps.ssysCoreLibrary.value,
      CommonDeps.ssysJsonExtender.value,
      CommonDeps.ssysJDBCWrapper.value,
      CommonDeps.jodaTime.value,
      CommonDeps.jodaConvert.value,

//      CommonDeps.ssysCommon.value,
//      CommonDeps.scalazCore.value,
      CommonDeps.scalaTest.value % "test"
    )
  ).settings(DevPlugin.devPluginGeneratorSettings).settings({
    import ru.simplesys.plugins.sourcegen.DevPlugin._
    Seq(
      sourceSchemaDir in DevConfig := (resourceDirectory in Compile).value / "defs",
      startPackageName in DevConfig := "ru.simplesys.defs",
      contextPath in DevConfig := "acrchive-kd",
      maxArity := 254,
      sourceGenerators in Compile <+= (generateBoScalaCode in DevConfig)
    )
  }
    ).settings(CommonSettings.defaultProjectSettings)
}
