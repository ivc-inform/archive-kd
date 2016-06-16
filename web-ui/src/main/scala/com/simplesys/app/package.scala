package com.simplesys

import com.simplesys.SmartClient.System.SCApply
import com.simplesys.js.components.CommonListGridEditorComponent
import com.simplesys.js.components.props.CommonListGridEditorComponentProps

//import com.simplesys.macros.PropsToDictionary
import com.simplesys.macros.PropsToDictionary

package object app {
    val loadSchemas = false

    object CommonListGridEditorComponent extends SCApply[CommonListGridEditorComponent, CommonListGridEditorComponentProps]
    //    object AbonentsOrg extends SCApply[AbonentsOrg, AbonentsOrgProps]
}
