package com.simplesys.js.components.cards.props

import com.simplesys.SmartClient.App.props.CommonListGridEditorComponentProps
import com.simplesys.SmartClient.Grids.props.listGrid.ListGridFieldProps
import com.simplesys.System.Types.ListGridFieldType
import com.simplesys.js.components.cards.Attach
import com.simplesys.option.ScOption._
import com.simplesys.option.DoubleType._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen._
import com.simplesys.System._
import com.simplesys.SmartClient.System._

class AttachProps extends CommonListGridEditorComponentProps {
    type classHandler <: Attach

    simpleTable = false.opt
    //autoFetchData = false.opt

    dataSource = DataSourcesJS.arx_attatch_DS.opt
    identifier = "12DC1876-F489-3172-EE97-729FFB73B575".opt

    editingFields = FormItemsJS.arx_attatch_FRMITM.opt

    replacingFields = Seq(
        new ListGridFieldProps {
            nameStrong = arx_attatch_vcrcode_NameStrong.opt
            hidden = true.opt
            `type` = ListGridFieldType.sCode_SimpleType.opt
        }).opt

    fields = (ListGridFiledsJS.arx_attatch_FLDS ++ Seq(
        new ListGridFieldProps {
            nameStrong = "uploadFile".nameStrongOpt
            title = "Файл ревизии".ellipsis.opt
            `type` = ListGridFieldType.sCode_SimpleType.opt
        },
        new ListGridFieldProps {
            nameStrong = "fileName".nameStrongOpt
            title = "Файл ревизии".opt
            `type` = ListGridFieldType.sCode_SimpleType.opt
        },
        new ListGridFieldProps {
            nameStrong = arx_docizvstat_vname_NameStrong.opt
            title = "Статус извещения".opt
            `type` = ListGridFieldType.sCaption_SimpleType.opt
        },
        new ListGridFieldProps {
            title = "Тип извещения".opt
            nameStrong = arx_docizvtype_vizvname_NameStrong.opt
            `type` = ListGridFieldType.sCaption_SimpleType.opt
        }
    )).opt

    height = 250
}
