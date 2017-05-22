/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.ActionContext;
import com.opensymphony.xwork.ActionSupport;
import com.resoft.prophet.security.SecurityContext;
import com.resoft.prophet.security.acegi.userdetails.User;

/**
 * 简单封装WebWork ActionSupport的基类。<br/>
 * 提供一些基本的简化函数，将不断增强。<br/>
 * 
 * @author 韦海晗
 */
public class WebWorkAction extends ActionSupport implements BaseAction {
	private static final long serialVersionUID = -3483437076223152127L;
	
	/**
	 * 获得HttpServletRequest。</br>
	 * 
	 * @return HttpServletRequest
	 */
	public HttpServletRequest getRequest() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		return request;
	}
	
	/**
	 * 获得HttpServletResponse。</br>
	 * 
	 * @return HttpServletResponse
	 */
	public HttpServletResponse getResponse() {
		ActionContext context = ActionContext.getContext();
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		return response;
	}
	
	/**
	 * 获得当前会话用户信息。</br>
	 * 
	 * @return 会话用户
	 */
	public User getUser() {
		ActionContext context = ActionContext.getContext();
		Map session = context.getSession();
		// 获取user
		User user = SecurityContext.getUser(session);
		return user;
	}
}
