<%--
Prophet 1.0
CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD

http://resoft.css.com.cn
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/templets/common/global.jsp"%>
<%@ include file="/templets/common/taglibs.jsp"%>
<%@ page import="com.resoft.prophet.security.SecurityContext"%>
<%@ page import="com.resoft.prophet.security.acegi.userdetails.User"%>

<html>
	<head>
		<title>Prophet示例：更改密码</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<!-- Common JS -->
		<%@ include file="/templets/common/scripts.jsp"%>
		<!-- DWR 2.0 (http://getahead.org/dwr) -->
  		<!-- Ext JS Extend -->
  		<!-- Prophet CSS -->
  		<link rel="stylesheet" type="text/css" href="<%=rootPath%>/styles/common.css"></link>
  		<!-- Page JS -->
		<script type="text/javascript">
			function setUserPassword() {
				var op = Ext.getDom('oldPassword');
				var np = Ext.getDom('newPassword');
				var cp = Ext.getDom('confirmNewPassword');
				
				if (op.value == '') {
					alert('请填写旧密码！');
					op.focus();
					return;
				}
				
				if (np.value == '') {
					alert('请填写新密码！');
					np.focus();
					return;
				}
				
				if (cp.value == '') {
					alert('请确认新密码！');
					cp.focus();
					return;
				}
				
				if (np.value != cp.value) {
					alert('你确认的新密码不一致，请重试！');
					np.select();
					np.focus();
					return;
				}
				
			    var f = Ext.getDom('passForm');
				f.submit();
			}
		</script>
	</head>
	<body>
		<h2>
			更改密码
		</h2>
		<br />
		<br />
		<font color="red"><ww:property value="message"/><BR>
		</font>
		<form id="passForm" action="<%=rootPath%>/security/setUserPassword.action" method="post">
			<table>
				<tr>
					<td>
						用户名:
					</td>
					<td>
						<%
						User user = SecurityContext.getUser(request);
						if (user != null) {
							out.print(user.getUsername());
						}
						%>
					</td>
				</tr>
				<tr>
					<td>
						旧密码:
					</td>
					<td>
						<input type='password' id="oldPassword" name='oldPassword' />
					</td>
				</tr>
				<tr>
					<td>
						新密码:
					</td>
					<td>
						<input type='password' id="newPassword" name='newPassword' />
					</td>
				</tr>
				<tr>
					<td>
						确认新密码:
					</td>
					<td>
						<input type='password' id='confirmNewPassword' />
					</td>
				</tr>
				<tr>
					<td>
						<input name="reset" type="reset" />
					</td>
					<td>
						<input type="button" value="确认" onclick="javascript: setUserPassword();"/>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>