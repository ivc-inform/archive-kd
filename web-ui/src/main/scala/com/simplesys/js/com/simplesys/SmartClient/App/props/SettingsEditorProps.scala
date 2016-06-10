package com.simplesys.js.com.simplesys.SmartClient.App.props

import com.simplesys.SmartClient.Forms.DynamicForm
import com.simplesys.SmartClient.Forms.FormsItems.FormItem
import com.simplesys.SmartClient.Forms.FormsItems.props.{CheckboxItemProps, SkinBoxItemProps}
import com.simplesys.SmartClient.Forms.props.DynamicFormSSProps
import com.simplesys.SmartClient.Layout.props.{TabSetSSProps, WindowSSProps}
import com.simplesys.SmartClient.System._
import com.simplesys.System.{JSAny, JSUndefined, jSUndefined}
import com.simplesys.System.Types.Skin.Skin
import com.simplesys.isc.system.typesDyn.Skin
import com.simplesys.option.ScOption._
import com.simplesys.function._
import com.simplesys.option.DoubleType._

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

            val oldSkin = simpleSyS.skin
            var skin: JSUndefined[Skin] = jSUndefined

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
                                value = simpleSyS.skin.getOrElse(Skin)
                                changed = {
                                    (form: DynamicForm, item: FormItem, value: JSAny) =>
                                        skin = value
                                }.toFunc.opt
                            }
                        )
                    ).opt
                }
            )

            val tabSet = TabSetSS.create(
                new TabSetSSProps{
                  tabs = Seq(

                  ).opt
                }
            )

    }.toThisFunc.opt
}
