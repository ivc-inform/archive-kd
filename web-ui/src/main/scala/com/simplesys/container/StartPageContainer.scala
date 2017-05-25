package com.simplesys.container

import com.simplesys.annotation.RSTransfer
import com.simplesys.app.http.StartPage
import com.simplesys.servlet.http.{HttpServletRequest, HttpServletResponse}
import com.simplesys.servlet.{GetData, ServletActor, ServletContext}

@RSTransfer(urlPattern = "/StartPage")
class StartPageContainer(val request: HttpServletRequest, val response: HttpServletResponse, val servletContext: ServletContext) extends ServletActor {

    def receive = {
        case GetData => {
            val textHTML = new StartPage(scalatags.Text)

            val html: String = textHTML.bodyHTML.render
            Out("""<!DOCTYPE html>
                  |<html lang="en">
                  |    <head>
                  |        <title>Архив электронных документов</title>
                  |        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
                  |    </head>
                  |    <body style="margin: 0px" id="body">
                  |
                  |        <script>var isomorphicDir = "isomorphic/"</script>
                  |        <script src="isomorphic/client/modules/ISC_Core.js"></script>
                  |        <script src="isomorphic/client/modules/ISC_Foundation.js"></script>
                  |        <script src="isomorphic/client/modules/ISC_Containers.js"></script>
                  |        <script src="isomorphic/client/modules/ISC_Grids.js"></script>
                  |        <script src="isomorphic/client/modules/ISC_Forms.js"></script>
                  |        <script>isc.licenseType = "Enterprise"</script>
                  |        <script src="isomorphic/client/modules/ISC_DataBinding.js"></script>
                  |        <script src="isomorphic/client/modules/ISC_Calendar.js"></script>
                  |        <script src="isomorphic/client/modules/ISC_RichTextEditor.js"></script>
                  |        <script src="isomorphic/client/modules/ISC_History.js"></script>
                  |        <script src="isomorphic/client/modules/ISC_Workflow.js"></script>
                  |        <script src="isomorphic/client/modules/ISC_Drawing.js"></script>
                  |        <script src="isomorphic/client/modules/ISC_Charts.js"></script>
                  |        <script src="isomorphic/client/modules/ISC_FileLoader.js"></script>
                  |
                  |        <!--from CoffeeScript-->
                  |        <script src="managed/javascript/isc-misc/beautify.js"></script>
                  |        <script src="managed/javascript/common-webapp/generated/generatedComponents/coffeescript/common.js"></script>
                  |
                  |        <! !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!--В отладочном варианте "managed/ заменить (во всем, что ниже) на " и на оборот при продакшене !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!-->
                  |        <script src="javascript/generated/generatedComponents/coffeescript/PreDefined.js"></script>
                  |        <script src="javascript/generated/generatedComponents/MakeAboutData.js"></script>
                  |        <script src="javascript/generated/generatedComponents/coffeescript/isc-components/com/simplesys/system/ArraySS.js"></script>
                  |        <script src="javascript/generated/generatedComponents/coffeescript/isc-components/com/simplesys/system/LogSS.js"></script>
                  |        <script src="javascript/generated/generatedComponents/coffeescript/isc-components/com/simplesys/RPCManagerSS.js"></script>
                  |        <script src="javascript/generated/generatedComponents/coffeescript/isc-components/com/simplesys/DataSourceSS.js"></script>
                  |        <script src="javascript/generated/generatedComponents/coffeescript/isc-components/com/simplesys/commons.js"></script>
                  |        <script src="javascript/generated/generatedComponents/coffeescript/isc-components/com/simplesys/OfflineSS.js"></script>
                  |        <script src="javascript/generated/generatedComponents/coffeescript/isc-components/com/simplesys/RestDataSourceSS.js"></script>
                  |        <script src="javascript/generated/generatedComponents/coffeescript/isc-components/com/simplesys/JSONSS.js"></script>
                  |        <script src="javascript/generated/generatedComponents/coffeescript/isc-components/com/simplesys/system/ClassSS.js"></script>
                  |        <script src="javascript/generated/generatedComponents/coffeescript/isc-components/com/simplesys/foundation/CanvasSS.js"></script>
                  |        <script src="javascript/generated/generatedComponents/coffeescript/isc-components/com/simplesys/foundation/JoinJSCanvas.js"></script>
                  |        <script src="javascript/generated/generatedComponents/coffeescript/isc-components/com/simplesys/layout/LayoutSS.js"></script>
                  |        <script src="javascript/generated/generatedComponents/coffeescript/isc-components/com/simplesys/LayoutSpacerSS.js"></script>
                  |        <script src="javascript/generated/generatedComponents/coffeescript/isc-components/com/simplesys/DynamicFormSS.js"></script>
                  |        <script src="javascript/generated/generatedComponents/coffeescript/isc-components/com/simplesys/FilterBuilderSS.js"></script>
                  |        <script src="javascript/generated/generatedComponents/coffeescript/isc-components/com/simplesys/HTMLPaneItem.js"></script>
                  |        <script src="javascript/generated/generatedComponents/coffeescript/isc-components/com/simplesys/FormItems/IButtonItem.js"></script>
                  |        <script src="javascript/generated/generatedComponents/coffeescript/isc-components/com/simplesys/layout/TabSetSS.js"></script>
                  |        <script src="javascript/generated/generatedComponents/coffeescript/isc-components/com/simplesys/SkinBoxItem.js"></script>
                  |        <script src="javascript/generated/generatedComponents/coffeescript/isc-components/com/simplesys/DataViewSS.js"></script>
                  |        <script src="javascript/generated/generatedComponents/coffeescript/isc-components/com/simplesys/DialogSS.js"></script>
                  |        <script src="javascript/generated/generatedComponents/coffeescript/isc-components/com/simplesys/layout/VStackSS.js"></script>
                  |        <script src="javascript/generated/generatedComponents/coffeescript/isc-components/com/simplesys/layout/HStackSS.js"></script>
                  |        <script src="javascript/generated/generatedComponents/coffeescript/isc-components/com/simplesys/layout/VLayoutSS.js"></script>
                  |        <script src="javascript/generated/generatedComponents/coffeescript/isc-components/com/simplesys/layout/HLayoutSS.js"></script>
                  |        <script src="javascript/generated/generatedComponents/coffeescript/isc-components/com/simplesys/layout/HTMLPaneSS.js"></script>
                  |        <script src="javascript/generated/generatedComponents/coffeescript/isc-components/com/simplesys/layout/IconMenuButtonSS.js"></script>
                  |        <script src="javascript/generated/generatedComponents/coffeescript/isc-components/com/simplesys/control/IButtonSS.js"></script>
                  |        <script src="javascript/generated/generatedComponents/coffeescript/isc-components/com/simplesys/FormItems/ComboboxItemWithButtons.js"></script>
                  |        <script src="javascript/generated/generatedComponents/coffeescript/isc-components/com/simplesys/FormItems/ComboboxItemWithClearButton.js"></script>
                  |        <script src="javascript/generated/generatedComponents/coffeescript/isc-components/com/simplesys/IconButtonSS.js"></script>
                  |        <script src="javascript/generated/generatedComponents/coffeescript/isc-components/com/simplesys/WindowSS.js"></script>
                  |        <script src="javascript/generated/generatedComponents/coffeescript/isc-components/com/simplesys/control/MenuSS.js"></script>
                  |        <script src="javascript/generated/generatedComponents/coffeescript/isc-components/com/simplesys/PortletSS.js"></script>
                  |        <script src="javascript/generated/generatedComponents/coffeescript/isc-components/com/simplesys/PortalLayoutSS.js"></script>
                  |        <script src="javascript/generated/generatedComponents/coffeescript/isc-components/com/simplesys/ListGridSS.js"></script>
                  |        <script src="javascript/generated/generatedComponents/coffeescript/isc-components/com/simplesys/ErrorDetailMessage.js"></script>
                  |        <script src="javascript/generated/generatedComponents/coffeescript/isc-components/com/simplesys/ErrorMessage.js"></script>
                  |        <script src="javascript/generated/generatedComponents/coffeescript/isc-components/com/simplesys/layout/HPanelSS.js"></script>
                  |        <script src="javascript/generated/generatedComponents/coffeescript/isc-components/com/simplesys/OkCancelPanel.js"></script>
                  |        <script src="javascript/generated/generatedComponents/coffeescript/isc-components/com/simplesys/FunctionPanel.js"></script>
                  |        <script src="javascript/generated/generatedComponents/coffeescript/isc-components/com/simplesys/OkCancelFunctionPanel.js"></script>
                  |        <script src="javascript/generated/generatedComponents/coffeescript/isc-components/com/simplesys/OkCancelPanelWithOutOwnerDestroy.js"></script>
                  |        <script src="javascript/generated/generatedComponents/coffeescript/isc-components/com/simplesys/OkCancelFunctionPanelWithOutOwnerDestroy.js"></script>
                  |        <script src="javascript/generated/generatedComponents/coffeescript/isc-components/com/simplesys/layout/ChainMasterDetail.js"></script>
                  |        <script src="javascript/generated/generatedComponents/coffeescript/isc-components/com/simplesys/ListGridEditor.js"></script>
                  |        <script src="javascript/generated/generatedComponents/coffeescript/isc-components/com/simplesys/TreeGridEditor.js"></script>
                  |        <script src="javascript/generated/generatedComponents/coffeescript/isc-components/com/simplesys/TreeListGridEditor.js"></script>
                  |        <script src="javascript/generated/generatedComponents/coffeescript/isc-components/com/simplesys/LoginWindow.js"></script>
                  |        <script src="javascript/generated/generatedComponents/coffeescript/isc-components/com/simplesys/MessagingSS.js"></script>
                  |        <script src="javascript/generated/generatedComponents/coffeescript/isc-components/com/simplesys/WindowWrapper.js"></script>
                  |        <script src="javascript/generated/generatedComponents/coffeescript/isc-components/com/simplesys/LookupEditor.js"></script>
                  |
                  |        <script src="javascript/generated/generatedComponentsJS/web-ui-fastopt.js"></script>
                  |        <link href="managed/css/common-webapp/logging_styles.css" rel="stylesheet" type="text/css"></link>
                  |
                  |        <script>
                  |            CreateSimpleTypes()
                  |            CreateSmartClientJS()
                  |            CreateAppJS()
                  |            GetUIContent()
                  |        </script>
                  |    </body>
                  |</html>""".stripMargin)

            selfStop()
        }
        case x =>
            throw new RuntimeException(s"Bad branch $x")
    }
}
