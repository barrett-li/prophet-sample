/**
 * Ext JS Library 2.0 Extend
 * BaseBox Widget Libraries
 * weihaihan@gmail.com
 *
 * http://sourceforge.net/projects/basebox
 */

/**
 * 该类继承 {@link Ext.data.DataProxy} 类，通过DWR远程对象获取当前节点的子节点对象。
 *
 * @author  韦海晗
 * @class Ext.ux.tree.TreeDWRLoader
 * @extends Ext.tree.TreeLoader
 */
Ext.namespace('Ext.ux.tree.TreeDWRLoader');
Ext.ux.tree.TreeDWRLoader = function(config){
	Ext.ux.tree.TreeDWRLoader.superclass.constructor.call(this, config);
};

Ext.extend(Ext.ux.tree.TreeDWRLoader, Ext.tree.TreeLoader, {

	timeout: 30000,
	
    load : function(node, callback){
        if(this.clearOnLoad){
            while(node.firstChild){
                node.removeChild(node.firstChild);
            }
        }
        if(this.doPreload(node)){ // preloaded json children
            if(typeof callback == "function"){
                callback();
            }
        }else if(this.method){
            this.requestData(node, callback);
        }
    },
    
    requestData : function(node, callback){
        if(this.fireEvent("beforeload", this, node, callback) !== false){
        	var params = {};
        	Ext.apply(params, this.baseParams);

        	this.transId = this.method(node.attributes, params, {
        		
				callback: function (response) {
					this.handleResponse({
						argument: {
							callback: callback, 
							node: node
						},
						data: response
					});
				}.createDelegate(this), 
				
				exceptionHandler: function(exception) {
					this.handleFailure({
						argument: {
							callback: callback, 
							node: node
						}, 
						responseText: exception
					});
				}.createDelegate(this),
				
				timeout: this.timeout
			});
        }else{
            // if the load is cancelled, make sure we notify
            // the node that we are done
            if(typeof callback == "function"){
                callback();
            }
        }
    },
	
    processResponse : function(response, node, callback){
        try {
            var o = response.data;
            node.beginUpdate();
            for(var i = 0, len = o.length; i < len; i++){
                var n = this.createNode(o[i]);
                if(n){
                    node.appendChild(n);
                }
            }
            node.endUpdate();
            if(typeof callback == "function"){
                callback(this, node);
            }
        }catch(e){
            this.handleFailure(response);
        }
    }
});
