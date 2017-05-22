/**
 * Ext JS Library 2.0 Extend
 * BaseBox Widget Libraries
 * weihaihan@gmail.com
 *
 * http://sourceforge.net/projects/basebox
 */

/**
 * 该类继承 {@link Ext.ux.form.LayerField} 类，带有Layer的Field。
 *
 * @author  韦海晗
 * @class Ext.ux.form.TreeField
 * @extends Ext.ux.form.LayerField
 */
Ext.namespace('Ext.ux.form.TreeField');
Ext.ux.form.TreeField = Ext.extend(Ext.ux.form.LayerField, {
	autoScroll: true,
	filter: false,
	
    // private
    onRender : function(ct, position){
        Ext.ux.form.TreeField.superclass.onRender.call(this, ct, position);
        if(this.filter && this.editable !== false && this.items){
            this.items.each(function(item){
		    	if(item instanceof Ext.tree.TreePanel){
					var filter = new Ext.tree.TreeFilter(item, {
						clearBlank: true,
						autoClear: true
					});
					
					var hiddenPkgs = [];
					function filterTree(e){
						var text = e.target.value;
						Ext.each(hiddenPkgs, function(n){
							n.ui.show();
						});
						if(!text){
							filter.clear();
							return;
						}
						item.expandAll();
						
						var re = new RegExp('^' + Ext.escapeRe(text), 'i');
						filter.filterBy(function(n){
							return !n.attributes.leaf || re.test(n.text);
						});
						
						// hide empty packages that weren't filtered
						hiddenPkgs = [];
						item.root.cascade(function(n){
							if(!n.attributes.leaf && n.ui.ctNode.offsetHeight < 3){
								n.ui.hide();
								hiddenPkgs.push(n);
							}
						});
					}
		    	}
		    	this.el.on("keydown", filterTree, this, {buffer: 350});
            }, this);
        }
    },

    // private
    onCollapse : function(){
    	Ext.ux.form.TreeField.superclass.onCollapse.call(this);
    	if(this.items){
    		values = [];
    		texts = [];
            this.items.each(function(item){
		    	if(item instanceof Ext.tree.TreePanel){
					var nodeHash = item.nodeHash;
					for(var i in nodeHash){
						var node = nodeHash[i];
						var ui = node.ui;
						if(ui.checkbox){
							if(ui.checkbox.checked) {
								values.push(ui.checkbox.value ? ui.checkbox.value : node.attributes.id);
								texts.push(node.attributes.text);
							}
						}
					}
		    	}
            }, this);
			this.setValue(texts.length > 0 ? texts.join(',') : '');
			this.setHiddenValue(values.length > 0 ? values.join(',') : '');
    	}
    },
    
    reloadData: function(jsonDatas){
    	var i = 0;
        this.items.each(function(item){
	    	if(item instanceof Ext.tree.TreePanel){
		    	var node = item.getRootNode();
		    	node.attributes.children = [jsonDatas[i]];
		    	node.reload();
		    	i++;
	    	}
	    });
    },
    
    setHiddenValue : function(v){
        if(this.hiddenField){
            this.hiddenField.value = v;
        }
    }
});
Ext.reg('treefield', Ext.ux.form.TreeField);