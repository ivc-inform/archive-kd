package com.simplesys.SmartClient.Forms.FormsItems

import com.simplesys.SmartClient.DataBinding.Criterion
import com.simplesys.SmartClient.Forms.DateRangeDialog
import com.simplesys.SmartClient.Forms.FormsItems.formItem.FormItemIcon
import com.simplesys.SmartClient.System.DateRange.DateRange
import com.simplesys.SmartClient.System.date.Date
import com.simplesys.System.Types.DateDisplayFormat.DateDisplayFormat
import com.simplesys.System.Types.{AutoChild, RelativeDateString, void}
import com.simplesys.System.Types.TimeUnit.TimeUnit

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait MiniDateRangeItem extends StaticTextItem {
    val allowRelativeDates: Boolean
    var autoValidate: Boolean
    val dateDisplayFormat: DateDisplayFormat
    var fromDate: Date | RelativeDateString | TimeUnit
    val fromDateOnlyPrefix: String
    def getCriterion(): Criterion
    def getValue(): DateRange
    val pickerIcon: FormItemIcon
    val rangeDialog: DateRangeDialog with AutoChild
    def setAutoValidate(autoValidate: Boolean): void
    def setValue(value: DateRange): void
    var toDate: Date | RelativeDateString | TimeUnit
    val toDateOnlyPrefix: String
}


