<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Új regisztráció</title>
</head>
<body bgcolor="lightblue">
<form method="post" action="OrderHandlingServlet" >
	<table>
		<tr>
			<td>Vásárló neve</td>
			<td><input type="text" id="accountName" name="accountName" ></td>
		</tr>
		<tr>
			<td>Vásárló email</td>
			<td><input type="text" id="accountEmail" name="accountEmail" ></td>
		</tr>
	</table>
	<br>
	<div align="center">
	<input type="submit" value="Tovább" />
	</div>
	

</form>

</body>
</html>