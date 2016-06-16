package com.simplesys.js.components.refs.props

import com.simplesys.SmartClient.Grids.props.ListGridEditorProps
import com.simplesys.SmartClient.System._
import com.simplesys.System.Types.SelectionStyle
import com.simplesys.System.Types.SelectionStyle._
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.option.ScOption._

import scala.scalajs.js._

class CommonListGridEditorComponentProps extends ListGridEditorProps {
    drawAheadRatio = simpleSyS.config.drawAheadRatio.getOrElse(2.0).opt
    dataPageSize = simpleSyS.config.dataPageSize.getOrElse(75).opt
    canEdit = true.opt
    filterOnKeypress = true.opt
    autoFetchData = false.opt
    selectionType = SelectionStyle.multiple.opt
}
