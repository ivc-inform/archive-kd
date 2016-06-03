// This file is generated automatically (at 03.06.2016 14:32:36), do not spend any changes here, because they will be lost. Generator: "GenBOContainer, stage: #765"

package ru.simplesys.defs.app.scala.container

import com.simplesys.app.SessionContextSupport
import com.simplesys.isc.system.ServletActorDyn
import com.simplesys.isc.dataBinging.DSRequestDyn
import com.simplesys.common.Strings._
import ru.simplesys.defs.bo.eakd._

trait eakd_zaprositems_SemiHandTrait_Remove extends SessionContextSupport with ServletActorDyn {
    
/////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!!!! DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////    
    val requestData = new DSRequestDyn(request)    
    
    logger debug s"Request for Remove: ${newLine + requestData.toPrettyString}"    
    
    val dataSet = ZaprositemsDS(ds)    
/////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!! END DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////    
    

}