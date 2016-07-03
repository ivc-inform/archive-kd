package com.simplesys.SmartClient

import com.simplesys.SmartClient.App._
import com.simplesys.SmartClient.App.formItems.LookupListGridEditorItem
import com.simplesys.SmartClient.App.formItems.props.LookupListGridEditorItemProps
import com.simplesys.SmartClient.App.props._
import com.simplesys.SmartClient.Control._
import com.simplesys.SmartClient.Control.menu.{MenuItem, MenuSSItem}
import com.simplesys.SmartClient.Control.props._
import com.simplesys.SmartClient.Control.props.menu.{MenuItemProps, MenuSSItemProps}
import com.simplesys.SmartClient.DataBinding._
import com.simplesys.SmartClient.DataBinding.props._
import com.simplesys.SmartClient.Drawing._
import com.simplesys.SmartClient.Drawing.drawItem._
import com.simplesys.SmartClient.Drawing.drawItem.props._
import com.simplesys.SmartClient.Drawing.gradient._
import com.simplesys.SmartClient.Drawing.gradient.props._
import com.simplesys.SmartClient.Drawing.props._
import com.simplesys.SmartClient.Forms.FormsItems._
import com.simplesys.SmartClient.Forms.FormsItems.props._
import com.simplesys.SmartClient.Forms._
import com.simplesys.SmartClient.Forms.props._
import com.simplesys.SmartClient.Foundation._
import com.simplesys.SmartClient.Foundation.props._
import com.simplesys.SmartClient.Grids._
import com.simplesys.SmartClient.Grids.listGrid.ListGridField
import com.simplesys.SmartClient.Grids.props._
import com.simplesys.SmartClient.Grids.props.listGrid.ListGridFieldProps
import com.simplesys.SmartClient.Grids.props.tileGrid.SimpleTileProps
import com.simplesys.SmartClient.Grids.props.treeGrid.TreeGridFieldProps
import com.simplesys.SmartClient.Grids.tileGrid.SimpleTile
import com.simplesys.SmartClient.Grids.treeGrid.{Tree, TreeGridField}
import com.simplesys.SmartClient.Layout._
import com.simplesys.SmartClient.Layout.props._
import com.simplesys.SmartClient.Layout.props.tabSet.TabProps
import com.simplesys.SmartClient.Layout.props.toolStrip.{ToolStripButtonProps, ToolStripMenuButtonProps, ToolStripSeparatorProps}
import com.simplesys.SmartClient.Layout.tabSet.Tab
import com.simplesys.SmartClient.Layout.toolStrip.{ToolStripButton, ToolStripMenuButton, ToolStripSeparator}
import com.simplesys.SmartClient.RPC.RPCRequest
import com.simplesys.SmartClient.RPC.props.RPCRequestProps
import com.simplesys.SmartClient.System.date.Date
import com.simplesys.SmartClient.System.date.props.DateProps
import com.simplesys.SmartClient.System.props.TreeProps
import com.simplesys.SmartClient.Tools.editProxy.DrawPaneEditProxy
import com.simplesys.SmartClient.Tools.palette._
import com.simplesys.SmartClient.Tools.palette.props._
import com.simplesys.SmartClient.Tools.props._
import com.simplesys.SmartClient.Tools.props.editProxy.DrawPaneEditProxyProps
import com.simplesys.SmartClient.Tools.{EditContext, EditProxy, PaletteNode}


//Должны быть
//import com.simplesys.macros.PropsToDictionary
//

import com.simplesys.macros.PropsToDictionary

