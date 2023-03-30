<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="model.Order" %>
<%@ page import="model.Item" %>
<%@ page import="model.BillingAddress" %>
<%@ page import="java.util.ArrayList" %>
<%
    int orderNumber = Integer.parseInt(request.getParameter("orderNumber"));
	ArrayList<Order> orders = (ArrayList<Order>) request.getAttribute("orders");
	Order order = orders.get(orderNumber);
%>
<div class="order-item my-3">
	<div class="order-item-body">
		<div class="w-100 d-flex flex-row justify-content-between mb-1">
			<p class="mb-2 text-muted fw-bold">Order Placed <%= order.getDateString() %></p>
			<h6 class="fw-bold">Total: $<%= order.getTotal() %></h6>
		</div>
		<p class="mb-2 text-muted card-footer">Shipped to: <%= order.getBillingAddress().toString() %></p>
		<button class="btn btn-custom mt-2" type="button" data-bs-toggle="collapse" data-bs-target="#order-${param.orderNumber}" aria-expanded="false" aria-controls="collapseExample">
		    	Order Details <span class="material-symbols-outlined align-text-bottom">expand_more</span>
		 </button>
	</div>
</div>

<div class="collapse" id="order-${param.orderNumber}">
  <div class="card card-body">
    <%
    ArrayList<Item> orderItems = (ArrayList<Item>) order.getItems();
    
	for (Item item: orderItems) {
	%>
	<jsp:include page="order-history-item.jsp" >
	 	<jsp:param name="name" value="<%= item.getName() %>" />
	 	<jsp:param name="price" value="<%= item.getPrice() %>" />
	 	<jsp:param name="quantity" value="<%= order.getItemQuantity(item) %>" />
	 	<jsp:param name="item_id" value="<%= item.getId() %>" />
	 	<jsp:param name="img" value="<%= item.getImg() %>" />
	</jsp:include>
	<hr class="mb-3">
	<%}
%>
  </div>
</div>
