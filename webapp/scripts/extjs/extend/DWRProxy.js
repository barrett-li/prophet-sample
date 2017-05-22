/**
 * Ext JS Library 2.0 Extend
 * BaseBox Widget Libraries
 * weihaihan@gmail.com
 *
 * http://sourceforge.net/projects/basebox
 */

/**
 * 该类实现 {@link Ext.data.DataProxy} 接口通过DWR远程对象获取数据。<br/>
 *
 * @author  韦海晗
 * @class Ext.ux.data.DWRProxy
 * @extends Ext.data.DataProxy
 */
Ext.namespace('Ext.ux.data.DWRProxy');
Ext.ux.data.DWRProxy = function(config){
    Ext.apply(this, config);
    Ext.ux.data.DWRProxy.superclass.constructor.call(this);
};

Ext.extend(Ext.ux.data.DWRProxy, Ext.data.DataProxy, {

	timeout : 30000,
	
    mode: 'remote',
    
    load : function(params, reader, callback, scope, arg){
        if(this.fireEvent("beforeload", this, params) !== false){
        	Ext.apply(params, reader.meta);
        	
            var o = {
                params : params || {},
                request: {
                    callback : callback,
                    scope : scope,
                    arg : arg
                },
                reader: reader,
                callback : this.loadResponse,
                scope: this
            };
            
            if(this.mode == 'remote'){
	        	this.method(
		        	params["start"], 
		        	params["limit"], 
		        	params["sort"], 
		        	params["dir"], 
		        	params["root"], 
		        	params["totalProperty"], 
		        	params["successProperty"], 
		        	params, 
		        	this.createCallback(o)
	        	);
            } else if(this.mode == 'local'){
            	this.method(
            		this,
            		o,
	            	params["start"], 
	            	params["limit"], 
	            	params["sort"], 
	            	params["dir"], 
	            	params["root"], 
	            	params["totalProperty"], 
	            	params["successProperty"], 
	            	params, 
	            	this.createCallback(o)
            	);
            }
            
        }else{
            callback.call(scope||this, null, arg, false);
        }
    },
    
    // private
    createCallback : function(opts){
		var opts = opts || {};
        return {
			callback: function(response) {
				this.loadResponse(opts, true, response);
			}.createDelegate(this), 
			exceptionHandler: function(exception) {
				this.loadResponse(opts, false, false, exception);
			}.createDelegate(this),
			timeout: this.timeout 
		};
    },
    
    // private
    loadResponse : function(o, success, response, exception){
        if(!success){
            this.fireEvent("loadexception", this, o, response, exception);
            o.request.callback.call(o.request.scope, null, o.request.arg, false);
            return;
        }
        var result;
        try {
            result = o.reader.readRecords(response);
        }catch(e){
            this.fireEvent("loadexception", this, o, response, e);
            o.request.callback.call(o.request.scope, null, o.request.arg, false);
            return;
        }
        this.fireEvent("load", this, o, o.request.arg);
        o.request.callback.call(o.request.scope, result, o.request.arg, true);
    },
    
    // private
    update : function(dataSet){
        
    },
    
    // private
    updateResponse : function(dataSet){
        
    }
});

/**
 * 该类实现 {@link Ext.data.DataProxy} 接口通过DWR远程对象获取和包含JFreeChart显示信息的数据。<br/>
 *
 * @author  韦海晗
 * @date    September 16, 2008
 *
 * @class Ext.ux.data.DWRChartProxy
 * @extends Ext.ux.data.DWRProxy
 */
Ext.namespace('Ext.ux.data.DWRChartProxy');
Ext.ux.data.DWRChartProxy = function(config){
    Ext.ux.data.DWRChartProxy.superclass.constructor.call(this);
    Ext.apply(this, config);
};

Ext.extend(Ext.ux.data.DWRChartProxy, Ext.ux.data.DWRProxy, {
    load : function(params, reader, callback, scope, arg){
        if(this.fireEvent("beforeload", this, params) !== false){
        	Ext.apply(params, reader.meta);

            var o = {
                params : params || {},
                request: {
                    callback : callback,
                    scope : scope,
                    arg : arg
                },
                reader: reader,
                callback : this.loadResponse,
                scope: this
            };
            
            if(this.mode == 'remote'){
	        	this.method(
	        		params["start"], 
		        	params["limit"], 
		        	params["sort"], 
		        	params["dir"], 
		        	params["root"], 
		        	params["totalProperty"], 
		        	params["chartsProperty"], 
		        	params["successProperty"], 
		        	params, 
		        	this.createCallback(o)
		    	);
            } else if(this.mode == 'local'){
	        	this.method(
            		this,
            		o,
	        		params["start"], 
		        	params["limit"], 
		        	params["sort"], 
		        	params["dir"], 
		        	params["root"], 
		        	params["totalProperty"], 
		        	params["chartsProperty"], 
		        	params["successProperty"], 
		        	params, 
		        	this.createCallback(o)
	        	);
            }
        }else{
            callback.call(scope||this, null, arg, false);
        }
    }
});