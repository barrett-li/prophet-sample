/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.org.service;

import java.util.List;

import com.resoft.prophet.exception.EntityExistedException;
import com.resoft.prophet.exception.HasRefEntitysException;
import com.resoft.prophet.org.dao.DepartmentDAO;
import com.resoft.prophet.org.dao.EmployeeDAO;
import com.resoft.prophet.org.domain.Department;
import com.resoft.prophet.org.domain.Employee;

/**
 * 组织机构管理服务接口实现类。<br/>
 *
 * @author 伍军军 
 */
public class OrgServiceImpl implements OrgService {
	
	private String deptExistedExceptionMessage;
	private String deptHasEmployeeExceptionMessage;
    private DepartmentDAO departmentDAO;
    private EmployeeDAO employeeDAO;
    
    public void setDeptExistedExceptionMessage(String deptExistedExceptionMessage) {
		this.deptExistedExceptionMessage = deptExistedExceptionMessage;
	}

	public void setDeptHasEmployeeExceptionMessage(String deptHasEmployeeExceptionMessage) {
		this.deptHasEmployeeExceptionMessage = deptHasEmployeeExceptionMessage;
	}
	
    public void setDepartmentDAO(DepartmentDAO departmentDAO) {
        this.departmentDAO = departmentDAO;
    }

    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

	
    /* (non-Javadoc)
     * @see com.resoft.prophet.org.service.OrgService#insertDept(com.resoft.prophet.org.domain.Department)
     */
    public void insertDept(Department department) throws EntityExistedException {
        if (departmentDAO.isDeptExisted(department)) {
            throw new EntityExistedException(deptExistedExceptionMessage);
        }
        departmentDAO.insertDept(department);
    }
    
	
    /* (non-Javadoc)
     * @see com.resoft.prophet.org.service.OrgService#deleteDept(java.lang.String)
     */
    public void deleteDept(String id) throws com.resoft.prophet.exception.HasRefEntitysException {
        List employees = employeeDAO.getEmployees(id);
        if (employees != null && employees.size() > 0) {
            throw new HasRefEntitysException(deptHasEmployeeExceptionMessage);
        }
        departmentDAO.deleteDept(id);
    }
    
	
    /* (non-Javadoc)
     * @see com.resoft.prophet.org.service.OrgService#updateDept(com.resoft.prophet.org.domain.Department)
     */
    public void updateDept(Department department) throws EntityExistedException {
        if (departmentDAO.isDeptExisted(department)) {
            throw new EntityExistedException(deptExistedExceptionMessage);
        }
        departmentDAO.updateDept(department);
    }
    
	
    /* (non-Javadoc)
     * @see com.resoft.prophet.org.service.OrgService#getDept(java.lang.String)
     */
    public Department getDept(String id) {
        return departmentDAO.getDept(id);
    }

    /* (non-Javadoc)
     * @see com.resoft.prophet.org.service.OrgService#getAllDepts(java.lang.String, java.lang.String)
     */
    public List getAllDepts(String sort, String direction) {
    	return departmentDAO.getAllDepts(sort, direction);
	}
	
    /* (non-Javadoc)
     * @see com.resoft.prophet.org.service.OrgService#getDepts(java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String)
     */
    public List getDepts(String fullName, String shortName, Integer firstResult, Integer maxResults, String sort, String direction) {
        return departmentDAO.getDepts(fullName, shortName, firstResult, maxResults, sort, direction);
    }
    
	
    /* (non-Javadoc)
     * @see com.resoft.prophet.org.service.OrgService#countDepts(java.lang.String, java.lang.String)
     */
    public Integer countDepts(String fullName, String shortName) {
		return departmentDAO.countDepts(fullName, shortName);
	}
    
	
    /* (non-Javadoc)
     * @see com.resoft.prophet.org.service.OrgService#insertEmployee(com.resoft.prophet.org.domain.Employee)
     */
    public void insertEmployee(Employee employee) {
        employeeDAO.insertEmployee(employee);
    }
    
	
    /* (non-Javadoc)
     * @see com.resoft.prophet.org.service.OrgService#deleteEmployee(java.lang.String)
     */
    public void deleteEmployee(String id) {
        employeeDAO.deleteEmployee(id);
    }

	
    /* (non-Javadoc)
     * @see com.resoft.prophet.org.service.OrgService#updateEmployee(com.resoft.prophet.org.domain.Employee)
     */
    public void updateEmployee(Employee employee) {
        employeeDAO.updateEmployee(employee);
    }

	
    /* (non-Javadoc)
     * @see com.resoft.prophet.org.service.OrgService#getEmployee(java.lang.String)
     */
    public Employee getEmployee(String id) {
        return employeeDAO.getEmployee(id);
    }

	
	/* (non-Javadoc)
	 * @see com.resoft.prophet.org.service.OrgService#getEmployees(java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String)
	 */
	public List getEmployees(String name, String departmentId, Integer firstResult, Integer maxResults, String sort, String direction) {
		return employeeDAO.getEmployees(name, departmentId, firstResult, maxResults, sort, direction);
	}
	
	
	/* (non-Javadoc)
	 * @see com.resoft.prophet.org.service.OrgService#countEmployees(java.lang.String, java.lang.String)
	 */
	public Integer countEmployees(String name, String departmentId) {
		return employeeDAO.countEmployees(name, departmentId);
	}

	
}
