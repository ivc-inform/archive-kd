package com.simplesys.app

import com.simplesys.SmartClient.App.StaticJSCode
import com.simplesys.SmartClient.DataBinding.props.SimpleTypeProps
import com.simplesys.SmartClient.System.{SimpleType, isc}
import com.simplesys.System.JSAny
import com.simplesys.System.Types.OperatorId
import com.simplesys.option.ScOption._
import com.simplesys.option.{ScOption, ScSome}

import scala.scalajs.js.annotation.JSExport


@JSExport
class SimpleTypes1 extends StaticJSCode {
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
        isc debugTrap(0)

        val bBoolean_SimpleType =
            SimpleType.create(new SimpleTypeProps {
                inheritsFrom = "boolean"
                name = "bBoolean_SimpleType"
            })

        val blob_SimpleType =
            SimpleType.create(new SimpleTypeProps {
                inheritsFrom = "binary"
                name = "blob_SimpleType"
            })

        val clob_SimpleType =
            SimpleType.create(new SimpleTypeProps {
                inheritsFrom = "textArea"
                name = "clob_SimpleType"
                validOperators = Seq(
                    OperatorId.contains,
                    OperatorId.isNull,
                    OperatorId.notInSet,
                    OperatorId.iContains,
                    OperatorId.startsWith,
                    OperatorId.inSet,
                    OperatorId.iNotEqual,
                    OperatorId.notEqual,
                    OperatorId.equals,
                    OperatorId.notNull,
                    OperatorId.endsWith,
                    OperatorId.iEndsWith,
                    OperatorId.iStartsWith,
                    OperatorId.iEquals
                ).opt
            })

        val dDate_SimpleType =
            SimpleType.create(new SimpleTypeProps {
                inheritsFrom = "date"
                name = "dDate_SimpleType"
                validOperators = Seq(
                    OperatorId.contains,
                    OperatorId.isNull,
                    OperatorId.notInSet,
                    OperatorId.notStartsWith,
                    OperatorId.lessOrEqual,
                    OperatorId.notEndsWith,
                    OperatorId.containsPattern,
                    OperatorId.equalsField,
                    OperatorId.greaterOrEqual,
                    OperatorId.startsWith,
                    OperatorId.greaterOrEqualField,
                    OperatorId.lessOrEqualField,
                    OperatorId.notContains,
                    OperatorId.inSet,
                    OperatorId.lessThan,
                    OperatorId.greaterThanField,
                    OperatorId.notEqualField,
                    OperatorId.notEqual,
                    OperatorId.equals,
                    OperatorId.lessThanField,
                    OperatorId.notNull,
                    OperatorId.matchesPattern,
                    OperatorId.endsWith,
                    OperatorId.regexp,
                    OperatorId.between,
                    OperatorId.betweenInclusive,
                    OperatorId.greaterThan
                ).opt
            })

        val dDateOptTime_SimpleType =
            SimpleType.create(new SimpleTypeProps {
                inheritsFrom = "datetime"
                name = "dDateOptTime_SimpleType"
                validOperators = Seq(
                    OperatorId.contains,
                    OperatorId.isNull,
                    OperatorId.notInSet,
                    OperatorId.notStartsWith,
                    OperatorId.lessOrEqual,
                    OperatorId.notEndsWith,
                    OperatorId.containsPattern,
                    OperatorId.equalsField,
                    OperatorId.greaterOrEqual,
                    OperatorId.startsWith,
                    OperatorId.greaterOrEqualField,
                    OperatorId.lessOrEqualField,
                    OperatorId.notContains,
                    OperatorId.inSet,
                    OperatorId.lessThan,
                    OperatorId.greaterThanField,
                    OperatorId.notEqualField,
                    OperatorId.notEqual,
                    OperatorId.equals,
                    OperatorId.lessThanField,
                    OperatorId.notNull,
                    OperatorId.matchesPattern,
                    OperatorId.endsWith,
                    OperatorId.regexp,
                    OperatorId.between,
                    OperatorId.betweenInclusive,
                    OperatorId.greaterThan
                ).opt
            })

