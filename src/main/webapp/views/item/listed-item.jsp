<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<div class="listed-item card mx-1 my-1 text-center overflow-hidden" style="width: 18rem;" onclick="redirect('Item_detail?item_id=${param.item_id}')"
data-rating="${param.rating}" data-brand="${param.brand}" data-color="${param.color}" data-category="${param.category}" data-price="${param.price}" data-name="${param.name}">
  <img src="${param.img}" class="card-img-top my-1 mx-1 item-listed-img" alt="">
  <div class="card-body">
    <div class="card-title">
    	<h5>${param.name}</h5>
    	<h5>$${param.price}</h5>
    </div>
    <p class="card-text align-middle">${param.description}</p>
  </div>
   <div class="card-footer">
    <a href="" class="btn btn-custom w-100">Try On</a>
  </div>
</div>