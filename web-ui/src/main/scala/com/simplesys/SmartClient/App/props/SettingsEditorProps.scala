package com.simplesys.SmartClient.App.props

import com.simplesys.SmartClient.App.SettingsEditor
import com.simplesys.SmartClient.Forms.DynamicForm
import com.simplesys.SmartClient.Forms.formsItems.FormItem
import com.simplesys.SmartClient.Forms.formsItems.props.{CheckboxItemProps, SkinBoxItemProps}
import com.simplesys.SmartClient.Forms.props.DynamicFormSSProps
import com.simplesys.SmartClient.Layout.props.tabSet.TabProps
import com.simplesys.SmartClient.Layout.props.{OkCancelPanelProps, TabSetSSProps, WindowSSProps}
import com.simplesys.SmartClient.System._
import com.simplesys.System.Types.Skin
import com.simplesys.System.{JSAny, JSUndefined, jSUndefined}
import com.simplesys.function._
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._
import com.simplesys.SmartClient.Forms.formsItems.props.SpinnerItemProps

import scala.scalajs.js

class SettingsEditorProps extends WindowSSProps {
    type classHandler <: SettingsEditor

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
            isc debugTrac(thiz.getClassName(), thiz.getIdentifier())

            thiz.Super("initWidget", arguments)

            val identifierApp = thiz.identifier
            val oldSkin = simpleSyS.skin
            var skin: JSUndefined[String] = jSUndefined

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
                                    (form: DynamicForm, item: FormItem, value: JSUndefined[Boolean]) =>
                                        simpleSyS.expertMode = value

                                }.toFunc.opt
                            }
                        ),
                        SkinBoxItem(
                            new SkinBoxItemProps {
                                title = "Темы оформления (Skins)".opt
                                value = simpleSyS.skin.getOrElse(Skin.Enterprise.toString).asInstanceOf[JSAny].opt
                                changed = {
                                    (form: DynamicForm, item: FormItem, value: JSUndefined[String]) =>
                                        skin = value
                                }.toFunc.opt
                            }
                        ),
                        SpinnerItem(
                            new SpinnerItemProps {
                                disabled = true.opt
                                title = "Количество копий графа состояний".opt
                                defaultValue = (if (simpleSyS.qtyGraphCopies.isEmpty) 100 else simpleSyS.qtyGraphCopies get).asInstanceOf[JSAny].opt
                                min = 0.0.opt
                                step = 1.0.opt
                                changed = {
                                    import com.simplesys.SmartClient.Forms.DynamicFormSS
                                    (form: DynamicFormSS, formItem: FormItem, value: JSUndefined[Int]) ⇒
                                        simpleSyS.qtyGraphCopies = value
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

                                isc.OfflineSS.put(s"Skin$identifierApp", skin)
                                isc.OfflineSS.putBoolean(s"ScenarioTestMode$identifierApp", simpleSyS.scenarioTestMode.getOrElse(false))
                                js.Dynamic.global.window.location.reload(false)
                            }

                    }.toThisFunc.opt
                }
            )

            thiz.addItems(IscArray(tabSet, ed))

    }.toThisFunc.opt
}
