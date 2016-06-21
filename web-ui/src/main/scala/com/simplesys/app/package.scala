package com.simplesys

import com.simplesys.SmartClient.App.CommonListGridEditorComponent
import com.simplesys.SmartClient.App.props.CommonListGridEditorComponentProps
import com.simplesys.SmartClient.System.SCApply
import com.simplesys.js.components.refs.{Abonents, AbonentsOrg, AbonentsTypes}
import com.simplesys.js.components.refs.props.{AbonentsOrgProps, AbonentsProps, AbonentsTypesProps}

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
    val guid = "guid.png"

    object AbonentsOrg extends SCApply[AbonentsOrg, AbonentsOrgProps]
    object AbonentsTypes extends SCApply[AbonentsTypes, AbonentsTypesProps]
    object Abonents extends SCApply[Abonents, AbonentsProps]
    object AttachTypes extends SCApply[AttachTypes, AttachTypesProps]
}
