// This file is generated automatically (at 06.06.2016 18:09:27), do not spend any changes here, because they will be lost. Generator: "GenBOContainer, stage: #765"

package ru.simplesys.defs.app.scala.container

import com.simplesys.app.SessionContextSupport
import com.simplesys.isc.system.ServletActorDyn
import com.simplesys.isc.dataBinging.DSRequestDyn
import com.simplesys.common.Strings._
import akka.actor.Actor
import ru.simplesys.defs.bo.eakd._

trait eakd_zapros_SemiHandTrait_Remove extends SessionContextSupport with ServletActorDyn {
    
/////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!!!! DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////    
    val requestData = new DSRequestDyn(request)    
    
    logger debug s"Request for Remove: ${newLine + requestData.toPrettyString}"    
    
    val dataSet = ZaprosDS(ds)    
/////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!! END DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////    
    
    def receiveBase: Option[Actor.Receive] = None
}