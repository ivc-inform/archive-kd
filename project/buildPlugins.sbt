import ru.simplesys.eakd.sbtbuild.{CommonSettings, PluginDeps}
import sbt._
import sbt.Project._


//lazy val devPlugin = uri("../../sbt-plugins/dev-plugin")
//lazy val sbtCoffeeScript = uri("../../sbt-plugins/sbt-coffeescript")
//lazy val sbtNativePackager = uri("../../sbt-native-packager")

lazy val root = Project(id = "buildPlugins", base = file(".")).dependsOn(/*RootProject(devPlugin)*/  /*RootProject(sbtCoffeeScript)*/ /* RootProject(sbtNativePackager)*/).
  settings(sbt.inThisBuild(CommonSettings.defaultSettings)).
  settings(
      classpathTypes += "maven-plugin",
      PluginDeps.devPlugin,
      PluginDeps.sbtCoffeeScript,
      PluginDeps.mergeJS,
      PluginDeps.xsbtWeb,
      PluginDeps.sbtNativePackager,
      PluginDeps.jrebelPlugin,
      PluginDeps.scalaJSPlugin
  )
