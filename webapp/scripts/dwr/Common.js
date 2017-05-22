/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

if(DWREngine){
	// 统一设置超时
	DWREngine.setTimeout(60000);
	// 统一的异常处理
	DWREngine.setErrorHandler(function(exception) {
		Ext.MessageBox.alert("警告", exception);
	});
}