        val dDateTime_SimpleType =
            SimpleType.create(new SimpleTypeProps {
                inheritsFrom = "datetime"
                name = "dDateTime_SimpleType"
                validOperators = Seq(
                    OperatorId.contains,
                    OperatorId.isNull,
                    OperatorId.notInSet,
                    OperatorId.notStartsWith,
                    OperatorId.lessOrEqual,
                    OperatorId.notEndsWith,
                    OperatorId.containsPattern,
                    OperatorId.equalsField,
                    OperatorId.greaterOrEqual,
                    OperatorId.startsWith,
                    OperatorId.greaterOrEqualField,
                    OperatorId.lessOrEqualField,
                    OperatorId.notContains,
                    OperatorId.inSet,
                    OperatorId.lessThan,
                    OperatorId.greaterThanField,
                    OperatorId.notEqualField,
                    OperatorId.notEqual,
                    OperatorId.equals,
                    OperatorId.lessThanField,
                    OperatorId.notNull,
                    OperatorId.matchesPattern,
                    OperatorId.endsWith,
                    OperatorId.regexp,
                    OperatorId.between,
                    OperatorId.betweenInclusive,
                    OperatorId.greaterThan
                ).opt
            })

        val dTime_SimpleType =
            SimpleType.create(new SimpleTypeProps {
                inheritsFrom = "time"
                name = "dTime_SimpleType"
                validOperators = Seq(
                    OperatorId.contains,
                    OperatorId.isNull,
                    OperatorId.notInSet,
                    OperatorId.notStartsWith,
                    OperatorId.lessOrEqual,
                    OperatorId.notEndsWith,
                    OperatorId.containsPattern,
                    OperatorId.equalsField,
                    OperatorId.greaterOrEqual,
                    OperatorId.startsWith,
                    OperatorId.greaterOrEqualField,
                    OperatorId.lessOrEqualField,
                    OperatorId.notContains,
                    OperatorId.inSet,
                    OperatorId.lessThan,
                    OperatorId.greaterThanField,
                    OperatorId.notEqualField,
                    OperatorId.notEqual,
                    OperatorId.equals,
                    OperatorId.lessThanField,
                    OperatorId.notNull,
                    OperatorId.matchesPattern,
                    OperatorId.endsWith,
                    OperatorId.regexp,
                    OperatorId.between,
                    OperatorId.betweenInclusive,
                    OperatorId.greaterThan
                ).opt
            })

        val dTimestamp_SimpleType =
            SimpleType.create(new SimpleTypeProps {
                inheritsFrom = "datetime"
                name = "dTimestamp_SimpleType"
                validOperators = Seq(
                    OperatorId.contains,
                    OperatorId.isNull,
                    OperatorId.notInSet,
                    OperatorId.notStartsWith,
                    OperatorId.lessOrEqual,
                    OperatorId.notEndsWith,
                    OperatorId.containsPattern,
                    OperatorId.equalsField,
                    OperatorId.greaterOrEqual,
                    OperatorId.startsWith,
                    OperatorId.greaterOrEqualField,
                    OperatorId.lessOrEqualField,
                    OperatorId.notContains,
                    OperatorId.inSet,
                    OperatorId.lessThan,
                    OperatorId.greaterThanField,
                    OperatorId.notEqualField,
                    OperatorId.notEqual,
                    OperatorId.equals,
                    OperatorId.lessThanField,
                    OperatorId.notNull,
                    OperatorId.matchesPattern,
                    OperatorId.endsWith,
                    OperatorId.regexp,
                    OperatorId.between,
                    OperatorId.betweenInclusive,
                    OperatorId.greaterThan
                ).opt
            })

