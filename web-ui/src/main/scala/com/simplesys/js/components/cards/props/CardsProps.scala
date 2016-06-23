package com.simplesys.js.components.cards.props

import com.simplesys.SmartClient.App.props.CommonListGridEditorComponentProps
import com.simplesys.SmartClient.Forms.FormsItems.props.TextItemProps
import com.simplesys.SmartClient.Layout.props.WindowSSProps
import com.simplesys.SmartClient.System._
import com.simplesys.js.components.cards.Cards
import com.simplesys.option.ScOption._
import com.simplesys.option.DoubleType._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.DataSourcesJS

class CardsProps extends CommonListGridEditorComponentProps with Implicits {
    override val simpleTable = false

    type classHandler <: Cards

    editWindowProperties = WindowSS(
        new WindowSSProps {
            width = 290
            height = 645
        }
    ).opt

    dataSource = DataSourcesJS.eakd_card_DS.opt
    identifier = "D8DC1876-F489-3172-EE97-729FFB73B575".opt
    editingFields =
      Seq(
          TextItem(new TextItemProps {
              name = "datecard"
          }),
          TextItem(new TextItemProps {
              hidden = true
              name = "idcats"
          }),
          TextItem(new TextItemProps {
              name = "idcrd_fk"
          }),
          TextItem(new TextItemProps {
              hidden = true
              name = "iddocitem"
          }),
          TextItem(new TextItemProps {
              hidden = true
              name = "idgroup"
          }),
          TextItem(new TextItemProps {
              hidden = true
              name = "idliter"
          }),
          TextItem(new TextItemProps {
              hidden = true
              name = "idstate"
          }),
          TextItem(new TextItemProps {
              hidden = true
              name = "idtype"
          }),
          TextItem(new TextItemProps {
              name = "vcrcode"
          }),
          TextItem(new TextItemProps {
              name = "vcrcodeprns"
          }),
          TextItem(new TextItemProps {
              name = "vcrinvent"
          }),
          TextItem(new TextItemProps {
              name = "vcrkolpages"
          }),
          TextItem(new TextItemProps {
              name = "vcrname"
          }),
          TextItem(new TextItemProps {
              name = "vcrsdescr"
          }),
          TextItem(new TextItemProps {
              name = "vfileident"
          }),
          TextItem(new TextItemProps {
              name = "vformats"
          }),
          TextItem(new TextItemProps {
              name = "vrazr"
          }),
          TextItem(new TextItemProps {
              name = "vlrcode"
          }),
          TextItem(new TextItemProps {
              name = "vlrname"
          }),
          TextItem(new TextItemProps {
              name = "vtyname"
          }),
          TextItem(new TextItemProps {
              name = "vtyname"
          }),
          TextItem(new TextItemProps {
              name = "vctcode"
          }),
          TextItem(new TextItemProps {
              name = "vsecode"
          }),
          TextItem(new TextItemProps {
              name = "vgicode"
          }),
          TextItem(new TextItemProps {
              name = "vitcode"
          })
      ).opt
}
