package com.simplesys.SmartClient.Forms.FormsItems.props

import com.simplesys.SmartClient.Control.IButtonSS
import com.simplesys.SmartClient.Control.props.IButtonSSProps
import com.simplesys.SmartClient.Forms.DynamicForm
import com.simplesys.SmartClient.Forms.FormsItems.{CanvasItem, FormItemWithButton}
import com.simplesys.SmartClient.Forms.props.DynamicFormSSProps
import com.simplesys.SmartClient.Layout.props.HLayoutSSProps
import com.simplesys.SmartClient.System.{Common, DynamicFormSS, HLayoutSS, IButtonSS, IscArray, TextItem}
import com.simplesys.System.JSAny
import com.simplesys.System.Types._
import com.simplesys.function._
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js.ThisFunction0

class FormItemWithButtonProps extends CanvasItemProps {
    type classHandler <: FormItemWithButton

    var iconButton: ScOption[SCImgURL] = ScNone
    var button: ScOption[IButtonSS] = ScNone

    var clickButton: ScOption[ThisFunction0[IButtonSS, Boolean]] = ScNone

    createCanvas = {
        (thiz: classHandler, form: DynamicForm, item: CanvasItem) =>
            val res = HLayoutSS.create(
                new HLayoutSSProps {
                    height = 20
                    members = Seq(
                        DynamicFormSS.create(
                            new DynamicFormSSProps {
                                cellPadding = 0.opt
                                owner = thiz.opt
                                width = "*"
                                minColWidth = 0.opt
                                colWidths = Seq[JSAny](0, "*").opt
                                items = Seq(
                                    TextItem(
                                        new TextItemProps {
                                            colSpan = 2.opt
                                            name = thiz.name.opt
                                            width = "*"
                                            showTitle = false.opt
                                        }
                                    )
                                ).opt
                            }
                        )
                    ).opt
                }
            )


            thiz.button.foreach(res addMember _)
            res
    }.toThisFunc.opt


    init = {
        (thiz: classHandler, arguments: IscArray[JSAny]) =>
            val _icon: SCImgURL = thiz.iconButton.getOrElse(Common.ellipsis)
            thiz.iconButton = _icon

            val defaultButton = IButtonSS.create(
                new IButtonSSProps {
                    width = 20
                    icon = _icon.opt
                    click = {
                        (thiz1: IButtonSS) =>
                           if (thiz.clickButton.isDefined) thiz.clickButton.get(thiz1) else false
                    }.toThisFunc.opt
                })

            val button: IButtonSS = thiz.button.getOrElse(defaultButton)
            thiz.button = button
            thiz.Super("init", arguments)

    }.toThisFunc.opt

    `type` = FormItemComponentType.FormItemWithButton
}
