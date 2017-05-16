import com.simplesys.json.{JsonList, JsonObject}
import com.simplesys.mergewebapp.MergeWebappPlugin
import com.typesafe.sbt.coffeescript.TranspileCoffeeScript
import org.scalajs.sbtplugin.ScalaJSPlugin
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import ru.simplesys.eakd.sbtbuild.{CommonDeps, CommonDepsScalaJS, CommonSettings, PluginDeps}
import ru.simplesys.plugins.sourcegen.DevPlugin
import sbt.Keys._
import sbt.{Build, Compile, Project, file, _}

object Common extends Build {
    lazy val server = Project(id = "server", base = file("server")).dependsOn(webUI).settings(
        libraryDependencies ++= Seq(
            CommonDeps.scalaTest.value % Test
        )
    ).settings(CommonSettings.defaultProjectSettings)

    lazy val dbObjects = Project(id = "db-objects", base = file("db-objects")).enablePlugins(DevPlugin).settings(
        libraryDependencies ++= Seq(
            CommonDeps.ssysCoreLibrary.value,
            CommonDeps.ssysJsonExtender.value,
            CommonDeps.ssysJDBCWrapper.value,
            CommonDeps.jodaTime.value,
            CommonDeps.jodaConvert.value,
            CommonDeps.scalaTest.value % Test
        )
    ).settings(DevPlugin.devPluginGeneratorSettings).settings({
        import ru.simplesys.plugins.sourcegen.DevPlugin._
        Seq(
            sourceSchemaDir in DevConfig := (resourceDirectory in Compile).value / "defs",
            startPackageName in DevConfig := "ru.simplesys.defs",
            contextPath in DevConfig := "acrchive-kd",
            maxArity := 254,
            sourceGenerators in Compile <+= (generateBoScalaCode in DevConfig)
        )
    }).settings(CommonSettings.defaultProjectSettings)

