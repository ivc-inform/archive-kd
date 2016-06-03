package ru.simplesys.eakd.sbtbuild

import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import sbt._

object CommonDepsScalaJS {

    //val smartclientScalaJSWrapper = Def.setting("com.simplesys" %%% "smartclient-wrapper" % versions.scalaJSVersion)
    val scalaJSWrapper = Def.setting("com.simplesys" %%% "common-types" % CommonDeps.versions.scalaJSVersion)

    val jointJS = Def.setting("com.simplesys" %%% "joint-js" % CommonDeps.versions.scalaJSVersion)
    val backboneJS = Def.setting("com.simplesys" %%% "backbone-js" % CommonDeps.versions.scalaJSVersion)
    val underscoreJS = Def.setting("com.simplesys" %%% "underscore-js" % CommonDeps.versions.scalaJSVersion)
    val scalajsDOM = Def.setting("org.scala-js" %%% "scalajs-dom" % CommonDeps.versions.scalajsDOMVersion)
    val scalajsJQuey = Def.setting("be.doeraene" %%% "scalajs-jquery" % CommonDeps.versions.scalajsJQueryVersion)

}
