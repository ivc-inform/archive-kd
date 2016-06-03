import com.typesafe.sbt.SbtGit.git
import com.typesafe.sbt.{GitVersioning, SbtGit}
import ru.simplesys.eakd.sbtbuild._
import ru.simplesys.eakd.sbtbuild.WebUI
import sbt.Keys._
import sbt._

object ArchiveKdBuild extends Build
with DbObjects
with WebUI
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

      liquibaseUsername in DevConfig := "eakd",
      liquibasePassword in DevConfig := "eakd",
      liquibaseDriver in DevConfig := "oracle.jdbc.OracleDriver",
      liquibaseUrl in DevConfig := "jdbc:oracle:thin:@orapg.simplesys.lan:1521/test"
    )
  }

  lazy val root = Project(id = "acrchive-kd", base = file(".")).enablePlugins(GitVersioning).aggregate(
    dbObjects,
    webUI
  ).settings(
    libraryDependencies ++= Seq(
      CommonDeps.scalaTest.value % "test",
      CommonDeps.akkaTestKit.value % "test",
      CommonDeps.ssysScalaGen.value % "test",
      CommonDeps.ssysBoneCPWrapper.value % "test"
    )).settings(
      publishArtifact in (Compile, packageBin) := false,
      publishArtifact in (Compile, packageDoc) := false,
      publishArtifact in (Compile, packageSrc) := false
    )
    //settings(CommonSettings.defaultProjectSettings)
}
