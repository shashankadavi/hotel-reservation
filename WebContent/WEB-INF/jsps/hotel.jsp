<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>16-Bit Hotel-Home</title>
</head>
<body>

Hello from hotel JSP


<p>
		<a href="${pageContext.request.contextPath}/account/login">Manage Account</a>
	</p>
	<p>
	<a href="${pageContext.request.contextPath}/reservation/">Reserve</a>
	</p>
</body>
</html>