<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>REAGAIL</title>

<%@ include file="/views/main-layout/head.html" %>
<link rel="StyleSheet" href="res/css/shoppingCart.css" type="text/css"></link>

</head>
<body class="d-flex flex-column min-vh-100">

<jsp:include page="/views/main-layout/nav.jsp"/>


<div class="page-wrapper overflow-hidden d-flex flex-row justify-content-center">

	<div class="bg-light w-50 ms-auto my-3">
	
		<div class="d-flex flex-row justify-content-between">
			<span class="ms-3 mt-3" >Items in your cart</span>
			<span class="me-3 mt-3 fw-bold">(5) items</span>
		</div>
		<hr>
		
		<div class="d-flex flex-column">
		<%
			int number_of_items = 5;
			for(int i = 0; i < number_of_items; i++)
			{%>
				<jsp:include page="cart-item.jsp" />
			<%}
		%>
		
		</div>
		<div class="d-flex flex-row justify-content-between mb-3 mx-3">
			<a href="Catalog" class="btn btn-custom"> &#8592 Continue Shopping</a>
			<a href="" class="btn btn-custom"> Checkout</a>
		</div>
	</div>
	
	<div class="w-25 ms-4 me-auto my-3">
		<div class="card">
		  <h5 class="card-header fw-bold">Order Summary</h5>
		  <div class="card-body">
		    <ul class="list-group list-group-flush">
                <li class="list-group-item d-flex justify-content-between align-items-center border-0 px-0 pb-0">
                  Products
                  <span>$999.99</span>
                </li>
                <li class="list-group-item d-flex justify-content-between align-items-center px-0">
                  Shipping
                  <span>$9.99</span>
                </li>
                <li class="list-group-item d-flex justify-content-between align-items-center border-0 px-0 mb-3">
                  <div>
                    <strong>Total amount</strong>
                  </div>
                  <span><strong>$999.99</strong></span>
                </li>
              </ul>
		    <a href="#" class="btn btn-custom ms-auto">Continue to Checkout</a>
		  </div>
		</div>
	</div>
	
</div>

<%@ include file="/views/main-layout/footer.html" %>

</body>
</html>