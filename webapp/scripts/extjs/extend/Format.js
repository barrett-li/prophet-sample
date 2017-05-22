/**
 * Ext JS Library 2.0 Extend
 * BaseBox Widget Libraries
 * weihaihan@gmail.com
 *
 * http://sourceforge.net/projects/basebox
 */

/**
 * Ext.util.Format extend.
 * 
 * @author  韦海晗
 * @class Ext.ux.util.Format
 * @singleton
 */
Ext.namespace('Ext.ux.util.Format');
Ext.ux.util.Format = function(){
	var round = function(v) {
        v = (Math.round((v-0)*100))/100;
        v = (v == Math.floor(v)) ? v + ".00" : ((v*10 == Math.floor(v*10)) ? v + "0" : v);
		return v;
	};
	
	var money = function(v, prefix){
    	var f = prefix || '';
        v = String(round(v));
        var ps = v.split('.');
        var whole = ps[0];
        var sub = ps[1] ? '.'+ ps[1] : '.00';
        var r = /(\d+)(\d{3})/;
        while (r.test(whole)) {
            whole = whole.replace(r, '$1' + ',' + '$2');
        }
        v = whole + sub;
        if(v.charAt(0) == '-'){
            return '-' + f + v.substr(1);
        }
        return f +  v;
    };
    
	return {
	    /**
	     * Format a number as percent
	     * @param {Number/String} value The numeric value to format
	     * @param {Number/String} value The prefix to format
	     * @return {String} The formatted percent string
	     */
		percent: function(v) {
			if(v == null || isNaN(v) || v.length == 0) {
				return;
			}
			var s = String(round((v - 0) * 100));
			return s + "%";
		},
		
	    /**
	     * Format a number as currency
	     * @param {Number/String} value The numeric value to format
	     * @param {Number/String} value The prefix to format
	     * @return {String} The formatted currency string
	     */
	    money : money,
	    
	    /**
	     * Format a number as Chinese currency
	     * @param {Number/String} value The numeric value to format
	     * @return {String} The formatted currency string
	     */
	    cnMoney : function(v){
	        return money(v, '¥' /* '&#165;', '&#xA5;', '&yen;' or '¥' */);
	    }
	}
}();
