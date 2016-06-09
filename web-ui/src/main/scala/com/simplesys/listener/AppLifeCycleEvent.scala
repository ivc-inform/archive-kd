package com.simplesys.listener

import java.sql.SQLException
import javax.servlet.annotation.WebListener

import com.simplesys.bonecp.BoneCPDataSource
import com.simplesys.servlet.ServletContextEvent

@WebListener
class AppLifeCycleEvent extends CommonWebAppListener {

    override val loadSchemas: Boolean = false

    override def UserContextInitialized(sce: ServletContextEvent) {

        com.simplesys.messages.ActorConfig.initSingletonActors(system)

        val ds: BoneCPDataSource = getString("dbPool.default") match {
            case x@"oracleMFMS" => cpStack OracleDataSource x
            case any => throw new RuntimeException(s"Bad: ${any}")
        }

        //        val dsProd: BoneCPDataSource = getString("dbPool.defaultProd") match {
        //            case x@"oracleMFMSProd" => cpStack OracleDataSource x
        //            case any => throw new RuntimeException(s"Bad: ${any}")
        //        }
        //
        //        val dsSave: BoneCPDataSource = getString("dbPool.defaultSave") match {
        //            case x@"oracleMFMSSave" => cpStack OracleDataSource x
        //            case any => throw new RuntimeException(s"Bad: ${any}")
        //        }
        //
        //        val dsConfig: BoneCPDataSource = getString("dbPool.defaultConfig") match {
        //            case x@"oracleMFMSConfig" => cpStack OracleDataSource x
        //            case any => throw new RuntimeException(s"Bad: ${any}")
        //        }

        sce.ServletContext.Attribute("ds", Some(ds))
        //        sce.ServletContext.Attribute("dsProd", Some(dsProd))
        //        sce.ServletContext.Attribute("dsSave", Some(dsSave))
        //        sce.ServletContext.Attribute("dsConfig", Some(dsConfig))

        try {
            //ds.Connection.close()
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
