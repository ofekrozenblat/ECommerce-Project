<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<div class="order-item my-3">
	<div class="order-item-body">
		<div class="w-100 d-flex flex-row justify-content-between mb-1">
			<p class="mb-2 text-muted fw-bold">Order Placed on February 13, 2023</p>
			<h6 class="fw-bold">Total: $999</h6>
		</div>
		<p class="mb-2 text-muted card-footer">Shipped to: 4700 Keele St, Toronto, Ontario M3J 1P3 Canada</p>
		<button class="btn btn-custom mt-2" type="button" data-bs-toggle="collapse" data-bs-target="#order-${param.orderNumber}" aria-expanded="false" aria-controls="collapseExample">
		    	Order Details <span class="material-symbols-outlined align-text-bottom">expand_more</span>
		 </button>
	</div>
</div>

<div class="collapse" id="order-${param.orderNumber}">
  <div class="card card-body">
    <%
	int number_of_items__in_order = 5;
	for (int i = 0; i < number_of_items__in_order; i++) {
	%>
	<jsp:include page="order-history-item.jsp" ></jsp:include>
	<hr class="mb-3">
	<%}
%>
  </div>
</div>
