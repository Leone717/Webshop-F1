<%@page import="java.util.Iterator"%>
<%@page import="hu.leone.datatypes.Product"%>
<%@page import="hu.leone.datatypes.Order"%>
<%@page import="hu.leone.ejbs.OrderEJB"%>
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
<title>Megrendelések</title>
<script type="text/javascript" src="js/catalogUtils.js" /></script>
</head>
<body>
<form id="listForm" name="listForm" method="post" onsubmit="return onSubmitForm()" >
		<input type="hidden" id="buttonPressed" name="buttonPressed" />
		<input type="hidden" id="selectedOrderId" name="selectedOrderId" />

		<!-- Rendelés lista -->
		<table border=1 bgcolor="0099FF">
		
		<tr>
			<td style="color:white;" bgcolor="0099FF" align="center">Rendelés azonosító </td>
			<td style="color:white;" bgcolor="0099FF"  align="center">Megrendelő neve </td>
			<td style="color:white;" bgcolor="0099FF"  align="center">Megrendelő email címe</td>
			<td style="color:white;" bgcolor="0099FF"  align="center">Rendelés részletei</td>
			<td style="color:white;" bgcolor="0099FF"  align="center">Rendelés teljes összege</td>
			<td style="color:white;" bgcolor="CC3300"  align="center">X</td>
		</tr>
<%
		/* rendeléslist lekérdezése OrderEJB-btől
		és ciklusban a táblázat kirajzolása */
		Context ctx = new InitialContext();
		OrderEJB ordEJB = (OrderEJB)ctx.lookup("java:global.WebshopEAR.WebshopEJB.OrderEJB");
		
		Order[]orderList = ordEJB.getOrderList();
		for(Order order : orderList)
		{
			/*
			System.out.println("Rendelés azonosító: " + order.getOrderId());
			System.out.println("Megrendelő neve: " + order.getAccount().getName());
			System.out.println("Megrendelő email címe: " + order.getAccount().getEmail());
			System.out.println("Rendelés részletei: " + order.getProductList());
			System.out.println("Rendelés teljes összege: "); */
		%>
		
		<tr bgcolor="lightgrey">
			<td align="center">
			<b><%= order.getOrderId()%></b>
			<td align="center"> 
			<b><%= order.getAccount().getName()%></b>
			</td>
			<td align="center">
			<b><%= order.getAccount().getEmail()%></b>
			</td>
			<td align="center" bgcolor="lightgreen">
			<table border=1 bgcolor="lightgreen" width="100%" >
				<tr>
					<td>Termék neve</td>
					<td>Termék egységára</td>
					<td>Rendelt mennyiség</td>
					<td>Teljes ár</td>
				</tr>
				
					<% Map<Product, Integer> productList = order.getProductList();
					
					//Iterator<Integer> iterator = productList.values().iterator();
					Iterator<Map.Entry<Product, Integer>> iterator = productList.entrySet().iterator();
					int rendelesTeljesOsszege = 0;  
					while (iterator.hasNext()) { %>
				<tr bgcolor="lightyellow">
					        <%
					        Map.Entry<Product, Integer> entry = iterator.next();
					        Product product = entry.getKey();
					        /*
					        System.out.println(entry.getKey() + ":" + entry.getValue());
					        System.out.println("Product name: " + product.getName());
					        System.out.println("Product price: " + product.getPrice());*/
					%>
					<td><%=product.getName()%></td>
					<td><%=product.getPrice()%></td>
					<!-- az entry.getValue() erteke megfelel a rendelt mennyisegnek -->
					<td><%=entry.getValue()%></td>
					<td><%=entry.getValue() * product.getPrice()%></td> 
				</tr>
				<% rendelesTeljesOsszege += entry.getValue() * product.getPrice(); }%>
			</table>
			</td>
			<td align="center">
			<b><%=rendelesTeljesOsszege%></b>
			</td>
			<td>  
				<input type="image" width="30px" height="25px" src="<%=request.getContextPath()%>/images/trash.png"
				onclick="setButtonPressed('DeleteOrder'); setSelectedOrderId(<%=order.getOrderId() %>)" /> 
				<!-- 
				<input type="image" width="30px" height="25px" src="<%=request.getContextPath()%>/images/trash.png"
				onclick="setButtonPressed('DeleteOrder'); setSelectedOrderId(<%=order.getOrderId() %>)" /> 
				<input type="submit" value="Kosár törlése" onclick="setButtonPressed('DeleteCart')" /> -->
			</td> 
		</tr>
	<% } %>
	</table>
	<br>
</form>

</body>
</html>