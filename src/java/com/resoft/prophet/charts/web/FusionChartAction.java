/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.charts.web;

import com.resoft.prophet.web.PlainTextAction;

/**
 * 为FusionChart提供远程XML数据
 * 
 * @author 韦海晗
 */
public class FusionChartAction  extends PlainTextAction {
	private static final long serialVersionUID = 7023317715141378271L;
	private final static String CONTENT_DEFAULT = "<chart></chart>";
	private final static String CONTENT_TYPE_DEFAULT = "text/xml;charset=gbk";
	private final static String CHARSET_NAME_DEFAULT = "GBK";
	
	/**
	 * 返回用于Chart数据的模版方法。<br/>
	 * 当WebWork框架调用时执行该方法。
	 * 
	 * @return 用于Chart的XML数据的内容文本
	 */
	protected String getXML() {
		return null;
	}
	
	protected String getText() {
		this.setContentType(CONTENT_TYPE_DEFAULT);
		this.setDefaultContent(CONTENT_DEFAULT);
		this.setCharsetName(CHARSET_NAME_DEFAULT);
		return getXML();
	}
}
