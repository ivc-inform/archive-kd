import com.simplesys.jrebel.JRebelPlugin
import com.simplesys.jrebel.JRebelPlugin._
import com.simplesys.json.{JsonList, JsonObject}
import com.typesafe.sbt.packager.Keys.executableScriptName
import com.typesafe.sbt.packager.docker.{Cmd, ExecCmd}
import ru.simplesys.eakd.sbtbuild.{CommonDeps, CommonDepsScalaJS, CommonSettings, PluginDeps}
import ru.simplesys.plugins.sourcegen.DevPlugin._
import com.typesafe.sbt.packager.docker.DockerPlugin._
import sbt.Keys.version

name := CommonSettings.settingValues.name

lazy val root = (project in file(".")).
  //enablePlugins(GitVersioning).
  aggregate(dbObjects, webUI, common, testModule).
  settings(
      inThisBuild(Seq(
          //git.baseVersion := CommonSettings.settingValues.baseVersion,
          scalaVersion := CommonSettings.settingValues.scalaVersion,
          version := CommonSettings.settingValues.version,
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
      DevPlugin, MergeWebappPlugin, TranspileCoffeeScript, ScalaJSPlugin, JettyPlugin, WarPlugin, WebappPlugin, JRebelPlugin, DockerPlugin
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
).settings({
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

        defaultLinuxInstallLocation in Docker := "",
        dockerBaseImage := "ivcinform/jetty:9.4.6.v20170531",
        daemonUser in Docker := "",
        daemonGroup in Docker := "",
        dockerDocfileCommands := Seq(),
        dockerEntrypoint := Seq(),
        dockerCmd := Seq(),
        dockerExposedPorts in Docker := Seq(8080),

        version := version.value,
        packageName in Docker := CommonSettings.settingValues.name,
        dockerUsername in Docker := None,
        dockerRepository in Docker := Some("hub.docker.com"),
        dockerUpdateLatest in Docker := true,
        dockerAlias in Docker := DockerAlias(dockerRepository.value, (dockerUsername in Docker).value, CommonSettings.settingValues.name, Some(CommonSettings.settingValues.version)),
        dockerDocfileCommands := Seq(
            /*RUN("groupadd", "-r", "jetty"),
            RUN("useradd", "-r", "-g", "jetty", "jetty"),

            ENV("JETTY_HOME", "/usr/local/jetty"),
            ENV("PATH", "$PATH:$JETTY_HOME"),
            RUN$("mkdir -p $JETTY_HOME"),

            WORKDIR("$JETTY_HOME"),

            ENV("JETTY_VERSION", "9.4.6.v20170531"),
            ENV("JETTY_TGZ_URL", "https://repo1.maven.org/maven2/org/eclipse/jetty/jetty-home/$JETTY_VERSION/jetty-home-$JETTY_VERSION.tar.gz"),

            RUN("set", "-xe"),
            RUN("sed", "-i", "-e", "'s/us.archive.ubuntu.com/archive.ubuntu.com/g' /etc/apt/sources.list"),
            RUN("apt-get", "update"),
            RUN("apt-get", "upgrade", "-y", "-o", "Dpkg::Options::=\"--force-confold\""),
            RUN("DEBIAN_FRONTEND=noninteractive", "apt-get", "install", "-y", "curl", "mc", "nano"),
            RUN("curl -SL \"$JETTY_TGZ_URL\" -o jetty.tar.gz"),
            RUN("curl -SL \"$JETTY_TGZ_URL.asc\" -o jetty.tar.gz.asc"),
            RUN("export GNUPGHOME=\"$(mktemp -d)\""),
            RUN("rm -rf \"$GNUPGHOME\""),
            RUN("tar -xvf jetty.tar.gz --strip-components=1"),
            RUN("sed -i '/jetty-logging/d' etc/jetty.conf"),
            RUN("rm jetty.tar.gz*"),
            RUN("rm -rf /tmp/hsperfdata_root"),

            ENV("JETTY_BASE", "/var/lib/jetty"),
            RUN$("mkdir -p $JETTY_BASE"),
            WORKDIR("$JETTY_BASE"),
            RUN("set -xe"),
            RUN("java -jar \"$JETTY_HOME/start.jar\" --create-startd --add-to-start=\"server,http,deploy,jsp,jstl,ext,resources,websocket,setuid\""),
            RUN("chown -R jetty:jetty \"$JETTY_BASE\""),
            RUN("rm -rf /tmp/hsperfdata_root"),

            ENV("TMPDIR", "/tmp/jetty"),
            RUN("set -xe"),
            RUN$("mkdir -p $TMPDIR"),
            RUN$("chown -R jetty:jetty $TMPDIR"),

            COPY("docker-entrypoint.sh /"),*/
            COPY(s"web-ui/${target.value.getName}/webapp", s"${"$JETTY_BASE/webapps/"}${CommonSettings.settingValues.name}") //,
            /*EXPOSE(8080),
            ENTRYPOINT("/docker-entrypoint.sh"),
            CMD("java", "-jar", "/usr/local/jetty/start.jar")*/
        ),
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





