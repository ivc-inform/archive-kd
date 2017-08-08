import com.simplesys.jrebel.JRebelPlugin
import com.simplesys.jrebel.JRebelPlugin._
import com.simplesys.json.{JsonList, JsonObject}
import com.typesafe.sbt.SbtGit.git
import ru.simplesys.eakd.sbtbuild.{CommonDeps, CommonDepsScalaJS, CommonSettings, PluginDeps}
import ru.simplesys.plugins.sourcegen.DevPlugin._

name := "acrchive-kd"

version := "1.0.0.0"

lazy val root = (project in file(".")).
  //enablePlugins(GitVersioning).
  aggregate(dbObjects, webUI, common, testModule).
  settings(
      inThisBuild(Seq(
          //git.baseVersion := CommonSettings.settingValues.baseVersion,
          scalaVersion := CommonSettings.settingValues.scalaVersion,

          liquibaseUsername in DevConfig := "eakd",
          liquibasePassword in DevConfig := "eakd",
          liquibaseDriver in DevConfig := "oracle.jdbc.OracleDriver",
          liquibaseUrl in DevConfig := "jdbc:oracle:thin:@orapg.simplesys.lan:1521/test"

      )
        ++ CommonSettings.defaultSettings),
      publishArtifact in(Compile, packageBin) := false,
      publishArtifact in(Compile, packageDoc) := false,
      publishArtifact in(Compile, packageSrc) := false
  )

lazy val common = Project(id = "common", base = file("common")).settings(
    libraryDependencies ++= Seq(
        CommonDeps.commonsIO,
        CommonDeps.configWrapper,
        CommonDeps.ssysCommon,
        CommonDeps.scalaTest % Test
    )
).settings(CommonSettings.defaultProjectSettings)

lazy val testModule = Project(id = "test", base = file("test")).
  dependsOn(dbObjects).
  settings(
      libraryDependencies ++= Seq(
          CommonDeps.ssysJDBCWrapper,
          CommonDeps.slick,
          CommonDeps.scalaTest % Test
      )
  ).settings(CommonSettings.defaultProjectSettings)

lazy val dbObjects = Project(id = "db-objects", base = file("db-objects")).
  dependsOn(common).
  enablePlugins(DevPlugin).
  settings(
      libraryDependencies ++= Seq(
          CommonDeps.ssysCoreLibrary,
          CommonDeps.ssysJsonExtender,
          CommonDeps.ssysJDBCWrapper,
          CommonDeps.poolsDataSources,
          CommonDeps.jdbcOracle12,
          CommonDeps.jdbcOracle12UCP,
          CommonDeps.jdbcOracleN18_12,
          CommonDeps.scalaTest % Test
      )
  ).settings(DevPlugin.devPluginGeneratorSettings).
  settings({
      import ru.simplesys.plugins.sourcegen.DevPlugin._
      Seq(
          sourceSchemaDir in DevConfig := (resourceDirectory in Compile).value / "defs",
          startPackageName in DevConfig := "ru.simplesys.defs",
          contextPath in DevConfig := "acrchive-kd",
          maxArity := 254,
          quoted := true,
          sourceGenerators in Compile += (generateBoScalaCode in DevConfig)
      )
  }).settings(CommonSettings.defaultProjectSettings)

