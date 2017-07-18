import com.typesafe.sbt.GitVersioning
import com.typesafe.sbt.SbtGit.git
import ru.simplesys.eakd.sbtbuild.{CommonSettings, PluginDeps}
import sbt._


//lazy val scenarioPlugin = uri("../../sbt-plugins/scenario-plugin")
//lazy val devPlugin = uri("../../sbt-plugins/dev-plugin")
//lazy val scalaFmtPlugin = uri("../../sbt-plugins/scala-fmt")
//lazy val transpileCoffeeScript = uri("../../sbt-plugins/transpile-coffeescript")

lazy val root = Project(id = "buildPlugins", base = file(".")).enablePlugins(GitVersioning).dependsOn(/*scenarioPlugin*//*, scalaFmtPlugin,*/ /*devPlugin*/  /*transpileCoffeeScript*/).
  settings(inThisBuild(CommonSettings.defaultSettings ++ Seq(
      git.baseVersion := CommonSettings.settingValues.baseVersion
  ))).
  settings(
      PluginDeps.devPlugin,
      PluginDeps.transpileCoffeeScript,
      PluginDeps.mergeJS,
      PluginDeps.xsbtWeb,
      PluginDeps.sbtNativePackager,
      PluginDeps.jrebelPlugin,
      PluginDeps.scalaJSPlugin
  )
