/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.charts.sample.service;

import java.util.List;

/**
 * 为图表提供数据服务。
 * 
 * @author 韦海晗
 */
public interface ChartsDataService {
	/**
	 * 返回银行贷款数据。
	 * 
	 * @param area 地区
	 * @param org 机构
	 * @param firstResult 列表起始位置
	 * @param maxResults 列表最大记录数
	 * @param sort 列表排序字段
	 * @param direction 列表排序方式, 'ASC'或'DESC'
	 * @return 图表演示数据。
	 */
	public List getBankLoans(String area, String org, Integer firstResult, Integer maxResults, String sort, String direction);
	
	/**
	 * 返回银行贷款数据记录数。<br/>
	 * 
	 * @param area 地区
	 * @param org 机构
	 * 
	 * @return 银行贷款记录数
	 */
    public Integer countBankLoans(String area, String org);
}
