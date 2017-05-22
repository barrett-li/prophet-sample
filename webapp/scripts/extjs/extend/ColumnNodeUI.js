/**
 * Ext JS Library 2.0 Extend
 * BaseBox Widget Libraries
 * weihaihan@gmail.com
 *
 * http://sourceforge.net/projects/basebox
 */

Ext.namespace('Ext.ux.tree.ColumnTree');
Ext.ux.tree.ColumnTree = Ext.extend(Ext.tree.TreePanel, {
    lines: false,
    borderWidth: Ext.isBorderBox ? 2 : 4, // the combined left/right border for each cell
    cls : 'x-column-tree',
    scrollOffset : 19,
    
    onRender : function(){
        Ext.ux.tree.ColumnTree.superclass.onRender.apply(this, arguments);
		this.headers = this.body.createChild({cls : 'x-tree-headers'}, this.innerCt.dom);
		var cols = this.columns
		var totalWidth = 0;

        for(var i = 0, len = cols.length; i < len; i++){
			var c = cols[i];
			var w = c.width - this.borderWidth;
			totalWidth += w;
			this.headers.createChild({
			 	cls : 'x-tree-hd ' + (c.cls ? c.cls + '-hd' : ''),
				cn : {
					cls : 'x-tree-hd-text',
					html : c.header
				},
				style : 'width:' + w + 'px;'
			});
        }
        
        var ctWidth = totalWidth + this.borderWidth;
        
		this.headers.createChild({
			cls : 'x-tree-hd ',
			cn : {
				html : ''
			},
			style : 'width:' + (this.width - ctWidth) + 'px;'
		});

        this.headers.createChild({cls : 'x-clear'});
		// prevent floats from wrapping when clipped
		this.setInnerCtWidth(ctWidth);
        this.innerCt.on('resize', function() {
			this.setInnerCtWidth(ctWidth);
        }, this);
    },
    
    // private
    setInnerCtWidth: function(ctWidth) {
    	var maxWidth = this.width - this.borderWidth;
    	if (this.body.isScrollable()) {
    		maxWidth = this.width - this.scrollOffset;
    	};
    	var w = ctWidth > maxWidth ? ctWidth : maxWidth;
		this.headers.setWidth(w);
		this.innerCt.setWidth(w);
    }
});

Ext.ux.tree.ColumnTreeNodeUI = Ext.extend(Ext.tree.TreeNodeUI, {
    focus: Ext.emptyFn, // prevent odd scrolling behavior

    renderElements : function(n, a, targetNode, bulkRender){
        this.indentMarkup = n.parentNode ? n.parentNode.ui.getChildIndent() : '';

    	var tree = n.getOwnerTree();
		var cols = tree.columns;
		var bw = tree.borderWidth;
		var c = cols[0];

        var href = a.href ? a.href : Ext.isGecko ? '' : '#';
		var buf = ['<li class="x-tree-node"><div ext:tree-node-id="', n.id, '" class="x-tree-node-el x-tree-node-leaf x-unselectable ', a.cls, '" unselectable="on">',
			'<div class="x-tree-col" style="width:', c.width - bw, 'px;">',
				'<span class="x-tree-node-indent">', this.indentMarkup, '</span>',
				'<img src="', this.emptyIcon, '" class="x-tree-ec-icon x-tree-elbow">',
				'<img src="',a.icon || this.emptyIcon, '" class="x-tree-node-icon', (a.icon ? " x-tree-node-inline-icon" : ''), (a.iconCls ? " " + a.iconCls : ''), '" unselectable="on">',
				'<a hidefocus="on" class="x-tree-node-anchor" href="',href,'" tabIndex="1" ',
				a.hrefTarget ? ' target="' + a.hrefTarget + '"' : '', '>',
				'<span unselectable="on">', n.text || (a[c.dataIndex] ? (c.renderer ? c.renderer(a[c.dataIndex], n, a) : a[c.dataIndex]) : ''), '&nbsp;</span></a>',
			"</div>"];
        for (var i = 1, len = cols.length; i < len; i++) {
			c = cols[i];
			buf.push('<div class="x-tree-col ', (c.cls ? c.cls : ''), '" style="width:', c.width - bw, 'px;">',
					'<div class="x-tree-col-text">', (a[c.dataIndex] ? (c.renderer ? c.renderer(a[c.dataIndex], n, a) : a[c.dataIndex]) : ''), '&nbsp;</div>',
				"</div>");
        }
		buf.push('<div class="x-clear"></div>',
			'</div>',
			'<ul class="x-tree-node-ct" style="display:none;"></ul>',
			"</li>");

		if (bulkRender !== true && n.nextSibling && n.nextSibling.ui.getEl()) {
			this.wrap = Ext.DomHelper.insertHtml('beforeBegin', n.nextSibling.ui.getEl(), buf.join(''));
		} else {
			this.wrap = Ext.DomHelper.insertHtml('beforeEnd', targetNode, buf.join(''));
		}

        this.elNode = this.wrap.childNodes[0];
        this.ctNode = this.wrap.childNodes[1];
        var cs = this.elNode.firstChild.childNodes;
        
		this.indentNode = cs[0];
		this.ecNode = cs[1];
		this.iconNode = cs[2];
		var index = 3;
		this.anchor = cs[index];
		this.textNode = cs[index].firstChild;
    }
});

