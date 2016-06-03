
package com.simplesys.SmartClient.Layout.props

import com.simplesys.SmartClient.Layout.TabSetSS
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js

class TabSetSSProps extends TabSetProps {
    type classHandler <: TabSetSS

    var tabsReordered: ScOption[js.ThisFunction0[classHandler, _]] = ScNone
}
