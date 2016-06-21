package com.simplesys.js.components

import com.simplesys.SmartClient.App.props.{EditorUserGroupsProps, EditorUsersProps}
import com.simplesys.SmartClient.App.{LoggedGroup, TabSetStack, WebApp}
import com.simplesys.SmartClient.Control.MenuSS
import com.simplesys.SmartClient.Control.menu.MenuSSItem
import com.simplesys.SmartClient.Control.props.MenuSSProps
import com.simplesys.SmartClient.Control.props.menu.MenuSSItemProps
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Foundation.props.LabelProps
import com.simplesys.SmartClient.Layout.props._
import com.simplesys.SmartClient.RPC.RPCManagerSS
import com.simplesys.SmartClient.System.{RibbonBar, RibbonGroupSS, _}
import com.simplesys.System.Types.{Alignment, ID, IconOrientation, Visibility}
import com.simplesys.System._
import com.simplesys.app
import com.simplesys.app._
import com.simplesys.function._
import com.simplesys.js.components.refs.props._
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.DataSourcesJS

import scala.scalajs.js.annotation.JSExport

@JSExport
object EaKdProcWindowMain extends WebApp with TabSetStack {

    override val loadSchemas = com.simplesys.app.loadSchemas

    override val identifier: ID = "5814FE1C-252A-01C4-11A1-557FA3222D3F"

    override val appImageDir: String = "images/"

    val functionButton = IconMenuButtonSS.create(
        new IconMenuButtonSSProps {
            title = "Операции".ellipsis.opt
            icon = Common.iconConstructor.opt
        }
    )

    val functionGroup = RibbonGroupSS.create(
        new RibbonGroupSSProps {
            title = "Управление".ellipsis.opt
            visibility = Visibility.hidden.opt
            controls = Seq(
                functionButton
            ).opt
        })

