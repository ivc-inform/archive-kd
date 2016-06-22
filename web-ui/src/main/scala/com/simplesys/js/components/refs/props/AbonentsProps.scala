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
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.DataSourcesJS

class AbonentsProps extends CommonListGridEditorComponentProps with Implicits {
    override val simpleTable = false

    type classHandler <: Abonents

    val abonentOrg = AbonentsOrg.create(new AbonentsOrgProps)
    val abonentType = AbonentsTypes.create(new AbonentsTypesProps)

    val orgCodeItem = LookupListGridEditorItem(new LookupListGridEditorItemProps {
        name = "orgcode"
        width = "100%"
        editorListGrid = abonentOrg.opt
        title = "Код предприятия"
    })

    val orgNameItem = LookupListGridEditorItem(new LookupListGridEditorItemProps {
        name = "orgname"
        width = "100%"
        editorListGrid = abonentOrg.opt
        title = "Наименование предприятия"
    })

    val abonTypeItem = LookupListGridEditorItem(new LookupListGridEditorItemProps {
        name = "vabontype"
        width = "100%"
        editorListGrid = abonentType.opt
    })

    dataSource = DataSourcesJS.eakd_abonents_DS.opt
    identifier = "15EC1A89-2233-358F-1186-372AF0FD1DC2".opt
    editingFields = Seq(
        TextItem(new TextItemProps {
            name = "vabcode"
            width = "100%"
        }),
        TextItem(new TextItemProps {
            name = "vabname"
            width = "100%"
        }),
        DateTimeItem(new DateTimeItemProps {
            name = "tdatein"
            width = "100%"
        }),
        TextItem(new TextItemProps {
            name = "vemail"
            width = "100%"
        }),
        TextItem(new TextItemProps {
            name = "vperson"
            width = "100%"
        }),
        TextItem(new TextItemProps {
            name = "vphone1"
            width = "100%"
        }),
        TextItem(new TextItemProps {
            name = "vphone2"
            width = "100%"
        }),
        IntegerItem(new IntegerItemProps {
            name = "idaborg"
            width = "100%"
            hidden = true.opt
        }),
        orgCodeItem,
        orgNameItem,
        IntegerItem(new IntegerItemProps {
            name = "idabtype"
            width = "100%"
            hidden = true.opt
        }),
        abonTypeItem,
        TextAreaItem(new TextAreaItemProps {
            name = "vabdesc"
            width = "100%"
        })
    ).opt

    fields = Seq(
        new ListGridFieldProps {
            name = "vabcode"
        },
        new ListGridFieldProps {
            name = "vabname"
        },
        new ListGridFieldProps {
            name = "tdatein"
            `type` = ListGridFieldType.date.opt
            format = "MMM dd yyyy"
        },
        new ListGridFieldProps {
            name = "vemail"
        },
        new ListGridFieldProps {
            name = "vperson"
        },
        new ListGridFieldProps {
            name = "vphone1"
        },
        new ListGridFieldProps {
            name = "vphone2"
        },
        new ListGridFieldProps {
            name = "vabontype"
            editorType = FormItemComponentType.LookupEditorItem
            editorProperties = abonTypeItem.opt
        },
        new ListGridFieldProps {
            name = "orgcode"
            editorType = FormItemComponentType.LookupEditorItem
            editorProperties = orgCodeItem.opt
        },
        new ListGridFieldProps {
            name = "orgname"
            editorType = FormItemComponentType.LookupEditorItem
            editorProperties = orgNameItem.opt
        },
        new ListGridFieldProps {
            name = "vabdesc"
        }
    ).opt

    editWindowProperties = WindowSS(
        new WindowSSProps {
            width = 285
            height = 425
        }
    ).opt
}
