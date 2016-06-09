package com.simplesys.SmartClient.Layout.props

import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.JSAny
import com.simplesys.option.ScOption._

class RibbonGroupSSProps extends RibbonGroupProps {
    numRows = 3.opt
    rowHeight = 26.opt
    colWidths  = IscArray[JSAny](40, "*").opt
}
