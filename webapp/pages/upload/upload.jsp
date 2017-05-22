<%--
Prophet 1.0
CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD

http://resoft.css.com.cn
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/templets/common/global.jsp"%>
<%@ include file="/templets/common/taglibs.jsp"%>

<html>
	<head>
		<title>Prophet示例：文件上传</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<!-- Common JS -->
		<%@ include file="/templets/common/scripts.jsp"%>
		<!-- DWR 2.0 (http://getahead.org/dwr) -->
  		<!-- Ext JS Extend -->
  		<!-- Page JS -->
  		<link rel="stylesheet" type="text/css" href="<%=rootPath%>/styles/common.css"></link>
		<script type="text/javascript">
			function upLoadData(){
				var file = document.uploadForm.upLoad.value;
				if(file == ''){
					Ext.MessageBox.alert('提示','您上传的文件为空!' );
					return ;
				}else{
 					uploadForm.submit();
 				}			
			}
			function showMessage(){
				var message = "<ww:property value="message"/>";
				if(message != ''){
					Ext.MessageBox.alert('提示', message);
				}
			}
		</script>
	</head>
	<body onload="showMessage();">
		<h2>
			文件上传演示
		</h2>
		<br />
		<br />
		<form name="uploadForm" action="<%=rootPath%>/upload/upload.action" enctype="multipart/form-data" method="post">
			<input type="file" name="upLoad" size="40">
			<br />
			<br />
			<input class="input_button" type="button" value="上传" onclick="javascript: upLoadData();"/>
			<input class="input_button" type="reset" value="重置" />
		</form>
	</body>
</html>