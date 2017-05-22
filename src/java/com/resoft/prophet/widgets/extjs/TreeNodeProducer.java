/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.widgets.extjs;

import java.util.List;
import java.util.Map;

/**
 * TreeNode列表构造器
 * 
 * @author 韦海晗
 */
public interface TreeNodeProducer {
	/**
	 * 构造TreeNode列表(java.util.List).
	 * @param node  当前node的id
	 * @param params  查询参数.
	 * @return TreeNode列表(java.util.List).
	 * @exception Exception.
	 */
	public List produceTreeNodes(String node, Map params) throws Exception;
}
