package com.simplesys.SmartClient.Forms.FormsItems.props

import com.simplesys.SmartClient.Forms.FormsItems.TimerItem
import com.simplesys.System.Types.FormItemComponentType

class TimerItemProps extends TextItemProps {
    type classHandler <: TimerItem
    `type` = FormItemComponentType.TimerItem
}
