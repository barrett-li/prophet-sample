/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.org.dao;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.resoft.prophet.org.domain.Employee;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * 员工DAO。<br/>
 *
 * @author wujun
 */
public class EmployeeDAO extends SqlMapClientDaoSupport {
	/**
	 * 新增员工。<br/>
	 * 
	 * @param employee 员工
	 */
    public void insertEmployee(Employee employee) {
        getSqlMapClientTemplate().insert("Employee.insertEmployee", employee);
    }
    
	/**
	 * 删除员工。<br/>
	 * 
	 * @param id 员工ID
	 */
    public void deleteEmployee(String id) {
        getSqlMapClientTemplate().delete("Employee.deleteEmployee", id);
    }

	/**
	 * 更新员工。<br/>
	 * 
	 * @param employee 员工
	 */
    public void updateEmployee(Employee employee) {
        getSqlMapClientTemplate().update("Employee.updateEmployee", employee);
    }

	/**
	 * 根据员工ID获得员工。<br/>
	 * 
	 * @param id 员工ID
	 * 
	 * @return 员工
	 */
    public Employee getEmployee(String id) {
        return (Employee) getSqlMapClientTemplate().queryForObject("Employee.getEmployee", id);
    }

	/**
	 * 根据所属部门ID获得员工列表。<br/>
	 * 
	 * @param departmentId 所属部门ID
	 * 
	 * @return 员工列表
	 */
    public List getEmployees(String departmentId) {
        Map map = new HashMap();
        map.put("departmentId", departmentId);
        return getSqlMapClientTemplate().queryForList("Employee.getEmployees", map);
    }

	/**
	 * 根据员工全称或所属部门ID获得员工列表。<br/>
	 * 
	 * @param name 员工全称
	 * @param departmentId 所属部门ID
	 * @param firstResult 列表起始位置
	 * @param maxResults 列表最大记录数
	 * @param sort 列表排序字段
	 * @param direction 列表排序方式, 'ASC'或'DESC'
	 * 
	 * @return 员工列表
	 */
    public List getEmployees(String name, String departmentId, Integer firstResult, Integer maxResults, String sort, String direction) {
        Map map = new HashMap();
        map.put("name", name);
        map.put("departmentId", departmentId);
        map.put("firstResult", firstResult);
        map.put("maxResults", maxResults);
        map.put("sort", sort);
        map.put("direction", direction);
        return getSqlMapClientTemplate().queryForList("Employee.getEmployees", map);
    }
    
	/**
	 * 根据员工全称或所属部门ID统计部门记录数。<br/>
	 * 
	 * @param name 员工全称
	 * @param departmentId 所属部门ID
	 * 
	 * @return 员工记录数
	 */
    public Integer countEmployees(String name, String departmentId) {
        Map map = new HashMap();
        map.put("name", name);
        map.put("departmentId", departmentId);
        return (Integer) getSqlMapClientTemplate().queryForObject("Employee.countEmployees", map);
    }
}
