package com.simplesys.container.upload.props

import com.simplesys.SmartClient.DataBinding.props.{DSRequestProps, DataSourceSSProps, RestDataSourceProps}
import com.simplesys.SmartClient.DataBinding.props.dataSource.{DataSourceFieldProps, OperationBindingProps}
import com.simplesys.SmartClient.DataBinding.{DSRequest, DSResponse}
import com.simplesys.SmartClient.Forms.DynamicFormSS
import com.simplesys.SmartClient.Forms.formsItems.props._
import com.simplesys.SmartClient.Forms.formsItems.{FormItem, UploadItem}
import com.simplesys.SmartClient.Forms.props.{DynamicFormProps, DynamicFormSSProps}
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
                    //colWidths = Seq[JSAny]("50%", "*").opt
                    action = "UploadServlet".opt
                    //target = "hidden_frame".opt
                    encoding = Encoding.multipart.opt
                    /*dataSource = RestDataSource.create(
                        new RestDataSourceProps {
                            //serverType = DSServerType.sql.opt
                            addDataURL = "UploadServlet".opt
                            fields = Seq(
                                new DataSourceFieldProps {
                                    name = "file".opt
                                    showFileInline = true.opt
                                    `type` = FieldType.binary
                                }
                            ).opt
                            operationBindings = Seq(
                                new OperationBindingProps {
                                    dataFormat = DSDataFormat.json.opt
                                    dataProtocol = DSProtocol.postXML.opt
                                    operationType = DSOperationType.add.opt
                                }
                            ).opt
                        }
                    ).opt*/
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
                        ), SubmitItem(
                            new SubmitItemProps {
                                disabled = true.opt
                                //colSpan = 2
                                title = "Upload".ellipsis.opt
                                name = "upload".opt
                                /*click = {
                                    (form: DynamicFormSS, item: FormItem) ⇒
                                        form.submitForm()
                                        false
                                }.toFunc.opt*/
                            }
                        ),
                        ProgressbarItem(
                            new ProgressbarItemProps {
                                //colSpan = 2
                                showTitle = false.opt
                                title = "Процесс выгрузки".ellipsis.opt
                            }
                        ),
                        HiddenItem(
                            new HiddenItemProps {
                                name = "hidden_frame".opt
                            }
                        )
                    ).opt
                }
            )

            thiz addMember member

    }.toThisFunc.opt
}
