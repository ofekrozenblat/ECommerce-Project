<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<h5 class="mb-3 fw-bold">Order History</h5>
<hr>
<div class="d-flex flex-column">
	<%
	int number_of_orders = 5;
	for (int i = 0; i < number_of_orders; i++) {
	%>
	<jsp:include page="order-history-order.jsp" >
		<jsp:param name="orderNumber" value="<%= i %>" />
	</jsp:include>
	<hr class="mb-3">
	<%}
%>
</div>