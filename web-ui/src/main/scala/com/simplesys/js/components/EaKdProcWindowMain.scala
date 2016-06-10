package com.simplesys.js.components

import com.simplesys.SmartClient.App.WebApp
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Foundation.props.LabelProps
import com.simplesys.SmartClient.Layout.props.{IconButtonProps, LayoutSpacerProps, RibbonBarProps, RibbonGroupSSProps}
import com.simplesys.SmartClient.RPC.RPCManagerSS
import com.simplesys.SmartClient.System.{RibbonBar, RibbonGroupSS, _}
import com.simplesys.System.Types.{Alignment, IconOrientation}
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.js.com.simplesys.SmartClient.App.LoggedGroup
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._

import scala.scalajs.js.annotation.JSExport

@JSExport
object EaKdProcWindowMain extends WebApp {

    override val loadSchemas: Boolean = false

    private val managedUsersGroups = Seq(
        RibbonGroupSS.create(
            new RibbonGroupSSProps {
                title = "Справочники".ellipsis.opt
            }
        ),
        RibbonGroupSS.create(
            new RibbonGroupSSProps {
                title = "Системные".ellipsis.opt
            }
        )

    ).map {
        item =>
            item.hide()
            item
    }

    private val managedAdminsGroups = Seq(
        RibbonGroupSS.create(
            new RibbonGroupSSProps {
                title = "Справочники".ellipsis.opt
            }
        )
    ).map {
        item =>
            item.hide()
            item
    }

    private val managedDevsGroups = Seq(
        RibbonGroupSS.create(
            new RibbonGroupSSProps {
                title = "Misc".ellipsis.opt
            }
        )
    ).map {
        item =>
            item.hide()
            item
    }

    private val captionUserLabel = Label.create(
        new LabelProps {
            showEdges = true.opt
            contents = "Иванов Иван Иванович".opt
            icon = Common.approved.opt
            wrap = true.opt
        }
    )

    override protected def mainCanvas: Canvas = RibbonBar.create(
        new RibbonBarProps {
            width = "100%"
            showResizeBar = true.opt
            members = (
              managedUsersGroups ++
                managedAdminsGroups ++
                managedDevsGroups ++
                Seq(
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
                                                if (!LoggedGroup.logged) {
                                                    RPCManagerSS.loginRequired({
                                                        (res: Boolean, captionUser: JSUndefined[String], codeGroup: JSUndefined[String]) =>
                                                            if (res) {

                                                                captionUserLabel setContents s"Работает: '${captionUser.toOption.getOrElse("Не определен")}'"
                                                                managedUsersGroups.foreach(_.show())

                                                                if (LoggedGroup.isAdminsGroup() || LoggedGroup.isDevsGroup())
                                                                    managedAdminsGroups.foreach(_.show())

                                                                if (LoggedGroup.isDevsGroup())
                                                                    managedDevsGroups.foreach(_.show())

                                                                LoggedGroup.logged = true
                                                                LoggedGroup.codeGroup = codeGroup.toOption
                                                                thiz setTitle "Выход"
                                                                thiz setIcon Common.closeProgram
                                                            } else {
                                                                managedUsersGroups.foreach(_.hide())
                                                                managedAdminsGroups.foreach(_.hide())
                                                                managedDevsGroups.foreach(_.hide())

                                                                LoggedGroup.logged = false
                                                                thiz setTitle "Вход".ellipsis
                                                                thiz setIcon Common.login
                                                            }
                                                    }.toFunc)

                                                } else {
                                                    RPCManagerSS.logoutRequired()
                                                    thiz setTitle "Вход".ellipsis
                                                    thiz setIcon Common.login
                                                    LoggedGroup.logged = false
                                                    windowsStack.destroyAll()
                                                }
                                                false
                                        }.toThisFunc.opt
                                        title = "Выйти".ellipsis.opt
                                        iconOrientation = IconOrientation.center.opt
                                        icon = Common.closeProgram.opt
                                        largeIcon = Common.login.opt
                                        orientation = "horizontal".opt
                                    }
                                ),
                                captionUserLabel
                            ).opt
                            numRows = 2.opt
                            titleHeight = 18.opt
                        }
                    )
                )).opt
        }
    )
}
