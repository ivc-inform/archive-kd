package com.simplesys.listener

import java.sql.SQLException
import javax.servlet.annotation.WebListener

import com.simplesys.oracle.pool.OraclePoolDataSource
import com.simplesys.servlet.ServletContextEvent

object AppLifeCycleEvent {
    val oraclePoolAttributeName = "oraclePool"
}

@WebListener
class AppLifeCycleEvent extends CommonWebAppListener {

    import AppLifeCycleEvent._
    override val loadSchemas = com.simplesys.app.loadSchemas

    override def UserContextInitialized(sce: ServletContextEvent) {

        com.simplesys.messages.ActorConfig.initSingletonActors(system)

        //val oraclePool = new OraclePoolDataSource("db-connection-stack.docker.oraclcePoolDataSource")
        val oraclePool = new OraclePoolDataSource("db-connection-stack.prod.oraclcePoolDataSource")

        try {
            oraclePool.getConnection().close()
            logger trace s"$oraclePoolAttributeName checked"
            sce.ServletContext.Attribute(oraclePoolAttributeName, Some(oraclePool))
        }
        catch {
            case ex: SQLException => throw new RuntimeException(s"Not database conection ${oraclePool.settings.user}")
            case ex: Throwable => throw ex
        }

        logger.trace(s"DriverClass: ${oraclePool.settings.className}")

        super.UserContextInitialized(sce)
    }


    override def ContextDestroyed1(sce: ServletContextEvent) {
        
    }
}
