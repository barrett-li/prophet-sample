/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.security.acegi.resourcedetails;

import java.util.Iterator;
import java.util.List;

import org.acegisecurity.GrantedAuthority;

import com.resoft.prophet.security.acegi.GrantedAuthorityHelper;
import com.resoft.prophet.security.service.SecurityService;

/**
 * 资源处理器实现类。<br/> 
 * 该类实现{@link com.resoft.prophet.security.acegi.resourcedetails.ResourceProvider}接口，用于与Acegi安全框架集成。
 * 
 * @author 韦海晗
 */
public class ResourceProviderImpl implements ResourceProvider {
	private String rolePrefix = "";

	private ResourceCache resourceCache;

	private SecurityService securityService;

	public SecurityService getSecurityService() {
		return securityService;
	}

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}

	public ResourceCache getResourceCache() {
		return resourceCache;
	}

	public void setResourceCache(ResourceCache resourceCache) {
		this.resourceCache = resourceCache;
	}

	public String getRolePrefix() {
		return rolePrefix;
	}

	public void setRolePrefix(String rolePrefix) {
		this.rolePrefix = rolePrefix;
	}

	public void init() {
		List resources = securityService.getResourceByTypes(new String[] { com.resoft.prophet.security.domain.Resource.RESOURCE_FUNCTION,
				com.resoft.prophet.security.domain.Resource.RESOURCE_URL });
		for (Iterator iter = resources.iterator(); iter.hasNext();) {
			com.resoft.prophet.security.domain.Resource rd = (com.resoft.prophet.security.domain.Resource) iter.next();
			List dbAuths = securityService.getAuthoritiesByResString(rd.getResString(), rolePrefix);
			GrantedAuthority[] arrayAuths = GrantedAuthorityHelper.copyToGrantedAuthorities(dbAuths);
			resourceCache.putAuthorityInCache(new Resource(rd.getResString(), rd.getResType(), arrayAuths));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.resoft.prophet.security.acegi.resourcedetails.ResourceProvider#getAuthorityFromCache(java.lang.String)
	 */
	public ResourceDetails getAuthorityFromCache(String resString) {
		return resourceCache.getAuthorityFromCache(resString);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.resoft.prophet.security.acegi.resourcedetails.ResourceProvider#getFunctions()
	 */
	public List getFunctions() {
		return resourceCache.getFunctions();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.resoft.prophet.security.acegi.resourcedetails.ResourceProvider#getUrlResStrings()
	 */
	public List getUrlResStrings() {
		return resourceCache.getUrlResStrings();
	}
}