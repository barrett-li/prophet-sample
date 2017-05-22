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
		<title>新增部门</title>
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
			function back() {
				document.location.href = '<%=rootPath%>/org/getDepts.action';
			}
		</script>
	</head>
	<body >
		<h2>新增部门</h2>
	   	<br />
	   	<br />
		<form name="insertDept" action="<%=rootPath%>/org/insertDept.action" method="post">
			部门名称：<input type="text" name="department.fullName"/><br>
			部门简称：<input type="text" name="department.shortName"/><br>
			备注：<br/>
			<textarea rows="5" cols="30" name="department.remark"></textarea>	
			<br/>
			<input  type="submit" value="增加" />
			<input  type="reset" value="重置" />
			<input  type="button" value="返回" onclick="javascript: back();" />		
		</form>
		<ww:if test="message != null">
			<br/>
			<font color="red">
				<ww:property value="message"/>
			</font>
		</ww:if>
	</body>
</html>