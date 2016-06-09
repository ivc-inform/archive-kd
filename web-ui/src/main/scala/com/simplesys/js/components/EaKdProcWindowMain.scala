package com.simplesys.js.components

import com.simplesys.SmartClient.App.WebApp
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Foundation.props.LabelProps
import com.simplesys.SmartClient.Layout.props.{IconButtonProps, LayoutSpacerProps, RibbonBarProps, RibbonGroupSSProps}
import com.simplesys.SmartClient.System.{RibbonBar, RibbonGroupSS, _}
import com.simplesys.System.Types.{Alignment, IconOrientation}
import com.simplesys.function._
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._

import scala.scalajs.js.annotation.JSExport

@JSExport
object EaKdProcWindowMain extends WebApp {

    override val loadSchemas: Boolean = false

    override protected def mainCanvas: Canvas = RibbonBar.create(
        new RibbonBarProps {
            width = "100%"
            showResizeBar = true.opt
            members = Seq(
                RibbonGroupSS.create(
                    new RibbonGroupSSProps {
                        title = "Справочники".ellipsis.opt
                    }
                ),
                LayoutSpacer.create(
                    new LayoutSpacerProps {
                        width = "*"
                    }
                ),
                RibbonGroupSS.create(
                    new RibbonGroupSSProps {
                        title = "Аутентификация".ellipsis.opt
                        defaultLayoutAlign = Alignment.center
                        controls = Seq(
                            IconButton.create(
                                new IconButtonProps {
                                    click = {
                                        (thiz: classHandler) =>
                                            false
                                    }.toThisFunc.opt
                                    title = "Выйти".ellipsis.opt
                                    iconOrientation = IconOrientation.center.opt
                                    icon = Common.closeProgram.opt
                                    largeIcon = Common.closeProgram.opt
                                    orientation = "horizontal".opt
                                }
                            ),
                            Label.create(
                                new LabelProps {
                                    showEdges = true.opt
                                    contents = "Иванов Иван Иванович".opt
                                    icon = Common.approved.opt
                                    wrap = true.opt
                                }
                            )
                        ).opt
                        numRows = 2.opt
                        titleHeight = 18.opt
                    }
                )
            ).opt
        }
    )
}
