package ru.simplesys.eakd.sbtbuild

import sbt.Setting

object CommonSettings {
  object settingValues {
    val baseVersion = "1.0"

    val scalaVersion = "2.12.3"
    val organization = "com.ivc-inform.archive"
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

  val dockerGroupName = "uandrew1965"
}
