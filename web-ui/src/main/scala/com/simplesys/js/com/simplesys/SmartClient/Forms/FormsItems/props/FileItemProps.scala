package com.simplesys.SmartClient.Forms.FormsItems.props

import com.simplesys.SmartClient.Forms.DynamicForm
import com.simplesys.SmartClient.Forms.FormsItems.{StaticTextItem, UploadItem}
import com.simplesys.System.Types.{FormItemComponentType, SCImgURL}
import com.simplesys.option.{ScNone, ScOption}

class FileItemProps extends CanvasItemProps {
    var accept: ScOption[String] = ScNone
    var displayForm: ScOption[DynamicForm] = ScNone
    var displayItem: ScOption[StaticTextItem] = ScNone
    var downloadIconSrc: ScOption[SCImgURL] = ScNone
    var editForm: ScOption[DynamicForm] = ScNone
    var showFileInline: ScOption[Boolean] = ScNone
    var uploadItem: ScOption[UploadItem] = ScNone
    var viewIconSrc: ScOption[SCImgURL] = ScNone
    `type` = FormItemComponentType.FileItem
}
