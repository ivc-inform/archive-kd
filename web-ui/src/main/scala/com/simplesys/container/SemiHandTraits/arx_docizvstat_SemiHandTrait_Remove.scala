// This file is generated automatically (at 24.01.2018 12:13:07), do not spend any changes here, because they will be lost. Generator: "GenBOContainer, stage: #765"

package ru.simplesys.defs.app.scala.container.arx

import com.simplesys.app.SessionContextSupport
import io.circe.generic.auto._
import io.circe.syntax._
import com.simplesys.circe.Circe._
import com.simplesys.servlet.isc.ServletActor
import com.simplesys.common.Strings._
import com.simplesys.jdbc.control.clob._
import com.simplesys.isc.dataBinging.DSRequest
import akka.actor.Actor
import ru.simplesys.defs.bo.arx._

 
trait arx_docizvstat_SemiHandTrait_Remove extends SessionContextSupport with ServletActor {
    
/////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!!!! DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////    
    val requestData = new DSRequest(request.JSONData)    
    
    logger debug s"Request for Remove: ${newLine + requestData.asJson.toPrettyString}"    
    
    val dataSet = DocizvstatDS(oraclePool)    
/////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!! END DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////    
    
     def receiveBase: Option[Actor.Receive] = None    
    
     def wrapperBlobGetter(blob: Blob): String = blob.asString
}