        val dTimestampWithTZ_SimpleType =
            SimpleType.create(new SimpleTypeProps {
                inheritsFrom = "datetime"
                name = "dTimestampWithTZ_SimpleType"
                validOperators = Seq(
                    OperatorId.contains,
                    OperatorId.isNull,
                    OperatorId.notInSet,
                    OperatorId.notStartsWith,
                    OperatorId.lessOrEqual,
                    OperatorId.notEndsWith,
                    OperatorId.containsPattern,
                    OperatorId.equalsField,
                    OperatorId.greaterOrEqual,
                    OperatorId.startsWith,
                    OperatorId.greaterOrEqualField,
                    OperatorId.lessOrEqualField,
                    OperatorId.notContains,
                    OperatorId.inSet,
                    OperatorId.lessThan,
                    OperatorId.greaterThanField,
                    OperatorId.notEqualField,
                    OperatorId.notEqual,
                    OperatorId.equals,
                    OperatorId.lessThanField,
                    OperatorId.notNull,
                    OperatorId.matchesPattern,
                    OperatorId.endsWith,
                    OperatorId.regexp,
                    OperatorId.between,
                    OperatorId.betweenInclusive,
                    OperatorId.greaterThan
                ).opt
            })

        val di_SimpleType =
            SimpleType.create(new SimpleTypeProps {
                inheritsFrom = "float"
                name = "di_SimpleType"
                validOperators = Seq(
                    OperatorId.isNull,
                    OperatorId.notInSet,
                    OperatorId.equalsField,
                    OperatorId.inSet,
                    OperatorId.notEqualField,
                    OperatorId.notEqual,
                    OperatorId.equals,
                    OperatorId.notNull
                ).opt
            })

        val fDouble_SimpleType =
            SimpleType.create(new SimpleTypeProps {
                inheritsFrom = "float"
                name = "fDouble_SimpleType"
                validOperators = Seq(
                    OperatorId.contains,
                    OperatorId.isNull,
                    OperatorId.notInSet,
                    OperatorId.notStartsWith,
                    OperatorId.lessOrEqual,
                    OperatorId.notEndsWith,
                    OperatorId.containsPattern,
                    OperatorId.equalsField,
                    OperatorId.greaterOrEqual,
                    OperatorId.startsWith,
                    OperatorId.greaterOrEqualField,
                    OperatorId.lessOrEqualField,
                    OperatorId.notContains,
                    OperatorId.inSet,
                    OperatorId.lessThan,
                    OperatorId.greaterThanField,
                    OperatorId.notEqualField,
                    OperatorId.notEqual,
                    OperatorId.equals,
                    OperatorId.lessThanField,
                    OperatorId.notNull,
                    OperatorId.matchesPattern,
                    OperatorId.endsWith,
                    OperatorId.regexp,
                    OperatorId.between,
                    OperatorId.betweenInclusive,
                    OperatorId.greaterThan
                ).opt
            })

        val fPrice_SimpleType =
            SimpleType.create(new SimpleTypeProps {
                inheritsFrom = "float"
                name = "fPrice_SimpleType"
                validOperators = Seq(
                    OperatorId.contains,
                    OperatorId.isNull,
                    OperatorId.notInSet,
                    OperatorId.notStartsWith,
                    OperatorId.lessOrEqual,
                    OperatorId.notEndsWith,
                    OperatorId.containsPattern,
                    OperatorId.equalsField,
                    OperatorId.greaterOrEqual,
                    OperatorId.startsWith,
                    OperatorId.greaterOrEqualField,
                    OperatorId.lessOrEqualField,
                    OperatorId.notContains,
                    OperatorId.inSet,
                    OperatorId.lessThan,
                    OperatorId.greaterThanField,
                    OperatorId.notEqualField,
                    OperatorId.notEqual,
                    OperatorId.equals,
                    OperatorId.lessThanField,
                    OperatorId.notNull,
                    OperatorId.matchesPattern,
                    OperatorId.endsWith,
                    OperatorId.regexp,
                    OperatorId.between,
                    OperatorId.betweenInclusive,
                    OperatorId.greaterThan
                ).opt
            })

