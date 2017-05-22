/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.security.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单实体。<br/>
 * 
 * @author 韦海晗
 */
public class Menu {
	private String id;
	private String name;
	private String note;
	private String href;
	private String sort;
	private boolean leaf;
	private List childNodes = new ArrayList();

	public void addChildNode(Menu menu) {
		childNodes.add(menu);
	}

	public List getChildNodes() {
		return childNodes;
	}

	public Menu getChildNode(int i) {
		return (Menu) childNodes.get(i);
	}

	public void setChildNodes(List childNodes) {
		this.childNodes = childNodes;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

}
