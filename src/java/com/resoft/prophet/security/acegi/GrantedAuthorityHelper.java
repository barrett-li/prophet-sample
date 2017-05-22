/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.security.acegi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;

import com.resoft.prophet.security.domain.Authorities;

/**
 * GrantedAuthority帮助类。
 * 
 * @author 韦海晗
 */
public class GrantedAuthorityHelper {
	/**
	 * 复制Authorities列表中的Authorities到新的GrantedAuthorities列表中，
	 * 并创建新的GrantedAuthorities对象。
	 * 
	 * @param dbAuths Authorities列表
	 * 
	 * @return GrantedAuthority列表
	 */
	public static GrantedAuthority[] copyToGrantedAuthorities(List dbAuths) {
		List auths = new ArrayList();
		for (Iterator iter = dbAuths.iterator(); iter.hasNext();) {
			Authorities auth = (Authorities) iter.next();
			auths.add(new GrantedAuthorityImpl(auth.getAuthority()));
		}
		GrantedAuthority[] arrayAuths = (GrantedAuthority[]) auths.toArray(new GrantedAuthority[auths.size()]);
		return arrayAuths;
	}
}
