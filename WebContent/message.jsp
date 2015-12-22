<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.qcis.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Upload</title>
</head>
<body>
	<h3>${requestScope.message}</h3>
	
	<form name="MyForm" action="message.jsp" method="post">
		Click Next to Load Data into Database:<input type="submit"
			name="upload2db" value="Next" />
		<%
			if (request.getParameter("upload2db") != null) {
				PDF2DB ba = new PDF2DB();
				ba.batchload();
			}
		%>
	</form>
</body>
</html>