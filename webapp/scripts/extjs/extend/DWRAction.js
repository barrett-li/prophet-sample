/**
 * Ext JS Library 2.0 Extend
 * BaseBox Widget Libraries
 * weihaihan@gmail.com
 *
 * http://sourceforge.net/projects/basebox
 */

Ext.namespace('Ext.ux.form.DWRAction');
Ext.ux.form.DWRAction.prototype = {
	
    // private
    processResponse : function(response){
        if(!response){
            return true;
        }
        this.response = response;
        this.result = this.handleResponse(response);
        return this.result;
    },

    // private
    getParams : function(){
        var bp = this.form.baseParams;
        var op = this.options.params;
		var p = Ext.apply({}, op);
		Ext.apply(p, bp);
        return p;
    },
    
    // private
    createCallback : function(opts){
		var opts = opts || {};
        return {
			callback: this.success.createDelegate(this), 
			exceptionHandler: this.failure.createDelegate(this),
			timeout: (opts.timeout*1000) || (this.form.timeout*1000)
		};
    }
}

/**
 * A class which handles submission of data from {@link Ext.form.BasicForm Form}s
 * and processes the returned response.
 * 
 * @author  韦海晗
 * @class Ext.ux.form.DWRAction.Submit
 * @extends Ext.form.Action.Submit
 */
Ext.ux.form.DWRAction.Submit = function(form, options){
    Ext.ux.form.DWRAction.Submit.superclass.constructor.call(this, form, options);
};

Ext.extend(Ext.ux.form.DWRAction.Submit, Ext.form.Action.Submit, {
	
    type : 'dwrsubmit',

    // private
    run : function(){
    	var o = this.options;
        if((o.clientValidation === false || this.form.isValid()) && o.remote){
        	var params = this.getParams();
        	var reader = this.form.errorReader;
        	if(reader){
        		Ext.apply(params, reader.meta);
        	}
        	o.remote(this, o, params["root"], params["successProperty"], params, this.createCallback(o));
        }else if (o.clientValidation !== false){ // client validation failed
            this.failureType = Ext.form.Action.CLIENT_INVALID;
            this.form.afterAction(this, false);
        }
    },
    
    // private
    handleResponse : function(response){
        if(this.form.errorReader){
            var rs = this.form.errorReader.readRecords(response);
            var errors = [];
            if(rs.records){
                for(var i = 0, len = rs.records.length; i < len; i++) {
                    var r = rs.records[i];
                    errors[i] = r.data;
                }
            }
            if(errors.length < 1){
                errors = null;
            }
            return {
                success : rs.success,
                errors : errors
            };
        }
        return response;
    }
});
Ext.apply(Ext.ux.form.DWRAction.Submit.prototype, Ext.ux.form.DWRAction.prototype);

/**
 * A class which handles loading of data from a server into the Fields of
 * an {@link Ext.form.BasicForm}. 
 * 
 * @author  韦海晗
 * @class Ext.ux.form.DWRAction.Load
 * @extends Ext.form.Action.Load
 */
Ext.ux.form.DWRAction.Load = function(form, options){
    Ext.ux.form.DWRAction.Load.superclass.constructor.call(this, form, options);
    this.reader = this.form.reader;
};

Ext.extend(Ext.ux.form.DWRAction.Load, Ext.form.Action.Load, {
    // private
    type : 'dwrload',

    // private
    run : function(){
        var o = this.options;
    	if(o.remote !== false) {
    		var params = this.getParams();
        	if(this.reader){
        		Ext.apply(params, this.reader.meta);
        	}
        	o.remote(this, o, params["root"], params["successProperty"], params, this.createCallback(o));
    	}
    },
    
    // private
    handleResponse : function(response){
        if(this.form.reader){
            var rs = this.form.reader.readRecords(response);
            var data = rs.records && rs.records[0] ? rs.records[0].data : null;
            return {
                success : rs.success,
                data : data
            };
        }
        return response;
    }
    
});
Ext.apply(Ext.ux.form.DWRAction.Load.prototype, Ext.ux.form.DWRAction.prototype);