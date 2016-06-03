package com.simplesys.SmartClient.Forms.FormsItems.props

import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.Types.FormItemComponentType
import com.simplesys.option.{ScNone, ScOption}

class RichTextItemProps extends CanvasItemProps {
    var controlGroups: ScOption[IscArray[String]] = ScNone
    var moveFocusOnTab: ScOption[Boolean] = ScNone
    `type` = FormItemComponentType.RichTextItem
}
