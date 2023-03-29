<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<div class="order-item my-3">
	<div class="order-item-img cursor-pointer" onclick="redirect('Item_detail?item_id=${param.item_id}')">
		<img src="${param.img}" class="item-cart-img" alt="">
	</div>
	<div class="order-item-body">
		<div class="w-100 d-flex flex-row justify-content-between mb-1">
			<h6 class="fw-bold">${param.name} (${param.quantity})</h6>
			<h6 class="fw-bold">$${param.price}</h6>
		</div>
	</div>
</div>
