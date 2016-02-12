webSettings

port in container.Configuration := 8080

webInfIncludeJarPattern in Compile := Some( """.*com\.simplesys.*/*\.jar$|.*ru\.simplesys.*/*\.jar$|.*/classes/.*""")

container.deploy("/archive-kd" -> webUI)

addCommandAlias("debug-restart", "; packageWar ; container:restart")

val HostingDeploy = config("hostingDeploy") extend (Compile)

packageWar in HostingDeploy <<= packageWar in Compile

warPostProcess in HostingDeploy <<= (target) map {
    (target) => {
        (x) => {
            val webapp = target / "webapp"
        }
    }
}
