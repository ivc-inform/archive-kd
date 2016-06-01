package ru.simplesys.dmprocessing.sbtbuild

import sbt.Setting

object CommonSettings {
  object settingValues {
    val baseVersion = "1.0"

    val scalaVersion = "2.11.8"
    val organization = "com.simplesys.dmprocessing"
    val scalacOptions = Seq(
      "-feature",
      "-language:higherKinds",
      "-language:implicitConversions",
      "-language:existentials",
      "-language:postfixOps",
      "-deprecation",
      "-unchecked")
  }

  val defaultSettings = {
    import sbt.Keys._
    Seq(
      scalacOptions := settingValues.scalacOptions,
      organization := settingValues.organization
    )
  }

  val defaultProjectSettings: Seq[Setting[_]] = {
    aether.AetherPlugin.autoImport.overridePublishSettings
  }
}
