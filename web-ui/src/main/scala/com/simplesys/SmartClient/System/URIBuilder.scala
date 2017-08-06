package com.simplesys.SmartClient.System


import com.simplesys.System.Types.URL

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@js.native
trait URIBuilder extends Class {
}

@js.native
abstract trait AbstractURIBuilderCompanion extends AbstractClassCompanion {
    def create(uri: URL): URIBuilder = js.native
}

@js.native
@JSGlobal("URIBuilder")
object URIBuilderStatic extends AbstractURIBuilderCompanion
