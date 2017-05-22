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
 * 分页用表格数据集(java.util.Map)构造器
 * 
 * @author 韦海晗
 */
public interface GridDatasetProducer {
	/**
	 * 构造用于分页的表格数据集(java.util.Map).
	 * @param start  当前页起始行
	 * @param limit  当前页结束行
	 * @param sort  排序的列
	 * @param direction  排序顺序，'ASC'或者'DESC'
	 * @param root  表格数据集根节点键名，用于设置结果集的键
	 * @param totalProperty  表格数据集行数节点键名，用于设置行数的键.
	 * @param successProperty  表示构造是否成功键名，用于设置构造成功的键.
	 * @param params  查询参数. 
	 * @return 分页的表格数据集(java.util.Map)，该数据集被json协议序列化后的表示：   { 'totalProperty': 2, 'successProperty': true, 'root': [
	 *   { 'id': 1, 'name': 'Bill' },
	 *   { 'id': 2, 'name': 'Ben' } ]
	 * }.
	 * @exception Exception.
	 */
	public Map produceDataset(Integer start, Integer limit, String sort, String direction, String root, String totalProperty, String successProperty, Map params) throws Exception;
	
	/**
	 * 初始化查询参数.
	 * @param params  查询参数.
	 */
	public void init(Map params);
	
	/**
	 * 构造数据集是否成功.
	 * @return 默认,new Boolean(true).
	 */
	public Boolean isSuccess();
	
	/**
	 * 获取构造数据集的行数.
	 * @param params  查询参数.
	 * @return 默认,new Boolean(true).
	 * @exception Exception.
	 */
	public Integer getTotalRecords(Map params) throws Exception;
	
	/**
	 * 获取构造数据集的结果集.
	 * @param start  当前页起始行
	 * @param limit  当前页结束行
	 * @param sort  排序的列
	 * @param direction  排序顺序，'ASC'或者'DESC'
	 * @param params  查询参数. 
	 * @return 查询结果集(java.util.List).
	 * @exception Exception.
	 */
	public List getRecords(Integer start, Integer limit, String sort, String direction, Map params) throws Exception;
}
