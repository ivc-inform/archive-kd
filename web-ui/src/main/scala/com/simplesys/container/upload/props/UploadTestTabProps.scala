package com.simplesys.container.upload.props

import com.simplesys.SmartClient.DataBinding.props.DataSourceSSProps
import com.simplesys.SmartClient.DataBinding.props.dataSource.DataSourceFieldProps
import com.simplesys.SmartClient.DataBinding.{DSRequest, DSResponse}
import com.simplesys.SmartClient.Forms.DynamicFormSS
import com.simplesys.SmartClient.Forms.formsItems.props.{ProgressbarItemProps, SubmitItemProps, UploadItemProps}
import com.simplesys.SmartClient.Forms.formsItems.{FormItem, UploadItem}
import com.simplesys.SmartClient.Forms.props.DynamicFormSSProps
import com.simplesys.SmartClient.Layout.props.HLayoutProps
import com.simplesys.SmartClient.System._
import com.simplesys.System.Types.FormItemComponentType
import com.simplesys.System._
import com.simplesys.container.upload.UploadTestTab
import com.simplesys.function._
import com.simplesys.option.ScOption._
import com.simplesys.option.DoubleType._

class UploadTestTabProps extends HLayoutProps {
    type classHandler <: UploadTestTab

    identifier = "69EC6EB4-E51F-B7A9-C1E0-CF216088816AF".opt

    initWidget = {
        (thiz: classHandler, arguments: IscArray[JSAny]) =>

            thiz.Super("initWidget", arguments)

            thiz addMember DynamicFormSS.create(
                new DynamicFormSSProps {
                    width = "100%"
                    //action = "UploadServlet".opt
                    //encoding = Encoding.multipart.opt
                    dataSource = DataSourceSS.create(
                        new DataSourceSSProps {
                            fields = Seq(
                                new DataSourceFieldProps {
                                    name = "file".opt
                                    showFileInline = true.opt
                                    `type` = FormItemComponentType.FileItem
                                }
                            ).opt
                        }
                    ).opt
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
                                title = "Upload".opt
                                nameStrong = "upload".nameStrongOpt
                                click = {
                                    (form: DynamicFormSS, item: FormItem) ⇒
                                        form.saveData(
                                            {
                                                (response: DSResponse, obj: JSObject, request: DSRequest) ⇒
                                                    if (response.status == DSResponse.STATUS_SUCCESS)
                                                        isc ok "OK"
                                            }.toFunc
                                        )
                                        false
                                }.toFunc.opt
                            }
                        ),
                        ProgressbarItem(
                            new ProgressbarItemProps {
                                title = "Процесс выгрузки".ellipsis.opt
                            }
                        )
                    ).opt
                }
            )

        //isc debugTrac (thiz.getClassName(), thiz.getIdentifier())

    }.toThisFunc.opt
}
