package com.simplesys.SmartClient.Forms.props

import com.simplesys.SmartClient.Forms.DynamicFormSS
import com.simplesys.SmartClient.Forms.FormsItems.FormItem
import com.simplesys.SmartClient.System.{IscArray, isc}
import com.simplesys.System.JSAny
import com.simplesys.System.Types.FormItemType
import com.simplesys.System.Types.FormItemType.FormItemType
import com.simplesys.function._
import com.simplesys.option.ScOption
import com.simplesys.option.ScOption._

import scala.scalajs.js.{ThisFunction2, ThisFunction3}

class DynamicFormSSProps extends DynamicFormProps {
    type classHandler <: DynamicFormSS

    var createItem: ScOption[ThisFunction2[classHandler, FormItem, String, _]] = {
        (thiz: classHandler, item: FormItem, tp: String) =>
            //isc debugTrap item
            item.nameStrong.foreach(name => item.name = name.name)
            //isc debugTrap item
            thiz.Super("createItem", IscArray[JSAny](item, tp))
    }.toThisFunc.opt
}
