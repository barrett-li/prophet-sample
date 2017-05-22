/**
 * Ext JS Library 2.0 Extend
 * BaseBox Widget Libraries
 * weihaihan@gmail.com
 *
 * http://sourceforge.net/projects/basebox
 */

/**
 * Basic Label field.
 * 
 * @author  韦海晗
 * @class Ext.ux.form.Label
 * @extends Ext.BoxComponent
 */
Ext.namespace('Ext.ux.form.Label');
Ext.ux.form.Label = Ext.extend(Ext.BoxComponent, {
    /**
     * @cfg {String} text The plain text to display within the label (defaults to ''). If you need to include HTML 
     * tags within the label's innerHTML, use the {@link #html} config instead.
     */
    /**
     * @cfg {String} forId The id of the input element to which this label will be bound via the standard 'htmlFor'
     * attribute. If not specified, the attribute will not be added to the label.
     */
    /**
     * @cfg {String} html An HTML fragment that will be used as the label's innerHTML (defaults to ''). 
     * Note that if {@link #text} is specified it will take precedence and this value will be ignored.
     */
    /**
     * @cfg {Mixed} value A value to initialize this field with.
     */
    /**
     * @cfg {String} cls A CSS class to apply to the field's underlying element.
     */
    /**
     * @cfg {String/Object} autoCreate A DomHelper element spec, or true for a default element spec (defaults to
     * {tag: "div"})
     */
    defaultAutoCreate : {tag: "div"},
    /**
     * @cfg {String} labelClass The default CSS class for the text (defaults to "x-form-label")
     */
    labelClass : "x-form-label",
    
    // private
    isFormField : true,
    
    // private
    encode: true,
    
    // private
    onRender : function(ct, position){
        if(!this.el){
            var cfg = this.getAutoCreate();
            cfg.id = this.getId();
            var t = this.text ? this.text : this.value;
            t = this.encode ? Ext.util.Format.htmlEncode(t) : t;
            cfg.html = t ? t : (this.html || '');
            this.el = ct.createChild(cfg, position);
            if(this.forId){
                this.el.setAttribute('htmlFor', this.forId);
            }
            this.el.addClass([this.labelClass, this.cls]);
        }
        Ext.ux.form.Label.superclass.onRender.call(this, ct, position);
    },
    
    /**
     * Updates the label's innerHTML with the specified string.
     * @param {String} text The new label text
     * @param {Boolean} encode (optional) False to skip HTML-encoding the text when rendering it
     * to the label (defaults to true which encodes the value). This might be useful if you want to include 
     * tags in the label's innerHTML rather than rendering them as string literals per the default logic.
     * @return {Label} this
     */
    setText: function(t, encode){
        this.text = t;
        if(this.rendered){
            this.el.dom.innerHTML = encode !== false ? Ext.util.Format.htmlEncode(t) : t;
        }
        return this;
    },
    
    /**
     * Returns the normalized data value (undefined or emptyText will be returned as '').  To return the raw value see {@link #getRawValue}.
     * @return {Mixed} value The field value
     */
    getValue : function(){
        if(!this.rendered) {
            return this.value;
        }
        var v = this.el.dom.innerHTML;
        if(v === this.emptyText || v === undefined){
            v = '';
        }
        return v;
    },
    
    /**
     * Sets a data value into the field and validates it.  To set the value directly without validation see {@link #setRawValue}.
     * @param {Mixed} value The value to set
     */
    setValue : function(v){
		this.setText(this.value = v);
    },

    // private
    validate : function(){
        return true;
    },
    
    // private
    isDirty : function(){
        return true;
    },
    
	// private
    reset : Ext.emptyFn,
    
    // private
    clearInvalid : Ext.emptyFn
});

Ext.reg('label', Ext.ux.form.Label);