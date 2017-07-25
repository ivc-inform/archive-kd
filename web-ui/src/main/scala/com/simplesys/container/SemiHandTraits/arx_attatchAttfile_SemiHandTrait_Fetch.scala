// This file is generated automatically (at 19.07.2017 19:27:32), do not spend any changes here, because they will be lost. Generator: "GenBOContainer, stage: #765"

package ru.simplesys.defs.app.scala.container.arx

import com.simplesys.app.SessionContextSupport
import com.simplesys.isc.system.ServletActorDyn
import com.simplesys.isc.dataBinging.DSRequestDyn
import com.simplesys.common.Strings._
import com.simplesys.jdbc.control.clob._
import akka.actor.Actor
import ru.simplesys.defs.bo.arx._

 
trait arx_attatchAttfile_SemiHandTrait_Fetch extends SessionContextSupport with ServletActorDyn {
    
/////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!!!! DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////    
    val requestData = new DSRequestDyn(request)    
    
    logger debug s"Request for Fetch: ${newLine + requestData.toPrettyString}"    
    
    val dataSet = AttatchAttfileDS(ds)    
/////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!! END DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////    
    
     def receiveBase: Option[Actor.Receive] = None    
    
     def wrapperBlobGetter(blob: Blob): String = blob.asString
}