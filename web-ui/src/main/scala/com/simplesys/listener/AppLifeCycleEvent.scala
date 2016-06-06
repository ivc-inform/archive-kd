package com.simplesys.listener

import java.io.File
import java.sql.SQLException
import javax.servlet.annotation.WebListener

import com.simplesys.bonecp.BoneCPDataSource
import com.simplesys.isc.dataBinging.DSResponseDyn
import com.simplesys.isc.dataBinging.RPC.RPCResponseDyn
import com.simplesys.json.{JsonList, JsonObject}
import com.simplesys.play.Xml
import com.simplesys.servlet.ServletContextEvent
import com.simplesys.xml.factory.XMLLoader

import scala.io.Codec

@WebListener
class AppLifeCycleEvent extends CommonWebAppListener with XMLLoader {

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

        val path = s"${sce.ServletContext.RealPath(".").getOrElse("")}/${getString("app.isomorphicDir")}${getString("app.schemasDir")}"
        val schemasFiles = new File(path).listFiles.filter(_.getName.endsWith("ds.xml")).sortWith(_.getName < _.getName)

        val list = JsonList()

        logger debug "/////////////////////////////////////////////////////////////// Schema files: ///////////////////////////////////////////////////////////////////"
        schemasFiles.foreach {
            file =>
                val componentName = file.getName.replace(".ds.xml", "")
                val json = Xml.getJS(loadFile(file)(Codec.UTF8), componentName, false).trim

                if (json != "") {
                    list += JsonObject("component" -> componentName, "jsonStr" -> json)
                    logger debug s"Parsed schema: $componentName"
                }
        }
        logger debug "/////////////////////////////////////////////////////////////// End Schema files: ///////////////////////////////////////////////////////////////"

        val schemaList = new DSResponseDyn {
            Status = RPCResponseDyn.statusSuccess
            Data = list
        }

        sce.ServletContext.Attribute("schemaList", Some(schemaList))

        logger.trace(s"DriverClass: ${ds.getDriverClass}")

        super.UserContextInitialized(sce)
    }


    override def ContextDestroyed1(sce: ServletContextEvent) {
        cpStack.Close()
    }
}