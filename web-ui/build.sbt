webSettings

port in container.Configuration := 8080

webInfIncludeJarPattern in Compile := Some( """.*com\.simplesys.*/*\.jar$|.*ru\.simplesys.*/*\.jar$|.*/classes/.*""")

container.deploy("/dm-processing" -> webUI)

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
            IO.delete(webapp / "WEB-INF" / "lib" / "opencorpora-data-1.0.jar")
            IO.delete(webapp / "WEB-INF" / "lib" / "fias-data-1.0.jar")
        }
    }
}
