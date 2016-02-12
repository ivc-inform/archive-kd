package ru.simplesys.dmprocessing.sbtbuild

import sbt._
import Keys._
import org.scalajs.sbtplugin.ScalaJSPlugin.AutoImport._

trait WebUIClient {
  self: Build =>

  lazy val webUIClient = crossProject./*dependsOn(commonTypesCrossProj).*/
    settings(
      name := "webUIClient",
      libraryDependencies ++= {
        Seq()
      },
      testFrameworks += new TestFramework("utest.runner.Framework")
    ).
    jvmSettings(

      libraryDependencies ++= {
        Seq()
      }).
    jsSettings(
      libraryDependencies ++= Seq()
    )/*.jsConfigure(x => x.dependsOn(macroJsSub)).jvmConfigure(x => x.dependsOn(macroJvmSub))*/

  // Needed, so sbt finds the projects
  lazy val webUIClientJVM = webUIClient.jvm
  lazy val webUIClientJS = webUIClient.js

}
