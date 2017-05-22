/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.security.web;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.resoft.prophet.security.domain.Menu;
import com.resoft.prophet.security.service.SecurityService;
import com.resoft.prophet.web.DWRService;
import com.resoft.prophet.widgets.extjs.data.TreeNode;

/**
 * 安全框架DWR服务类。
 * 
 * @author 韦海晗
 */
public class SecurityDWRService extends DWRService {
	private SecurityService securityService;

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}

	/**
	 * 获取菜单TreeNode列表(java.util.List)。
	 * 
	 * @param node 当前node
	 * @param params 查询参数
	 * 
	 * @return TreeNode列表(java.util.List)
	 * 
	 * @exception Exception
	 */
	public List getMenuTreeNodes(TreeNode node, Map params) throws Exception {
		Object uiProvider = params.get("uiProvider");
		Object hrefTarget = params.get("hrefTarget");

		List treeNodes = new ArrayList();
		// 获取父结点id
		String parentId = node.getId();
		// 获取用户信息
		String userId = getUser().getId();
		// 获取当前登录用户的菜单
		List menus = securityService.getMenusByParentId(parentId, userId);
		// 组装TreeNode
		for (Iterator it = menus.iterator(); it.hasNext();) {
			Menu menu = (Menu) it.next();
			TreeNode treeNode = new TreeNode();
			// 远程获取子节点
			treeNode.setChildren(null);
			// 设置uiProvider属性
			if (uiProvider != null) {
				treeNode.setUiProvider((String) uiProvider);
			}
			if (hrefTarget != null) {
				treeNode.setHrefTarget((String) hrefTarget);
			}
			treeNode.setId(menu.getId());
			treeNode.setText(menu.getName());
			treeNode.setQtip(menu.getNote());
			String href = menu.getHref();
			if (StringUtils.isBlank(href)) {
				treeNode.setHref(href);
			} else {
				treeNode.setHref(getRequest().getContextPath() + menu.getHref());
			}
			treeNode.setLeaf(new Boolean(menu.isLeaf()));
			treeNodes.add(treeNode);
		}
		return treeNodes;
	}
}
