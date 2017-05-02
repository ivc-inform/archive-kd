package ru.simplesys.eakd.sbtbuild

import sbt.Keys._
import sbt._

object PluginDeps {
    object versions {
        val devPluginVersion = "1.0.11-SNAPSHOT"
        val sourceGenJSVersion = "1.0.3"
        val transpileCoffeScriptVersion = "1.0.10"
        val mergeJSVersion = "1.0.4-SNAPSHOT"

        val sbtAspectJVersion = "0.10.2"
        val xsbtWebVersion = "0.9.1"
        val sbtPackVersion = "1.2.0-M8"

        val scalaJSPluginVersion = "0.6.15"
        val macroParadiseVersion = "2.0.1"
        val scalaFmtVersion = "0.5.4"
    }

    val devPlugin = addSbtPlugin("ru.simplesys" % "dev-plugin" % versions.devPluginVersion)
    val mergeJS = addSbtPlugin("ru.simplesys" % "merge-js" % versions.mergeJSVersion)
    val sourceGenJS = addSbtPlugin("com.simplesys" % "source-gen-js" % versions.sourceGenJSVersion)
    val transpileCoffeeScript = addSbtPlugin("ru.simplesys" % "transpile-coffeescript" % versions.transpileCoffeScriptVersion)

    val xsbtWeb = addSbtPlugin("com.earldouglas" % "xsbt-web-plugin" % versions.xsbtWebVersion)
    val sbtAspectJ = addSbtPlugin("com.typesafe.sbt" % "sbt-aspectj" % versions.sbtAspectJVersion)
    val sbtPack = addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % versions.sbtPackVersion)

    val scalaJSPlugin = addSbtPlugin("org.scala-js" % "sbt-scalajs" % versions.scalaJSPluginVersion)
    val scalafmt = addSbtPlugin("com.geirsson" % "sbt-scalafmt" % versions.scalaFmtVersion)

    val macroParadise = addCompilerPlugin("org.scalamacros" %% "paradise" % versions.macroParadiseVersion cross CrossVersion.full)
}

object CommonDeps {
    object versions {
        val scalaModulesVersion = "1.0.6"

        val jodaVersion = "2.8.2"
        val jodaConvertVersion = "1.7"

        val kamonVersion = "0.5.2"

        val doobieVersion = "0.2.3"

        val ssysCoreVersion = "1.3-SNAPSHOT"
        //val ssysCoreVersion = "1.2.98"

        val ssysDictionariesDataVersion = "1.1.2"
        val dictionaryMitEduInterfaceVersion = "2.3.3"

        //val smartclientVersion = "10.1.1"
        val smartclientVersion = "11.0-v20160805.10"
        //val smartclientVersion = "10.1-v20160316"

        val akkaVersion = "2.4.17"
        val akkaHttpVersion = "10.0.5"

        val servletAPIVersion = "3.1.0"

        val scalaTestVersion = "3.0.1"
        val scalaTagsVersion = "0.6.2"
        val scalaJSVersion = "1.1-SNAPSHOT"
        //val scalaJSVersion = "1.0.15"

        val scalajsDOMVersion = "0.9.1"
        val scalajsJQueryVersion = "0.9.0"
    }

    val scalaXml: Def.Initialize[Option[ModuleID]] = Def.setting(
        DepsHelper.moduleId(scalaVersion.value, Some("org.scala-lang.modules" %% "scala-xml" % versions.scalaModulesVersion), None)
    )


    val servletAPI = Def.setting("javax.servlet" % "javax.servlet-api" % versions.servletAPIVersion)

    val jodaTime = Def.setting("joda-time" % "joda-time" % versions.jodaVersion)
    val jodaConvert = Def.setting("org.joda" % "joda-convert" % versions.jodaConvertVersion)

