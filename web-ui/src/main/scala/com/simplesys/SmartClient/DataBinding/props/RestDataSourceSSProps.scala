package com.simplesys.SmartClient.DataBinding.props

import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js._

class RestDataSourceSSProps extends RestDataSourceProps {
    var fullClassName: ScOption[String] = ScNone
    var lobName: ScOption[String] = ScNone
}
