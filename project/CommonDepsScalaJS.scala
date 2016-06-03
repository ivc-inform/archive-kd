package ru.simplesys.dmprocessing.sbtbuild
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import sbt._

object CommonDepsScalaJS {

  //val smartclientScalaJSWrapper = Def.setting("com.simplesys" %%% "smartclient-wrapper" % versions.scalaJSVersion)
  val scalaJSWrapper = Def.setting("com.simplesys" %%% "common-types" % PluginDeps.versions.scalaJSVersion)

}
