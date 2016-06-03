simpleSyS = @simpleSyS

isc.defineClass("OkCancelPanel", isc.HPanelSS).addProperties
	"defaultLayoutAlign": "center"
	"autoDraw"          : false
	"okBtn"             : null
	"ownerDestroy"      : true
	"ownerHide"         : true
	"cancelCaption"     : "Отменить"
	"okCaption"         : "Выполнить"
	"timeoutClose"      : 1000
	setDisabledOk       : (value) -> @okBtn.setDisabled(value); @
	setOwner            : (owner) -> @owner = owner; @
	"initWidget"        : ->
		@Super "initWidget", arguments

		@addMembers [
						isc.LayoutSpacerSS.create
							"width": "*"

						@okBtn = isc.IButtonSS.create
							"click": =>
								@okFunction? @
								if @owner?
									simpleSyS?.checkOwner? @owner
									@owner?.okFunction? @
									if @ownerDestroy is true
										@owner?.markForDestroy?()
									else if @ownerHide is true
										@owner?.hide?()
								return

							"title"           : @okCaption
							"icon"            : "ok.png"
							"width"           : 100
							"showDisabledIcon": true

						isc.LayoutSpacerSS.create
							"width": "*"

						@cancelButton = isc.IButtonSS.create
							"click": =>
								@cancelFunction? @
								if @owner?
									simpleSyS?.checkOwner? @owner
									@owner?.cancelFunction? @
									if @ownerDestroy is true
										@owner?.markForDestroy?()
									else
										@owner?.hide?()
								return

							"title": @cancelCaption
							"icon" : "cancel.png"
							"width": 100

						isc.LayoutSpacerSS.create
							"width": "*"
					]
		return

isc.defineClass("OkPanel", isc.HPanelSS).addProperties
	"defaultLayoutAlign": "center"
	"autoDraw": false
	"okBtn": null
	"ownerDestroy": true
	"ownerHide": true
	"okCaption": "Выполнить"
	"timeoutClose": 1000
	setDisabledOk: (value) -> @okBtn.setDisabled(value); @
	setOwner: (owner) -> @owner = owner; @
	"initWidget": ->
		@Super "initWidget", arguments

		@addMembers [
			isc.LayoutSpacerSS.create
				"width": "*"

			@okBtn = isc.IButtonSS.create
				"click": =>
					@okFunction? @
					if @owner?
						simpleSyS?.checkOwner? @owner
						@owner?.okFunction? @
						if @ownerDestroy is true
							@owner?.markForDestroy?()
						else if @ownerHide is true
							@owner?.hide?()
					return

				"title": @okCaption
				"icon": "ok.png"
				"width": 100
				"showDisabledIcon": true

			isc.LayoutSpacerSS.create
				"width": "*"
		]
		return
