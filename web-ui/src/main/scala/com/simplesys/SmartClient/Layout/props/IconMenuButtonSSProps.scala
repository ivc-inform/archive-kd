package com.simplesys.SmartClient.Layout.props

import com.simplesys.SmartClient.Layout.IconMenuButtonSS
import com.simplesys.System.Types.IconOrientation
import com.simplesys.function._
import com.simplesys.option.ScOption._
import com.simplesys.option.{ScNone, ScOption}

class IconMenuButtonSSProps extends IconMenuButtonProps {
    type classHandler <: IconMenuButtonSS
    click = {
        (thiz: classHandler) =>
            thiz.showMenu()
            false
    }.toThisFunc.opt
    iconOrientation = IconOrientation.center.opt
    orientation = "vertical".opt
}
