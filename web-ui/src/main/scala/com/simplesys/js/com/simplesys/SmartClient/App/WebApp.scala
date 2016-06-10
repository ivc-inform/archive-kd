package com.simplesys.SmartClient.App

import com.simplesys.SmartClient.DataBinding.props.dataSource.DataSourceFieldProps
import com.simplesys.SmartClient.DataBinding.props.{DataSourceProps, DataViewProps}
import com.simplesys.SmartClient.DataBinding.{DataSource, DataSourceSSstatic}
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Grids.props.ListGridEditorProps
import com.simplesys.SmartClient.Grids.props.listGrid.ListGridFieldProps
import com.simplesys.SmartClient.System._
import com.simplesys.SmartClient.Tools.WindowsStack
import com.simplesys.System.Types.Skin.Skin
import com.simplesys.System.Types._
import com.simplesys.System._
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._

import scala.scalajs.js.annotation.JSExport

trait WebApp {

    protected val windowsStack = new WindowsStack

    val loadSchemas: Boolean
    val identifier: ID

    //Можно при наследование объявлять как lazy val
    protected def mainCanvas: Canvas

    @JSExport
    def getUIContent() {
        Page.setEvent(
            PageEvent.load, { (target: JSObject) =>

                isc.params.locale = "ru_RU"

                val skin: Skin = simpleSyS.skin.toOption match {
                    case Some(skin) => skin
                    case None => isc.OfflineSS.get(s"Skin$identifier", Skin.Enterprise)
                }

                Page setAppImgDir "managed/images/common-webapp/app/"

                FileLoader.loadSkin(
                    skin, {
                        () =>
                            var localeFile = "isomorphic/locales/frameworkMessages.properties"
                            if (isc.params.locale != "en")
                                localeFile = "isomorphic/locales/frameworkMessages_" + isc.params.locale + ".properties"

                            FileLoader.loadJSFiles(localeFile, {
                                () =>
                                    if (loadSchemas)
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
                                    else
                                        DataView.create(
                                            new DataViewProps {
                                                height = "100%"
                                                width = "100%"
                                                members = Seq(
                                                    mainCanvas
                                                ).opt
                                            }
                                        )
                            })
                    }
                )
            }
        )
    }

    private lazy val aboutDS: DataSource =
        DataSource.create(
            new DataSourceProps {
                clientOnly = true.opt
                dataFormat = DSDataFormat.json.opt
                cacheData = simpleSyS.aboutData.seq.opt
                fields = Seq(
                    new DataSourceFieldProps {
                        required = true.opt
                        `type` = FieldType.sCode_SimpleType.opt
                        title = "\u041D\u0430\u0438\u043C\u0435\u043D\u043E\u0432\u0430\u043D\u0438\u0435".opt
                        primaryKey = true.opt
                        name = "libName".opt
                    },
                    new DataSourceFieldProps {
                        required = true.opt
                        `type` = FieldType.sCode_SimpleType.opt
                        title = "\u0412\u0435\u0440\u0441\u0438\u044F".opt
                        primaryKey = true.opt
                        name = "libVersion".opt
                    }
                ).opt
            }
        )

    protected def getAbout(): Unit = {
        //isc debugTrap simpleSyS.aboutData
        aboutDS setCacheData simpleSyS.aboutData

        isc.infos(
            ListGridEditor(
                new ListGridEditorProps {
                    identifier = "544C01DA-5F30-0126-8546-00F31AC35541".opt
                    autoFetchData = true.opt
                    dataSource = aboutDS.opt
                    fields = Seq(
                        new ListGridFieldProps {
                            name = "libName".opt
                        },
                        new ListGridFieldProps {
                            name = "libVersion".opt
                            align = Alignment.center.opt
                        }
                    ).opt
                }
            ),
            "544C01DA-5F30-0126-8546-00F31AC36341"
        )
    }

    protected def getSetting(): Unit = {

    }
}
