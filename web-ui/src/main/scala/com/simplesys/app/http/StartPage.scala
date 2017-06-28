package com.simplesys.app.http

import scalatags.generic.Bundle

class StartPage[Builder, Output <: FragT, FragT](val _title:String, val bundle: Bundle[Builder, Output, FragT]) {
    def bodyHTML(lastScript: String, fullOpt: Boolean = false) = {
        import bundle.all._

        val subPath = "javascript/generated/generatedComponents"
        html(lang := "en",
            head(
                bundle.tags2.title(_title),
                meta(httpEquiv := "Content-Type", content := "text/html; charset=utf-8")
            ),
            body(
                style := "margin: 0px",

                script("var isomorphicDir = \"isomorphic/\""),
                script(src := "isomorphic/client/modules/ISC_Core.js"),
                script(src := "isomorphic/client/modules/ISC_Foundation.js"),
                script(src := "isomorphic/client/modules/ISC_Containers.js"),
                script(src := "isomorphic/client/modules/ISC_Grids.js"),
                script(src := "isomorphic/client/modules/ISC_Forms.js"),
                script("isc.licenseType = \"Enterprise\""),
                script(src := "isomorphic/client/modules/ISC_DataBinding.js"),
                script(src := "isomorphic/client/modules/ISC_Calendar.js"),
                script(src := "isomorphic/client/modules/ISC_RichTextEditor.js"),
                script(src := "isomorphic/client/modules/ISC_History.js"),
                script(src := "isomorphic/client/modules/ISC_Workflow.js"),
                script(src := "isomorphic/client/modules/ISC_Drawing.js"),
                script(src := "isomorphic/client/modules/ISC_Charts.js"),
                script(src := "isomorphic/client/modules/ISC_FileLoader.js"),

                script(src := "managed/javascript/isc-misc/beautify.js"),
                script(src := "managed/javascript/common-webapp/generated/generatedComponents/coffeescript/common.js"),

                script(src := s"$subPath/coffeescript/PreDefined.js"),
                script(src := s"$subPath/MakeAboutData.js"),
                script(src := s"$subPath/coffeescript/isc-components/com/simplesys/system/ArraySS.js"),
                script(src := s"$subPath/coffeescript/isc-components/com/simplesys/system/LogSS.js"),
                script(src := s"$subPath/coffeescript/isc-components/com/simplesys/RPCManagerSS.js"),
                script(src := s"$subPath/coffeescript/isc-components/com/simplesys/DataSourceSS.js"),
                script(src := s"$subPath/coffeescript/isc-components/com/simplesys/commons.js"),
                script(src := s"$subPath/coffeescript/isc-components/com/simplesys/OfflineSS.js"),
                script(src := s"$subPath/coffeescript/isc-components/com/simplesys/RestDataSourceSS.js"),
                script(src := s"$subPath/coffeescript/isc-components/com/simplesys/JSONSS.js"),
                script(src := s"$subPath/coffeescript/isc-components/com/simplesys/system/ClassSS.js"),
                script(src := s"$subPath/coffeescript/isc-components/com/simplesys/foundation/CanvasSS.js"),
                script(src := s"$subPath/coffeescript/isc-components/com/simplesys/foundation/JoinJSCanvas.js"),
                script(src := s"$subPath/coffeescript/isc-components/com/simplesys/layout/LayoutSS.js"),
                script(src := s"$subPath/coffeescript/isc-components/com/simplesys/LayoutSpacerSS.js"),
                script(src := s"$subPath/coffeescript/isc-components/com/simplesys/DynamicFormSS.js"),
                script(src := s"$subPath/coffeescript/isc-components/com/simplesys/HTMLPaneItem.js"),
                script(src := s"$subPath/coffeescript/isc-components/com/simplesys/FormItems/IButtonItem.js"),
                script(src := s"$subPath/coffeescript/isc-components/com/simplesys/layout/TabSetSS.js"),
                script(src := s"$subPath/coffeescript/isc-components/com/simplesys/SkinBoxItem.js"),
                script(src := s"$subPath/coffeescript/isc-components/com/simplesys/layout/VStackSS.js"),
                script(src := s"$subPath/coffeescript/isc-components/com/simplesys/layout/HStackSS.js"),
                script(src := s"$subPath/coffeescript/isc-components/com/simplesys/layout/VLayoutSS.js"),
                script(src := s"$subPath/coffeescript/isc-components/com/simplesys/layout/HLayoutSS.js"),
                script(src := s"$subPath/coffeescript/isc-components/com/simplesys/layout/HTMLPaneSS.js"),
                script(src := s"$subPath/coffeescript/isc-components/com/simplesys/layout/IconMenuButtonSS.js"),
                script(src := s"$subPath/coffeescript/isc-components/com/simplesys/control/IButtonSS.js"),
                script(src := s"$subPath/coffeescript/isc-components/com/simplesys/FormItems/ComboboxItemWithButtons.js"),
                script(src := s"$subPath/coffeescript/isc-components/com/simplesys/FormItems/ComboboxItemWithClearButton.js"),
                script(src := s"$subPath/coffeescript/isc-components/com/simplesys/IconButtonSS.js"),
                script(src := s"$subPath/coffeescript/isc-components/com/simplesys/WindowSS.js"),
                script(src := s"$subPath/coffeescript/isc-components/com/simplesys/control/MenuSS.js"),
                script(src := s"$subPath/coffeescript/isc-components/com/simplesys/PortletSS.js"),
                script(src := s"$subPath/coffeescript/isc-components/com/simplesys/PortalLayoutSS.js"),
                script(src := s"$subPath/coffeescript/isc-components/com/simplesys/ListGridSS.js"),
                script(src := s"$subPath/coffeescript/isc-components/com/simplesys/ErrorDetailMessage.js"),
                script(src := s"$subPath/coffeescript/isc-components/com/simplesys/ErrorMessage.js"),
                script(src := s"$subPath/coffeescript/isc-components/com/simplesys/layout/HPanelSS.js"),
                script(src := s"$subPath/coffeescript/isc-components/com/simplesys/OkCancelPanel.js"),
                script(src := s"$subPath/coffeescript/isc-components/com/simplesys/FunctionPanel.js"),
                script(src := s"$subPath/coffeescript/isc-components/com/simplesys/OkCancelFunctionPanel.js"),
                script(src := s"$subPath/coffeescript/isc-components/com/simplesys/OkCancelPanelWithOutOwnerDestroy.js"),
                script(src := s"$subPath/coffeescript/isc-components/com/simplesys/OkCancelFunctionPanelWithOutOwnerDestroy.js"),
                script(src := s"$subPath/coffeescript/isc-components/com/simplesys/layout/ChainMasterDetail.js"),
                script(src := s"$subPath/coffeescript/isc-components/com/simplesys/ListGridEditor.js"),
                script(src := s"$subPath/coffeescript/isc-components/com/simplesys/TreeGridEditor.js"),
                script(src := s"$subPath/coffeescript/isc-components/com/simplesys/TreeListGridEditor.js"),
                script(src := s"$subPath/coffeescript/isc-components/com/simplesys/LoginWindow.js"),
                script(src := s"$subPath/coffeescript/isc-components/com/simplesys/MessagingSS.js"),
                script(src := s"$subPath/coffeescript/isc-components/com/simplesys/WindowWrapper.js"),
                script(src := s"$subPath/coffeescript/isc-components/com/simplesys/LookupEditor.js"),

                script(src := s"javascript/generated/generatedComponentsJS/${if (fullOpt) "web-ui-opt.js" else "web-ui-fastopt.js"}"),

                link(href := "managed/css/common-webapp/logging_styles.css", rel := "stylesheet", `type` := "text/css"),
                script(lastScript)
            )
        )
    }
}
