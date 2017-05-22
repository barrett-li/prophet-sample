/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.widgets.extjs.data;

/**
 * Ext.CheckTreeNode值对象
 * String value; // The value for this check
 * String name; // The text for this check
 * 
 * @author 韦海晗
 */
public class CheckTreeNode extends TreeNode {
	protected String name; // The name for this check
	protected String value; // The value for this check

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
