
enablePlugins(ScalaJSPlugin)

webSettings

container.deploy("/archive-kd" -> Common.webUI)

port in container.Configuration := 8083

webInfIncludeJarPattern in Compile := Some( """.*com\.simplesys.*/*\.jar$|.*ru\.simplesys.*/*\.jar$|.*/classes/.*""")

addCommandAlias("debug-restart", "; fastOptJS ; packageWar ; container:restart")

