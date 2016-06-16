package com.simplesys.js

import com.simplesys.SmartClient.System.SCApply
import com.simplesys.js.components.refs.AbonentsOrg
import com.simplesys.js.components.refs.props.{AbonentsOrgProps, CommonListGridEditorComponentProps}

//import com.simplesys.macros.PropsToDictionary
import com.simplesys.macros.PropsToDictionary

package object components {
    object CommonListGridEditorComponent extends SCApply[CommonListGridEditorComponent, CommonListGridEditorComponentProps]
    object AbonentsOrg extends SCApply[AbonentsOrg, AbonentsOrgProps]
}