    val akkaActor = Def.setting("com.typesafe.akka" %% "akka-actor" % versions.akkaVersion)
    val akkaSLF4J = Def.setting("com.typesafe.akka" %% "akka-slf4j" % versions.akkaVersion)
    val akkaPersistence = Def.setting("com.typesafe.akka" %% "akka-persistence" % versions.akkaVersion)
    //exclude("org.iq80.leveldb","leveldb"))
    val akkaTestKit = Def.setting("com.typesafe.akka" %% "akka-testkit" % versions.akkaVersion)
    val akkaHttpCore = Def.setting("com.typesafe.akka" %% "akka-http-core" % versions.akkaHttpVersion)
    val akkaHttp = Def.setting("com.typesafe.akka" %% "akka-http" % versions.akkaHttpVersion)
    val akkaHttpXml = Def.setting("com.typesafe.akka" %% "akka-http-xml" % versions.akkaHttpVersion)
    val akkaHttpSprayJson = Def.setting("com.typesafe.akka" %% "akka-http-spray-json" % versions.akkaHttpVersion)
    val akkaQuery = Def.setting("com.typesafe.akka" %% "akka-persistence-query-experimental" % versions.akkaVersion)

    val smartclient = Def.setting("com.simplesys" % "smartclient-js" % versions.smartclientVersion)

    val ssysIscMisc = Def.setting("com.simplesys.core" %% "isc-misc" % versions.ssysCoreVersion)
    val ssysIscComponents = Def.setting("com.simplesys.core" %% "isc-components" % versions.ssysCoreVersion)
    val ssysXMLExtender = Def.setting("com.simplesys.core" %% "xml-extender" % versions.ssysCoreVersion)
    val ssysCommonWebapp = Def.setting("com.simplesys.core" %% "common-webapp" % versions.ssysCoreVersion)
    val ssysCoreLibrary = Def.setting("com.simplesys.core" %% "core-library" % versions.ssysCoreVersion)
    val ssysCoreUtils = Def.setting("com.simplesys.core" %% "core-utils" % versions.ssysCoreVersion)
    val ssysAkkaExtender = Def.setting("com.simplesys.core" %% "akka-extender" % versions.ssysCoreVersion)
    val ssysConfigWrapper = Def.setting("com.simplesys.core" %% "config-wrapper" % versions.ssysCoreVersion)
    val ssysCommon = Def.setting("com.simplesys.core" %% "common" % versions.ssysCoreVersion)
    val ssysScalaIOExtender = Def.setting("com.simplesys.core" %% "scala-io-extender" % versions.ssysCoreVersion)
    val ssysJsonExtender = Def.setting("com.simplesys.core" %% "json-extender-typesafe" % versions.ssysCoreVersion)
    val ssysJDBCWrapper = Def.setting("com.simplesys.core" %% "jdbc-wrapper" % versions.ssysCoreVersion)
    val ssysCoreDomains = Def.setting("com.simplesys.core" %% "core-domains" % versions.ssysCoreVersion)
    val ssysScalaGen = Def.setting("com.simplesys.core" %% "scala-gen" % versions.ssysCoreVersion)
    val ssysBoneCPWrapper = Def.setting("com.simplesys.core" %% "bonecp-wrapper" % versions.ssysCoreVersion)
    val ssysLogBackWrapper = Def.setting("com.simplesys.core" %% "logback-wrapper" % versions.ssysCoreVersion)
    val scalaJSWrapper = Def.setting("com.simplesys" %% "common-types" % versions.scalaJSVersion)
    val scalaTags = Def.setting("com.lihaoyi" %% "scalatags" % versions.scalaTagsVersion)

    val scalaTest = Def.setting("org.scalatest" %% "scalatest" % versions.scalaTestVersion)
}

object DepsHelper {
    def moduleId(scalaVer: String, moduleId_2_11: Option[ModuleID], moduleId_2_10: Option[ModuleID]): Option[ModuleID] =
        CrossVersion.partialVersion(scalaVer) match {
            case Some((2, scalaMajor)) if scalaMajor >= 11 => moduleId_2_11
            case _ => moduleId_2_10
        }
}
