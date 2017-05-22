/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.security.acegi.userdetails;

import java.util.List;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UserDetailsService;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.springframework.dao.DataAccessException;

import com.resoft.prophet.security.acegi.GrantedAuthorityHelper;
import com.resoft.prophet.security.service.SecurityService;

/**
 * 用户登录服务类。<br/> 
 * 该类实现{@link org.acegisecurity.userdetails.UserDetailsService}接口， 
 * 用于获取与当前登录用户的信息，提供给Acegi安全框架。
 * 
 * @author 韦海晗
 */
public class UserDetailsServiceImpl implements UserDetailsService {
	private String rolePrefix = "";
	private SecurityService securityService;

	public String getRolePrefix() {
		return rolePrefix;
	}

	public void setRolePrefix(String rolePrefix) {
		this.rolePrefix = rolePrefix;
	}

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}
	
    /**
     * Locates the user based on the username. In the actual implementation, the search may possibly be case
     * insensitive, or case insensitive depending on how the implementaion instance is configured. In this case, the
     * <code>UserDetails</code> object that comes back may have a username that is of a different case than what was
     * actually requested..
     *
     * @param username the username presented to the {@link DaoAuthenticationProvider}
     *
     * @return a fully populated user record (never <code>null</code>)
     *
     * @throws UsernameNotFoundException if the user could not be found or the user has no GrantedAuthority
     * @throws DataAccessException if user could not be found for a repository-specific reason
     */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		com.resoft.prophet.security.domain.User user = securityService.getUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}

		List dbAuths = securityService.getAuthoritiesByUsername(username, rolePrefix);
		if (dbAuths.size() == 0) {
			throw new UsernameNotFoundException("User has no GrantedAuthority");
		}
		GrantedAuthority[] arrayAuths = GrantedAuthorityHelper.copyToGrantedAuthorities(dbAuths);

		return new User(user.getId(), user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, arrayAuths);
	}
}