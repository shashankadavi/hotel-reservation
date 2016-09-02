<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Account Login</title>
</head>
<body>
	<h3>Login to your 16-Bit Account.</h3>
	<c:if test="${param.error !=null}">
		<p class="error">Login failed. Check username and password</p>
	</c:if>
	<form name='f' action='${pageContext.request.contextPath}/account/login'
		method='POST'>

		<table class="login">
			<tr class="username">
				<td>Username:</td>
				<td><input type="text" name="username"></td>
			</tr>
			<tr class="password">
				<td>Password:</td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr class="lbutton">
				<td colspan="3"><input type="hidden"
					name="${_csrf.parameterName}" value="${_csrf.token}" /> <input
					type="submit" value="Login" class="loginbutton"></td>
			</tr>
			<tr class="caccount">
				<td colspan="3"><a href='<c:url value="/account/newaccount"/>'
					class="loginlink">Create Account</a><br></td>
			</tr>
			
			<tr class="forgotpass">
				<td colspan="3"><a href='<c:url value="/account/forgotpassword"/>'
					class="forgotpass">Forgot Password?</a><br></td>
			</tr>
		</table>
	</form>
</body>
</html>