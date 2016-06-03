package com.simplesys.SmartClient.Layout.portalLayout

import com.simplesys.SmartClient.Control.Dialog
import com.simplesys.SmartClient.Foundation.AbstractCanvasCompanion
import com.simplesys.SmartClient.Layout.{PortalLayout, Window}
import com.simplesys.System.Types.{void, SCClassName}

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait Portlet extends Window {
    var closeConfirmationDialogProperties: Dialog
    var closeConfirmationMessage: String
    var destroyOnClose: Boolean
    def getPortalLayout(): PortalLayout
    def getPortalPosition(): PortalLayout
    var rowHeight: Int | String
    def setRowHeight(height: Int): void
    var showCloseConfirmationMessage: Boolean
}



