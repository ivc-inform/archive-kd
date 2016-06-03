package com.simplesys.js.com.simplesys.SmartClient.App

import com.simplesys.SmartClient.DataBinding.DataSourceSSstatic
import com.simplesys.SmartClient.DataBinding.props.DataViewProps
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.System.{Canvas, DataView, FileLoader, Page, isc}
import com.simplesys.System.Types.{PageEvent, Skin}
import com.simplesys.System._
import com.simplesys.js.com.simplesys.SmartClient.Tools.WindowsStack

import scala.scalajs.js.annotation.JSExport
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._

trait WebApp {

    protected val windowsStack = new WindowsStack

    //Можно при наследование объявлять как lazy val
    protected def mainCanvas: Canvas

    @JSExport
    def getUIContent() {
        Page.setEvent(
            PageEvent.load, { (target: JSObject) =>

                isc.params.locale = "ru_RU"

                val skin = Skin.Enterprise

                Page setAppImgDir "managed/images/common-webapp/app/"

                FileLoader.loadSkin(
                    skin, {
                        () =>
                            var localeFile = "isomorphic/locales/frameworkMessages.properties"
                            if (isc.params.locale != "en")
                                localeFile = "isomorphic/locales/frameworkMessages_" + isc.params.locale + ".properties"

                            FileLoader.loadJSFiles(localeFile, {
                                () =>
                                    DataSourceSSstatic.loadComponentSchemas(
                                        () =>
                                            DataView.create(
                                                new DataViewProps {
                                                    height = "100%"
                                                    width = "100%"
                                                    members = Seq(
                                                        mainCanvas
                                                    ).opt
                                                }
                                            )
                                    )
                            })
                    }
                )
            }
        )
    }
}
