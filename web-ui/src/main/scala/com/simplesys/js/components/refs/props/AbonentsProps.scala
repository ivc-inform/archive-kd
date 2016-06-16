package com.simplesys.js.components.refs.props

import com.simplesys.SmartClient.DataBinding.props.SortSpecifierProps
import com.simplesys.SmartClient.Forms.FormsItems.props.SelectItemProps
import com.simplesys.SmartClient.Grids.props.listGrid.ListGridFieldProps
import com.simplesys.SmartClient.System._
import com.simplesys.System._
import com.simplesys.js.components.props.CommonListGridEditorComponentProps
import com.simplesys.js.components.refs.Abonents
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.DataSourcesJS

class AbonentsProps extends CommonListGridEditorComponentProps with Implicits {

    type classHandler <: Abonents

    dataSource = DataSourcesJS.eakd_abonents_DS.opt
    identifier = "15EC1A89-2233-358F-1186-372AF0FD1DC2".opt

    fields = Seq(
        new ListGridFieldProps {
            name = "vabcode"
        },
        new ListGridFieldProps {
            name = "vabname"
        },
        new ListGridFieldProps {
            name = "tdatein"
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
            editorProperties = SelectItem(
                new SelectItemProps {
                    optionDataSource = DataSourcesJS.eakd_abonents_types_DS.opt
                    displayField = "vabontype"
                    valueField = "id".asInstanceOf[JSAny].opt
                    initialSort = Seq(
                        new SortSpecifierProps {
                            property = "vabontype"
                        }
                    ).opt
                }
            ).opt
            filterEditorProperties = SelectItem(
                new SelectItemProps {
                    optionDataSource = DataSourcesJS.eakd_abonents_types_DS.opt
                    initialSort = Seq(
                        new SortSpecifierProps {
                            property = "vabontype"
                        }
                    ).opt
                }
            ).opt
        },
        new ListGridFieldProps {
            name = "orgcode"
            editorProperties = SelectItem(
                new SelectItemProps {
                    optionDataSource = DataSourcesJS.eakd_abonents_org_DS.opt
                    displayField = "orgcode"
                    valueField = "id".asInstanceOf[JSAny].opt
                    initialSort = Seq(
                        new SortSpecifierProps {
                            property = "orgcode"
                        }
                    ).opt
                }
            ).opt
            filterEditorProperties = SelectItem(
                new SelectItemProps {
                    optionDataSource = DataSourcesJS.eakd_abonents_org_DS.opt
                    initialSort = Seq(
                        new SortSpecifierProps {
                            property = "orgcode"
                        }
                    ).opt
                }
            ).opt
        },
        new ListGridFieldProps {
            name = "vabdesc"
        }
    ).opt
}
