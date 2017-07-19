package com.simplesys.js.components.cards.props

import com.simplesys.SmartClient.App.props.CommonListGridEditorComponentProps
import com.simplesys.js.components.cards.Attach
import com.simplesys.option.ScOption._
import com.simplesys.option.DoubleType._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.{DataSourcesJS, FormItemsJS, ListGridFiledsJS}

class AttachProps extends CommonListGridEditorComponentProps {
    type classHandler <: Attach

    simpleTable = false.opt
    //autoFetchData = false.opt

    dataSource = DataSourcesJS.arx_attatch_DS.opt
    identifier = "12DC1876-F489-3172-EE97-729FFB73B575".opt

    editingFields = FormItemsJS.arx_attatch_FRMITM.opt
    fields = ListGridFiledsJS.arx_attatch_FLDS.opt

    height = 250
}
