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
		<title>查看用户日志信息</title>
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
			
		</script>
	</head>
	<body >
		<h2>用户日志信息查询</h2>
		<br />
		<br />
		<form  id="getUserLogs" name="getUserLogs" action="<%=rootPath%>/log/getUserLogs.action"  method="post">
			类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型：
			<input type="text" name="type" value="<ww:property value="type"/>"  />
			用户名称：
			<input type="text" name="userName" value="<ww:property value="userName"/>" />
			&nbsp;&nbsp;&nbsp;&nbsp;<br/>
			开始时间：
			<input type="text" name="beginTime" value="<ww:property value="beginTime"/>" />
			结束时间：
			<input type="text" name="endTime" value="<ww:property value="endTime"/>" />
			<br />
			<input type="submit" value="查询"/>
			<input type="reset" value="重置"/>
			<br/>
		</form>
		<ww:if test="userLogs.size != 0">
			<table border="1">
				<tr>
				<th>序号</th>
	          	<th >类型</th>
	          	<th >日志内容</th>
	          	<th >用户名称</th>
	          	<th >记录时间</th>
				</tr>
				<ww:iterator value="userLogs" id="userLog" >
					<tr>
						<td>
				 			<ww:property value="#userLog.id"/>
				 		</td>
				 		<td>
				 			<ww:property value="#userLog.type"/>
				 		</td>
				 		<td>
				 			<ww:property value="#userLog.content"/>
				 		</td>
				 		<td>
				 			<ww:property value="#userLog.userName"/>
				 		</td>
				 		<td>
				 			<ww:property value="#userLog.createTime"/>
				 		</td>
			 		</tr>
		 		</ww:iterator>		
			</table>
		</ww:if>	
	</body>
</html>
