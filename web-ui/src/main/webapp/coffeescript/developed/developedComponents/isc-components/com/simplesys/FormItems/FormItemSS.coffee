isc.FormItem.addProperties
	init: `function () {
        if (this.nameStrong != null && this.nameStrong.name != null) this.name = nameStrong.name
        if (isc._traceMarkers) arguments.__this = this;

        this._origCanEdit = this.getCanEdit();
        this._origReadOnlyDisplay = this.getReadOnlyDisplay();

        // get a global ID so we can be called in the global scope
        // If getID() is called before this (typically only likely in an override of init),
        // we will already have a global ID - in this case avoid clobbering it.
        if (this.ID == null || window[this.ID] != this) {
            isc.ClassFactory.addGlobalID(this);
        }
        // if "options" was specified, switch to "valueMap"
        if (this.options && !this.valueMap) {
            this.valueMap = this.options;
            delete this.options;
        }

        // Make sure that any 'measure' properties are in the correct format

        this._convertRawToMeasure(this._$height);
        this._convertRawToMeasure(this._$width);
        this._convertRawToMeasure(this._$colSpan);
        this._convertRawToMeasure(this._$rowSpan);

        // Start with our default value
        // - we do this rather than calling 'this.setValue(this.getDefaultValue())' for a
        //   couple of reasons:
        //   a) At this point the form's "values" object may not be initialized
        //   b) In subclass overrides, such as the container item, setValue() makes use of
        //      properties that get set up after this (for them Superclass) init()
        //      implementation
        // - setValue() would also call form.saveItemValue() - it's ok to skip this at this
        //   stage as after the form item has been created this call would be made after form
        //   item creation via 'setValues()' for any items where 'shouldSaveValue' is true.
        // - setValue() would also call setElementValue() - ok to skip as our elements haven't
        //   been set up until draw().
        this._value = this.getDefaultValue();
        // Note that this is the default value.
        this._setToDefault = true;

        this._setUpIcons();

        // If any validators have stopOnError set, this form item must be marked
        // validateOnExit:true. SynchronousValidation is also enabled.

        if ((!this.validateOnExit || !this.synchronousValidation) &&
            this.validators && this.validators.length > 0) {
            for (var i = 0; i < this.validators.length; i++) {
                if (this.validators[i].stopOnError) {
                    this.validateOnExit = true;
                    this.synchronousValidation = true;
                    break;
                }
            }
        }
        // If any form or form item has stopOnError set, this form item must be marked
        // validateOnExit:true. SynchronousValidation is also enabled.
        if ((!this.validateOnExit || !this.synchronousValidation) &&
            ((this.stopOnError == null && this.form && this.form.stopOnError) || this.stopOnError)) {
            this.validateOnExit = true;
            this.synchronousValidation = true;
        }

        if (this.__sgwtRelink) this.__sgwtRelink();

        this.onInit();
    }`
