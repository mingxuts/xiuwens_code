<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.qcis.* " %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>File Upload</title>
</head>
<body>
	<h1>Upload File</h1>
	<h3>Note file format: userId.pdf</h3>
	
	<form name="myForm" method="post" action="UploadFile" enctype="multipart/form-data">
	
		<table>
			<tr>
				<th>PDF1:</th>
				<td><input type="file" name="PDF1" /></td>
			</tr>
			<tr>
				<th>PDF2:</th>
				<td><input type="file" name="PDF2" /></td>
			</tr>
			<tr>
				<th>PDF3:</th>
				<td><input type="file" name="PDF3" /></td>
			</tr>
			<tr>
				<th>PDF4:</th>
				<td><input type="file" name="PDF4" /></td>
			</tr>
			<tr>
				<th>PDF5:</th>
				<td><input type="file" name="PDF5" /></td>
			</tr>
		</table>
		<br /> 
		<input type="submit"  name="mySubmit" value="Upload" />
	</form>

</body>
</html>