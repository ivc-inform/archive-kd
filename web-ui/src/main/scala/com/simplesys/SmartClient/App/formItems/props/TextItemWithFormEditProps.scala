package com.simplesys.SmartClient.App.formItems.props

import com.simplesys.SmartClient.App.formItems.TextItemWithFormEdit
import com.simplesys.SmartClient.Control.props.IButtonSSProps
import com.simplesys.SmartClient.Forms.{DynamicForm, DynamicFormSS}
import com.simplesys.SmartClient.Forms.FormsItems.CanvasItem
import com.simplesys.SmartClient.Forms.FormsItems.props.{CanvasItemProps, TextItemProps}
import com.simplesys.SmartClient.Forms.props.DynamicFormSSProps
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Layout.props.HLayoutSSProps
import com.simplesys.SmartClient.System.{Common, DynamicFormSS, HLayoutSS, IButtonSS, TextItem}
import com.simplesys.System.Types.{FormItemComponentType, SCImgURL}
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.option.DoubleType._
import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.option.ScOption._

class TextItemWithFormEditProps extends CanvasItemProps {
    type classHandler <: TextItemWithFormEdit

    var buttonIcon : ScOption[SCImgURL] = ScNone

    createCanvas = {
        (thiz: classHandler, form: DynamicFormSS, item: CanvasItem) =>
            val df = DynamicFormSS.create(
                new DynamicFormSSProps {
                    cellPadding = 0.opt
                    width = "*"
                    minColWidth = 0.opt
                    colWidths = Seq[JSAny](0, "*").opt
                    items = Seq(
                        TextItem(
                            new TextItemProps {
                                colSpan = 2.opt
                                name = item.name.opt
                                width = "*"
                                showTitle = false.opt
                                value = item.value.opt
                            }
                        )
                    ).opt
                }
            )

            HLayoutSS.create(
                new HLayoutSSProps {
                    height = 20
                    members = Seq(
                        df,
                        IButtonSS.create(
                            new IButtonSSProps {
                                iconAlign = "center".opt
                                icon = thiz.buttonIcon.getOrElse(Common.ellipsis).opt
                                width = 22
                            }
                        )
                    ).opt
                }
            )
    }.toThisFunc.opt

    `type` = FormItemComponentType.TextItemWithFormEdit

}
