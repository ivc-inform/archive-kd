isc.defineClass("LookupEditorItem", isc.CanvasItem).addProperties
	buttonIcon: "ellipsis.png"
	createCanvas: ->
		#@value = @record?[@name]
		res = isc.HLayoutSS.create
			height: 20,
			members: [
				df = isc.DynamicFormSS.create
					cellPadding: 0
					owner: @
					width: "*"
					minColWidth: 0
					colWidths: [0, "*"]
					items: [
						(
							colSpan: 2
							name: @name
							width: "*"
							showTitle: false
							type: "TextItem"
							canEdit: false
							#value: @value
							###editorEnter: (form, item, value) ->
								v = value###
						)
					]
			]

		@textItem = df.getItems()[0]
		res.addMember isc.IButtonSS.create isc.addProperties
											   "iconAlign": "center"
											   "click": =>
												   #isc.info "Click"
												   @textItem.setValue "12456"
												   return
											   "icon": @buttonIcon
											   "width": 22
		res