        val fSum_SimpleType =
            SimpleType.create(new SimpleTypeProps {
                inheritsFrom = "float"
                name = "fSum_SimpleType"
                validOperators = Seq(
                    OperatorId.contains,
                    OperatorId.isNull,
                    OperatorId.notInSet,
                    OperatorId.notStartsWith,
                    OperatorId.lessOrEqual,
                    OperatorId.notEndsWith,
                    OperatorId.containsPattern,
                    OperatorId.equalsField,
                    OperatorId.greaterOrEqual,
                    OperatorId.startsWith,
                    OperatorId.greaterOrEqualField,
                    OperatorId.lessOrEqualField,
                    OperatorId.notContains,
                    OperatorId.inSet,
                    OperatorId.lessThan,
                    OperatorId.greaterThanField,
                    OperatorId.notEqualField,
                    OperatorId.notEqual,
                    OperatorId.equals,
                    OperatorId.lessThanField,
                    OperatorId.notNull,
                    OperatorId.matchesPattern,
                    OperatorId.endsWith,
                    OperatorId.regexp,
                    OperatorId.between,
                    OperatorId.betweenInclusive,
                    OperatorId.greaterThan
                ).opt
            })

        val id_SimpleType =
            SimpleType.create(new SimpleTypeProps {
                inheritsFrom = "integer"
                name = "id_SimpleType"
                validOperators = Seq(
                    OperatorId.isNull,
                    OperatorId.notInSet,
                    OperatorId.equalsField,
                    OperatorId.inSet,
                    OperatorId.notEqualField,
                    OperatorId.notEqual,
                    OperatorId.equals,
                    OperatorId.notNull
                ).opt
            })

        val nInt_SimpleType =
            SimpleType.create(new SimpleTypeProps {
                inheritsFrom = "integer"
                name = "nInt_SimpleType"
                validOperators = Seq(
                    OperatorId.contains,
                    OperatorId.isNull,
                    OperatorId.notInSet,
                    OperatorId.notStartsWith,
                    OperatorId.lessOrEqual,
                    OperatorId.notEndsWith,
                    OperatorId.containsPattern,
                    OperatorId.equalsField,
                    OperatorId.greaterOrEqual,
                    OperatorId.startsWith,
                    OperatorId.greaterOrEqualField,
                    OperatorId.lessOrEqualField,
                    OperatorId.notContains,
                    OperatorId.inSet,
                    OperatorId.lessThan,
                    OperatorId.greaterThanField,
                    OperatorId.notEqualField,
                    OperatorId.notEqual,
                    OperatorId.equals,
                    OperatorId.lessThanField,
                    OperatorId.notNull,
                    OperatorId.matchesPattern,
                    OperatorId.endsWith,
                    OperatorId.regexp,
                    OperatorId.between,
                    OperatorId.betweenInclusive,
                    OperatorId.greaterThan
                ).opt
            })

        val sAddress_SimpleType =
            SimpleType.create(new SimpleTypeProps {
                inheritsFrom = "text"
                name = "sAddress_SimpleType"
                validOperators = Seq(
                    OperatorId.contains,
                    OperatorId.isNull,
                    OperatorId.notInSet,
                    OperatorId.iContains,
                    OperatorId.startsWith,
                    OperatorId.inSet,
                    OperatorId.iNotEqual,
                    OperatorId.notEqual,
                    OperatorId.equals,
                    OperatorId.notNull,
                    OperatorId.endsWith,
                    OperatorId.iEndsWith,
                    OperatorId.iStartsWith,
                    OperatorId.iEquals
                ).opt
            })

        val sBarCode_SimpleType =
            SimpleType.create(new SimpleTypeProps {
                inheritsFrom = "text"
                name = "sBarCode_SimpleType"
                validOperators = Seq(
                    OperatorId.contains,
                    OperatorId.isNull,
                    OperatorId.notInSet,
                    OperatorId.iContains,
                    OperatorId.startsWith,
                    OperatorId.inSet,
                    OperatorId.iNotEqual,
                    OperatorId.notEqual,
                    OperatorId.equals,
                    OperatorId.notNull,
                    OperatorId.endsWith,
                    OperatorId.iEndsWith,
                    OperatorId.iStartsWith,
                    OperatorId.iEquals
                ).opt
            })

