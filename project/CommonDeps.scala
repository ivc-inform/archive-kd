package ru.simplesys.dmprocessing.sbtbuild

import sbt._
import Keys._

object PluginDeps {
  object versions {
    val devPluginVersion = "1.0.10-SNAPSHOT"
    val sourceGenJSVersion = "1.0.3"
    val transpileCoffeScriptVersion = "1.0.3"
    val mergeJSVersion = "1.0.4"

    val sbtAspectJVersion = "0.10.2"
    //val xsbtWebVersion = "2.0.4"
    val xsbtWebVersion = "0.9.1"
    val sbtPackVersion = "0.7.7"

    val scalaJSPluginVersion = "0.6.5"

    val macroParadiseVersion = "2.0.1"
  }

  val devPlugin = addSbtPlugin("ru.simplesys" % "dev-plugin" % versions.devPluginVersion)
  val mergeJS = addSbtPlugin("ru.simplesys" % "merge-js" % versions.mergeJSVersion)
  val sourceGenJS = addSbtPlugin("com.simplesys" % "source-gen-js" % versions.sourceGenJSVersion)
  val transpileCoffeeScript = addSbtPlugin("ru.simplesys" % "transpile-coffeescript" % versions.transpileCoffeScriptVersion)

  val xsbtWeb = addSbtPlugin("com.earldouglas" % "xsbt-web-plugin" % versions.xsbtWebVersion)
  val sbtAspectJ = addSbtPlugin("com.typesafe.sbt" % "sbt-aspectj" % versions.sbtAspectJVersion)
  val sbtPack = addSbtPlugin("org.xerial.sbt" % "sbt-pack" % versions.sbtPackVersion)

  val scalaJSPlugin = addSbtPlugin("org.scala-js" % "sbt-scalajs" % versions.scalaJSPluginVersion)

  val macroParadise = addCompilerPlugin("org.scalamacros" %% "paradise" % versions.macroParadiseVersion cross CrossVersion.full)
}

object CommonDeps {
  object versions {
    val scalaModulesVersion = "1.0.4"

    val jodaVersion = "2.8.2"
    val jodaConvertVersion = "1.7"

    val jettyVersion = "9.3.7.v20160115"

    val kamonVersion = "0.5.2"

    val doobieVersion = "0.2.3"

    val ssysCoreVersion = "1.2-SNAPSHOT"
    //val ssysCoreVersion = "1.2.63"

    val ssysDictionariesDataVersion = "1.1.2"
    val dictionaryMitEduInterfaceVersion = "2.3.3"

    //val smartclientVersion = "10.1.1"
    val smartclientVersion = "10.1-v20151216"

    val akkaVersion = "2.4.1"
    val akkaHttpVersion = "2.0.2"

    val twitterChill = "0.6.0"
    //val akkaPersistenceBerkleyExtVersion = "1.0.1"

    val shapelessVersion = "2.2.5"
    val scalazVersion = "7.1.5"
    val scalazStreamVersion = "0.7.2a"
    val parboiled2Version = "2.1.0"

    val breezeVersion = "0.11.2"

    val enumeratumVersion = "1.3.4"

    val monocleVersion = "1.1.1"

    val levelDBVersion = "0.7"
    val levelDBJNIVersion = "1.8"

    val servletAPIVersion = "3.1.0"

    val scoptVersion = "3.3.0"

    val oracle11DriverVersion = "11.2.0.4"
    val hikariCPVersion = "2.4.0"

    val scalaTestVersion = "2.2.4"
    val scalajHttpVersion = "1.1.5"

    val json4sJacksonVersion = "3.2.11"

    val jacksonVersion = "2.6.0"

    val csvReaderVersion = "1.2.2"

    val log4jdbcVersion = "1.16"

    val scalaIncubatorIOVersion = "0.4.3"

    val apachePoiVersion = "3.13"
  }

  val scalaXml: Def.Initialize[Option[ModuleID]] = Def.setting(
    DepsHelper.moduleId(scalaVersion.value, Some("org.scala-lang.modules" %% "scala-xml" % versions.scalaModulesVersion), None)
  )


  val servletAPI = Def.setting("javax.servlet" % "javax.servlet-api" % versions.servletAPIVersion)

  val jodaTime = Def.setting("joda-time" % "joda-time" % versions.jodaVersion)
  val jodaConvert = Def.setting("org.joda" % "joda-convert" % versions.jodaConvertVersion)


  val scalazCore = Def.setting("org.scalaz" %% "scalaz-core" % versions.scalazVersion)
  val scalazStream = Def.setting("org.scalaz.stream" %% "scalaz-stream" % versions.scalazStreamVersion)

  val twitterChillAkka = Def.setting("com.twitter" %% "chill-akka" % versions.twitterChill)
  //val akkaPersistenceBerkleyExt = Def.setting("com.github.bseibel" %% "akka-persistence-bdb" % versions.akkaPersistenceBerkleyExtVersion)

  val akkaActor = Def.setting("com.typesafe.akka" %% "akka-actor" % versions.akkaVersion)
  val akkaSLF4J = Def.setting("com.typesafe.akka" %% "akka-slf4j" % versions.akkaVersion)
  val akkaPersistence = Def.setting("com.typesafe.akka" %% "akka-persistence" % versions.akkaVersion)  //exclude("org.iq80.leveldb","leveldb"))
  val akkaTestKit = Def.setting("com.typesafe.akka" %% "akka-testkit" % versions.akkaVersion)

