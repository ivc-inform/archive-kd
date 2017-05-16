import com.typesafe.sbt.GitVersioning
import com.typesafe.sbt.SbtGit.git
import ru.simplesys.eakd.sbtbuild.{CommonSettings, PluginDeps}
import sbt._


//lazy val scenarioPlugin = uri("../../build-plugins/scenario-plugin")
//lazy val devPlugin = uri("../../build-plugins/dev-plugin")
//lazy val scalaFmtPlugin = uri("../../build-plugins/scala-fmt")

lazy val root = Project(id = "buildPlugins", base = file(".")).enablePlugins(GitVersioning).dependsOn(/*scenarioPlugin*//*, scalaFmtPlugin*//*devPlugin*/).
  settings(inThisBuild(CommonSettings.defaultSettings ++ Seq(
      git.baseVersion := CommonSettings.settingValues.baseVersion
  ))).
  settings(
      PluginDeps.devPlugin,
      PluginDeps.sourceGenJS,
      PluginDeps.transpileCoffeeScript,
      PluginDeps.mergeJS,
      PluginDeps.sbtAspectJ,
      PluginDeps.xsbtWeb,
      PluginDeps.sbtPack,
      PluginDeps.scalaJSPlugin
  )