        val sCaption_SimpleType =
            SimpleType.create(new SimpleTypeProps {
                inheritsFrom = "text"
                name = "sCaption_SimpleType"
                validOperators = Seq(
                    OperatorId.contains,
                    OperatorId.isNull,
                    OperatorId.notInSet,
                    OperatorId.iContains,
                    OperatorId.startsWith,
                    OperatorId.inSet,
                    OperatorId.iNotEqual,
                    OperatorId.notEqual,
                    OperatorId.equals,
                    OperatorId.notNull,
                    OperatorId.endsWith,
                    OperatorId.iEndsWith,
                    OperatorId.iStartsWith,
                    OperatorId.iEquals
                ).opt
            })

        val sCode_SimpleType =
            SimpleType.create(new SimpleTypeProps {
                inheritsFrom = "text"
                name = "sCode_SimpleType"
                validOperators = Seq(
                    OperatorId.contains,
                    OperatorId.isNull,
                    OperatorId.notInSet,
                    OperatorId.iContains,
                    OperatorId.startsWith,
                    OperatorId.inSet,
                    OperatorId.iNotEqual,
                    OperatorId.notEqual,
                    OperatorId.equals,
                    OperatorId.notNull,
                    OperatorId.endsWith,
                    OperatorId.iEndsWith,
                    OperatorId.iStartsWith,
                    OperatorId.iEquals
                ).opt
            })

        val sDescription_SimpleType =
            SimpleType.create(new SimpleTypeProps {
                inheritsFrom = "textArea"
                name = "sDescription_SimpleType"
                validOperators = Seq(
                    OperatorId.contains,
                    OperatorId.isNull,
                    OperatorId.notInSet,
                    OperatorId.iContains,
                    OperatorId.startsWith,
                    OperatorId.inSet,
                    OperatorId.iNotEqual,
                    OperatorId.notEqual,
                    OperatorId.equals,
                    OperatorId.notNull,
                    OperatorId.endsWith,
                    OperatorId.iEndsWith,
                    OperatorId.iStartsWith,
                    OperatorId.iEquals
                ).opt
            })

        val sEMail_SimpleType =
            SimpleType.create(new SimpleTypeProps {
                inheritsFrom = "text"
                name = "sEMail_SimpleType"
                validOperators = Seq(
                    OperatorId.contains,
                    OperatorId.isNull,
                    OperatorId.notInSet,
                    OperatorId.iContains,
                    OperatorId.startsWith,
                    OperatorId.inSet,
                    OperatorId.iNotEqual,
                    OperatorId.notEqual,
                    OperatorId.equals,
                    OperatorId.notNull,
                    OperatorId.endsWith,
                    OperatorId.iEndsWith,
                    OperatorId.iStartsWith,
                    OperatorId.iEquals
                ).opt
            })

        val sPasswordHashSHA_SimpleType =
            SimpleType.create(new SimpleTypeProps {
                inheritsFrom = "password"
                name = "sPasswordHashSHA_SimpleType"
                validOperators = Seq(
                    OperatorId.contains,
                    OperatorId.isNull,
                    OperatorId.notInSet,
                    OperatorId.iContains,
                    OperatorId.startsWith,
                    OperatorId.inSet,
                    OperatorId.iNotEqual,
                    OperatorId.notEqual,
                    OperatorId.equals,
                    OperatorId.notNull,
                    OperatorId.endsWith,
                    OperatorId.iEndsWith,
                    OperatorId.iStartsWith,
                    OperatorId.iEquals
                ).opt
            })

        val sPasswordPlain_SimpleType =
            SimpleType.create(new SimpleTypeProps {
                inheritsFrom = "password"
                name = "sPasswordPlain_SimpleType"
                validOperators = Seq(
                    OperatorId.contains,
                    OperatorId.isNull,
                    OperatorId.notInSet,
                    OperatorId.iContains,
                    OperatorId.startsWith,
                    OperatorId.inSet,
                    OperatorId.iNotEqual,
                    OperatorId.notEqual,
                    OperatorId.equals,
                    OperatorId.notNull,
                    OperatorId.endsWith,
                    OperatorId.iEndsWith,
                    OperatorId.iStartsWith,
                    OperatorId.iEquals
                ).opt
            })

