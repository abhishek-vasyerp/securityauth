<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	 <h1>${Token}</h1>
	 <h2>${Role}</h2>
</body>
</html> --%>

<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Login Result</title>
</head>
<body>

	<h2>Login Result</h2>

	<%
	if (request.getAttribute("error") != null) {
	%>
	<p style="color: red;"><%=request.getAttribute("error")%></p>
	<%
	} else {
	%>
	<p>
		Token:
		<%=request.getAttribute("Token")%></p>
	<p>
		Role:
		<%=request.getAttribute("Role")%></p>
	<%
	}
	%>

</body>
</html>
