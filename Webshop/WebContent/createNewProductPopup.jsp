<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Új termék bevitele</title>
</head>
<body bgcolor="lightblue" >
<form id="form" method="post" action="ProductHandlingServlet">
	<table>
		<tr>
			<td>Termék neve</td>
			<td><input type="text" id="productName" name="productName"/></td>
		</tr>
		<tr>
			<td>Termék leírása</td>
			<td><textarea id="productDescription" name="productDescription" cols="40" rows="5" ></textarea></td>
		</tr>
		<tr>
			<td>Termék ára</td>
			<td><input type="number" id="productPrice" name="productPrice" min="1" max="10000000" size="7" /></td>
		</tr>
		<tr>
			<td>Termék kép</td>
			<td><input type="text" id="productImage" name="productImage" /></td>
		</tr>
	</table>
	<br>
	<div align="center">
		<input type="submit" value="Rögzítés" />
	</div>

</form>
</body>
</html>