/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.widgets.extjs.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Ext.TreeNode值对象
 * 属性列表说明
 * @cfg {String} text The text for this node
 * @cfg {Boolean} expanded true to start the node expanded
 * @cfg {Boolean} allowDrag False to make this node undraggable if {@link #draggable} = true (defaults to true)
 * @cfg {Boolean} allowDrop False if this node cannot have child nodes dropped on it (defaults to true)
 * @cfg {Boolean} disabled true to start the node disabled
 * @cfg {String} icon The path to an icon for the node. The preferred way to do this
 * is to use the cls or iconCls attributes and add the icon via a CSS background image.
 * @cfg {String} cls A css class to be added to the node
 * @cfg {String} iconCls A css class to be added to the nodes icon element for applying css background images
 * @cfg {String} href URL of the link used for the node (defaults to #)
 * @cfg {String} hrefTarget target frame for the link
 * @cfg {String} qtip An Ext QuickTip for the node
 * @cfg {Boolean} expandable If set to true, the node will always show a plus/minus icon, even when empty
 * @cfg {String} qtipCfg An Ext QuickTip config for the node (used instead of qtip)
 * @cfg {Boolean} singleClickExpand True for single click expand on this node
 * @cfg {Function} uiProvider A UI <b>class</b> to use for this node (defaults to Ext.tree.TreeNodeUI)
 * @cfg {Boolean} checked True to render a checked checkbox for this node, false to render an unchecked checkbox
 * (defaults to undefined with no checkbox rendered)
 * @cfg {Boolean} draggable True to make this node draggable (defaults to false)
 * @cfg {Boolean} isTarget False to not allow this node to act as a drop target (defaults to true)
 * @cfg {Boolean} allowChildren False to not allow this node to have child nodes (defaults to true)
 * @cfg {constructor} children Collection of references to all child nodes
 * 
 * @author 韦海晗
 */
public class TreeNode {
	protected String id; // The id for this node
	protected String text; // The text for this node
	protected Boolean expanded; // true to start the node expanded
	protected Boolean allowDrag; // false to make this node undraggable if DD is on (defaults to true)
	protected Boolean allowDrop; // false if this node cannot be drop on
	protected Boolean disabled; // true to start the node disabled
	protected String icon; // The path to an icon for the node. The preferred way to do this is to use the cls or iconCls attributes and add the icon via a CSS background image.
	protected String cls; // A css class to be added to the node
	protected String iconCls; // A css class to be added to the nodes icon element for applying css background images
	protected String href; // URL of the link used for the node (defaults to #)
	protected String hrefTarget; // target frame for the link
	protected String qtip; // An Ext QuickTip for the node
	protected Boolean expandable; // If set to true, the node will always show a plus/minus icon, even when empty
	protected String qtipCfg; // An Ext QuickTip config for the node (used instead of qtip)
	protected Boolean singleClickExpand; // True for single click expand on this node
	protected String uiProvider; // A UI <b>class</b> to use for this node (defaults to Ext.tree.TreeNodeUI)
	protected Boolean checked; // True to render a checked checkbox for this node, false to render an unchecked checkbox
	protected Boolean draggable; // True to make this node draggable (defaults to false)
	protected Boolean isTarget; // False to not allow this node to act as a drop target (defaults to true)
	protected Boolean allowChildren; // False to not allow this node to have child nodes (defaults to true)
	protected Boolean leaf; // true if this node is left node
	protected List children = new ArrayList(); //Collection of references to all child nodes
	
    public TreeNode() {
    	super();
	}
	
    public TreeNode(String id, String text, String qtip, String href, String hrefTarget, Boolean leaf) {
    	this.id = id;
    	this.text = text;
    	this.qtip = qtip;
    	this.href = href;
    	this.hrefTarget = hrefTarget;
    	this.leaf = leaf;
	}

	public Boolean getAllowChildren() {
		return allowChildren;
	}

	public void setAllowChildren(Boolean allowChildren) {
		this.allowChildren = allowChildren;
	}

	public Boolean getAllowDrag() {
		return allowDrag;
	}

	public void setAllowDrag(Boolean allowDrag) {
		this.allowDrag = allowDrag;
	}

	public Boolean getAllowDrop() {
		return allowDrop;
	}

	public void setAllowDrop(Boolean allowDrop) {
		this.allowDrop = allowDrop;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public String getCls() {
		return cls;
	}

	public void setCls(String cls) {
		this.cls = cls;
	}

	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	public Boolean getDraggable() {
		return draggable;
	}

	public void setDraggable(Boolean draggable) {
		this.draggable = draggable;
	}

	public Boolean getExpandable() {
		return expandable;
	}

	public void setExpandable(Boolean expandable) {
		this.expandable = expandable;
	}

	public Boolean getExpanded() {
		return expanded;
	}

	public void setExpanded(Boolean expanded) {
		this.expanded = expanded;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getHrefTarget() {
		return hrefTarget;
	}

	public void setHrefTarget(String hrefTarget) {
		this.hrefTarget = hrefTarget;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getIsTarget() {
		return isTarget;
	}

	public void setIsTarget(Boolean isTarget) {
		this.isTarget = isTarget;
	}

	public Boolean getLeaf() {
		return leaf;
	}

	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}

	public String getQtip() {
		return qtip;
	}

	public void setQtip(String qtip) {
		this.qtip = qtip;
	}

	public String getQtipCfg() {
		return qtipCfg;
	}

	public void setQtipCfg(String qtipCfg) {
		this.qtipCfg = qtipCfg;
	}

	public Boolean getSingleClickExpand() {
		return singleClickExpand;
	}

	public void setSingleClickExpand(Boolean singleClickExpand) {
		this.singleClickExpand = singleClickExpand;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUiProvider() {
		return uiProvider;
	}

	public void setUiProvider(String uiProvider) {
		this.uiProvider = uiProvider;
	}

	public List getChildren() {
		return children;
	}

	public void setChildren(List children) {
		this.children = children;
	}

	public void addChildNode(TreeNode treeNode) {
		this.children.add(treeNode);
	}
	
	public TreeNode getChildNode(int index) {
		return (TreeNode) this.children.get(index);
	}
}