    private val managedSystemGroups = Seq(
        RibbonGroupSS.create(
            new RibbonGroupSSProps {
                title = "Пользователь".ellipsis.opt
                controls = Seq(
                    IconMenuButtonSS.create(
                        new IconMenuButtonSSProps {
                            title = "Справочники".ellipsis.opt
                            icon = Common.ref_RefRefs.opt
                            menu = MenuSS.create(
                                new MenuSSProps {
                                    items = Seq(
                                        new MenuSSItemProps {
                                            name = "abonents_org".opt
                                            icon = app.organization.opt
                                            title = "Предприятия".ellipsis.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                    addTab(AbonentsOrg.create(new AbonentsOrgProps), item)
                                            }.toFunc.opt
                                        },
                                        new MenuSSItemProps {
                                            name = "doccats".opt
                                            icon = app.doccats.opt
                                            title = "Виды документов".ellipsis.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                    addTab(DocCats.create(new DocCatsProps), item)
                                            }.toFunc.opt
                                        },
                                        new MenuSSItemProps {
                                            name = "docformats".opt
                                            icon = app.doctypes.opt
                                            title = "Форматы".ellipsis.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                    addTab(DocFormats.create(new DocFormatsProps), item)
                                            }.toFunc.opt
                                        },
                                        new MenuSSItemProps {
                                            name = "docitem".opt
                                            icon = app.docitem.opt
                                            title = "Изделия".ellipsis.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                    addTab(DocItem.create(new DocItemProps), item)
                                            }.toFunc.opt
                                        },
                                        new MenuSSItemProps {
                                            name = "docizvstat".opt
                                            icon = app.status.opt
                                            title = "Статусы извещений".ellipsis.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                    addTab(DocIzvStat.create(new DocIzvStatProps), item)
                                            }.toFunc.opt
                                        },
                                        ////////////////////
                                        ////////////////////
                                        new MenuSSItemProps {
                                            name = "doctypes".opt
                                            icon = app.doctypes.opt
                                            title = "Тип документов".ellipsis.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                    addTab(DocTypes.create(new DocTypesProps), item)
                                            }.toFunc.opt
                                        },
                                        new MenuSSItemProps {
                                            name = "abonents_types".opt
                                            icon = app.abonents.opt
                                            title = "Типы абонентов".ellipsis.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                    addTab(AbonentsTypes.create(new AbonentsTypesProps), item)
                                            }.toFunc.opt
                                        },
                                        new MenuSSItemProps {
                                            name = "abonents_types".opt
                                            icon = app.admin.opt
                                            title = "Абоненты".ellipsis.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                    addTab(Abonents.create(new AbonentsProps), item)
                                            }.toFunc.opt
                                        },
                                        new MenuSSItemProps {
                                            name = "attachtypes".opt
                                            icon = app.attach.opt
                                            title = "Тип вложения".ellipsis.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                    addTab(AttachTypes.create(new AttachTypesProps), item)
                                            }.toFunc.opt
                                        },
                                        new MenuSSItemProps {
                                            name = "austat".opt
                                            icon = app.status.opt
                                            title = "Статусы версий".ellipsis.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                    addTab(AuStat.create(new AuStatProps), item)
                                            }.toFunc.opt
                                        }
                                    ).opt
                                }
                            ).opt
                        }
                    )
                ).opt
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
                                            name = "groups".opt
                                            icon = Common.admin_UserGroup.opt
                                            title = "Группы".ellipsis.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                    addTab(EditorUserGroups.create(new EditorUserGroupsProps {
                                                        dataSource = DataSourcesJS.admin_UserGroup_DS.opt
                                                    }), item)
                                            }.toFunc.opt
                                        },
                                        new MenuSSItemProps {
                                            name = "users".opt
                                            icon = Common.admin_User.opt
                                            title = "Пользователи".ellipsis.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                    addTab(EditorUsers.create(new EditorUsersProps {
                                                        dataSourceList = DataSourcesJS.admin_User_DS.opt
                                                        dataSourceTree = DataSourcesJS.admin_UserGroup_DS.opt
                                                    }), item)
                                            }.toFunc.opt
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
                controls = Seq(
                    IconButton.create(
                        new IconButtonProps {
                            title = "GUID".opt
                            icon = app.guid.opt
                            orientation = "gorizontal".opt
                            click = {
                                (thiz: classHandler) =>
                                    isc info simpleSyS.guid
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

    private val captionUserLabel = Label.create(
        new LabelProps {
            showEdges = true.opt
            contents = "Иванов Иван Иванович".opt
            icon = Common.approved.opt
            wrap = true.opt
            visibility = Visibility.hidden.opt
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
                              managedSystemGroups ++
                                managedAdminsGroups ++
                                Seq(functionGroup) ++
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
                                                                                managedSystemGroups.foreach(_.show())
                                                                                LoggedGroup.codeGroup = codeGroup.toOption

                                                                                if (LoggedGroup.isAdminsGroup() || LoggedGroup.isDevsGroup())
                                                                                    managedAdminsGroups.foreach(_.show())

                                                                                if (LoggedGroup.isRoot()) {
                                                                                    managedAdminsGroups.foreach(_.show())
                                                                                }

                                                                                if (LoggedGroup.isDevsGroup())
                                                                                    managedDevsGroups.foreach(_.show())

                                                                                LoggedGroup.logged = true

                                                                                thiz setTitle "Выход"
                                                                                thiz setIcon Common.closeProgram
                                                                            } else {
                                                                                managedSystemGroups.foreach(_.hide())
                                                                                managedAdminsGroups.foreach(_.hide())
                                                                                managedDevsGroups.foreach(_.hide())
                                                                                captionUserLabel.hide()
                                                                                tabSet.removeAllTabs()

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
                                                                    managedSystemGroups.foreach(_.hide())
                                                                    managedAdminsGroups.foreach(_.hide())
                                                                    managedDevsGroups.foreach(_.hide())
                                                                    captionUserLabel.hide()
                                                                    functionGroup.hide()
                                                                    windowsStack.destroyAll()
                                                                    tabSet.removeAllTabs()
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
