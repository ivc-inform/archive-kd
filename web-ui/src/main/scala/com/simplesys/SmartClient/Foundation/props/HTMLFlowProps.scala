package com.simplesys.SmartClient.Foundation.props

import com.simplesys.SmartClient.DataBinding.props.DataBoundComponentProps
import com.simplesys.SmartClient.Foundation.HTMLFlow
import com.simplesys.SmartClient.RPC.{RPCRequest, RPCResponse}
import com.simplesys.System.JSObject
import com.simplesys.System.Types.ContentsType.ContentsType
import com.simplesys.System.Types.SendMethod.SendMethod
import com.simplesys.System.Types.{HTMLString, URL}
import com.simplesys.option.{ScNone, ScOption}
import org.scalajs.dom.raw.HTMLElement

import scala.scalajs.js

class HTMLFlowProps extends CanvasProps with DataBoundComponentProps{
    type classHandler <: HTMLFlow

    var allowCaching: ScOption[Boolean] = ScNone
    var captureSCComponents: ScOption[Boolean] = ScNone
    var contentLoaded: ScOption[js.ThisFunction0[classHandler, _]] = ScNone
    var contentsType: ScOption[ContentsType] = ScNone
    var contentsURL: ScOption[URL] = ScNone
    var contentsURLParams: ScOption[JSObject] = ScNone
    var evalScriptBlocks: ScOption[Boolean] = ScNone
    var handleError: ScOption[js.Function2[RPCRequest, RPCResponse, Boolean]] = ScNone
    var httpMethod: ScOption[SendMethod] = ScNone
    var loadingMessage: ScOption[HTMLString] = ScNone
    var selectContentOnSelectAll: ScOption[Boolean] = ScNone
    var transformHTML: ScOption[js.Function1[HTMLElement, _]] = ScNone
}
