package com.simplesys.listener

import java.sql.SQLException
import javax.servlet.annotation.WebListener

import com.simplesys.bonecp.BoneCPDataSource
import com.simplesys.servlet.ServletContextEvent
import oracle.ucp.jdbc.PoolDataSourceFactory

@WebListener
class AppLifeCycleEvent extends CommonWebAppListener {

    override val loadSchemas = com.simplesys.app.loadSchemas

    override def UserContextInitialized(sce: ServletContextEvent) {

        com.simplesys.messages.ActorConfig.initSingletonActors(system)

        val ds: BoneCPDataSource = getString("dbPool.default") match {
            case x@"oracleEAKD" => cpStack OracleDataSource x
            case any => throw new RuntimeException(s"Bad: ${any}")
        }

        sce.ServletContext.Attribute("ds", Some(ds))

        try {
            ds.Connection.close()
            logger trace "ds checked"
            sce.ServletContext.Attribute("ds", Some(ds))
        }
        catch {
            case ex: SQLException => throw new RuntimeException(s"Not database conection ${ds.getUsername}")
            case ex: Throwable => throw ex
        }

        logger.trace(s"DriverClass: ${ds.getDriverClass}")

        super.UserContextInitialized(sce)
    }


    override def ContextDestroyed1(sce: ServletContextEvent) {
        cpStack.Close()
    }
}
