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
        Seq(
          //CommonSettings.cmnDependencies.prickle.value
          //CommonSettings.cmnDependencies.scalaAsync.value
          //CommonSettings.cmnDependencies.uTest.value
        )
      },
      testFrameworks += new TestFramework("utest.runner.Framework")
    ).
    jvmSettings(

      libraryDependencies ++= {
        Seq(
          //CommonSettings.jvmDependencies.scalaTest % "test"
        )
      }).
    jsSettings(
      //scalacOptions += "-Xlog-implicits",
      //persistLauncher := true,
      //mainClass := Some("ru.simplesys.dmprocess.webuijs.ScalaJSExample"),
      libraryDependencies ++= Seq(
        //CommonDepsScalaJS.smartclientScalaJS.value
        //CommonSettings.jsDependencies.smartClient.value % "provided"
      )
      //jsDependencies += "org.example" %% "js-thing" % "0.1" / "foo.js"
    )/*.jsConfigure(x => x.dependsOn(macroJsSub)).jvmConfigure(x => x.dependsOn(macroJvmSub))*/

  // Needed, so sbt finds the projects
  lazy val webUIClientJVM = webUIClient.jvm
  lazy val webUIClientJS = webUIClient.js

}
