Docs = {};
Docs.HOME_ID = 'home';

ApiPanel = function() {

    ApiPanel.superclass.constructor.call(this, {
        id:'api-tree',
        region:'west',
        split:true,
        width: 200,
        minSize: 175,
        maxSize: 500,
        collapsible: true,
        margins:'0 0 5 5',
        cmargins:'0 0 0 0',
        rootVisible:false,
        lines:false,
        autoScroll:true,
        animCollapse:false,
        animate: false,
        collapseMode:'mini',
        loader: new Ext.tree.TreeLoader({
			preloadChildren: true,
			clearOnLoad: false
		}),
		root: new Ext.tree.AsyncTreeNode({
		    text: '首页',
		    id: Docs.HOME_ID,
		    expanded: true,
		    children: [Docs.datas[0]]
		}),
		collapseFirst:false
    });
    // no longer needed!
    //new Ext.tree.TreeSorter(this, {folderSort:true,leafAttr:'isClass'});

    this.getSelectionModel().on('beforeselect', function(sm, node){
        return node.isLeaf();
    });
};

Ext.extend(ApiPanel, Ext.tree.TreePanel, {
    initComponent : function(){
    	var api = this;
    	
		var filter = new Ext.tree.TreeFilter(api, {
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
			api.expandAll();
			
			var re = new RegExp('^' + Ext.escapeRe(text), 'i');
			filter.filterBy(function(n){
				return !n.attributes.leaf || re.test(n.text);
			});
			
			// hide empty packages that weren't filtered
			hiddenPkgs = [];
			api.root.cascade(function(n){
				if(!n.attributes.leaf && n.ui.ctNode.offsetHeight < 3){
					n.ui.hide();
					hiddenPkgs.push(n);
				}
			});
		}
    	this.tbar = [{
                iconCls: 'icon-expand-all',
				tooltip: '全部展开',
                handler: function(){ api.root.expand(true); }
            }, {
                iconCls: 'icon-collapse-all',
                tooltip: '全部关闭',
                handler: function(){ api.root.collapse(true); }
            }, '', 
			new Ext.form.TextField({
				width: 150,
				emptyText:'过滤菜单',
				listeners:{
					render: function(f){
						f.el.on('keydown', filterTree, f, {buffer: 350});
					}
				}
			})
		];
		
        ApiPanel.superclass.initComponent.call(this);
    },
    
    selectClass : function(cls){
        if(cls){
            var clss = cls.split('.');
            var last = clss.length;
            var parts = [];
            for(var i = 0; i < last; i++){
				parts[i] = (parts[i - 1] ? (parts[i - 1] + '.') : '') + clss[i];
            }
            this.selectPath(['/', Docs.HOME_ID, '/'].join('') + parts.join('/'));
        }
    },
    
    reloadData: function(jsonData){
    	var node = this.getRootNode();
    	node.attributes.children = [jsonData];
    	node.reload();
    }
});


DocPanel = Ext.extend(Ext.Panel, {
    closable: true,
    autoScroll:true,

    // private
    onRender : function(ct, position){
        DocPanel.superclass.onRender.call(this, ct, position);
		var buf = ['<iframe ', 
			'name="iframe-', this.id , '" ',
			'src="', this.href , '" ',
			'width="100%" height="100%" frameborder="no" />'];
        this.iframe = this.body.createChild({html: buf.join('')});
    },
    // private
    onDestroy : function() {
    	DocPanel.superclass.onDestroy.call(this);
    	this.iframe.remove();
    }
    
});


MainPanel = function(){
	
    MainPanel.superclass.constructor.call(this, {
        id:'doc-body',
        region:'center',
        margins:'0 5 5 0',
        resizeTabs: true,
        minTabWidth: 135,
        tabWidth: 135,
        plugins: new Ext.ux.TabCloseMenu(),
        enableTabScroll: true,
        activeTab: 0,

        items: new DocPanel({
            id:'docs-' + Docs.HOME_ID,
            title: '首页',
            cclass : Docs.HOME_ID,
            href: ROOT_PATH + '/pages/frame/center.jsp',
            iconCls:'icon-home',
            autoScroll: true,
            closable: false
        })
    });
};

Ext.extend(MainPanel, Ext.TabPanel, {
    loadClass : function(href, title, cls){
        var id = 'docs-' + cls;
        var tab = this.getComponent(id);
        if(tab){
            this.setActiveTab(tab);
        }else{
            var p = this.add(new DocPanel({
                id: id,
                title: title,
                cclass : cls,
                href: href,
                iconCls: Docs.icons[cls]
            }));
            this.setActiveTab(p);
        }
    }
});


Ext.onReady(function(){

    Ext.QuickTips.init();

    var api = new ApiPanel();
    var mainPanel = new MainPanel();

    api.on('click', function(node, e){
         if(node.isLeaf()){
            e.stopEvent();
            mainPanel.loadClass(node.attributes.href, node.text, node.id);
         }
    });

    mainPanel.on('tabchange', function(tp, tab){
        api.selectClass(tab.cclass); 
    });

	var reloadApi = function(jsonData, expandId) {
		api.reloadData(jsonData);
		api.expandPath(['/', Docs.HOME_ID, '/', expandId].join(''));
        mainPanel.items.each(function(item){
            if(item.closable){
                mainPanel.remove(item);
            }
        });
	}
	
	var activate = function(n){
		if(n == 0){
			mainPanel.activate(0);
			return;
		}
    	var ctxItem = mainPanel.getActiveTab();
    	var i = 0;
        mainPanel.items.each(function(item){
            if(item.id == ctxItem.id){
                mainPanel.activate(i + n); 
            }
            i++;
        });
	}
	
    var hd = new Ext.Panel({
        border: false,
        layout:'anchor',
        region:'north',
        cls: 'docs-header',
        height:60,
        items: [{
            xtype:'box',
            el:'header',
            border:false,
            anchor: 'none -25'
        },
        new Ext.Toolbar({
            cls:'top-toolbar',
            items:['-', {
	            text:'功能演示',
	            iconCls: 'icon-example',
	            handler: function(b, pressed){
	            	reloadApi(Docs.datas[0], 'sample');
	            }
        	}, '-', {
	            text:'系统管理',
	            iconCls: 'icon-system',
	            handler: function(b, pressed){
					reloadApi(Docs.datas[1], 'system');
				}
        	}, '-', '->', {
                tooltip: '首页',
                iconCls: 'icon-home',
                handler : function(b, pressed){
                	activate(0);
                }
            }, '-', {
                tooltip: '后退',
                iconCls: 'icon-prev',
                handler : function(b, pressed){
  					activate(-1);
                }
            }, '-', {
                tooltip: '前进',
                iconCls: 'icon-next',
                handler : function(b, pressed){
					activate(1);
                }
            }, '-', {
                tooltip: '打印',
                iconCls: 'icon-print',
                handler : function(b, pressed){
                	var ctxItem = mainPanel.getActiveTab();
                	var iframe = ctxItem.iframe.dom.getElementsByTagName('iframe')[0].name;
                	window.parent.frames[iframe].print();
                }
            }, '-', {
                tooltip: '邮件',
                iconCls: 'icon-mail',
                handler : function(b, pressed){
                }
            }, '-', {
                tooltip: '关联图谱',
                iconCls: 'icon-sivukartta',
                handler : function(b, pressed){
                }
            }, '-', {
                tooltip: '记事本',
                iconCls: 'icon-tuotetiedot',
                handler : function(b, pressed){
                }
            }, '-', {
                tooltip: '系统帮助',
                iconCls: 'icon-help',
                handler : function(b, pressed){
                }
            }, '-', {
                tooltip: '退出系统',
                iconCls: 'icon-exit',
                handler : function(b, pressed){
                }
            }, '-']
        })]
    })

    var viewport = new Ext.Viewport({
        layout:'border',
        items:[ hd, api, mainPanel ]
    });

    api.expandPath(['/', Docs.HOME_ID, '/sample'].join(''));

    viewport.doLayout();
	
	setTimeout(function(){
        Ext.get('loading').remove();
        Ext.get('loading-mask').fadeOut({remove:true});
    }, 250);
});