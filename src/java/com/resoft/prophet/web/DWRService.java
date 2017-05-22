/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import com.resoft.prophet.security.SecurityContext;
import com.resoft.prophet.security.acegi.userdetails.User;

/**
 * DWR服务的基类。<br/>
 * 实现{@link com.resoft.prophet.web.RemoteService}接口。
 * 提供一些基本的简化函数，将不断增强。<br/>
 * 
 * @author 韦海晗
 */
public class DWRService implements RemoteService {
	
	/**
	 * 获得HttpServletRequest。</br>
	 * 
	 * @return HttpServletRequest
	 */
	public HttpServletRequest getRequest() {
		WebContext context = WebContextFactory.get();
		HttpServletRequest request = context.getHttpServletRequest();
		return request;
	}
	
	/**
	 * 获得HttpServletResponse。</br>
	 * 
	 * @return HttpServletResponse
	 */
	public HttpServletResponse getResponse() {
		WebContext context = WebContextFactory.get();
		HttpServletResponse response = context.getHttpServletResponse();
		return response;
	}
	
	/**
	 * 获得当前会话用户信息。</br>
	 * 
	 * @return 会话用户
	 */
	public User getUser() {
		HttpServletRequest request = getRequest();
		// 获取user
		User user = SecurityContext.getUser(request);
		return user;
	}
}
