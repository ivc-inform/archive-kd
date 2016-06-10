package com.simplesys.js.com.simplesys.SmartClient.App.props

import com.simplesys.SmartClient.Forms.DynamicForm
import com.simplesys.SmartClient.Forms.FormsItems.FormItem
import com.simplesys.SmartClient.Forms.FormsItems.props.{CheckboxItemProps, SkinBoxItemProps}
import com.simplesys.SmartClient.Forms.props.DynamicFormSSProps
import com.simplesys.SmartClient.Layout.props.tabSet.TabProps
import com.simplesys.SmartClient.Layout.props.{OkCancelPanelProps, TabSetSSProps, WindowSSProps}
import com.simplesys.SmartClient.System._
import com.simplesys.System.Types.Skin.Skin
import com.simplesys.System.Types.{ID, Skin}
import com.simplesys.System.{JSAny, JSUndefined, jSUndefined}
import com.simplesys.function._
import com.simplesys.js.com.simplesys.SmartClient.App.SettingsEditor
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js

class SettingsEditorProps extends WindowSSProps {
    type classHandler <: SettingsEditor

    var identifierApp: ScOption[ID] = ScNone

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

            val oldSkin = simpleSyS.skin
            var skin: JSUndefined[Skin] = jSUndefined
            val identifierApp = thiz.identifierApp

            val commons = DynamicFormSS.create(
                new DynamicFormSSProps {
                    fields = Seq(
                        CheckboxItem(
                            new CheckboxItemProps {
                                title = "Показать дерево виджетов".opt
                                height = 25
                                value = simpleSyS.expertMode.getOrElse(false).asInstanceOf[JSAny].opt
                                disabled = true.opt
                                changed = {
                                    (form: DynamicForm, item: FormItem, value: JSAny) =>
                                        simpleSyS.expertMode = value.asInstanceOf[Boolean]

                                }.toFunc.opt
                            }
                        ),
                        SkinBoxItem(
                            new SkinBoxItemProps {
                                title = "Темы оформления (Skins)".opt
                                value = simpleSyS.skin.getOrElse(Skin.Enterprise).asInstanceOf[JSAny].opt
                                changed = {
                                    (form: DynamicForm, item: FormItem, value: JSAny) =>
                                        skin = value.asInstanceOf[Skin]
                                }.toFunc.opt
                            }
                        )
                    ).opt
                }
            )

            val tabSet = TabSetSS.create(
                new TabSetSSProps {
                    tabs = Seq(
                        Tab(
                            new TabProps {
                                title = "Общие".opt
                                pane = commons.opt
                            }
                        )
                    ).opt
                }
            )

            val ed = OkCancelPanel.create(
                new OkCancelPanelProps {
                    height = 35
                    owner = thiz.opt
                    ownerDestroy = false.opt
                    okFunction = {
                        (thiz: classHandler) =>
                            if (oldSkin != skin) {
                                simpleSyS.skin = skin
                                identifierApp.foreach(identifierApp => isc.OfflineSS.put(s"Skin$identifierApp", skin))
                                js.Dynamic.global.window.location.reload(false)
                            }

                    }.toThisFunc.opt
                }
            )

            thiz.addItems(IscArray(tabSet, ed))

    }.toThisFunc.opt
}
