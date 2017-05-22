/**
 * Ext JS Library 2.0 Extend
 * BaseBox Widget Libraries
 * weihaihan@gmail.com
 *
 * http://sourceforge.net/projects/basebox
 */

Ext.namespace('Ext.ux.JFreeChart');
Ext.ux.JFreeChart.prototype = {
	
	emptyText: 'No images to display',
	
	tpl: new Ext.XTemplate(
		'<tpl for=".">',
            '<div class="thumb-wrap" id="{name}">',
            '<div>{usemapContent}</div>',
		    '<div class="thumb"><img src="{src}" title="{name}" width="{width}" height="{height}" usemap="{usemapName}" border="0" ></div>',
        '</tpl>',
        '<div class="x-clear"></div>'
	),
	
	meta: {
		totalProperty: 'totalCount',
		root: 'images',
		id: 'name',
	    fields: [
        	{name: 'name', mapping: 'name'},
        	{name: 'src', mapping: 'src'},
            {name: 'width', mapping: 'width'},
            {name: 'height', mapping: 'height'},
            {name: 'usemapName', mapping: 'usemapName'},
            {name: 'usemapContent', mapping: 'usemapContent'}
        ]
	},
	
	maskText: 'Loading...',
	
	loadMask: false,

	init: function(){
		this.results = {};
		this.chartsMeta = false;
		
        // check and correct shorthanded configs
        if(this.ds){
            this.store = this.ds;
            delete this.ds;
        }
        this.store = Ext.StoreMgr.lookup(this.store);

        this.view = this.getView();
        this.add(this.view);
	},

    getView : function(){
        if(!this.view){
			this.view = new Ext.DataView({
	            tpl: this.tpl,
	            autoHeight: true,
	            multiSelect: false,
	            overClass: 'x-view-over',
	            itemSelector: 'div.thumb-wrap',
	            emptyText: this.emptyText
	        });
	        
			this.view.on('render', this.onViewRender, this);
			this.store.on('beforeload', this.onBeforeLoad, this);
			this.store.on('load', this.onLoad, this);
			
			this.chartsMeta = this.store.reader.meta.chartsProperty;
			if(this.chartsMeta) {
			    this.view.store = new Ext.data.Store({
			        reader: new Ext.data.JsonReader(this.meta)
			    });
			} else {
				this.view.store = this.store;
			}
        }
        return this.view;
    },
    
    refreshChart: function() {
    	if(this.chartsMeta) {
    		this.view.store.loadData(this.results);
    	} else {
    		this.view.store.reload();
    	}
    },
    
    onBeforeLoad: function(store, options) {
    	if(this.loadMask){
    		this.body.mask(this.maskText, 'x-mask-loading');
    	}
	},
    
    onViewRender: function(view) {
    	if(this.loadMask){
    		this.body.mask(this.maskText, 'x-mask-loading');
    	}
    },
    
    onLoad: function(store, records, options) {
    	if(this.chartsMeta) {
			this.results[this.view.store.reader.meta.root] = store.reader.jsonData[this.chartsMeta];
			this.view.store.loadData(this.results);
    	}
		if(this.loadMask){
			this.body.unmask(false);
		}
	}
};

/**
 * 该类实现 {@link Ext.ux.JFreeChartPanel} 显示JFreeChart图表的Panel。
 * 
 * @author  韦海晗
 * @class Ext.ux.JFreeChartPanel
 * @extends Ext.Panel
 */
Ext.ux.JFreeChartPanel = Ext.extend(Ext.Panel, {
	
	ctype : "Ext.ux.Panel.JFreeChart",
	
	initComponent: function(){
		Ext.ux.JFreeChartPanel.superclass.initComponent.apply(this, arguments);
		//
		this.init();
	}
});

Ext.apply(Ext.ux.JFreeChartPanel.prototype, Ext.ux.JFreeChart.prototype);
Ext.reg('jfreechartpanel', Ext.ux.JFreeChartPanel);

/**
 * 该类实现 {@link Ext.ux.JFreeChartWindow} 显示JFreeChart图表的Window。
 * 
 * @author  韦海晗
 * @class Ext.ux.JFreeChartWindow
 * @extends Ext.Window
 */
Ext.ux.JFreeChartWindow = Ext.extend(Ext.Window, {
	
	ctype : "Ext.ux.Window.JFreeChart",

	resizeImage: false,
	
	closeAction: 'hide',
	
	initComponent: function(){
		Ext.ux.JFreeChartWindow.superclass.initComponent.apply(this, arguments);
		this.init();
		//
        if(this.resizeImage){
			this.on('resize', function(win, width, height) {
		    	if(this.view.el) {
		    		var w = win.body.getWidth();
		    		var h = win.body.getHeight();
			    	if(this.chartsMeta) {
			    		this.view.store.loadData(this.results);
		        		this.view.el.select('img').setWidth(w);
		        		this.view.el.select('img').setHeight(h);
			    	} else {
						Ext.apply(this.view.store.baseParams, {width: w, height: h});
		        		this.view.store.reload();
			    	}
		    	}
		    }, this);
        }
	}
});

Ext.apply(Ext.ux.JFreeChartWindow.prototype, Ext.ux.JFreeChart.prototype);
Ext.reg('jfreechartwindow', Ext.ux.JFreeChartWindow);