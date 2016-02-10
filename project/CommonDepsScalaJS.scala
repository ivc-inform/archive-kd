package ru.simplesys.dmprocessing.sbtbuild
import sbt._
import Keys._
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._

object CommonDepsScalaJS {
  object versions {
    val smartclientScalaJSWrapperVersion = "1.1-SNAPSHOT"
  }

  val smartclientScalaJSWrapper = Def.setting("com.simplesys" %%% "smartclient-wrapper" % versions.smartclientScalaJSWrapperVersion)

}