Ext.ux.tree.ColumnTreeCheckNodeUI = Ext.extend(Ext.ux.tree.TreeCheckNodeUI, {
    focus: Ext.emptyFn, // prevent odd scrolling behavior

    renderElements : function(n, a, targetNode, bulkRender){
        this.indentMarkup = n.parentNode ? n.parentNode.ui.getChildIndent() : '';

    	var tree = n.getOwnerTree();
		this.checkModel = tree.checkModel || this.checkModel;
		this.checkName = a.checkName || tree.checkName || this.checkName;
		this.onlyLeafCheckable = tree.onlyLeafCheckable || false;

		var cols = tree.columns;
		var bw = tree.borderWidth;
		var c = cols[0];

		cb = (!this.onlyLeafCheckable || a.leaf);
        var href = a.href ? a.href : Ext.isGecko ? '' : '#';
        var value = a.value ? a.value : '';
		var buf = ['<li class="x-tree-node"><div ext:tree-node-id="', n.id, '" class="x-tree-node-el x-tree-node-leaf x-unselectable ', a.cls, '" unselectable="on">',
			'<div class="x-tree-col" style="width:', c.width - bw, 'px;">',
				'<span class="x-tree-node-indent">', this.indentMarkup, '</span>',
				'<img src="', this.emptyIcon, '" class="x-tree-ec-icon x-tree-elbow">',
				'<img src="',a.icon || this.emptyIcon, '" class="x-tree-node-icon', (a.icon ? " x-tree-node-inline-icon" : ''), (a.iconCls ? " " + a.iconCls : ''), '" unselectable="on">',
				cb ? ('<input class="x-tree-node-cb" value="' + value + '" name="' + this.checkName + '" type="' + (this.checkModel == 'single' ? 'radio' : 'checkbox') + '" ' + (a.checked ? 'checked="checked" />' : '/>')) : '',
				'<a hidefocus="on" class="x-tree-node-anchor" href="',href,'" tabIndex="1" ',
				a.hrefTarget ? ' target="' + a.hrefTarget + '"' : '', '>',
				'<span unselectable="on">', n.text || (a[c.dataIndex] ? (c.renderer ? c.renderer(a[c.dataIndex], n, a) : a[c.dataIndex]) : ''), '&nbsp;</span></a>',
			"</div>"];
        for (var i = 1, len = cols.length; i < len; i++) {
			c = cols[i];
			buf.push('<div class="x-tree-col ', (c.cls ? c.cls : ''), '" style="width:', c.width - bw, 'px;">',
					'<div class="x-tree-col-text">', (a[c.dataIndex] ? (c.renderer ? c.renderer(a[c.dataIndex], n, a) : a[c.dataIndex]) : ''), '&nbsp;</div>',
				"</div>");
        }
		buf.push('<div class="x-clear"></div>',
			'</div>',
			'<ul class="x-tree-node-ct" style="display:none;"></ul>',
			"</li>");

		if (bulkRender !== true && n.nextSibling && n.nextSibling.ui.getEl()) {
			this.wrap = Ext.DomHelper.insertHtml('beforeBegin', n.nextSibling.ui.getEl(), buf.join(''));
		} else {
			this.wrap = Ext.DomHelper.insertHtml('beforeEnd', targetNode, buf.join(''));
		}

        this.elNode = this.wrap.childNodes[0];
        this.ctNode = this.wrap.childNodes[1];
        var cs = this.elNode.firstChild.childNodes;
        
		this.indentNode = cs[0];
		this.ecNode = cs[1];
		this.iconNode = cs[2];
		var index = 3;
        if(cb){
            this.checkbox = cs[3];
            Ext.fly(this.checkbox).on('click', this.check.createDelegate(this, [null]));
            index++;
        }
		this.anchor = cs[index];
		this.textNode = cs[index].firstChild;
    }
});