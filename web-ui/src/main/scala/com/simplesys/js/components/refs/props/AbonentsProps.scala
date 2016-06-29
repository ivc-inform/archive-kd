package com.simplesys.js.components.refs.props

import com.simplesys.SmartClient.App.formItems.props.LookupListGridEditorItemProps
import com.simplesys.SmartClient.App.props.CommonListGridEditorComponentProps
import com.simplesys.SmartClient.Forms.FormsItems.props._
import com.simplesys.SmartClient.Grids.props.listGrid.ListGridFieldProps
import com.simplesys.SmartClient.Layout.props.WindowSSProps
import com.simplesys.SmartClient.System._
import com.simplesys.System.Types.{FormItemComponentType, SelectionAppearance, SelectionStyle, SelectionType}
import com.simplesys.app.{AbonentsOrg, AbonentsTypes}
import com.simplesys.js.components.refs.Abonents
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen._

class AbonentsProps extends CommonListGridEditorComponentProps with Implicits {
    override val simpleTable = false

    type classHandler <: Abonents

    val abonentOrgEditor = AbonentsOrg.create(new AbonentsOrgProps)
    val abonentTypeEditor = AbonentsTypes.create(new AbonentsTypesProps)

    val abonentOrgFilterEditor = AbonentsOrg.create(
        new AbonentsOrgProps {
            selectionAppearance = SelectionAppearance.checkbox.opt
            selectionType = SelectionStyle.multiple.opt
            identifier = "7016FFF2-D154-AE84-B63F-CCA5A7D07650".opt
        }
    )
    val abonentTypeFilterEditor = AbonentsTypes.create(
        new AbonentsTypesProps {
            selectionAppearance = SelectionAppearance.checkbox.opt
            selectionType = SelectionStyle.multiple.opt
            identifier = "0CCA2A45-93E8-E019-5AC7-7C154EB602E2".opt
        }
    )

    dataSource = DataSourcesJS.eakd_abonents_DS.opt

    fields = ListGridFiledsJS.eakd_abonents_FLDS.opt
    editingFields = FormItemsJS.eakd_abonents_FRMITM.opt

    replacingFields = Seq(
        new ListGridFieldProps {
            nameStrong = eakd_abonents_vabontype_NameStrong.opt
            filterEditorType = FormItemComponentType.LookupListGridEditorItem
            filterEditorProperties = LookupListGridEditorItem(new LookupListGridEditorItemProps {
                listGridEditor = abonentTypeFilterEditor.opt
            }).opt
            editorType = FormItemComponentType.LookupListGridEditorItem
            editorProperties = LookupListGridEditorItem(new LookupListGridEditorItemProps {
                listGridEditor = abonentTypeEditor.opt
            }).opt
        },
        new ListGridFieldProps {
            nameStrong = eakd_abonents_orgcode_NameStrong.opt
            filterEditorType = FormItemComponentType.LookupListGridEditorItem
            filterEditorProperties = LookupListGridEditorItem(new LookupListGridEditorItemProps {
                listGridEditor = abonentOrgFilterEditor.opt
            }).opt
            editorType = FormItemComponentType.LookupListGridEditorItem
            editorProperties = LookupListGridEditorItem(new LookupListGridEditorItemProps {
                listGridEditor = abonentOrgEditor.opt
            }).opt
        },
        new ListGridFieldProps {
            nameStrong = eakd_abonents_orgname_NameStrong.opt
            filterEditorType = FormItemComponentType.LookupListGridEditorItem
            filterEditorProperties = LookupListGridEditorItem(new LookupListGridEditorItemProps {
                listGridEditor = abonentOrgFilterEditor.opt
            }).opt
            editorType = FormItemComponentType.LookupListGridEditorItem
            editorProperties = LookupListGridEditorItem(new LookupListGridEditorItemProps {
                listGridEditor = abonentOrgEditor.opt
            }).opt
        }
    ).opt

    identifier = "15EC1A89-2233-358F-1186-372AF0FD1DC2".opt

    editWindowProperties = WindowSS(
        new WindowSSProps {
            width = 285
            height = 425
        }
    ).opt
}
