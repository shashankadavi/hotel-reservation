<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form name='f'
		action='${pageContext.request.contextPath}/reservation/check'
		method='GET'>

		<table class="check">
			<tr class="roomType">
				<td>roomType:</td>
				<td><input type="text" name="roomType"></td>
			</tr>
			<tr class="startDate">
				<td>startDate:</td>
				<td><input type="text" name="startDate"></td>
			</tr>
			<tr class="endDate">
				<td>endDate:</td>
				<td><input type="text" name="endDate"></td>
			</tr>
			
			
			<tr class="lbutton">
				<td colspan="3"><input type="hidden"
					name="${_csrf.parameterName}" value="${_csrf.token}" /> <input
					type="submit" value="check" class="createbutton"></td>
			</tr>


		</table>
	</form>

</body>
</html>