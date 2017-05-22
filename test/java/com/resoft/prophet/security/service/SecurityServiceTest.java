/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.security.service;

import java.util.List;
import java.util.Map;

import com.resoft.prophet.AbstractTransactionalDataSourceTests;
import com.resoft.prophet.security.domain.Authorities;
import com.resoft.prophet.security.domain.Menu;
import com.resoft.prophet.security.domain.Resource;
import com.resoft.prophet.security.domain.User;

/**
 * 系统安全管理服务单元测试类
 * 
 * @author 韦海晗
 */
public class SecurityServiceTest extends AbstractTransactionalDataSourceTests {
	private SecurityService securityService;

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}

    public void onSetUpInTransaction() {
        jdbcTemplate.execute("delete from PRO_USER");
        jdbcTemplate.execute("insert into PRO_USER (USER_ID, USERNAME, PASSWORD, ENABLED)"
                + " values ('1', 'alice', '123', 0)");

        jdbcTemplate.execute("delete from PRO_AUTHORITIES");
        jdbcTemplate.execute("insert into PRO_AUTHORITIES (AUTH_ID, AUTHORITY, DISPLAY, NOTE)"
                + " values ('1', 'AUTH_USER', '一般用户权限', null)");
        
        jdbcTemplate.execute("delete from PRO_USER_AUTH");
        jdbcTemplate.execute("insert into PRO_USER_AUTH (USER_ID, AUTH_ID)"
                + " values ('1', '1')");
        
        jdbcTemplate.execute("delete from PRO_RESOURCE");
        jdbcTemplate.execute("insert into PRO_RESOURCE (RES_ID, RES_TYPE, RES_STRING, DISPLAY)"
                + " values ('1', 'URL', '/pages/protected/protected1.jsp*', '保护的资源 1 ')");
        jdbcTemplate.execute("insert into PRO_RESOURCE (RES_ID, RES_TYPE, RES_STRING, DISPLAY)"
                + " values ('2', 'MENU', '1', '菜单1ID')");
        
        jdbcTemplate.execute("delete from PRO_MENU");
        jdbcTemplate.execute("insert into PRO_MENU (MENU_ID, PARENT_ID, NAME, NOTE, HREF, SORT, LEAF)"
                + " values ('1', '0', '菜单1', '菜单说明', '/pages/protected/protected1.jsp', '1' , 1)");
        
        jdbcTemplate.execute("delete from PRO_AUTHORITIES");
        jdbcTemplate.execute("insert into PRO_AUTHORITIES (AUTH_ID, AUTHORITY, DISPLAY, NOTE)"
                + " values ('1', 'AUTH_USER', '一般用户权限', null)");
        
        jdbcTemplate.execute("delete from PRO_RES_AUTH");
        jdbcTemplate.execute("insert into PRO_RES_AUTH (RES_ID, AUTH_ID)"
                + " values ('1', '1')");
        jdbcTemplate.execute("insert into PRO_RES_AUTH (RES_ID, AUTH_ID)"
                + " values ('2', '1')");
    }
    
	public void testGetAuthoritiesByUsername() {
		List list = securityService.getAuthoritiesByUsername("alice", "AUTH_");
		assertEquals(1, list.size());
		Authorities authority = (Authorities) list.get(0);
		assertEquals("1", authority.getId());
		assertEquals("AUTH_USER", authority.getAuthority());
		assertEquals("一般用户权限", authority.getDisplay());
		assertEquals("", authority.getNote());
	}

	public void testGetAuthoritiesByResString() {
		List list = securityService.getAuthoritiesByResString("/pages/protected/protected1.jsp*", "AUTH_");
		assertEquals(1, list.size());
		Authorities authority = (Authorities) list.get(0);
		assertEquals("1", authority.getId());
		assertEquals("AUTH_USER", authority.getAuthority());
		assertEquals("一般用户权限", authority.getDisplay());
		assertEquals("", authority.getNote());
	}

	public void testGetResourceByTypes() {
		List list = securityService.getResourceByTypes(new String[] { Resource.RESOURCE_URL });
		assertEquals(1, list.size());
		Resource re = (Resource) list.get(0);
		assertEquals("1", re.getId());
		assertEquals("URL", re.getResType());
		assertEquals("/pages/protected/protected1.jsp*", re.getResString());
		assertEquals("保护的资源 1 ", re.getDisplay());
	}

	public void testGetUserById() {
		User user = securityService.getUserById("1");

		assertEquals("1", user.getId());
		assertEquals("alice", user.getUsername());
		assertEquals("123", user.getPassword());
		assertFalse(user.isEnabled());
	}
	
	public void testGetUserByUsername() {
		User user = securityService.getUserByUsername("alice");

		assertEquals("1", user.getId());
		assertEquals("alice", user.getUsername());
		assertEquals("123", user.getPassword());
		assertFalse(user.isEnabled());
	}

	public void testUpdateUser() {
		User user = new User();
		user.setId("1");
		user.setUsername("alice1");
		user.setPassword("1234");
		user.setEnabled(false);
		
		securityService.updateUser(user);
		
		Map userMap = getObject("select * from PRO_USER where USER_ID = '1'");
		
		assertEquals("1", userMap.get("USER_ID"));
		assertEquals("alice1", userMap.get("USERNAME"));
		assertEquals("1234", userMap.get("PASSWORD"));
		assertEquals("0", ((Integer) userMap.get("ENABLED")).toString());
	}
	
	public void testGetMenusByParentId() {
		List list = securityService.getMenusByParentId("0", "1");
		assertEquals(1, list.size());
		Menu menu = (Menu) list.get(0);
		assertEquals("1", menu.getId());
		assertEquals("菜单1", menu.getName());
		assertEquals("菜单说明", menu.getNote());
		assertEquals("/pages/protected/protected1.jsp", menu.getHref());
		assertEquals("1", menu.getSort());
		assertTrue(menu.isLeaf());
	}

}
