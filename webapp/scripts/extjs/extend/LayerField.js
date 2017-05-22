/**
 * Ext JS Library 2.0 Extend
 * BaseBox Widget Libraries
 * weihaihan@gmail.com
 *
 * http://sourceforge.net/projects/basebox
 */

/**
 * 该类继承 {@link Ext.form.TriggerField} 类，带有Layer的Field。
 *
 * @author  韦海晗
 * @class Ext.ux.form.LayerField
 * @extends Ext.form.TriggerField
 */
Ext.namespace('Ext.ux.form.LayerField');
Ext.ux.form.LayerField = Ext.extend(Ext.form.TriggerField, {
	html: '',
	
    layerClass: 'x-combo-list-inner',
    
    bodyClass: '',
    
    shadow:'sides',

    layerAlign: 'tl-bl?',
	
    editable: true,

	handleHeight : 8,

	maxHeight: 300,
	
    minLayerWidth : 70,

	minLayerHeight : 200,
	
    initComponent : function(){
        Ext.ux.form.LayerField.superclass.initComponent.call(this);
	    this.addEvents({
	    	
	        'expand' : true,
	        
	        'collapse' : true,
	        
	    	'keyup' : true
	    });
    },
    
    // private
    onRender : function(ct, position){
        Ext.ux.form.LayerField.superclass.onRender.call(this, ct, position);
        
        if(this.hiddenName){
            this.hiddenField = this.el.insertSibling({tag:'input', type:'hidden', name: this.hiddenName, id: (this.hiddenId||this.hiddenName)},
                    'before', true);
            this.hiddenField.value =
                this.hiddenValue !== undefined ? this.hiddenValue :
                this.value !== undefined ? this.value : '';

            // prevent input submission
            this.el.dom.removeAttribute('name');
        }
        
		var cls = 'x-combo-list';
        this.layer = new Ext.Layer({
            shadow: this.shadow, cls: [cls, this.layerClass].join(' '), constrain: false
        });

        var lw = this.layerWidth || Math.max(this.wrap.getWidth(), this.minLayerWidth);
        this.layer.setWidth(lw);
        this.layer.setHeight(this.layerHeight || this.minLayerHeight);
        this.assetHeight = 0;
        
        if(this.title){
            this.header = this.layer.createChild({cls: cls + '-hd', html: this.title});
            this.assetHeight += this.header.getHeight();
        }

     	this.view = new Ext.Panel({
      		autoHeight: true,
      		autoWidth: true,
      		border: false,
      		renderTo: this.layer.createChild(),
      		baseCls: this.bodyClass,
      		autoScroll: this.autoScroll,
      		html: this.html,
      		items: this.items
      	});
      	this.items = this.view.items;
      	this.body = this.view.body;
		this.view.setWidth(lw - this.layer.getFrameWidth('lr'));

        if(!this.editable){
            this.editable = true;
            this.setEditable(false);
        }
    },
    
    collapse : function(){
        if(!this.isExpanded()){
            return;
        }
        this.layer.hide();
        Ext.get(document).un('mousedown', this.collapseIf, this);
        this.onCollapse();
        this.fireEvent('collapse', this);
    },
    
    // private
    collapseIf : function(e){
        if(!e.within(this.wrap) && !e.within(this.layer)){
            this.collapse();
        }
    },
    
    expand : function(){
        if(this.isExpanded()){
            return;
        }
        var lw = this.layerWidth || Math.max(this.wrap.getWidth(), this.minLayerWidth);
        this.layer.setWidth(lw);
    	this.view.setWidth(lw - this.layer.getFrameWidth('lr'));
        this.layer.alignTo(this.el, this.layerAlign);
        this.layer.show();
        Ext.get(document).on('mousedown', this.collapseIf, this);
        this.onExpand();
        this.fireEvent('expand', this);
    },
    
    setEditable : function(value){
        if(value == this.editable){
            return;
        }
        this.editable = value;
        if(!value){
            this.el.dom.setAttribute('readOnly', true);
            this.el.on('mousedown', this.onTriggerClick,  this);
            this.el.addClass('x-combo-noedit');
        }else{
            this.el.dom.setAttribute('readOnly', false);
            this.el.un('mousedown', this.onTriggerClick,  this);
            this.el.removeClass('x-combo-noedit');
        }
    },
    
    // private
    isExpanded : function(){
        return this.layer.isVisible();
    },

    // private
    initEvents : function(){
        Ext.ux.form.LayerField.superclass.initEvents.call(this);
        
        this.keyNav = new Ext.KeyNav(this.el, {
            "up" : function(e){
				this.collapse();
            },

            "down" : function(e){
                if(!this.isExpanded()){
                    this.onTriggerClick();
                }
            },

            "enter" : function(e){
                this.expand();
                return true;
            },

            "esc" : function(e){
                this.collapse();
            },

            "tab" : function(e){
                this.expand();
                return true;
            },

            scope : this,

            doRelay : function(foo, bar, hname){
                if(hname == 'down' || this.scope.isExpanded()){
                   return Ext.KeyNav.prototype.doRelay.apply(this, arguments);
                }
                return true;
            }
        });
        
     	this.queryDelay = this.queryDelay || 10;
        this.dqTask = new Ext.util.DelayedTask(this.expand, this);
        if(this.editable !== false){
            this.el.on("keyup", this.onKeyUp, this);
        }
    },
    
    // private
    onKeyUp : function(e){
        if(this.editable !== false && !e.isSpecialKey()){
            this.dqTask.delay(this.queryDelay);
            this.fireEvent('keyup', this);
        }
    },
    
    // private
    onCollapse: Ext.emptyFn,
    
    // private
    onExpand: Ext.emptyFn,
    
    // private
    onDisable: function(){
        Ext.ux.form.LayerField.superclass.onDisable.apply(this, arguments);
        if(this.hiddenField){
            this.hiddenField.disabled = this.disabled;
        }
    },
    
    // private
    // Implements the default empty TriggerField.onTriggerClick function
    onTriggerClick : function(){
        if(this.disabled){
            return;
        }
        if(this.isExpanded()){
            this.collapse();
            this.el.focus();
        }else{
			this.expand();
			this.el.dom.select();
            this.el.focus();
        }
    }
});
Ext.reg('layerfield', Ext.ux.form.LayerField);