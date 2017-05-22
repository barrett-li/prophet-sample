/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.upload.web;

import java.io.File;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.opensymphony.xwork.ActionContext;
import com.resoft.prophet.util.FileUtils;
import com.resoft.prophet.web.WebWorkAction;

/**
 * 文件组件上传。<br/>
 * 
 * @author 伍军军
 */
public class UploadAction extends WebWorkAction {
	private static final long serialVersionUID = -7469590162205769509L;
	public static Log log = LogFactory.getLog(UploadAction.class);
	private File upLoad;
	private String upLoadContentType;
	private String upLoadFileName;
	private String filePath;
	private String message;
	
	public String getUpLoadContentType() {
		return upLoadContentType;
	}

	public void setUpLoadContentType(String upLoadContentType) {
		this.upLoadContentType = upLoadContentType;
	}

	public File getUpLoad() {
		return upLoad;
	}

	public void setUpLoad(File upLoad) {
		this.upLoad = upLoad;
	}

	public String getUpLoadFileName() {
		return upLoadFileName;
	}

	public void setUpLoadFileName(String upLoadFileName) {
		this.upLoadFileName = upLoadFileName;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String execute() throws Exception {
		Map request = (Map) ActionContext.getContext().get("request");
		if(upLoad == null||upLoadFileName == null){
			request.put("message", "该文件不存在！");
			this.setMessage("该文件不存在！");
		}else{
            //filePath由spring依赖注入 
			boolean isSucceed = FileUtils.upload(upLoad,filePath,upLoadFileName);
		    if(isSucceed){
		    	this.setMessage("文件上传成功！");
		    }else{
		    	request.put("message", "文件上传失败！");
		    	this.setMessage("文件上传失败！");
		    }
		}
		return SUCCESS;
	}
	
	
}