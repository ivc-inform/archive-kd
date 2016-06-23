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
import com.simplesys.js.components.cards.props.{CardsProps, ZaprosProps}
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
            icon = app.iconConstructor.opt
            identifier = "33EE1839-8D4D-FFA0-E491-22B54F212772A".opt
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
                            title = "Картотека".ellipsis.opt
                            icon = app.cards.opt
                            identifier = "33EE1839-8D4D-FFA0-E491-22B54F55772A".opt
                            menu = MenuSS.create(
                                new MenuSSProps {
                                    items = Seq(
                                        new MenuSSItemProps {
                                            name = "cards".opt
                                            icon = app.card.opt
                                            title = "Картотека".ellipsis.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                    addTab(Cards.create(new CardsProps), item)
                                            }.toFunc.opt
                                        },
                                        new MenuSSItemProps {
                                            name = "zapros".opt
                                            icon = app.zapros.opt
                                            title = "Запросы".ellipsis.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                    addTab(Zapros.create(new ZaprosProps), item)
                                            }.toFunc.opt
                                        },
                                        new MenuSSItemProps {
                                            name = "docizv".opt
                                            icon = app.docizv.opt
                                            title = "Извещения".ellipsis.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>

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
                title = "Пользователь".ellipsis.opt
                controls = Seq(
                    IconMenuButtonSS.create(
                        new IconMenuButtonSSProps {
                            title = "Справочники".ellipsis.opt
                            icon = app.ref_RefRefs.opt
                            identifier = "33EE1839-8D4D-FFA0-E491-85B54F2C772A".opt
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
                                        new MenuSSItemProps {
                                            name = "docizvtype".opt
                                            icon = app.docizvtype.opt
                                            title = "Типы извещений".ellipsis.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                    addTab(DocIzvType.create(new DocIzvTypeProps), item)
                                            }.toFunc.opt
                                        },
                                        new MenuSSItemProps {
                                            name = "docliter".opt
                                            icon = app.docliter.opt
                                            title = "Литеры".ellipsis.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                    addTab(DocLiter.create(new DocLiterProps), item)
                                            }.toFunc.opt
                                        },
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
                                            name = "groupitem".opt
                                            icon = app.groupitem.opt
                                            title = "Группы".ellipsis.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                    addTab(GroupItem.create(new GroupItemProps), item)
                                            }.toFunc.opt
                                        },
                                        new MenuSSItemProps {
                                            name = "mtype".opt
                                            icon = app.mtype.opt
                                            title = "Типы носителя".ellipsis.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                    addTab(MType.create(new MTypeProps), item)
                                            }.toFunc.opt
                                        },
                                        new MenuSSItemProps {
                                            name = "state".opt
                                            icon = app.state.opt
                                            title = "Состояния".ellipsis.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                    addTab(State.create(new StateProps), item)
                                            }.toFunc.opt
                                        },
                                        new MenuSSItemProps {
                                            name = "status".opt
                                            icon = app.status.opt
                                            title = "Статус запроса".ellipsis.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                    addTab(Status.create(new StatusProps), item)
                                            }.toFunc.opt
                                        },
                                        new MenuSSItemProps {
                                            name = "statusversion".opt
                                            icon = app.statusversion.opt
                                            title = "Статус ревизий".ellipsis.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                    addTab(StatVersion.create(new StatVersionProps), item)
                                            }.toFunc.opt
                                        },
                                        new MenuSSItemProps {
                                            name = "mvid".opt
                                            icon = app.mvid.opt
                                            title = "Вид носителя".ellipsis.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                    addTab(MVid.create(new MVidProps), item)
                                            }.toFunc.opt
                                        } /*,
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
                                        }*/
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
                title = "Пользователь".ellipsis.opt
                controls = Seq(
                    IconMenuButtonSS.create(
                        new IconMenuButtonSSProps {
                            title = "Выдача копий".ellipsis.opt
                            icon = app.copyProduct.opt
                            identifier = "33121839-8D4D-FFA0-E491-22B54F2C772A".opt
                            menu = MenuSS.create(
                                new MenuSSProps {
                                    items = Seq().opt
                                }
                            ).opt
                        }
                    )
                ).opt
            }
        ),
        RibbonGroupSS.create(
            new RibbonGroupSSProps {
                title = "Пользователь".ellipsis.opt
                controls = Seq(
                    IconMenuButtonSS.create(
                        new IconMenuButtonSSProps {
                            title = "Отчеты".ellipsis.opt
                            icon = app.reports.opt
                            identifier = "33EE1839-810D-FFA0-E491-22B54F2C772A".opt
                            menu = MenuSS.create(
                                new MenuSSProps {
                                    items = Seq().opt
                                }
                            ).opt
                        }
                    )
                ).opt
            }
        ),
        RibbonGroupSS.create(
            new RibbonGroupSSProps {
                title = "Пользователь".ellipsis.opt
                controls = Seq(
                    IconMenuButtonSS.create(
                        new IconMenuButtonSSProps {
                            title = "Магнитотека".ellipsis.opt
                            icon = app.recorder.opt
                            identifier = "33EE1839-8D4D-F550-E491-22B54F2C772A".opt
                            menu = MenuSS.create(
                                new MenuSSProps {
                                    items = Seq().opt
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
                            icon = app.info.opt
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
                            icon = app.settings.opt
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
                            icon = app.ref.opt
                            identifier = "33EE1839-8D4D-FFA0-E491-22B54F2C772A".opt
                            menu = MenuSS.create(
                                new MenuSSProps {
                                    items = Seq(
                                        new MenuSSItemProps {
                                            name = "groups".opt
                                            icon = app.admin_UserGroup.opt
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
                                            icon = app.admin_User.opt
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
            icon = app.approved.opt
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
                                                                                thiz setIcon app.closeProgram
                                                                            } else {
                                                                                managedSystemGroups.foreach(_.hide())
                                                                                managedAdminsGroups.foreach(_.hide())
                                                                                managedDevsGroups.foreach(_.hide())
                                                                                captionUserLabel.hide()
                                                                                tabGroupSet.removeAllTabs()

                                                                                LoggedGroup.logged = false
                                                                                thiz setTitle "Вход".ellipsis
                                                                                thiz setIcon app.login
                                                                            }
                                                                    }.toFunc)

                                                                } else {
                                                                    RPCManagerSS.logoutRequired()
                                                                    thiz setTitle "Вход".ellipsis
                                                                    thiz setIcon app.login
                                                                    LoggedGroup.logged = false
                                                                    managedSystemGroups.foreach(_.hide())
                                                                    managedAdminsGroups.foreach(_.hide())
                                                                    managedDevsGroups.foreach(_.hide())
                                                                    captionUserLabel.hide()
                                                                    functionGroup.hide()
                                                                    windowsStack.destroyAll()
                                                                    tabGroupSet.removeAllTabs()
                                                                }
                                                                false
                                                        }.toThisFunc.opt
                                                        title = "Войти".ellipsis.opt
                                                        iconOrientation = IconOrientation.center.opt
                                                        icon = app.login.opt
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
                    tabGroupSet
                ).opt
            }
        )
}
