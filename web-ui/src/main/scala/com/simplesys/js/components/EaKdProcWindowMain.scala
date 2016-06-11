package com.simplesys.js.components

import com.simplesys.SmartClient.App.{LoggedGroup, WebApp}
import com.simplesys.SmartClient.Control.props.MenuSSProps
import com.simplesys.SmartClient.Control.props.menu.MenuSSItemProps
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Foundation.props.LabelProps
import com.simplesys.SmartClient.Layout.props._
import com.simplesys.SmartClient.RPC.RPCManagerSS
import com.simplesys.SmartClient.System.{RibbonBar, RibbonGroupSS, _}
import com.simplesys.System.Types.{Alignment, ID, IconOrientation, Visibility}
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._

import scala.scalajs.js.annotation.JSExport

@JSExport
object EaKdProcWindowMain extends WebApp {

    override val loadSchemas = com.simplesys.app.loadSchemas

    override val identifier: ID = "5814FE1C-252A-01C4-11A1-557FA3222D3F"

    val functionButton = IconMenuButtonSS(
        new IconMenuButtonSSProps {
            title = "Операции".ellipsis.opt
            icon = Common.iconConstructor.opt
        }
    )

    private val managedUsersGroups = Seq(
        RibbonGroupSS.create(
            new RibbonGroupSSProps {
                title = "Управление".ellipsis.opt
                controls = Seq(
                    //functionButton
                ).opt
                //numRows = 1.opt
                //titleHeight = 18.opt
            }
        ),
        RibbonGroupSS.create(
            new RibbonGroupSSProps {
                title = "Справочники".ellipsis.opt
            }
        ),
        RibbonGroupSS.create(
            new RibbonGroupSSProps {
                title = "Системные".ellipsis.opt
                controls = Seq(
                    IconButton.create(
                        new IconButtonProps {
                            title = "Информация".ellipsis.opt
                            icon = Common.info.opt
                            click = {
                                (thiz: classHandler) =>
                                    getAbout()
                                    false
                            }.toThisFunc.opt
                        }
                    ),
                    IconButton.create(
                        new IconButtonProps {
                            title = "Настройки".ellipsis.opt
                            icon = Common.settings.opt
                            click = {
                                (thiz: classHandler) =>
                                    getSetting()
                                    false
                            }.toThisFunc.opt
                        }
                    )
                ).opt
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
                title = "Администратор".ellipsis.opt
                controls = Seq(
                    IconMenuButtonSS.create(
                        new IconMenuButtonSSProps {
                            title = "Справочники".ellipsis.opt
                            icon = Common.ref.opt
                            menu = MenuSS.create(
                                new MenuSSProps {
                                    items = Seq(
                                        new MenuSSItemProps {
                                            name = "usersGroups".opt
                                            icon = Common.admin_User.opt
                                            title = "Группы и пользователи".ellipsis.opt
                                        }
                                    ).opt
                                }
                            ).opt
                        }
                    )
                ).opt
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
            visibility = Visibility.hidden.opt
        }
    )

    private val tabSet = TabSetSS.create(
        new TabSetSSProps {

        }
    )

    override protected def mainCanvas: Canvas =
        VLayoutSS.create(
            new VLayoutSSProps {
                members = Seq(
                    RibbonBar.create(
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
                                            width = 40
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
                                                                                captionUserLabel.show()
                                                                                managedUsersGroups.foreach(_.show())
                                                                                LoggedGroup.codeGroup = codeGroup.toOption

                                                                                if (LoggedGroup.isAdminsGroup() || LoggedGroup.isDevsGroup())
                                                                                    managedAdminsGroups.foreach(_.show())

                                                                                if (LoggedGroup.isDevsGroup())
                                                                                    managedDevsGroups.foreach(_.show())

                                                                                LoggedGroup.logged = true

                                                                                thiz setTitle "Выход"
                                                                                thiz setIcon Common.closeProgram
                                                                            } else {
                                                                                managedUsersGroups.foreach(_.hide())
                                                                                managedAdminsGroups.foreach(_.hide())
                                                                                managedDevsGroups.foreach(_.hide())
                                                                                captionUserLabel.hide()

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
                                                                    managedUsersGroups.foreach(_.hide())
                                                                    managedAdminsGroups.foreach(_.hide())
                                                                    managedDevsGroups.foreach(_.hide())
                                                                    captionUserLabel.hide()
                                                                    windowsStack.destroyAll()
                                                                }
                                                                false
                                                        }.toThisFunc.opt
                                                        title = "Войти".ellipsis.opt
                                                        iconOrientation = IconOrientation.center.opt
                                                        icon = Common.login.opt
                                                    }
                                                ),
                                                captionUserLabel
                                            ).opt
                                            numRows = 3.opt
                                            titleHeight = 18.opt
                                        }
                                    )
                                )).opt
                        }
                    ),
                    tabSet
                ).opt
            }
        )
}
