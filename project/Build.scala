import com.typesafe.sbt.SbtGit.git
import com.typesafe.sbt.{SbtGit, GitVersioning}
import ru.simplesys.dmprocessing.sbtbuild._

import sbt.Keys._
import sbt._

object DmProcessingBuild extends Build
with XmlPullHelper
with DoobieHelpers
with Tokenizer
with Classifier
with Dictionary
with DbObjects
with Templates
with ProcessingCore
with OraEmbeddedHelpers
with CliTools
with WebUI
with WebUIClient
with CurrencyRates
{
  override def settings: Seq[Def.Setting[_]] = super.settings ++ CommonSettings.defaultSettings ++ {
    import ru.simplesys.plugins.sourcegen.DevPlugin._

    Seq(
      git.baseVersion := CommonSettings.settingValues.baseVersion,
      scalaVersion := CommonSettings.settingValues.scalaVersion,

      publishTo <<= version { (v: String) =>
        val corporateRepo = "http://toucan.simplesys.lan/"
        if (v.trim.endsWith("SNAPSHOT"))
          Some("snapshots" at corporateRepo + "artifactory/libs-snapshot-local")
        else
          Some("releases" at corporateRepo + "artifactory/libs-release-local")
      },

      credentials += Credentials(Path.userHome / ".ivy2" / ".credentials"),

      //liquibaseUsername in DevConfig   := "test",
      liquibaseUsername in DevConfig := "mosk",
      //liquibasePassword in DevConfig   := "test",
      liquibasePassword in DevConfig := "m125osk",
      //liquibaseDriver in DevConfig := "org.postgresql.Driver"
      liquibaseDriver in DevConfig := "oracle.jdbc.OracleDriver",
      //liquibaseUrl in DevConfig := "jdbc:postgresql://orapg.simplesys.lan:5432/webenzo",
      liquibaseUrl in DevConfig := "jdbc:oracle:thin:@orapg.simplesys.lan:1521/test"//,

//      autoCompilerPlugins := true,
//      addCompilerPlugin("com.nativelibs4java" %% "hs" % "0.4-SNAPSHOT"),
//      scalacOptions += "-Xplugin-require:scalaxy-streams",
//      scalacOptions in Test += "-Xplugin-disable:scalaxy-streams"
    )
  }

  lazy val root = Project(id = "dm-processing", base = file(".")).enablePlugins(GitVersioning).aggregate(
    oraEmbeddedHelpers,
    doobieHelpers,
    cliTools,
    xmlPullHelper,
    tokenizer,
    classifier,
    dictionary,
    dbObjects,
    templates,
    processingCore,
    webUI,
    webUIClientJS,
    webUIClientJVM,
    currencyRates
  ).settings(
    libraryDependencies ++= Seq(
      CommonDeps.scalaTest.value % "test",
      CommonDeps.akkaTestKit.value % "test",
      CommonDeps.json4sJackson.value % "test",
      CommonDeps.scalajHttp.value % "test",
      CommonDeps.ssysScalaGen.value % "test",
      CommonDeps.ssysBoneCPWrapper.value % "test"
    )).settings(
      publishArtifact in (Compile, packageBin) := false,
      publishArtifact in (Compile, packageDoc) := false,
      publishArtifact in (Compile, packageSrc) := false
    )
    //settings(CommonSettings.defaultProjectSettings)
}