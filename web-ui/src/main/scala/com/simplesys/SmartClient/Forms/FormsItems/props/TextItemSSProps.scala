package com.simplesys.SmartClient.Forms.FormsItems.props

import com.simplesys.SmartClient.Forms.DynamicFormSS
import com.simplesys.SmartClient.Forms.FormsItems.{FormItem, TextItemSS}
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.Types._
import com.simplesys.function._
import com.simplesys.option.ScOption._

class TextItemSSProps extends TextItemProps {
    type classHandler <: TextItemSS

    keyPress = {
        (thiz: classHandler, item: FormItem, form: DynamicFormSS, keyName: KeyName, characterValue: Int) =>
            var res = true
            thiz.readOnlyDisplay.foreach {
                readOnlyDisplay =>
                    if (readOnlyDisplay.toString == ReadOnlyDisplayAppearance.readOnly.toString)
                        res = false
                    else
                        res = thiz.Super("keyPress", IscArray(item, form, keyName, characterValue)).asInstanceOf[Boolean]
            }

            res
    }.toThisFunc.opt
}
