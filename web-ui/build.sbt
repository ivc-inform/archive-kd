webSettings

port in container.Configuration := 8080

webInfIncludeJarPattern in Compile := Some( """.*com\.simplesys.*/*\.jar$|.*ru\.simplesys.*/*\.jar$|.*/classes/.*""")

container.deploy("/archive-kd" -> webUI)

//configurationXml in container.Configuration :=
//      <session-config>
//          <session-timeout>1</session-timeout>
//      </session-config>

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
