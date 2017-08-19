import ru.simplesys.eakd.sbtbuild.{CommonSettings, PluginDeps}
import sbt._
import sbt.Project._


//lazy val devPlugin = uri("../../sbt-plugins/dev-plugin")
lazy val transpileCoffeeScript = uri("../../sbt-plugins/transpile-coffeescript")
//lazy val sbtNativePackager = uri("../../sbt-native-packager")

lazy val root = Project(id = "buildPlugins", base = file(".")).dependsOn(/*RootProject(devPlugin)*/  RootProject(transpileCoffeeScript) /* RootProject(sbtNativePackager)*/).
  settings(sbt.inThisBuild(CommonSettings.defaultSettings)).
  settings(
      classpathTypes += "maven-plugin",
      PluginDeps.devPlugin,
      PluginDeps.transpileCoffeeScript,
      PluginDeps.mergeJS,
      PluginDeps.xsbtWeb,
      PluginDeps.sbtNativePackager,
      PluginDeps.jrebelPlugin,
      PluginDeps.scalaJSPlugin
  )
