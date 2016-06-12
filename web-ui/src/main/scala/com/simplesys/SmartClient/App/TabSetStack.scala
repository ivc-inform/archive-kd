package com.simplesys.SmartClient.App

import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Layout.TabSetSS
import com.simplesys.SmartClient.System.isc

trait TabSetStack {
    protected val tabSet: TabSetSS

    def addTab(canvas: Canvas): Unit = {
       if (canvas.identifier.isEmpty)
           isc.error(s"Компонент ${canvas.getIdentifier()} не имеет постоянного identifier, поэтому не может быть добавлен.")
        else {
       }
    }
}
