<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<div class="listed-item card mx-1 my-1 text-center" style="width: 18rem;" onclick="redirect('Item_detail?item_id=1')">
  <img src="res/img/glasses-default.png" class="card-img-top" alt="">
  <div class="card-body">
    <div class="card-title">
    	<h5>${param.name}</h5>
    	<h5>$${param.price}</h5>
    </div>
    <p class="card-text">${param.description}</p>
    <a href="" class="btn btn-custom w-100">Try On</a>
  </div>
</div>