package com.simplesys.app

import com.simplesys.SmartClient.DataBinding.props.SimpleTypeProps
import com.simplesys.SmartClient.System.SimpleType
import com.simplesys.System.Types.OperatorId
import com.simplesys.System._
import com.simplesys.js.com.simplesys.app.StaticJSCode
import com.simplesys.option.{ScOption, ScSome}
import com.simplesys.option.ScOption._

import scala.scalajs.js.annotation.JSExport

@JSExport
class SimpleTypes extends StaticJSCode {
    implicit def anyToScOpt1(x: String) = ScSome(x)
    implicit def anyToScOpt2(x: Double) = ScSome(x)
    implicit def anyToScOpt3(x: Boolean) = ScSome(x)
    implicit def anyToScOpt4(x: Int) = ScSome(x)
    implicit def anyToScOpt5(x: String): ScOption[JSAny] = ScSome(x)
    implicit def anyToScOpt6(x: Double): ScOption[JSAny] = ScSome(x)
    implicit def anyToScOpt7(x: Boolean): ScOption[JSAny] = ScSome(x)
    implicit def anyToScOpt8(x: Int): ScOption[JSAny] = ScSome(x)

    @JSExport
    override def createJS(): Unit = {
        SimpleType.create(
            new SimpleTypeProps {
                inheritsFrom = "textArea"
                name = "clob_SimpleType"
                validOperators = Seq(
                    OperatorId.contains
                ).opt
            }
        )
    }
}
