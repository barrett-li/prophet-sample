/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.org.dao;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.apache.commons.lang.StringUtils;

import com.resoft.prophet.org.domain.Department;

/**
 * 部门DAO。<br/>
 *
 * @author 伍军军
 */
public class DepartmentDAO extends SqlMapClientDaoSupport {
	/**
	 * 新增部门。<br/>
	 * 
	 * @param department 部门
	 */
    public void insertDept(Department department) {
        getSqlMapClientTemplate().insert("Department.insertDept", department);
    }
    
	/**
	 * 删除部门。<br/>
	 * 
	 * @param id 部门ID
	 */
    public void deleteDept(String id) {
        getSqlMapClientTemplate().delete("Department.deleteDept", id);
    }
    
	/**
	 * 更新部门。<br/>
	 * 
	 * @param department 部门
	 */
    public void updateDept(Department department) {
        getSqlMapClientTemplate().update("Department.updateDept", department);
    }
    
	/**
	 * 根据部门ID获得部门。<br/>
	 * 
	 * @param id 部门ID
	 * 
	 * @return 部门
	 */
    public Department getDept(String id) {
        return (Department) getSqlMapClientTemplate().queryForObject("Department.getDept", id);
    }
    
	/**
	 * 获得全部部门列表。<br/>
	 * 
	 * @param sort 列表排序字段
	 * @param direction 列表排序方式, 'ASC'或'DESC'
	 * 
	 * @return 部门列表
	 */
    public List getAllDepts(String sort, String direction) {
        Map map = new HashMap();
        map.put("sort", sort);
        map.put("direction", direction);
        return getSqlMapClientTemplate().queryForList("Department.getAllDepts", map);
    }
    
	/**
	 * 根据部门全称或简称获得部门列表。<br/>
	 * 
	 * @param fullName 部门全称
	 * @param shortName 部门简称
	 * @param firstResult 列表起始位置
	 * @param maxResults 列表最大记录数
	 * @param sort 列表排序字段
	 * @param direction 列表排序方式, 'ASC'或'DESC'
	 * 
	 * @return 部门列表
	 */
    public List getDepts(String fullName, String shortName, Integer firstResult, Integer maxResults, String sort, String direction) {
        Map map = new HashMap();
        map.put("fullName", fullName);
        map.put("shortName", shortName);
        map.put("firstResult", firstResult);
        map.put("maxResults", maxResults);
        map.put("sort", sort);
        map.put("direction", direction);
        return getSqlMapClientTemplate().queryForList("Department.getDepts", map);
    }
    
	/**
	 * 根据部门全称或简称统计部门记录数。<br/>
	 * 
	 * @param fullName 部门全称
	 * @param shortName 部门简称
	 * 
	 * @return 部门记录数
	 */
    public Integer countDepts(String fullName, String shortName) {
        Map map = new HashMap();
        map.put("fullName", fullName);
        map.put("shortName", shortName);
        return (Integer) getSqlMapClientTemplate().queryForObject("Department.countDepts", map);
	}
    
	/**
	 * 判断部门已经存在。<br/>
	 * 
	 * @param department 部门
	 * 
	 * @return boolean
	 */
    public boolean isDeptExisted(Department department) {
        String id = department.getId();
        String fullName = department.getFullName();
        if (StringUtils.isNotBlank(id) && StringUtils.isNotBlank(fullName)) {
            Map map = new HashMap();
            map.put("id", id);
            map.put("fullName", fullName);
            List depts = getSqlMapClientTemplate().queryForList("Department.isDeptExisted", map);
            if (depts != null && depts.size() > 0) {
                return true;
            }
        }
        return false;
    }
}
