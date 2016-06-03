package com.simplesys.SmartClient.Grids.props.treeGrid

import com.simplesys.SmartClient.Grids.props.listGrid.ListGridFieldProps
import com.simplesys.option.{ScOption, ScNone}

import scala.scalajs.js._

class TreeGridFieldProps extends ListGridFieldProps {
    var treeField: ScOption[Boolean] = ScNone
}