lazy val webUI = Project(id = "web-ui", base = file("web-ui")).
  enablePlugins(
      DevPlugin, MergeWebappPlugin, TranspileCoffeeScript, ScalaJSPlugin, JettyPlugin, WarPlugin, WebappPlugin, JRebelPlugin, sbtdocker.DockerPlugin, JavaAppPackaging
  ).dependsOn(
    dbObjects
).aggregate(dbObjects).settings(

    addCommandAlias("debug-restart", "; jetty:stop ; fastOptJS ; package ; jetty:start"),
    addCommandAlias("reset", "; clean ; compile ; fastOptJS "),
    addCommandAlias("full-reset", "; clean ; package ; fastOptJS "),

    JRebelPlugin.jrebelSettings,
    jrebel.webLinks += (sourceDirectory in Compile).value / "webapp",
    jrebel.enabled := true,

    javaOptions in Jetty ++= Seq(
        "-javaagent:/home/uandrew/jrebel/legacy/jrebel.jar",
        "-noverify",
        "-XX:+UseConcMarkSweepGC",
        "-XX:+CMSClassUnloadingEnabled"
    ),

    scalacOptions += "-P:scalajs:sjsDefinedByDefault",
    scalacOptions ++= {
        if (scalaJSVersion.startsWith("0.6.")) Seq("-P:scalajs:sjsDefinedByDefault")
        else Nil
    },

    libraryDependencies ++= Seq(
        CommonDeps.servletAPI % Provided,
        CommonDeps.ssysCommon,
        CommonDeps.ssysCommonWebapp,
        CommonDeps.ssysIscComponents,
        CommonDeps.ssysXMLExtender,
        CommonDeps.ssysJsonExtender,
        CommonDeps.ssysIscMisc,

        CommonDeps.smartclient,

        CommonDeps.akkaActor,
        CommonDeps.akkaHttp,
        CommonDeps.akkaHttpCore,
        CommonDeps.akkaHttpXml,
        CommonDeps.akkaHttpSprayJson,
        CommonDeps.commonsFileupload,
        CommonDeps.commonsIO,

        CommonDeps.scalaTest % Test,

        CommonDeps.scalaJSWrapper,
        CommonDeps.scalaTags,
        CommonDeps.scalaURI,
        CommonDepsScalaJS.smartClientWrapper.value,
        CommonDepsScalaJS.macroJS.value,
        CommonDepsScalaJS.scalaTags.value,
        CommonDepsScalaJS.jQuery.value,
        CommonDepsScalaJS.scalaDom.value

    )
).settings(DevPlugin.devPluginGeneratorSettings).
  settings({
      import com.simplesys.mergewebapp.MergeWebappPlugin._
      import com.typesafe.sbt.coffeescript.TranspileCoffeeScript.autoImport._
      import com.typesafe.sbt.web.Import.WebKeys._
      import com.typesafe.sbt.web.SbtWeb.autoImport._
      import ru.simplesys.plugins.sourcegen.DevPlugin._

      Seq(
          //scala.js
          crossTarget in fastOptJS := (sourceDirectory in Compile).value / "webapp" / "javascript" / "generated" / "generatedComponentsJS",
          crossTarget in fullOptJS := (sourceDirectory in Compile).value / "webapp" / "javascript" / "generated" / "generatedComponentsJS",
          crossTarget in packageJSDependencies := (sourceDirectory in Compile).value / "webapp" / "javascript" / "generated" / "generatedComponentsJS",

          //coffeeScript
          CoffeeScriptKeys.sourceMap := false,
          CoffeeScriptKeys.bare := false,
          webTarget := (sourceDirectory in Compile).value / "webapp" / "javascript" / "generated" / "generatedComponents" / "coffeescript",
          sourceDirectory in Assets := (sourceDirectory in Compile).value / "webapp" / "coffeescript" / "developed" / "developedComponents",
          (managedResources in Compile) ++= CoffeeScriptKeys.csTranspile.value,

          //dev plugin
          sourceSchemaDir in DevConfig := (resourceDirectory in(dbObjects, Compile)).value / "defs",
          startPackageName in DevConfig := "ru.simplesys.defs",
          contextPath in DevConfig := "acrchive-kd",
          maxArity in DevConfig := 254,
          quoted in DevConfig := true,
          sourceGenerators in Compile += (generateScalaCode in DevConfig),

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
          merge in MergeWebappConfig := (merge in MergeWebappConfig).dependsOn(TranspileCoffeeScript.autoImport.CoffeeScriptKeys.csTranspile in Assets).value,

          containerPort := 8080,
          containerArgs := Seq("--path", "/acrchive-kd"),
          containerLibs in Jetty := Seq(
              CommonDeps.jettyRuner intransitive()
          ),
          artifactName := { (v: ScalaVersion, m: ModuleID, a: Artifact) =>
              a.name + "." + a.extension
          },
          webappWebInfClasses := true,

          dockerfile in docker := {
              val appDir = stage.value
              val targetDir = name.value

              new Dockerfile {
                  from("uandrew1965/java-sdk:1.8.0.144-b01")

                  // add our user and group first to make sure their IDs get assigned consistently, regardless of whatever dependencies get added
                  runShell(
                      "groupadd -r jetty",
                      "useradd -r -g jetty jetty"
                  )


                  env("JETTY_HOME", "/usr/local/jetty")
                  env("PATH", "$JETTY_HOME/bin:$PATH")
                  runShell("mkdir -p \"$JETTY_HOME\"")
                  workDir("$JETTY_HOME")

                  env("JETTY_VERSION", "9.4.6.v2017053")

                  env("JETTY_TGZ_URL", "https://repo1.maven.org/maven2/org/eclipse/jetty/jetty-home/$JETTY_VERSION/jetty-home-$JETTY_VERSION.tar.gz")

                  runShell(
                      "set -xe",
                      "sed -i -e 's/us.archive.ubuntu.com/archive.ubuntu.com/g' /etc/apt/sources.list",
                      "apt-get update",
                      "apt-get install -y curl mc nano",
                      "curl -SL \"$JETTY_TGZ_URL\" -o jetty.tar.gz",
                      "curl -SL \"$JETTY_TGZ_URL.asc\" -o jetty.tar.gz.asc",
                      "export GNUPGHOME=\"$(mktemp -d)\"",
                      "rm -rf \"$GNUPGHOME\"",
                      "tar -xvf jetty.tar.gz --strip-components=1",
                      "sed -i '/jetty-logging/d' etc/jetty.conf",
                      "rm jetty.tar.gz*",
                      "rm -rf /tmp/hsperfdata_root"
                  )

                  env("JETTY_BASE", "/var/lib/jetty")
                  runShell("mkdir -p \"$JETTY_BASE\"")
                  workDir("$JETTY_BASE")

                  runShell(
                      "set -xe",
                      "java -jar \"$JETTY_HOME/start.jar\" --create-startd --add-to-start=\"server,http,deploy,jsp,jstl,ext,resources,websocket,setuid\"",
                      "chown -R jetty:jetty \"$JETTY_BASE\"",
                      "rm -rf /tmp/hsperfdata_root"
                  )

                  env("TMPDIR", "/tmp/jetty")
                  runShell(
                      "set -xe",
                      "mkdir -p \"$TMPDIR\"",
                      "chown -R jetty:jetty \"$TMPDIR\""
                  )

                  copyRaw("docker-entrypoint.sh", "/")
                  expose(8080)

                  entryPoint("/docker-entrypoint.sh")
                  copyRaw("docker-entrypoint.sh", "/")
                  copyRaw(s"${targetDir}/webapp/", "123")
                  cmd(
                      "java",
                      "-jar",
                      "/usr/local/jetty/start.jar"
                  )
              }
          },

          buildOptions in docker := BuildOptions(cache = false),

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
                  JsonObject("libName" -> "Разработка :", "libVersion" -> "АО ИВЦ \"Информ\" (info@ivc-inform.ru)"),
                  JsonObject("libName" -> "Версия :", "libVersion" -> version.value)
              )

              makeVersionList(CommonDeps.versions)
              makeVersionList(PluginDeps.versions)

              IO.write(aboutFile, s"simpleSyS.aboutData = ${list.toPrettyString}")
              Seq()
          }

      )
  },
      skip in packageJSDependencies := false,
      jsDependencies += "org.webjars" % "jquery" % "3.2.0" / "3.2.0/jquery.js"
  ).settings(CommonSettings.defaultProjectSettings)





