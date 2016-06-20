package com.simplesys.js.components.refs.props

import com.simplesys.SmartClient.App.formItems.props.LookupEditorItemProps
import com.simplesys.SmartClient.App.props.CommonListGridEditorComponentProps
import com.simplesys.SmartClient.Forms.FormsItems.props._
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Grids.ListGrid
import com.simplesys.SmartClient.Grids.editors.LookupEditor
import com.simplesys.SmartClient.Grids.editors.props.LookupEditorProps
import com.simplesys.SmartClient.Grids.listGrid.ListGridRecord
import com.simplesys.SmartClient.Grids.props.listGrid.ListGridFieldProps
import com.simplesys.SmartClient.Layout.props.WindowSSProps
import com.simplesys.SmartClient.System.{LookupEditor, _}
import com.simplesys.System.Types.{ListGridFieldType, RecordComponentPoolingMode}
import com.simplesys.app.{AbonentsOrg, AbonentsTypes}
import com.simplesys.function._
import com.simplesys.js.components.refs.Abonents
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.DataSourcesJS

class AbonentsProps extends CommonListGridEditorComponentProps with Implicits {
    override val simpleTable = false

    type classHandler <: Abonents

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
        LookupEditorItem(new LookupEditorItemProps {
            name = "orgcode"
            width = "100%"
            editor = AbonentsOrg.create(new AbonentsOrgProps).opt
            captionLookupFieldName = "orgcode"
        }),
        IntegerItem(new IntegerItemProps {
            name = "idabtype"
            width = "100%"
            hidden = true.opt
        }),
        LookupEditorItem(new LookupEditorItemProps {
            name = "vabontype"
            width = "100%"
            editor = AbonentsTypes.create(new AbonentsTypesProps).opt
            captionLookupFieldName = "vabontype"
        }),
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
        },
        new ListGridFieldProps {
            name = "orgcode"
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

    showRecordComponents = true
    showRecordComponentsByCel = true
    recordComponentPoolingMode = RecordComponentPoolingMode.recycle.opt
    var column4Editing: ScOption[IscArray[String]] = IscArray("vabontype", "orgcode").opt

    createRecordComponent = {
        (thiz: ListGrid, listGridRecord: ListGridRecord, colNum: Int) =>
            isc debugTrap(thiz, colNum)
//            val colName = thiz.getFieldName(colNum)
//            isc debugTrap colName

//            if (thiz.column4Editing.contains(colName)) {
//                LookupEditor.create(
//                    new LookupEditorProps {
//                        editedFieldName = colName.opt
//                        record = listGridRecord.opt
//                    }
//                )
//            } else
                null
    }.toThisFunc.opt

    updateRecordComponent = {
        (thiz: ListGrid, record: ListGridRecord, colNum: Int, component: Canvas, recordChanged: Boolean) =>
            val editor = component.asInstanceOf[LookupEditor]
            editor setValueFromRecord record
            editor
    }.toThisFunc.opt
}
