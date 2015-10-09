<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/commons/taglibs.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>首页</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
var login = "<%=request.getParameter("login")%>";
var register = "<%=request.getParameter("register")%>";
function loadHandler() {
	if (login == "failed") {
		document.getElementById("loginTip").style.display = "";
		document.getElementById("loginMsg").innerHTML = "用户名或密码错误";
	} else if (login == "mustlogin") {
		document.getElementById("loginTip").style.display = "";
		document.getElementById("loginMsg").innerHTML = "请先登录再访问其他页面";
	}
	if (register == "success") {
		document.getElementById("registerTip").style.display = "";
		document.getElementById("tipMsg").innerHTML = "注册成功，可以去登录了";
	} else if (register == "exists") {
		document.getElementById("registerTip").style.display = "";
		document.getElementById("tipMsg").innerHTML = "用户名已经存在";
	}
}
</script>
</head>

<body onload="loadHandler()">
	<table style="margin: 50px;" align="center">
		<tr>
			<td>
			<form action="${ctx}/user/login.do" method="post">
				<table>
					<tr>
						<td colspan="2">已经有帐号，登录</td>
					</tr>
					<tr>
						<td>用户名</td>
						<td><input type="text" name="username" /></td>
					</tr>
					<tr>
						<td>密码</td>
						<td><input type="password" name="password" /></td>
					</tr>
					<tr id="loginTip" style="color: red;display: none;">
						<td colspan="2" id="loginMsg">用户名或密码错误</td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" value="登录" /></td>
					</tr>
				</table>
				</form>
			</td>
			<td style="padding: 100px;">
			<form action="${ctx }/user/register.do" method="post">
				<table>
					<tr>
						<td colspan="2">还没有帐号，注册</td>
					</tr>
					<tr>
						<td>用户名</td>
						<td><input type="text" name="username" /></td>
					</tr>
					<tr>
						<td>密码</td>
						<td><input type="password" name="password" /></td>
					</tr>
					<tr id="registerTip" style="color: red;display: none;">
						<td id="tipMsg" colspan="2"></td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" value="注册" /></td>
					</tr>
				</table>
				</form>
			</td>
		</tr>
	</table>
</body>
</html>