    lazy val webUI = Project(id = "web-ui", base = file("web-ui")).enablePlugins(
        DevPlugin, MergeWebappPlugin, TranspileCoffeeScript, ScalaJSPlugin
    ).dependsOn(
        dbObjects
    ).aggregate(dbObjects).settings(

        libraryDependencies ++= Seq(
            CommonDeps.servletAPI.value % Provided,
            CommonDeps.ssysCommonWebapp.value,
            CommonDeps.ssysIscComponents.value,
            CommonDeps.ssysXMLExtender.value,
            CommonDeps.ssysJsonExtender.value,
            CommonDeps.ssysIscMisc.value,
            //CommonDeps.play.value,

            CommonDeps.smartclient.value,

            CommonDeps.akkaActor.value,
            CommonDeps.akkaHttp.value,
            CommonDeps.akkaHttpCore.value,
            CommonDeps.akkaHttpXml.value,
            CommonDeps.akkaHttpSprayJson.value,

            CommonDeps.jettyWebapp.value % "container",
            CommonDeps.jettyAnnotations.value % "container",
            CommonDeps.jettyPlus.value % "container",

            CommonDeps.scalaTest.value % Test,
            //CommonDeps.play.value % Test,

            CommonDeps.scalaJSWrapper.value,
            CommonDeps.scalaTags.value,
            CommonDepsScalaJS.smartClientWrapper.value,
            CommonDepsScalaJS.macroJS.value,
            CommonDepsScalaJS.scalaTags.value

        )
    ).settings(DevPlugin.devPluginGeneratorSettings).settings({
        import com.simplesys.mergewebapp.MergeWebappPlugin._
        import com.typesafe.sbt.coffeescript.TranspileCoffeeScript.autoImport._
        import com.typesafe.sbt.web.Import.WebKeys._
        import com.typesafe.sbt.web.SbtWeb.autoImport._
        import ru.simplesys.plugins.sourcegen.DevPlugin._

        Seq(
            //scala.js
            crossTarget in fastOptJS := (sourceDirectory in Compile).value / "webapp" / "javascript" / "generated" / "generatedComponents",
            crossTarget in fullOptJS := (sourceDirectory in Compile).value / "webapp" / "javascript" / "generated" / "generatedComponents",
            crossTarget in packageJSDependencies := (sourceDirectory in Compile).value / "webapp" / "javascript" / "generated" / "generatedComponents",

            //coffeeScript
            CoffeeScriptKeys.sourceMap := false,
            CoffeeScriptKeys.bare := false,
            webTarget := (sourceDirectory in Compile).value / "webapp" / "javascript" / "generated" / "generatedComponents" / "coffeescript",
            sourceDirectory in Assets := (sourceDirectory in Compile).value / "webapp" / "coffeescript" / "developed" / "developedComponents",
            (managedResources in Compile) ++= CoffeeScriptKeys.csTranspile.value,

            //dev plugin
            sourceSchemaDir in DevConfig := (resourceDirectory in(dbObjects, Compile)).value / "defs",
            startPackageName in DevConfig := "ru.simplesys.defs",
            contextPath in DevConfig := "archive-kd",
            maxArity in DevConfig := 254,

            sourceGenerators in Compile <+= generateScalaCode in DevConfig,

            //merger
            mergeMapping in MergeWebappConfig := Seq(
                ("com.simplesys.core", "common-webapp") -> Seq(
                    Seq("webapp", "javascript", "generated", "generatedComponents", "coffeescript") -> Some(Seq("webapp", "managed", "javascript", "common-webapp", "generated", "generatedComponents", "coffeescript")),
                    Seq("webapp", "javascript", "developed") -> Some(Seq("webapp", "managed", "javascript", "common-webapp", "developed")),
                    Seq("webapp", "coffeescript", "developed") -> Some(Seq("webapp", "managed", "coffeescript", "common-webapp", "developed")),
                    Seq("webapp", "css") -> Some(Seq("webapp", "managed", "css", "common-webapp")),
                    Seq("webapp", "html") -> Some(Seq("webapp", "managed", "html", "common-webapp")),
                    Seq("webapp", "images") -> Some(Seq("webapp", "managed", "images", "common-webapp"))
                ),
                ("com.simplesys.core", "isc-components") -> Seq(
                    Seq("webapp", "javascript", "generated", "generatedComponents") -> Some(Seq("webapp", "managed", "javascript", "isc-components", "generated", "generatedComponents")),
                    Seq("webapp", "javascript", "generated", "generatedComponents", "coffeescript") -> Some(Seq("webapp", "managed", "javascript", "isc-components", "generated", "generatedComponents", "coffeescript")),
                    Seq("javascript", "com", "simplesys") -> Some(Seq("webapp", "managed", "javascript", "isc-components", "developed", "developedComponents")),
                    Seq("coffeescript") -> Some(Seq("webapp", "managed", "coffeescript", "isc-components", "developed", "developedComponents"))
                ),
                ("com.simplesys.core", "isc-misc") -> Seq(
                    Seq("javascript") -> Some(Seq("webapp", "managed", "javascript", "isc-misc"))
                ),
                ("com.simplesys", "smartclient-js") -> Seq(
                    Seq("isomorphic") -> Some(Seq("webapp", "isomorphic"))
                )
            ),
            currentProjectGenerationDirPath in MergeWebappConfig := (sourceDirectory in Compile).value / "webapp" / "javascript" / "generated" / "generatedComponents",
            currentProjectDevelopedDirPath in MergeWebappConfig := (sourceDirectory in Compile).value / "webapp" / "javascript" / "developed",
            currentProjectCoffeeDevelopedDirPath in MergeWebappConfig := (sourceDirectory in Compile).value / "webapp" / "coffeescript" / "developed",
            merge in MergeWebappConfig <<= (merge in MergeWebappConfig).dependsOn(TranspileCoffeeScript.autoImport.CoffeeScriptKeys.csTranspile in Assets),

            (resourceGenerators in Compile) += task[Seq[File]] {

                val aboutFile: File = (sourceDirectory in Compile).value / "webapp" / "javascript" / "generated" / "generatedComponents" / "MakeAboutData.js"

                val list = JsonList()

                import scala.reflect.ClassTag
                import scala.reflect.runtime.universe._
                import scala.reflect.runtime.{universe ⇒ ru}

                def makeVersionList[T: TypeTag : ClassTag](e: T): Unit = {

                    val classLoaderMirror = ru.runtimeMirror(this.getClass.getClassLoader)
                    val `type`: ru.Type = ru.typeOf[T]

                    val classSymbol = `type`.typeSymbol.asClass

                    val decls = `type`.declarations.sorted.filter(_.isMethod).filter(!_.name.toString.contains("<init>"))
                    val im = classLoaderMirror reflect e

                    decls.foreach {
                        item =>

                            val shippingTermSymb = `type`.declaration(ru.newTermName(item.name.toString)).asTerm
                            val shippingFieldMirror = im reflectField shippingTermSymb
                            val res = shippingFieldMirror.get.toString()

                            list += JsonObject("libName" -> item.name.toString, "libVersion" -> res)
                    }
                }

                list ++= Seq(
                    JsonObject("libName" -> "Исполнители :", "libVersion" -> "Юдин Андрей"),
                    JsonObject("libName" -> "Версия :", "libVersion" -> version.value)
                )

                makeVersionList(CommonDeps.versions)
                makeVersionList(PluginDeps.versions)

                IO.write(aboutFile, s"simpleSyS.aboutData = ${list.toPrettyString}")
                Seq()
            }
        )
    }).settings(CommonSettings.defaultProjectSettings)
}
