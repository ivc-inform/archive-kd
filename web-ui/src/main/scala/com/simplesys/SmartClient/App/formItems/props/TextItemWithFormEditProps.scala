package com.simplesys.SmartClient.App.formItems.props

import com.simplesys.SmartClient.Control.props.IButtonSSProps
import com.simplesys.SmartClient.Forms.DynamicForm
import com.simplesys.SmartClient.Forms.FormsItems.CanvasItem
import com.simplesys.SmartClient.Forms.FormsItems.props.{CanvasItemProps, TextItemProps}
import com.simplesys.SmartClient.Forms.props.DynamicFormSSProps
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Layout.props.HLayoutSSProps
import com.simplesys.SmartClient.System.{Common, DynamicFormSS, HLayoutSS, IButtonSS, TextItem}
import com.simplesys.System.Types.FormItemComponentType
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._

class TextItemWithFormEditProps extends CanvasItemProps {
    createCanvas = {
        (thiz: classHandler, form: DynamicForm, item: CanvasItem) =>
            HLayoutSS.create(
                new HLayoutSSProps {
                    height = 20
                    members = Seq(
                        DynamicFormSS.create(
                            new DynamicFormSSProps {
                                items = Seq(
                                    TextItem(
                                        new TextItemProps {
                                            canEdit = false.opt
                                            width = "*"
                                            showTitle = false.opt
                                            value = item.value.opt
                                        }
                                    )
                                ).opt
                            }
                        ),
                        IButtonSS.create(
                            new IButtonSSProps {
                                iconAlign = "center".opt
                                icon = Common.ellipsis.opt
                            }
                        )
                    ).opt
                }
            )
    }.toThisFunc.opt

    `type` = FormItemComponentType.TextItemWithFormEdit

}
