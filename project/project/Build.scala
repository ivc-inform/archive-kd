import com.typesafe.sbt.{GitVersioning, SbtGit}
import com.typesafe.sbt.SbtGit.git
import ru.simplesys.eakd.sbtbuild.{PluginDeps, CommonSettings}
import sbt.Keys._
import sbt._


object PluginsBuild extends Build
{
  override def settings: Seq[Def.Setting[_]] = super.settings ++ CommonSettings.defaultSettings ++ Seq(
    git.baseVersion := CommonSettings.settingValues.baseVersion
  )

  //lazy val devPlugin = uri("../../build-plugins/dev-plugin")

  lazy val root = Project(id = "buildPlugins", base = file(".")).enablePlugins(GitVersioning)./*dependsOn(devPlugin).*/settings(
    PluginDeps.devPlugin,
    PluginDeps.sourceGenJS,
    PluginDeps.transpileCoffeeScript,
    PluginDeps.mergeJS,
    PluginDeps.sbtAspectJ,
    PluginDeps.xsbtWeb,
    PluginDeps.sbtPack,
    PluginDeps.scalaJSPlugin
  )
}
