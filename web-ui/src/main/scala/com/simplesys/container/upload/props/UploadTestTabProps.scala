package com.simplesys.container.upload.props

import com.simplesys.SmartClient.DataBinding.props.{DSRequestProps, DataSourceSSProps, RestDataSourceProps}
import com.simplesys.SmartClient.DataBinding.props.dataSource.{DataSourceFieldProps, OperationBindingProps}
import com.simplesys.SmartClient.DataBinding.{DSRequest, DSResponse}
import com.simplesys.SmartClient.Forms.DynamicFormSS
import com.simplesys.SmartClient.Forms.formsItems.props._
import com.simplesys.SmartClient.Forms.formsItems.{FormItem, UploadItem}
import com.simplesys.SmartClient.Forms.props.{DynamicFormProps, DynamicFormSSProps}
import com.simplesys.SmartClient.Foundation.props.{CanvasProps, IframeProps}
import com.simplesys.SmartClient.Layout.props.HLayoutProps
import com.simplesys.SmartClient.System._
import com.simplesys.System.Types._
import com.simplesys.System._
import com.simplesys.container.upload.UploadTestTab
import com.simplesys.function._
import com.simplesys.option.ScOption._
import com.simplesys.option.DoubleType._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.DataSourcesJS._

class UploadTestTabProps extends HLayoutProps {
    type classHandler <: UploadTestTab

    identifier = "69EC6EB4-E51F-B7A9-C1E0-CF216088816AF".opt

    initWidget = {
        (thiz: classHandler, arguments: IscArray[JSAny]) =>

            thiz.Super("initWidget", arguments)
            
            val member = DynamicFormSS.create(
                new DynamicFormSSProps {
                    width = "100%"
                    action = "UploadServlet".opt
                    target = Iframe.create(
                        new IframeProps
                    ).ID.opt
                    encoding = Encoding.multipart.opt
                    canSubmit = true.opt
                    items = Seq(
                        UploadItem(
                            new UploadItemProps {
                                multiple = false.opt
                                nameStrong = "file".nameStrongOpt
                                showTitle = false.opt
                                title = "Choose file".opt
                                changed = {
                                    (form: DynamicFormSS, item: UploadItem, value: JSUndefined[JSAny]) ⇒
                                        val submit = form getItem "upload"
                                        //value.map(_.toString.replace("C:\\fakepath\\", "")).foreach(isc ok (_))
                                        if (value.isDefined) submit.enable() else submit.disable()

                                }.toFunc.opt
                            }
                        ),
                        SubmitItem(
                            new SubmitItemProps {
                                disabled = true.opt
                                //colSpan = 2
                                title = "Upload".ellipsis.opt
                                name = "upload".opt
                            }
                        ),
                        ProgressbarItem(
                            new ProgressbarItemProps {
                                //colSpan = 2
                                showTitle = false.opt
                                title = "Процесс выгрузки".ellipsis.opt
                            }
                        )
                    ).opt
                }
            )

            thiz addMember member

    }.toThisFunc.opt
}
