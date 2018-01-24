// This file is generated automatically (at 24.01.2018 12:49:41), do not spend any changes here, because they will be lost. Generator: "GenBOContainer, stage: #765"

package ru.simplesys.defs.app.scala.container.admin

import com.simplesys.app.SessionContextSupport
import io.circe.generic.auto._
import io.circe.syntax._
import com.simplesys.circe.Circe._
import com.simplesys.servlet.isc.ServletActor
import com.simplesys.common.Strings._
import com.simplesys.jdbc.control.clob._
import com.simplesys.isc.dataBinging.DSRequest
import akka.actor.Actor
import ru.simplesys.defs.bo.admin._

 
trait admin_UserGroup_SemiHandTrait_Fetch extends SessionContextSupport with ServletActor {
    
/////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!!!! DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////    
    val requestData: DSRequest = request.JSONData.as[DSRequest].getOrElse(throw new RuntimeException ("Dont parsed Request JSON"))    
    
    logger debug s"Request for Fetch: ${newLine + requestData.asJson.toPrettyString}"    
    
    val dataSet = UserGroupDS(oraclePool)    
/////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!! END DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////    
    
     def receiveBase: Option[Actor.Receive] = None    
    
     def wrapperBlobGetter(blob: Blob): String = inputStream2Sting(blob)
}
