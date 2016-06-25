package com.simplesys.js.components.refs.props

import com.simplesys.SmartClient.App.formItems.props.LookupListGridEditorItemProps
import com.simplesys.SmartClient.App.props.CommonListGridEditorComponentProps
import com.simplesys.SmartClient.Forms.FormsItems.props._
import com.simplesys.SmartClient.Grids.props.listGrid.ListGridFieldProps
import com.simplesys.SmartClient.Layout.props.WindowSSProps
import com.simplesys.SmartClient.System._
import com.simplesys.System.Types.FormItemComponentType
import com.simplesys.app.{AbonentsOrg, AbonentsTypes}
import com.simplesys.js.components.refs.Abonents
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen._

class AbonentsProps extends CommonListGridEditorComponentProps with Implicits {
    override val simpleTable = false

    type classHandler <: Abonents

    val abonentOrg = AbonentsOrg.create(new AbonentsOrgProps)
    val abonentType = AbonentsTypes.create(new AbonentsTypesProps)

    val orgCodeItem = LookupListGridEditorItem(new LookupListGridEditorItemProps {
        nameStrong = eakd_abonents_orgcode_NameStrong.opt
        listGridEditor = abonentOrg.opt
    })

    val orgNameItem = LookupListGridEditorItem(new LookupListGridEditorItemProps {
        nameStrong = eakd_abonents_orgname_NameStrong.opt
        listGridEditor = abonentOrg.opt
    })

    val abonTypeItem = LookupListGridEditorItem(new LookupListGridEditorItemProps {
        nameStrong = eakd_abonents_vabontype_NameStrong.opt
        listGridEditor = abonentType.opt
    })

    dataSource = DataSourcesJS.eakd_abonents_DS.opt
    fields = ListGridFiledsJS.eakd_abonents_FLDS.opt

    identifier = "15EC1A89-2233-358F-1186-372AF0FD1DC2".opt
    editingFields = FormItemsJS.eakd_abonents_FRMITM.opt

    replacingEditingFields = Seq(
            orgCodeItem,
            orgNameItem,
            abonTypeItem
        ).opt

    replacingFields = Seq(
        new ListGridFieldProps {
            nameStrong = eakd_abonents_vabontype_NameStrong.opt
            editorType = FormItemComponentType.LookupListGridEditorItem
            editorProperties = abonTypeItem.opt
        },
        new ListGridFieldProps {
            nameStrong = eakd_abonents_orgcode_NameStrong.opt
            editorType = FormItemComponentType.LookupListGridEditorItem
            editorProperties = orgCodeItem.opt
        },
        new ListGridFieldProps {
            nameStrong = eakd_abonents_orgname_NameStrong.opt
            editorType = FormItemComponentType.LookupListGridEditorItem
            editorProperties = orgNameItem.opt
        }
    ).opt

    editWindowProperties = WindowSS(
        new WindowSSProps {
            width = 285
            height = 425
        }
    ).opt
}
