import ru.simplesys.eakd.sbtbuild.{CommonSettings, PluginDeps}
import sbt._

//lazy val devPlugin = uri("../../sbt-plugins/dev-plugin")
//lazy val transpileCoffeeScript = uri("../../sbt-plugins/transpile-coffeescript")
//lazy val sbtNativePackager = uri("../../sbt-native-packager")

lazy val root = Project(id = "buildPlugins", base = file(".")).dependsOn(/*devPlugin*/ /*RootProject(transpileCoffeeScript)*//* sbtNativePackager*/).
  settings(inThisBuild(CommonSettings.defaultSettings)).
  settings(
      PluginDeps.devPlugin,
      //PluginDeps.transpileCoffeeScript,
      PluginDeps.mergeJS,
      PluginDeps.xsbtWeb,
      PluginDeps.sbtNativePackager,
      PluginDeps.jrebelPlugin,
      PluginDeps.scalaJSPlugin
  )
