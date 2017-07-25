package com.simplesys.js.components.cards

import com.simplesys.SmartClient.App.{AbstractCommonListGridEditorComponentCompanion, CommonListGridEditorComponent}
import com.simplesys.js.components.cards.props.ExpandAttahes.ExpandAttahes
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js

@js.native
trait Cards extends CommonListGridEditorComponent {
    var expandAttahes: ExpandAttahes
}

@js.native
abstract trait AbstractCardsCompanion extends AbstractCommonListGridEditorComponentCompanion


