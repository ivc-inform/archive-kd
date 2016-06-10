package com.simplesys.js.com.simplesys.SmartClient.App.props

import com.simplesys.SmartClient.Layout.props.WindowSSProps
import com.simplesys.SmartClient.System._
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._

class SettingsEditorProps extends WindowSSProps {
    height = 700
    width = 500
    isModal = true.opt
    showMaximizeButton = false.opt
    showMinimizeButton = false.opt
    title = "Настройки".ellipsis.opt
    headerIconPath = Common.settings.opt
    autoPosition = false.opt

    initWidget = {
        (thiz: classHandler, arguments: IscArray[JSAny]) =>
            thiz.Super("initWidget", arguments)

    }.toThisFunc.opt
}
