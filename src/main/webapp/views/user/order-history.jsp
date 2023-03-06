<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="model.Order" %>
<%@ page import="java.util.ArrayList" %>
<h5 class="mb-3 fw-bold">Order History</h5>
<hr>
<div class="d-flex flex-column">
	<%
	 ArrayList<Order> orders = (ArrayList<Order>) request.getAttribute("orders");
    
	for (int i = 0; i < orders.size(); i++) {
	%>
	<jsp:include page="order-history-order.jsp" >
		<jsp:param name="orderNumber" value="<%= i %>" />
	</jsp:include>
	<hr class="mb-3">
	<%}
%>
</div>