/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.security.acegi.resourcedetails;

import java.io.Serializable;

import org.acegisecurity.GrantedAuthority;

/**
 * 资源信息接口。<br/>
 * 用于与Acegi安全框架集成。
 * 
 * @author 韦海晗
 */
public interface ResourceDetails extends Serializable {

	/**
	 * 返回资源串。
	 * 
	 * @return 资源串
	 */
	String getResString();

	/**
	 * 返回资源类型，如URL，FUNCTION。
	 * 
	 * @return 资源类型
	 */
	String getResType();

	/**
	 * 返回属于该resource的权限列表。
	 * 
	 * @return 权限列表
	 */
	GrantedAuthority[] getAuthorities();
}
