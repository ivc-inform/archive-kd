package com.simplesys.SmartClient.Forms.FormsItems.props.multiFileItem

import com.simplesys.SmartClient.Layout.props.VStackProps
import com.simplesys.option.{ScOption, ScNone}

import scala.scalajs.js._

class MultiFilePickerProps extends VStackProps {
    var maxUploadFields: ScOption[Int] = ScNone
    var minUploadFields: ScOption[Int] = ScNone
}