package object System {

    type Point = IscArray[Double]
    type Rectangle = IscArray[Point]

    implicit class String1Opts(x: String) {
        def ellipsis = s"$x..."
        def fromSkinPath = s"[SKIN]$x"
        def dblQuoted: String = "\"" + x + "\""
    }

    object Date extends SCApply4Object[Date, DateProps]

    //<editor-fold desc="Control">
    object IButtonSS extends SCApply[IButtonSS, IButtonSSProps]
    object Slider extends SCApply[Slider, SliderProps]
    object Menu extends SCApply[Menu, MenuProps]
    object MenuSS extends SCApply[MenuSS, MenuSSProps]
    object MenuSSItem extends SCApply4Object[MenuSSItem, MenuSSItemProps]
    //</editor-fold>

    //<editor-fold desc="DataBinding">
    object DataView extends SCApply[DataView, DataViewProps]
    object DataSource extends SCApply[DataSource, DataSourceProps]
    object DataSourceSS extends SCApply[DataSourceSS, DataSourceSSProps]
    object RestDataSourceSS extends SCApply[RestDataSourceSS, RestDataSourceSSProps]
    object SimpleType extends SCApply[SimpleType, SimpleTypeProps]
    object SimpleTile extends SCApply[SimpleTile, SimpleTileProps]
    //</editor-fold>

    //<editor-fold desc="Forms">
    object DynamicFormSS extends SCApply[DynamicFormSS, DynamicFormSSProps]
    object FilterBuilder extends SCApply[FilterBuilder, FilterBuilderProps]
    //</editor-fold>

    //<editor-fold desc="FormsItems">
    object FormItem extends SCApply[FormItem, FormItemProps]
    object CanvasItem extends SCApply[CanvasItem, CanvasItemProps]
    object CheckboxItem extends SCApply[CheckboxItem, CheckboxItemProps]
    object ColorItem extends SCApply[ColorItem, ColorItemProps]
    object SkinBoxItem extends SCApply[SkinBoxItem, SkinBoxItemProps]
    object DateItem extends SCApply[DateItem, DateItemProps]
    object DateRangeItem extends SCApply[DateRangeItem, DateRangeItemProps]
    object DateTimeItem extends SCApply[DateTimeItem, DateTimeItemProps]
    object DateTimeItemSS extends SCApply[DateTimeItemSS, DateTimeItemSSProps]
    object DoubleItem extends SCApply[DoubleItem, DoubleItemProps]
    object IntegerItem extends SCApply[IntegerItem, IntegerItemProps]
    object FloatItem extends SCApply[FloatItem, FloatItemProps]
    object PickList extends SCApply[PickList, PickListProps]
    object SelectItem extends SCApply[SelectItem, SelectItemProps]
    object SpinnerItem extends SCApply[SpinnerItem, SpinnerItemProps]
    object TextAreaItem extends SCApply[TextAreaItem, TextAreaItemProps]
    object TextItem extends SCApply[TextItem, TextItemProps]
    object TextItemSS extends SCApply[TextItemSS, TextItemSSProps]
    object StaticTextItem extends SCApply[StaticTextItem, StaticTextItemProps]
    object TimeItem extends SCApply[TimeItem, TimeItemProps]
    object TimerItem extends SCApply[TimerItem, TimerItemProps]
    object Tab extends SCApply4Object[Tab, TabProps]
    object DateChooser extends SCApply4Object[DateChooser, DateChooserProps]
    object FormItemWithButton extends SCApply[FormItemWithButton, FormItemWithButtonProps]
    object ComboboxItemWithButtons extends SCApply[ComboboxItemWithButtons, ComboboxItemWithButtonsProps]
    object ComboboxItemWithClearButton extends SCApply[ComboboxItemWithClearButton, ComboboxItemWithClearButtonProps]
    object LookupListGridEditorItem extends SCApply[LookupListGridEditorItem, LookupListGridEditorItemProps]
    //</editor-fold>

    //<editor-fold desc="Foundation">
    object Canvas extends SCApply[Canvas, CanvasProps]
    object Label extends SCApply[Label, LabelProps]
    object HTMLFlow extends SCApply[HTMLFlow, HTMLFlowProps]
    //</editor-fold>

    //<editor-fold desc="RPC">
    object RPCRequest extends SCApply[RPCRequest, RPCRequestProps]
    object DSRequest extends SCApply[DSRequest, DSRequestProps]
    //</editor-fold>

    //<editor-fold desc="Grids">
    object ListGrid extends SCApply[ListGrid, ListGridProps]
    object ListGridEditor extends SCApply[ListGridEditor, ListGridEditorProps]
    object TreeGridEditor extends SCApply[TreeGridEditor, TreeGridEditorProps]
    object Tree extends SCApply[Tree, TreeProps]
    object ResultTree extends SCApply[ResultTree, ResultTreeProps]
    //</editor-fold>

    //<editor-fold desc="Layout">
    object HLayoutSS extends SCApply[HLayoutSS, HLayoutSSProps]
    object IconButton extends SCApply[IconButton, IconButtonProps]
    object IconButtonSS extends SCApply[IconButtonSS, IconButtonSSProps]
    object IconMenuButton extends SCApply[IconMenuButton, IconMenuButtonProps]
    object IconMenuButtonSS extends SCApply[IconMenuButtonSS, IconMenuButtonSSProps]
    object OkCancelPanel extends SCApply[OkCancelPanel, OkCancelPanelProps]
    object OkPanel extends SCApply[OkPanel, OkPanelProps]
    object VLayoutSS extends SCApply[VLayoutSS, VLayoutSSProps]
    object ChainMasterDetail extends SCApply[ChainMasterDetail, ChainMasterDetailProps]
    object TabSetSS extends SCApply[TabSetSS, TabSetSSProps]
    object WindowSS extends SCApply[WindowSS, WindowSSProps]
    object ToolStrip extends SCApply[ToolStrip, ToolStripProps]
    object ToolStripMenuButton extends SCApply[ToolStripMenuButton, ToolStripMenuButtonProps]
    object ToolStripSeparator extends SCApply[ToolStripSeparator, ToolStripSeparatorProps]
    object ToolStripButton extends SCApply[ToolStripButton, ToolStripButtonProps]
    object SectionStack extends SCApply[SectionStack, SectionStackProps]
    object SectionStackSS extends SCApply[SectionStackSS, SectionStackSSProps]
    object LayoutSpacer extends SCApply[LayoutSpacer, LayoutSpacerProps]
    //</editor-fold>

    //<editor-fold desc="drawing">
    object DrawPane extends SCApply[DrawPane, DrawPaneProps]
    object EditProxy extends SCApply[EditProxy, EditProxyProps]
    object DrawPaneEditProxy extends SCApply[DrawPaneEditProxy, DrawPaneEditProxyProps]
    object EditContext extends SCApply[EditContext, EditContextProps]
    object PaletteNode extends SCApply4Object[PaletteNode, PaletteNodeProps]

    //<editor-fold desc="gradient">
    object SimpleGradient extends SCApply4Object[SimpleGradient, SimpleGradientProps]
    //</editor-fold>

    //<editor-fold desc="Palette">
    object TilePalette extends SCApply[TilePalette, TilePaletteProps]
    //</editor-fold>

    //<editor-fold desc="DrawItem">
    object DrawItem extends SCApply[DrawItem, DrawItemProps]
    object DrawGroup extends SCApply[DrawGroup, DrawGroupProps]
    object DrawLine extends SCApply[DrawLine, DrawLineProps]
    object DrawRect extends SCApply[DrawRect, DrawRectProps]
    object DrawOval extends SCApply[DrawOval, DrawOvalProps]
    object DrawLabel extends SCApply[DrawLabel, DrawLabelProps]
    object DrawCurve extends SCApply[DrawCurve, DrawCurveProps]
    object DrawPath extends SCApply[DrawPath, DrawPathProps]
    object DrawLinePath extends SCApply[DrawLinePath, DrawLinePathProps]
    object DrawLinePathSS extends SCApply[DrawLinePathSS, DrawLinePathSSProps]
    object DrawKnob extends SCApply[DrawKnob, DrawKnobProps]
    object Shadow extends SCApply4Object[Shadow, ShadowProps]

    object RibbonBar extends SCApply[RibbonBar, RibbonBarProps]
    object RibbonGroup extends SCApply[RibbonGroup, RibbonGroupProps]
    object RibbonGroupSS extends SCApply[RibbonGroupSS, RibbonGroupSSProps]

    //</editor-fold>
    //</editor-fold>

    object SettingsEditor extends SCApply[SettingsEditor, SettingsEditorProps]
    object ListGridContextMenu extends SCApply[ListGridContextMenu, ListGridContextMenuProps]
    object ListGridContextMenuWithForm extends SCApply[ListGridContextMenu, ListGridContextMenuWithFormProps]
    object TreeGridContextMenu extends SCApply[TreeGridContextMenu, TreeGridContextMenuProps]
    object TreeListGridContextMenu extends SCApply[TreeListGridContextMenu, TreeListGridContextMenuProps]
    object CommonTreeListGridEditorComponent extends SCApply[CommonTreeListGridEditorComponent, CommonTreeListGridEditorComponentProps]
    object EditorUsers extends SCApply[EditorUsers, EditorUsersProps]
    object EditorUserGroups extends SCApply[EditorUserGroups, EditorUserGroupsProps]
    object TreeListGridEditor extends SCApply[TreeListGridEditor, TreeListGridEditorProps]

    object MenuItem extends SCApply4Object[MenuItem, MenuItemProps]
    object TreeGridField extends SCApply4Object[TreeGridField, TreeGridFieldProps]
    object ListGridField extends SCApply4Object[ListGridField, ListGridFieldProps]

    object CommonListGridEditorComponent extends SCApply[CommonListGridEditorComponent, CommonListGridEditorComponentProps]
    object CommonTreeGridEditorComponent extends SCApply[CommonTreeGridEditorComponent, CommonTreeGridEditorComponentProps]

}

