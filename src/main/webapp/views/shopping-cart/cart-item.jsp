<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<div class="cart-item">
	<div class="cart-item-img cursor-pointer" onclick="redirect('Item_detail?item_id=${param.item_id}')">
		<img src="res/img/glasses-default.png" class="img-fluid" alt="">
	</div>
	<div class="cart-item-body">
		<div class="w-100 d-flex flex-row justify-content-between mb-1">
			<h5 class="fw-bold"> ${param.name}</h5>
			<h5 class="fw-bold"> $${param.price}</h5>
		</div>
		<p class="mb-2">${param.description}</p>
		
		<div class="quantity-adjust d-flex flex-row mt-3 mb-3 align-items-center w-100">
			<span onclick="editQuantity('${param.item_id}', '-1')"class="material-icons-outlined btn-custom-round quantity-adjust-button">remove</span>
	        <span class="mx-4 fw-bold quantity-adjust-text">${param.quantity}</span>
			<span onclick="editQuantity('${param.item_id}', '1')" class="material-icons-outlined btn-custom-round quantity-adjust-button">add</span>
			
			<div class="ms-auto remove-item"> 
				<span class="material-icons-outlined remove-item-icon">delete</span> 
				<span onclick="deleteFromCart('${param.item_id}')"class="remove-item-text">Delete</span>
			</div>
		</div>
	</div>
</div>
<hr class="mb-3">