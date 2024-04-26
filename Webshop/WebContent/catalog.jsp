<%@page import="hu.leone.ejbs.CartEJB"%>
<%@page import="hu.leone.datatypes.Product"%>
<%@page import="hu.leone.ejbs.CatalogEJB"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.naming.Context" %>
<%@page import="java.util.Map"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Termékek</title>
<script type="text/javascript" src="js/catalogUtils.js" /></script>
</head>
<body>

<form name="dialog" action="" onsubmit="return false">
	<input type="button" value="Új termék hozzáadása" onclick="openNewProductPopup()" />
</form>

<!-- ----------------------------------------------- -->
<!-- Termék törlése form -->
<form id="listForm" name="listForm" method="post" onsubmit="return onSubmitForm()" >
	<input type="hidden" id="buttonPressed" name="buttonPressed" />
	<input type="hidden" id="selectedProductId" name="selectedProductId" />

<!-- Termék lista -->
<%
		/* terméklist lekérdezése CatalogEJB-btől
		és ciklusban a táblázat kirajzolása */
		Context ctx = new InitialContext();
		CatalogEJB catEJB = (CatalogEJB)ctx.lookup("java:global.WebshopEAR.WebshopEJB.CatalogEJB");
		
		Product[]productList = catEJB.getProductList();
		for(Product product : productList)
		{
			
		%>
	<table border=1 bgcolor="lightblue">
		<tr>
			<td align="center"> 	<!-- image --> 
				<img src="<%=request.getContextPath() %>/images/<%=product.getImagePath() %>" width="300px" />
			</td>
		<tr>
			<td align="center"> <!--  name --> 
			<b><%= product.getName() %></b>
			</td>
		<tr>
			<td align="left" width="50px">	<!-- description --> 				
				<pre style="white-space: pre-wrap;"><%=product.getDescription() %></pre>
			</td>
		</tr>
		<tr>
			<td align="center"> <!-- price --> 
			<fmt:setLocale value="hu_HU"/>
			<fmt:formatNumber maxFractionDigits="0" type="currency" value="<%=product.getPrice() %>" />
			</td>
		</tr>
		<tr>
			<td align="center"> <!-- termék törlés -->
				<input type="submit" value="Törlés" onclick="setButtonPressed('DeleteProduct'); setSelectedProductId(<%=product.getId() %>)" />
			</td>
		</tr>
		<tr>
			<td align="center">	<!-- termék kosárhoz adása  -->
				<input type="image" src="<%=request.getContextPath() %>/images/cart.jpg" 
				onclick="setButtonPressed('AddToCart'); setSelectedProductId(<%=product.getId() %>)" />
			</td>
		</tr>
	</table>
<% } %>

<!-- Kosár panel -->
<div id="cartDiv" align="center" 
	style="position: fixed; top: 5px; left: 50%; right: 0; border:1px solid black;
	 width: 450px ; z-index: 50; background-color: lightyellow">
	<table>
		<tr>
			<td bgcolor="green">
				<font color="white" style="font-weight: bold">Termék neve </font>
			</td>
			<td bgcolor="green">
				<font color="white" style="font-weight: bold">Mennyiség</font>
			</td>
			<td bgcolor="green">
				<font color="white" style="font-weight: bold">Eladási ár</font>
			</td>
			<td bgcolor="green">
				<font color="white" style="font-weight: bold">Összesen</font>
			</td>
			<td>
			</td>
		</tr>

<%
	//CartEJB a sessionből
	//majd ciklus a cartEJB.getProductList()
	
	CartEJB cartEjb = (CartEJB)session.getAttribute("cart");
	
	if(cartEjb == null || cartEjb.getProductList().isEmpty()) { //a kosár üres
	%>
		<tr>
			<td colspan="5" align="center">A kosár üres! </td>
		</tr>
	<%
	} else {
		System.out.println("Kosár tartalmának lekérése: " + cartEjb.getProductList());
		
		int totalPrice = 0;
		
		Map<Product, Integer> cartProductList = cartEjb.getProductList();
		for(Map.Entry<Product, Integer> e: cartProductList.entrySet())  //ciklus a kosárban lévő termékeken
		{	
			Product product = e.getKey();
			//System.out.println(cartProductList.containsKey(e.getKey()));
			int amount = e.getValue();
			//System.out.println(cartProductList.containsKey(e.getValue()));
			int itemPrice = amount * product.getPrice(); //darab x ár
			
			totalPrice += itemPrice;
	%>
		
		
		<tr>
			<td><%=product.getName()%></td>
			<td><%=amount%></td>
			<td><%=product.getPrice()%></td>
			<td><%=itemPrice%></td>
			<td>
				<input type="image" width="30px" height="25px" src="<%=request.getContextPath()%>/images/trash.png"
				onclick="setButtonPressed('DeleteProductFromCart'); setSelectedProductId(<%=product.getId() %>)" />
			</td>
			
		</tr>
		
		<tr>
			<td></td>
			<td></td>
			<td>Végösszeg</td>
			<td><%=totalPrice%></td>	
		</tr>
		<% } %> <!--  else záró -->
	</table>
	<br>
	<br>
	<input type="button" value="Megrendelés" onclick="openNewAccountPopup()" />
	<br>
	<br>
	<input type="submit" value="Kosár törlése" onclick="setButtonPressed('DeleteCart')" />
<% } %> <!-- else záró-->
	<br>
	<br>
</div>


</form>
</body>
</html>