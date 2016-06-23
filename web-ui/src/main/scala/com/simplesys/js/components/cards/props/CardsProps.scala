package com.simplesys.js.components.cards.props

import com.simplesys.SmartClient.App.props.CommonListGridEditorComponentProps
import com.simplesys.SmartClient.Forms.FormsItems.props.TextItemProps
import com.simplesys.SmartClient.Grids.props.listGrid.ListGridFieldProps
import com.simplesys.SmartClient.Layout.props.WindowSSProps
import com.simplesys.SmartClient.System._
import com.simplesys.js.components.cards.Cards
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._
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
    editingFields = Seq(
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
    fields = Seq(
        new ListGridFieldProps {
            hidden = true
            name = "id"
        },
        new ListGridFieldProps {
            hidden = true
            name = "idcats"
        },
        new ListGridFieldProps {
            hidden = true
            name = "iddocitem"
        },
        new ListGridFieldProps {
            hidden = true
            name = "idgroup"
        },
        new ListGridFieldProps {
            hidden = true
            name = "idliter"
        },
        new ListGridFieldProps {
            hidden = true
            name = "idstate"
        },
        new ListGridFieldProps {
            hidden = true
            name = "idtype"
        },
        new ListGridFieldProps {
            name = "vcrcode"
            title = "Обозначение"
        },
        new ListGridFieldProps {
            name = "vcrname"
            title = "Наименование"
        },
        new ListGridFieldProps {
            name = "datecard"
        },
        new ListGridFieldProps {
            name = "vcrsdescr"
            title = "Описание"
        },
        new ListGridFieldProps {
            name = "vcrcodeprns"
        },
        new ListGridFieldProps {
            name = "idcrd_fk"
        },
        new ListGridFieldProps {
            name = "vcrinvent"
        },
        new ListGridFieldProps {
            name = "vcrkolpages"
        },
        new ListGridFieldProps {
            name = "vfileident"
        },
        new ListGridFieldProps {
            name = "vformats"
        },
        new ListGridFieldProps {
            name = "vrazr"
        },
        new ListGridFieldProps {
            name = "vlrcode"
        },
        new ListGridFieldProps {
            name = "vlrname"
        },
        new ListGridFieldProps {
            name = "vtyname"
        },
        new ListGridFieldProps {
            name = "vtyname"
        },
        new ListGridFieldProps {
            name = "vctcode"
        },
        new ListGridFieldProps {
            name = "vsecode"
        },
        new ListGridFieldProps {
            name = "vgicode"
        },
        new ListGridFieldProps {
            name = "vitcode"
        }
    ).opt
}
