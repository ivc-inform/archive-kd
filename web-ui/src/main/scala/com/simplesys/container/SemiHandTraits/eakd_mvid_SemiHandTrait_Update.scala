// This file is generated automatically (at 16.06.2016 09:34:32), do not spend any changes here, because they will be lost. Generator: "GenBOContainer, stage: #765"

package ru.simplesys.defs.app.scala.container

import com.simplesys.app.SessionContextSupport
import com.simplesys.isc.system.ServletActorDyn
import com.simplesys.isc.dataBinging.DSRequestDyn
import com.simplesys.common.Strings._
import akka.actor.Actor
import ru.simplesys.defs.bo.eakd._

 
trait eakd_mvid_SemiHandTrait_Update extends SessionContextSupport with ServletActorDyn {
    
/////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!!!! DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////    
    val requestData = new DSRequestDyn(request)    
    
    logger debug s"Request for Update: ${newLine + requestData.toPrettyString}"    
    
    val dataSet = MvidDS(ds)    
/////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!! END DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////    
    
     def receiveBase: Option[Actor.Receive] = None
}