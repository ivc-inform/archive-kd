package ru.simplesys.eakd.sbtbuild

import sbt._

object PluginDeps {
    object versions {
        val devPluginVersion = "1.3.12-SNAPSHOT"
        val sbtCoffeScriptVersion = "1.1.3-SNAPSHOT"
        val mergeJSVersion = "1.0.10-SNAPSHOT"
        val xsbtWebVersion = "4.0.1-SNAPSHOT"
        val sbtNativePackagerVersion = "1.2.3-SNAPSHOT"
        val scalaJSPluginVersion = "0.6.20"
        val jrabelPluginVersion = "0.11.0-SNAPSHOT"
    }

    val devPlugin = addSbtPlugin("ru.simplesys" % "dev-plugin" % versions.devPluginVersion)
    val mergeJS = addSbtPlugin("ru.simplesys" % "merge-js" % versions.mergeJSVersion)
    val sbtCoffeeScript = addSbtPlugin("com.typesafe.sbt" % "sbt-coffeescript" % versions.sbtCoffeScriptVersion)
    val xsbtWeb = addSbtPlugin("com.earldouglas" % "xsbt-web-plugin" % versions.xsbtWebVersion)
    val sbtNativePackager = addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % versions.sbtNativePackagerVersion)
    val scalaJSPlugin = addSbtPlugin("org.scala-js" % "sbt-scalajs" % versions.scalaJSPluginVersion)
    val jrebelPlugin = addSbtPlugin("com.simplesys" % "jrebel-plugin" % versions.jrabelPluginVersion)
}

object CommonDeps {
    object versions {
        val scalaModulesVersion = "1.0.6"

        val jodaVersion = "2.8.2"
        val jodaConvertVersion = "1.7"

        val kamonVersion = "0.5.2"

        val doobieVersion = "0.4.1"

        val ssysCoreVersion = "1.4-SNAPSHOT"
        val smartclientVersion = "11.1-v20170703.1"

        val servletAPIVersion = "3.1.0"

        val scalaTestVersion = "3.0.1"
        val scalaTagsVersion = "0.6.5"
        val scalaDomVersion = "0.9.4-SNAPSHOT"
        val jQueryVersion = "0.9.1"
        val uPickleVersion = "0.4.4"

        val scalaJSVersion = "1.4-SNAPSHOT"
        //val scalaJSVersion = "1.3.5"

        val scalajsDOMVersion = "0.9.1"
        val scalajsJQueryVersion = "0.9.0"

        val jettyVersion = "9.4.6.v20170531"
        val jdbcOracle11DriverVersion = "11.2.0.4"
        val jdbcOracle12DriverVersion = "12.2.0.1"

        val commonsFileuploadVersion = "1.3.3"
        val commonsIOVersion = "2.5"
        val scalaURIVersion = "0.4.16"
        val slickVersion = "3.2.1"
        val configWrapperVersion = "0.4.4"
    }

    val servletAPI = "javax.servlet" % "javax.servlet-api" % versions.servletAPIVersion

    val smartclient = "com.simplesys" % "smartclient-js" % versions.smartclientVersion

    val ssysIscComponents = "com.simplesys.core" %% "isc-components" % versions.ssysCoreVersion
    val ssysCommonWebapp = "com.simplesys.core" %% "common-webapp" % versions.ssysCoreVersion

    val ssysIscMisc = "com.simplesys.core" %% "isc-misc" % versions.ssysCoreVersion
    val ssysXMLExtender = "com.simplesys.core" %% "xml-extender" % versions.ssysCoreVersion
    val ssysCoreLibrary = "com.simplesys.core" %% "core-library" % versions.ssysCoreVersion
    val ssysCoreUtils = "com.simplesys.core" %% "core-utils" % versions.ssysCoreVersion
    val ssysAkkaExtender = "com.simplesys.core" %% "akka-extender" % versions.ssysCoreVersion
    val ssysConfigWrapper = "com.simplesys.core" %% "config-wrapper" % versions.ssysCoreVersion
    val ssysCommon = "com.simplesys.core" %% "common" % versions.ssysCoreVersion
    val ssysScalaIOExtender = "com.simplesys.core" %% "scala-io-extender" % versions.ssysCoreVersion
    val ssysJsonExtender = "com.simplesys.core" %% "json-extender-typesafe" % versions.ssysCoreVersion
    val ssysJDBCWrapper = "com.simplesys.core" %% "jdbc-wrapper" % versions.ssysCoreVersion
    val ssysCoreDomains = "com.simplesys.core" %% "core-domains" % versions.ssysCoreVersion
    val ssysScalaGen = "com.simplesys.core" %% "scala-gen" % versions.ssysCoreVersion

    val oraclePoolDataSources = "com.simplesys.core" %% "oracle-pool-datasources" % versions.ssysCoreVersion
    val hikariPoolDataSources = "com.simplesys.core" %% "hikari-cp" % versions.ssysCoreVersion
    val ssysLogBackWrapper = "com.simplesys.core" %% "logback-wrapper" % versions.ssysCoreVersion
    val scalaJSWrapper = "com.simplesys" %% "common-types" % versions.scalaJSVersion
    val scalaTags = "com.lihaoyi" %% "scalatags" % versions.scalaTagsVersion
    val uPickle = "com.lihaoyi" %% "upickle" % versions.uPickleVersion

    val jettyRuner = "org.eclipse.jetty" % "jetty-runner" % versions.jettyVersion

    val scalaTest = "org.scalatest" %% "scalatest" % versions.scalaTestVersion

    val jdbcOracle12 = "com.oracle.jdbc" % "ojdbc8" % versions.jdbcOracle12DriverVersion
    val jdbcOracle12UCP = "com.oracle.jdbc" % "ucp" % versions.jdbcOracle12DriverVersion
    val jdbcOracleN18_12 = "com.oracle.jdbc" % "orai18n" % versions.jdbcOracle12DriverVersion

    val doobieCore = "org.tpolecat" %% "doobie-core" % versions.doobieVersion
    val doobieCoreCats = "org.tpolecat" %% "doobie-core-cats" % versions.doobieVersion

    val commonsFileupload = "commons-fileupload" % "commons-fileupload" % versions.commonsFileuploadVersion
    val commonsIO = "commons-io" % "commons-io" % versions.commonsIOVersion

    val scalaURI = "com.netaporter" %% "scala-uri" % versions.scalaURIVersion
    val slick = "com.typesafe.slick" %% "slick" % versions.slickVersion

    val configWrapper = "com.github.kxbmap" %% "configs" % versions.configWrapperVersion

}

