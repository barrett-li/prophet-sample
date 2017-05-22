/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.web;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;

/**
 * 为浏览器提供远程Stream数据的Action基类
 * 
 * @author 韦海晗
 */
public class StreamAction extends WebWorkAction {
	private static final long serialVersionUID = 1968086945533865552L;

	private final static String CONTENT_TYPE_DEFAULT = "content/plain;charset=utf-8";

	private boolean cache = false;

	protected String contentType;

	public boolean isCache() {
		return cache;
	}

	public void setCache(boolean cache) {
		this.cache = cache;
	}

	protected String getContentType() {
		return contentType;
	}

	protected void setContentType(String contentType) {
		this.contentType = contentType;
	}

	/**
	 * 用于生成Stream数据的模版方法。<br/> 
	 * 当WebWork框架调用时执行该方法。
	 * 
	 * @return Stream数据的内容文本
	 */
	protected byte[] getContent() {
		return null;
	}

	/**
	 * 用于生成Stream数据的模版方法，然后将Stream数据写入HttpServletResponse中，并对Stream文本进行编码和设置contentType等属性。<br/> 当WebWork框架调用时最先执行该方法。
	 */
	public String execute() throws Exception {
		// 执行生成Stream数据的模版方法
		byte[] bout = this.getContent();
		// 获取HttpServletResponse
		HttpServletResponse response = getResponse();
		// 禁用客户端缓存
		if (!this.cache) {
			response.setHeader("Pragma", "No-Cache");
			response.setHeader("Cache-Control", "No-Cache");
			response.setDateHeader("Expires", 0);
		}
		// 设置contentType
		response.setContentType((this.contentType == null ? CONTENT_TYPE_DEFAULT : this.contentType));
		response.setIntHeader("Content-Length", bout.length);
		OutputStream out = null;
		try {
			// 输出结果
			out = response.getOutputStream();
			out.write(bout);
			out.flush();
			out.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}
}
