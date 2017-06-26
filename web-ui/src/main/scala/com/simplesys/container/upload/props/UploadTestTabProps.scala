package com.simplesys.container.upload.props

import com.simplesys.SmartClient.Control.props.ProgressbarProps
import com.simplesys.SmartClient.Forms.DynamicFormSS
import com.simplesys.SmartClient.Forms.formsItems.{FormItem, SubmitItem}
import com.simplesys.SmartClient.Forms.formsItems.props.{SubmitItemProps, UploadItemProps}
import com.simplesys.SmartClient.Forms.props.DynamicFormSSProps
import com.simplesys.SmartClient.Layout.props.HLayoutProps
import com.simplesys.SmartClient.System._
import com.simplesys.System.Types.Encoding
import com.simplesys.System._
import com.simplesys.container.upload.UploadTestTab
import com.simplesys.function._
import com.simplesys.option.ScOption._

import scala.scalajs.js._

class UploadTestTabProps extends HLayoutProps {
    type classHandler <: UploadTestTab

    identifier = "69EC6EB4-E51F-B7A9-C1E0-CF21608516AF".opt

    initWidget = {
        (thiz: classHandler, arguments: IscArray[JSAny]) =>

            thiz.Super("initWidget", arguments)

            val form = DynamicForm.create(
                new DynamicFormSSProps {
                    action = "UploadServlet".opt
                    encoding = Encoding.multipart.opt
                    items = Seq(
                        UploadItem(
                            new UploadItemProps {
                                //showTitle = false.opt
                                title = "Choose file".opt
                                changed = {
                                    (form: DynamicFormSS, item: FormItem, value: JSUndefined[JSAny]) ⇒
                                        val submit = form getItem  1
                                        //isc debugTrap submit
                                        if (value.isDefined) submit.enable() else submit.disable()

                                }.toFunc.opt
                            }
                        ), SubmitItem(
                            new SubmitItemProps {
                                disabled = true.opt
                                title = "Upload".opt
                                click = {
                                    (form: DynamicFormSS, item: FormItem) ⇒
                                        form.submitForm()
                                        false
                                }.toFunc.opt
                            }
                        )/*,
                        Progressbar(
                            new ProgressbarProps {
                                
                            }
                        )*/
                    ).opt
                }
            )

            thiz addMember form

        //isc debugTrac (thiz.getClassName(), thiz.getIdentifier())

    }.toThisFunc.opt
}
