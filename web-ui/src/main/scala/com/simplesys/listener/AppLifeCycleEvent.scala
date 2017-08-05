package com.simplesys.listener

import java.sql.SQLException
import javax.servlet.annotation.WebListener

import com.simplesys.oracle.pool.OraclePoolDataSource
import com.simplesys.servlet.ServletContextEvent

@WebListener
class AppLifeCycleEvent extends CommonWebAppListener {

    override val loadSchemas = com.simplesys.app.loadSchemas

    override def UserContextInitialized(sce: ServletContextEvent) {

        com.simplesys.messages.ActorConfig.initSingletonActors(system)

        //val ds = new OraclePoolDataSource("db-connection-stack.docker.oraclcePoolDataSource")
        val ds = new OraclePoolDataSource("db-connection-stack.prod.oraclcePoolDataSource")

        sce.ServletContext.Attribute("ds", Some(ds))

        try {
            ds.getConnection().close()
            logger trace "ds checked"
            sce.ServletContext.Attribute("ds", Some(ds))
        }
        catch {
            case ex: SQLException => throw new RuntimeException(s"Not database conection ${ds.settings.user}")
            case ex: Throwable => throw ex
        }

        logger.trace(s"DriverClass: ${ds.settings.className}")

        super.UserContextInitialized(sce)
    }


    override def ContextDestroyed1(sce: ServletContextEvent) {
        
    }
}
