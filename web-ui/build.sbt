webSettings

webInfIncludeJarPattern in Compile := Some( """.*com\.simplesys.*/*\.jar$|.*ru\.simplesys.*/*\.jar$|.*/classes/.*""")

container.deploy("/archive-kd" -> webUI)

port in container.Configuration := 8083

addCommandAlias("debug-restart", "; packageWar ; container:restart; fastOptJS")

val HostingDeploy = config("hostingDeploy") extend (Compile)

packageWar in HostingDeploy <<= packageWar in Compile

warPostProcess in HostingDeploy <<= (target) map {
    (target) => {
        (x) => {
            val webapp = target / "webapp"
        }
    }
}