        val sPhone_SimpleType =
            SimpleType.create(new SimpleTypeProps {
                inheritsFrom = "text"
                name = "sPhone_SimpleType"
                validOperators = Seq(
                    OperatorId.contains,
                    OperatorId.isNull,
                    OperatorId.notInSet,
                    OperatorId.iContains,
                    OperatorId.startsWith,
                    OperatorId.inSet,
                    OperatorId.iNotEqual,
                    OperatorId.notEqual,
                    OperatorId.equals,
                    OperatorId.notNull,
                    OperatorId.endsWith,
                    OperatorId.iEndsWith,
                    OperatorId.iStartsWith,
                    OperatorId.iEquals
                ).opt
            })

        val sPostalIndex_SimpleType =
            SimpleType.create(new SimpleTypeProps {
                inheritsFrom = "text"
                name = "sPostalIndex_SimpleType"
                validOperators = Seq(
                    OperatorId.contains,
                    OperatorId.isNull,
                    OperatorId.notInSet,
                    OperatorId.iContains,
                    OperatorId.startsWith,
                    OperatorId.inSet,
                    OperatorId.iNotEqual,
                    OperatorId.notEqual,
                    OperatorId.equals,
                    OperatorId.notNull,
                    OperatorId.endsWith,
                    OperatorId.iEndsWith,
                    OperatorId.iStartsWith,
                    OperatorId.iEquals
                ).opt
            })

        val sURL_SimpleType =
            SimpleType.create(new SimpleTypeProps {
                inheritsFrom = "link"
                name = "sURL_SimpleType"
                validOperators = Seq(
                    OperatorId.contains,
                    OperatorId.isNull,
                    OperatorId.notInSet,
                    OperatorId.iContains,
                    OperatorId.startsWith,
                    OperatorId.inSet,
                    OperatorId.iNotEqual,
                    OperatorId.notEqual,
                    OperatorId.equals,
                    OperatorId.notNull,
                    OperatorId.endsWith,
                    OperatorId.iEndsWith,
                    OperatorId.iStartsWith,
                    OperatorId.iEquals
                ).opt
            })

        val sURLDomain_SimpleType =
            SimpleType.create(new SimpleTypeProps {
                inheritsFrom = "text"
                name = "sURLDomain_SimpleType"
                validOperators = Seq(
                    OperatorId.contains,
                    OperatorId.isNull,
                    OperatorId.notInSet,
                    OperatorId.iContains,
                    OperatorId.startsWith,
                    OperatorId.inSet,
                    OperatorId.iNotEqual,
                    OperatorId.notEqual,
                    OperatorId.equals,
                    OperatorId.notNull,
                    OperatorId.endsWith,
                    OperatorId.iEndsWith,
                    OperatorId.iStartsWith,
                    OperatorId.iEquals
                ).opt
            })

        val sURLImage_SimpleType =
            SimpleType.create(new SimpleTypeProps {
                inheritsFrom = "image"
                name = "sURLImage_SimpleType"
                validOperators = Seq(
                    OperatorId.contains,
                    OperatorId.isNull,
                    OperatorId.notInSet,
                    OperatorId.iContains,
                    OperatorId.startsWith,
                    OperatorId.inSet,
                    OperatorId.iNotEqual,
                    OperatorId.notEqual,
                    OperatorId.equals,
                    OperatorId.notNull,
                    OperatorId.endsWith,
                    OperatorId.iEndsWith,
                    OperatorId.iStartsWith,
                    OperatorId.iEquals
                ).opt
            })

        val ss_SimpleType =
            SimpleType.create(new SimpleTypeProps {
                inheritsFrom = "text"
                name = "ss_SimpleType"
                validOperators = Seq(
                    OperatorId.isNull,
                    OperatorId.notInSet,
                    OperatorId.equalsField,
                    OperatorId.inSet,
                    OperatorId.notEqualField,
                    OperatorId.notEqual,
                    OperatorId.equals,
                    OperatorId.notNull
                ).opt
            })
    }

}
