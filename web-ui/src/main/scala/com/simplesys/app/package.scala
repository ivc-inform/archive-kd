package com.simplesys

import com.simplesys.SmartClient.System.SCApply
import com.simplesys.js.components.refs._
import com.simplesys.js.components.refs.props._
import ru.simplesys.defs.bo.eakd.Docitem

//Должны быть
//import com.simplesys.SmartClient.System._
//import com.simplesys.macros.PropsToDictionary
//

import com.simplesys.SmartClient.System._
import com.simplesys.macros.PropsToDictionary

package object app {
    val loadSchemas = false
    val organization = "organization.png"
    val abonents = "abonents.png"
    val admin = "admin.png"
    val attach = "attach.png"
    val guid = "guid.png"
    val status = "status.png"
    val doctypes = "doctypes.png"
    val format = "format.png"
    val doccats = "doccats.png"
    val docitem = "docitem.png"

    object AbonentsOrg extends SCApply[AbonentsOrg, AbonentsOrgProps]
    object AbonentsTypes extends SCApply[AbonentsTypes, AbonentsTypesProps]
    object Abonents extends SCApply[Abonents, AbonentsProps]
    object AttachTypes extends SCApply[AttachTypes, AttachTypesProps]
    object AuStat extends SCApply[AuStat, AuStatProps]
    object DocTypes extends SCApply[DocTypes, DocTypesProps]
    object DocFormats extends SCApply[DocFormats, DocFormatsProps]
    object DocCats extends SCApply[DocCats, DocCatsProps]
    object DocItem extends SCApply[DocItem, DocItemProps]
}
