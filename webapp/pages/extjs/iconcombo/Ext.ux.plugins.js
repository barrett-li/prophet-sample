/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

// create namespace for plugins
Ext.namespace('Ext.ux.plugins');

/**
 * Ext.ux.plugins.IconCombo plugin for Ext.form.Combobox
 *
 * @author  Ing. Jozef Sakalos
 * @date    January 7, 2008
 *
 * @class Ext.ux.plugins.IconCombo
 * @extends Ext.util.Observable
 */
Ext.ux.plugins.IconCombo = function(config) {
    Ext.apply(this, config);
};
 
// plugin code
Ext.extend(Ext.ux.plugins.IconCombo, Ext.util.Observable, {
    init:function(combo) {
        Ext.apply(combo, {
            tpl: '<tpl for=".">'
                + '<div class="x-combo-list-item ux-icon-combo-item '
                + '{' + combo.iconClsField + '}">'
                + '{' + combo.displayField + '}'
                + '</div></tpl>',
                
 			// createSequence，Ext添加的Function类原型的方法。接受两个参数，第一个是函数，第二个表示第一个参数的范围。
 			// 两个函数使用相同的参数，调用函数先执行，然后执行形参函数，返回调用函数。
            onRender: combo.onRender.createSequence(function(ct, position) {
                // adjust styles
                this.wrap.applyStyles({position:'relative'});
                this.el.addClass('ux-icon-combo-input');
 
                // add div for icon
                this.icon = Ext.DomHelper.append(this.el.up('div.x-form-field-wrap'), {
                    tag: 'div', style:'position:absolute'
                });
            }), // end of function onRender
 
            setIconCls: function() {
                var rec = this.store.query(this.valueField, this.getValue()).itemAt(0);
                if(rec) {
                    this.icon.className = 'ux-icon-combo-icon ' + rec.get(this.iconClsField);
                }
            }, // end of function setIconCls
 
            setValue: combo.setValue.createSequence(function(value) {
                this.setIconCls();
            })
        });
    } // end of function init
}); // end of extend
 
// end of file