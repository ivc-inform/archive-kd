package com.simplesys.SmartClient.System


import com.simplesys.System.Types.{URL, void}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@js.native
trait URIBuilder extends Class {
    def init(): void
    def appendPath(path: String): void
    def containsQueryParam(name: String): Boolean
    def getQueryValue[T](paramName: String, firstOnly: Boolean): T
    def appendQueryParam[T](name: String, value: T): void
    def setQueryParam[T](name: String, value: T): void
}

@js.native
abstract trait AbstractURIBuilderCompanion extends AbstractClassCompanion {
    def create(uri: URL): URIBuilder = js.native
}

@js.native
@JSGlobal("URIBuilder")
object URIBuilderStatic extends AbstractURIBuilderCompanion
