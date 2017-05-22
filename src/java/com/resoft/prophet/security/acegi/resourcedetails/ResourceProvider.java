/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.security.acegi.resourcedetails;

import java.util.List;

/**
 * 资源处理器接口。<br/>
 * 用于与Acegi安全框架集成。
 * 
 * @author 韦海晗
 */
public interface ResourceProvider {
	
	/**
	 * 获取所有的URL资源。
	 */
	public List getUrlResStrings();

	/**
	 * 获取所有的FUNTION资源。
	 */
	public List getFunctions();

	/**
	 * 根据资源串获取资源。
	 * 
	 * @param resString 资源串
	 * 
	 * @return 资源信息
	 */
	public ResourceDetails getAuthorityFromCache(String resString);

}
