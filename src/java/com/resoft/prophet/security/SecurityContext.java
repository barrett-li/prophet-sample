/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.security;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.acegisecurity.context.HttpSessionContextIntegrationFilter;

import com.resoft.prophet.security.acegi.userdetails.User;

/**
 * Web站点安全上下文管理器。<br/>
 * 用于管理Session信息等。<br/>
 * 
 * @author 韦海晗
 */
public class SecurityContext {
	/**
	 * 获得当前会话用户信息。</br>
	 * 
	 * @param request 当前的请求
	 * 
	 * @return 会话用户
	 * */
    public static User getUser(HttpServletRequest request) {
    	HttpSession session = request.getSession();
    	org.acegisecurity.context.SecurityContext securityContext = (org.acegisecurity.context.SecurityContext) session.getAttribute(HttpSessionContextIntegrationFilter.ACEGI_SECURITY_CONTEXT_KEY);
		return getUserBySecurityContext(securityContext);
    }
    
	/**
	 * 获得当前会话用户信息。</br>
	 * 
	 * @param session 保存当前会话的Map
	 * 
	 * @return 会话用户
	 * */
    public static User getUser(Map session) {
    	org.acegisecurity.context.SecurityContext securityContext = (org.acegisecurity.context.SecurityContext) session.get(HttpSessionContextIntegrationFilter.ACEGI_SECURITY_CONTEXT_KEY);
        return getUserBySecurityContext(securityContext);
    }
    
	/*
	 * 从SecurityContext获取com.resoft.prophet.security.acegi.userdetails.User对象。
	 */
	private static User getUserBySecurityContext(org.acegisecurity.context.SecurityContext securityContext) {
		User user = null;
		if (securityContext != null) {
			Object obj = securityContext.getAuthentication().getPrincipal();
			if (obj instanceof User) {
				user = (User) obj;
			}
		}
		return user;
	}
}