  val levelDB = Def.setting("org.iq80.leveldb" % "leveldb" % versions.levelDBVersion)
  val levelDBJNI = Def.setting("org.fusesource.leveldbjni" % "leveldbjni-all" % versions.levelDBJNIVersion)

  val jettyWebapp = Def.setting("org.eclipse.jetty" % "jetty-webapp" % versions.jettyVersion)
  val jettyAnnotations = Def.setting("org.eclipse.jetty" % "jetty-annotations" % versions.jettyVersion)
  val jettyPlus = Def.setting("org.eclipse.jetty" % "jetty-plus" % versions.jettyVersion)

  val smartclient = Def.setting("com.simplesys" % "smartclient-js" % versions.smartclientVersion)

  val ssysIscComponents = Def.setting("com.simplesys.core" %% "isc-components" % versions.ssysCoreVersion)
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

  val ssysDictOpenCorporaData = Def.setting("com.simplesys.dictionaries" % "opencorpora-data" % versions.ssysDictionariesDataVersion)
  val ssysDictFiasData = Def.setting("com.simplesys.dictionaries" % "fias-data" % versions.ssysDictionariesDataVersion)
  val ssysDictGeonamesData = Def.setting("com.simplesys.dictionaries" % "geonames-data" % versions.ssysDictionariesDataVersion)
  val ssysDictWordNetData = Def.setting("com.simplesys.dictionaries" % "wordnet-data" % versions.ssysDictionariesDataVersion)
  val ssysDictWordNetAPI = Def.setting("com.simplesys.dictionaries" % "wordnet-api" % versions.ssysDictionariesDataVersion)

  //val dictionaryMitEduInterface = Def.setting("com.simplesys.edu.mit" % "jwi" % versions.dictionaryMitEduInterfaceVersion)
  
  val parboiled2 = Def.setting("org.parboiled" %% "parboiled" % versions.parboiled2Version)

  val breezeCore = Def.setting("org.scalanlp" %% "breeze" % versions.breezeVersion)
  val breezeNatives = Def.setting("org.scalanlp" %% "breeze-natives" % versions.breezeVersion)

  val doobieCore = Def.setting("org.tpolecat" %% "doobie-core" % versions.doobieVersion)
  val hikariCP = Def.setting("com.zaxxer" % "HikariCP" % versions.hikariCPVersion)
  val shapeless = Def.setting("com.chuusai" %% "shapeless" % versions.shapelessVersion)

  val enumeratum = Def.setting("com.beachape" %% "enumeratum" % versions.enumeratumVersion)

  val scopt = Def.setting("com.github.scopt" %% "scopt" % versions.scoptVersion)

  val monocleCore = Def.setting("com.github.julien-truffaut" %% "monocle-core" % versions.monocleVersion)
  val monocleGeneric = Def.setting("com.github.julien-truffaut" %% "monocle-generic" % versions.monocleVersion)
  val monocleMacro = Def.setting("com.github.julien-truffaut" %% "monocle-macro" % versions.monocleVersion)

  val kamonCore = Def.setting("io.kamon" %% "kamon-core" % versions.kamonVersion)
  val kamonAkka = Def.setting("io.kamon" %% "kamon-akka" % versions.kamonVersion)
  val kamonStatsD = Def.setting("io.kamon" %% "kamon-statsd" % versions.kamonVersion)
  val kamonSystemMetrics = Def.setting("io.kamon" %% "kamon-system-metrics" % versions.kamonVersion)


  val jdbcOracle11 = Def.setting("com.simplesys.jdbc.drivers" % "oracle" % versions.oracle11DriverVersion)

  val scalaTest = Def.setting("org.scalatest" %% "scalatest" % versions.scalaTestVersion)
  val scalajHttp = Def.setting("org.scalaj" %% "scalaj-http" % versions.scalajHttpVersion)
  val json4sJackson = Def.setting("org.json4s" %% "json4s-jackson" % versions.json4sJacksonVersion)

  val jacksonCore = Def.setting("com.fasterxml.jackson.core" % "jackson-core" % versions.jacksonVersion)
  val jacksonDatabind = Def.setting("com.fasterxml.jackson.core" % "jackson-databind" % versions.jacksonVersion)

  val csvReader = Def.setting("com.github.tototoshi" %% "scala-csv" % versions.csvReaderVersion)

  val scalaIncubatorIOCore = Def.setting("com.github.scala-incubator.io" %% "scala-io-core" % versions.scalaIncubatorIOVersion)
  val scalaIncubatorIOFile = Def.setting("com.github.scala-incubator.io" %% "scala-io-file" % versions.scalaIncubatorIOVersion)

  val apachePoi = Def.setting("org.apache.poi" % "poi" % versions.apachePoiVersion)

  val log4jdbc = Def.setting("org.bgee.log4jdbc-log4j2" % "log4jdbc-log4j2-jdbc4.1" % versions.log4jdbcVersion)
}

object DepsHelper {
  def moduleId(scalaVer: String, moduleId_2_11: Option[ModuleID], moduleId_2_10: Option[ModuleID]): Option[ModuleID] =
    CrossVersion.partialVersion(scalaVer) match {
      case Some((2, scalaMajor)) if scalaMajor >= 11 => moduleId_2_11
      case _ => moduleId_2_10
    }
}
