<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<%
Object user = request.getSession().getAttribute("OnlineUser");
if (user == null) {
	response.sendRedirect(request.getContextPath() + "/pages/index.jsp?login=mustlogin");
}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Welcome</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${ctx}/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
var save = "<%=request.getParameter("save")%>";
function loadHandler() {
	if (save == "success") {
		document.getElementById("saveTip").style.display = "";
		document.getElementById("tipMsg").innerHTML = "保存成功";
	} else if (save == "invalidtoken") {
		document.getElementById("saveTip").style.display = "";
		document.getElementById("tipMsg").innerHTML = "Token无效";
	}
}
</script>
</head>

<body onload="loadHandler()">
	<div align="center" style="font-size: 30px">Welcome</div>
	<div align="center" style="padding-top:100px;">
		修改个人信息 <a href="${ctx }/user/logout.do">退出</a>
		<form action="${ctx}/user/save.do" method="post">
		<table>
			<tr>
				<td>Token</td>
				<td><input type="text" name="token" value="${token}" readonly="readonly" style="color: gray;border: 0px" /></td>
			</tr>
			<tr>
				<td>用户ID</td>
				<td><input type="text" name="id" value="${OnlineUser.id }" readonly="readonly" style="color: gray;border: 0px" /></td>
			</tr>
			<tr>
				<td>用户名</td>
				<td><input type="text" name="username" value="${OnlineUser.username }" readonly="readonly" style="color: gray;border: 0px" /></td>
			</tr>
			<tr>
				<td>姓名</td>
				<td><input type="text" name="name" value="${OnlineUser.name }" /></td>
			</tr>
			<tr>
				<td>性别</td>
				<td>
				<label>男<input type="radio" name="sex" value="man"  <c:if test="${OnlineUser.sex == 'man'}">checked="checked"</c:if> /></label>
				<label>女<input type="radio" name="sex" value="woman" <c:if test="${OnlineUser.sex == 'woman'}">checked="checked"</c:if> /></label>
				</td>
			</tr>
			<tr>
				<td>生日</td>
				<td><input type="text" readonly="readonly" name="birthday" value="${OnlineUser.birthday }"  onFocus="WdatePicker()" /></td>
			</tr>
			<tr id="saveTip" style="color: red;display: none;">
				<td colspan="2" id="tipMsg">保存成功</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="保存" /></td>
			</tr>
		</table>
		</form>
	</div>
</body>
</html>
