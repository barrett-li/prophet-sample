/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.security.userdetails;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UserDetailsService;
import org.acegisecurity.userdetails.UsernameNotFoundException;

import com.resoft.prophet.AbstractTransactionalDataSourceTests;

/**
 * 用户登录服务单元测试类
 * 
 * @author 韦海晗
 */
public class UserDetailsServiceTest extends AbstractTransactionalDataSourceTests {
	private UserDetailsService userDetailsService;

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
    public void onSetUpInTransaction() {
        jdbcTemplate.execute("delete from PRO_USER");
        jdbcTemplate.execute("insert into PRO_USER (USER_ID, USERNAME, PASSWORD, ENABLED)"
                + "values ('1', 'alice', '123', 1)");

        jdbcTemplate.execute("delete from PRO_AUTHORITIES");
        jdbcTemplate.execute("insert into PRO_AUTHORITIES (AUTH_ID, AUTHORITY, DISPLAY, NOTE)"
                + "values ('1', 'AUTH_USER', '一般用户权限', null)");
        
        jdbcTemplate.execute("delete from PRO_USER_AUTH");
        jdbcTemplate.execute("insert into PRO_USER_AUTH (USER_ID, AUTH_ID)"
                + "values ('1', '1')");
    }
	
	public void testLoadUserByUsername() {
		UserDetails userDetails = userDetailsService.loadUserByUsername("alice");

		assertEquals("alice", userDetails.getUsername());
		assertEquals("123", userDetails.getPassword());
		GrantedAuthority[] grantedAuthority = userDetails.getAuthorities();
		assertEquals("AUTH_USER", grantedAuthority[0].getAuthority());

		try {
			userDetailsService.loadUserByUsername("alice1");
		} catch (UsernameNotFoundException e) {
			assertTrue(true);
		}
	}

}
