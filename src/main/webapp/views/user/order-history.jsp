<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<h5 class="mb-3 fw-bold">Order History</h5>
<hr>
<div class="d-flex flex-column">
	<%
	int number_of_orders = 5;
	for (int i = 0; i < number_of_orders; i++) {
	%>
	<jsp:include page="order-history-item.jsp" />
	<hr class="mb-3">
	<%}
%>
<button class="btn btn-custom-round w-50 mx-auto">Load More Orders</button>
</div>