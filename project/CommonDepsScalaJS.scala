package ru.simplesys.dmprocessing.sbtbuild
import sbt._
import Keys._
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._

object CommonDepsScalaJS {
  object versions {
    val scalaJSVersion = "1.0.1"
  }

  val smartclientScalaJSWrapper = Def.setting("com.simplesys" %%% "smartclient-wrapper" % versions.scalaJSVersion)

}
