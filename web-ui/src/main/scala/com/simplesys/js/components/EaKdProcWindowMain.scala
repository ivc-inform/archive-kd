package com.simplesys.js.components

import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Layout.props.RibbonBarProps
import com.simplesys.SmartClient.System.{RibbonBar, RibbonGroupSS}
import com.simplesys.js.com.simplesys.SmartClient.App.WebApp
import com.simplesys.js.com.simplesys.SmartClient.Layout.props.RibbonGroupSSProps
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._

import scala.scalajs.js.annotation.JSExport

@JSExport
class EaKdProcWindowMain extends WebApp {
    override protected def mainCanvas: Canvas = RibbonBar.create(
        new RibbonBarProps {
            width = "100%"
            members = Seq(
                RibbonGroupSS.create(
                    new RibbonGroupSSProps {
                        title = "Справочники".opt
                        showResizeBar = true.opt
                    }
                )
            ).opt
        }
    )
}
