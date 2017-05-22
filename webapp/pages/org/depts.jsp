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
		<title>查看部门信息</title>
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
			function insertDept(){
				document.location.href = '<%=rootPath%>/pages/org/addDept.jsp';
			}
			
			function deleteDept(id){
			  	if(window.confirm("是否确认删除?")){
					document.location.href = '<%=rootPath%>/org/deleteDept.action?department.id=' + id; 
				}	  											
			}  
			
			function getDept(id){
				document.location.href = '<%=rootPath%>/org/getDept.action?department.id=' + id;									
			}
			
			function exportExcel(){
			    var deptFrom = document.getElementById('getDepts');
				deptFrom.action = '<%=rootPath%>/org/exportExcelDepts.action';
				deptFrom.submit();
			}  
		</script>
	</head>
	<body >
		<h2>部门信息</h2>
		<br />
		<br />
		<form id="getDepts" name="getDepts" action="<%=rootPath%>/org/getDepts.action"  method="post">
			部门名称：<input type="text" name="fullName" value="<ww:property value="fullName"/>">
			&nbsp;&nbsp;&nbsp;&nbsp;
			部门简称：<input type="text" name="shortName" value="<ww:property value="shortName"/>">
			&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" value="增加新部门" onclick="insertDept();">
			<br>
			<input type="submit" value="查询">
			<br />
		</form>
		<ww:if test="depts.size != 0">
			<table border="1">
				<tr>
					<th>部门ID</th>
					<th>部门名称</th>
					<th>部门简称</th>
					<th>编辑</th>
				</tr>
				<ww:iterator value="depts" id="department" >
					<tr>
						<td>
				 			<ww:property value="#department.id"/>
				 		</td>
				 		<td>
				 			<ww:property value="#department.fullName"/>
				 		</td>
				 		<td>
				 			<ww:property value="#department.shortName"/>
				 		</td>
				 		<td>
				 			<a href="javaScript: getDept('<ww:property value="#department.id"/>');">修改</a>|<a href="javaScript: deleteDept('<ww:property value="#department.id"/>');">删除</a>
				 		</td>
			 		</tr>
		 		</ww:iterator>
			</table>
			<input type="submit" name="" value="导出EXCEL" onclick="exportExcel();">
		</ww:if>
		<ww:if test="message != null">
			<br/>
			<font color="red">
				<ww:property value="message"/>
			</font>
		</ww:if>
	</body>
</html>
