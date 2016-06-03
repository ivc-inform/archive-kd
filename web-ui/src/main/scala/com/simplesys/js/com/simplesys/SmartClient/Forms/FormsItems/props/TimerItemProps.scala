package com.simplesys.js.com.simplesys.SmartClient.Forms.FormsItems.props

import com.simplesys.SmartClient.Forms.FormsItems.props.{TextItemProps, TimeItemProps}
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.Types.{FormItemComponentType, OperatorId}
import com.simplesys.function._
import com.simplesys.js.com.simplesys.SmartClient.Forms.FormsItems.TimerItem
import com.simplesys.option.ScOption
import com.simplesys.option.ScOption._

import scala.scalajs.js._

class TimerItemProps extends TextItemProps {
    type classHandler <: TimerItem
    `type` = FormItemComponentType.TimerItem
}
