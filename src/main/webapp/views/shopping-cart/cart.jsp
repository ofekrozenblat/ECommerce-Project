<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="model.Item" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>REAGAIL</title>

<%@ include file="/views/main-layout/head.html" %>
<link rel="StyleSheet" href="res/css/shoppingCart.css" type="text/css"></link>
<script type="text/javascript" src="res/js/cart.js"></script>
</head>
<body class="d-flex flex-column min-vh-100">

<jsp:include page="/views/main-layout/nav.jsp"/>
<%
ArrayList<Item> cart_items = (ArrayList<Item>) request.getAttribute("cart_items");
ArrayList<Integer> cart_item_quantities = (ArrayList<Integer>) request.getAttribute("cart_item_quantities");
%>			

<div class="page-wrapper overflow-hidden d-flex flex-row justify-content-center">

	<div class="bg-light w-50 ms-auto my-3">
	
		<div class="d-flex flex-row justify-content-between">
			<span class="ms-3 mt-3" >Items in your cart</span>
			<span class="me-3 mt-3 fw-bold">(${cart_size}) items</span>
		</div>
		<hr>
		
		<div class="d-flex flex-column">
		<%
			for(int i = 0; i < cart_items.size(); i++)
			{
				Item item = cart_items.get(i);
			%>
				<jsp:include page="cart-item.jsp" >
		          <jsp:param name="name" value="<%= item.getName() %>" />
		          <jsp:param name="price" value="<%= item.getPrice() %>" />
		          <jsp:param name="description" value="<%= item.getDescription() %>" />
		          <jsp:param name="item_id" value="<%= item.getId() %>" />
		           <jsp:param name="img" value="<%= item.getImg() %>" />
		          <jsp:param name="quantity" value="<%= cart_item_quantities.get(i) %>" />
		        </jsp:include>
			<%}
		%>
		
		</div>
		<% if(cart_items.size() > 0){
			%>
			<div class="d-flex flex-row justify-content-between mb-3 mx-3">
				<a href="Catalog" class="btn btn-custom"> &#8592 Continue Shopping</a>
				<a href="Checkout" class="btn btn-custom"> Checkout</a>
			</div>
			<%}
		%>
	</div>
	
	<div class="w-25 ms-4 me-auto my-3">
		<div class="card">
		  <h5 class="card-header fw-bold">Order Summary</h5>
		  <div class="card-body">
		    <ul class="list-group list-group-flush">
                <li class="list-group-item d-flex justify-content-between align-items-center border-0 px-0 pb-0">
                  Products
                  <span>$${total}</span>
                </li>
                <li class="list-group-item d-flex justify-content-between align-items-center px-0">
                  Shipping
                  <span>$${shipping}</span>
                </li>
                <li class="list-group-item d-flex justify-content-between align-items-center border-0 px-0 mb-3">
                  <div>
                    <strong>Total amount</strong>
                  </div>
                  <span><strong>$${total + shipping}</strong></span>
                </li>
              </ul>
              <% if(cart_items.size() > 0){
				%>
				<a href="Checkout" class="btn btn-custom ms-auto mt-auto">Continue to Checkout</a>
				<%}
			%>
		  </div>
		</div>
	</div>
	
</div>

<%@ include file="/views/main-layout/footer.html" %>

</body>
</html>