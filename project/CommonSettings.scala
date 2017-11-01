package ru.simplesys.eakd.sbtbuild

object CommonSettings {
  object settingValues {

    val scalaVersion = "2.12.4"
    val organization = "com.ivc-inform"
    val name = "archive-kd"
    val version = "1.0.0.1"
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
}
