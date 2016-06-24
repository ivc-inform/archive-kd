package com.simplesys.js.components.refs.props

import com.simplesys.SmartClient.App.formItems.props.LookupListGridEditorItemProps
import com.simplesys.SmartClient.App.props.CommonListGridEditorComponentProps
import com.simplesys.SmartClient.Forms.FormsItems.props._
import com.simplesys.SmartClient.Grids.props.listGrid.ListGridFieldProps
import com.simplesys.SmartClient.Layout.props.WindowSSProps
import com.simplesys.SmartClient.System._
import com.simplesys.System.Types.{FormItemComponentType, ListGridFieldType}
import com.simplesys.app.{AbonentsOrg, AbonentsTypes}
import com.simplesys.js.components.refs.Abonents
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.{DataSourcesJS, ListGridFiledsJS}

class AbonentsProps extends CommonListGridEditorComponentProps with Implicits {
    override val simpleTable = false

    type classHandler <: Abonents

    val abonentOrg = AbonentsOrg.create(new AbonentsOrgProps)
    val abonentType = AbonentsTypes.create(new AbonentsTypesProps)

    val orgCodeItem = LookupListGridEditorItem(new LookupListGridEditorItemProps {
        name = "orgcode"
        listGridEditor = abonentOrg.opt
        title = "Код предприятия"
    })

    val orgNameItem = LookupListGridEditorItem(new LookupListGridEditorItemProps {
        name = "orgname"
        listGridEditor = abonentOrg.opt
        title = "Наименование предприятия"
    })

    val abonTypeItem = LookupListGridEditorItem(new LookupListGridEditorItemProps {
        name = "vabontype"
        listGridEditor = abonentType.opt
    })

    dataSource = DataSourcesJS.eakd_abonents_DS.opt
    fields = ListGridFiledsJS.eakd_abonents_FLDS.opt

    identifier = "15EC1A89-2233-358F-1186-372AF0FD1DC2".opt
    editingFields = Seq(
        TextItem(new TextItemProps {
            name = "vabcode"
        }),
        TextItem(new TextItemProps {
            name = "vabname"
        }),
        DateTimeItem(new DateTimeItemProps {
            name = "tdatein"
        }),
        TextItem(new TextItemProps {
            name = "vemail"
        }),
        TextItem(new TextItemProps {
            name = "vperson"
        }),
        TextItem(new TextItemProps {
            name = "vphone1"
        }),
        TextItem(new TextItemProps {
            name = "vphone2"
        }),
        IntegerItem(new IntegerItemProps {
            name = "idaborg"
            hidden = true.opt
        }),
        orgCodeItem,
        orgNameItem,
        IntegerItem(new IntegerItemProps {
            name = "idabtype"
            hidden = true.opt
        }),
        abonTypeItem,
        TextAreaItem(new TextAreaItemProps {
            name = "vabdesc"
        })
    ).opt

    replacingfields = Seq(
        new ListGridFieldProps {
            name = "vabontype"
            editorType = FormItemComponentType.LookupListGridEditorItem
            editorProperties = abonTypeItem.opt
        },
        new ListGridFieldProps {
            name = "orgcode"
            editorType = FormItemComponentType.LookupListGridEditorItem
            editorProperties = orgCodeItem.opt
        },
        new ListGridFieldProps {
            name = "orgname"
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
