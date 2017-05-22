/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.charts.sample.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * 图表演示数据，银行贷款DAO。
 * 
 * @author 韦海晗
 */
public class BankLoanDAO extends SqlMapClientDaoSupport {
	
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
	public List getBankLoans(String area, String org, Integer firstResult, Integer maxResults, String sort, String direction) {
        Map map = new HashMap();
        map.put("area", area);
        map.put("org", org);
        map.put("firstResult", firstResult);
        map.put("maxResults", maxResults);
        map.put("sort", sort);
        map.put("direction", direction);
        return getSqlMapClientTemplate().queryForList("Charts.getBankLoans", map);
	}
	
	/**
	 * 返回银行贷款数据记录数。<br/>
	 * 
	 * @param area 地区
	 * @param org 机构
	 * 
	 * @return 银行贷款记录数
	 */
    public Integer countBankLoans(String area, String org) {
        Map map = new HashMap();
        map.put("area", area);
        map.put("org", org);
        return (Integer) getSqlMapClientTemplate().queryForObject("Charts.countBankLoans", map);
    }
}
