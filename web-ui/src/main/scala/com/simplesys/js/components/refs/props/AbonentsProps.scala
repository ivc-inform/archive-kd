package com.simplesys.js.components.refs.props

import com.simplesys.SmartClient.Grids.props.listGrid.ListGridFieldProps
import com.simplesys.SmartClient.System.Implicits
import com.simplesys.System._
import com.simplesys.js.components.props.CommonListGridEditorComponentProps
import com.simplesys.js.components.refs.Abonents
import com.simplesys.option.ScOption._
import com.simplesys.option.{ScOption, ScSome}
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.DataSourcesJS

class AbonentsProps extends CommonListGridEditorComponentProps with Implicits{

    type classHandler <: Abonents

    dataSource = DataSourcesJS.eakd_abonents_DS.opt
    identifier = "60675A00-9949-20BE-D7B6-598C1F0D1439".opt

    fields = Seq(
        new ListGridFieldProps {
            hidden = true
            name = "id"
        },
        new ListGridFieldProps {
            hidden = true
            name = "idaborg"
        },
        new ListGridFieldProps {
            hidden = true
            name = "idabtype"
        },
        new ListGridFieldProps {
            name = "tdatein"
        },
        new ListGridFieldProps {
            name = "vabcode"
        },
        new ListGridFieldProps {
            name = "vabdesc"
        },
        new ListGridFieldProps {
            name = "vabname"
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
        }
    ).opt
}
