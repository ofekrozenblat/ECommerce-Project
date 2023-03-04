<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>REAGAIL</title>

<%@ include file="/views/main-layout/head.html" %>
<link rel="StyleSheet" href="res/css/checkout.css" type="text/css"></link>

</head>
<body class="d-flex flex-column min-vh-100">

<jsp:include page="/views/main-layout/nav.jsp"/>


<div class="page-wrapper overflow-hidden d-flex flex-row justify-content-center">

	<div class="bg-light w-50 ms-auto my-3">
		<div class="col-md-7 col-lg-8 mx-auto my-3">
	        <h4 class="mb-3 fw-bold">Place Order</h4>
	        <hr>
	        <h4 class="mb-3">Shipping Address</h4>
	        <form class="needs-validation" novalidate>
	          <div class="row g-3">
	
	            <div class="col-12">
	              <label for="address" class="form-label">Address</label>
	              <input type="text" class="form-control" id="address" placeholder="1234 Main St" required>
	              <div class="invalid-feedback">
	                Please enter your shipping address.
	              </div>
	            </div>
	
	            <div class="col-12">
	              <label for="address2" class="form-label">Address 2 <span class="text-muted">(Optional)</span></label>
	              <input type="text" class="form-control" id="address2" placeholder="Apartment or suite">
	            </div>
	
	            <div class="col-md-5">
	              <label for="country" class="form-label">Country</label>
	              <select class="form-select" id="country" required>
	                <option value="">Choose...</option>
	                <option>Canada</option>
	              </select>
	              <div class="invalid-feedback">
	                Please select a valid country.
	              </div>
	            </div>
	
	            <div class="col-md-4">
	              <label for="state" class="form-label">Province</label>
	              <select class="form-select" id="state" required>
	                <option value="">Choose...</option>
	                <option>Ontario</option>
	              </select>
	              <div class="invalid-feedback">
	                Please provide a valid state.
	              </div>
	            </div>
	
	            <div class="col-md-3">
	              <label for="zip" class="form-label">Postal Code</label>
	              <input type="text" class="form-control" id="zip" placeholder="" required>
	              <div class="invalid-feedback">
	                Postal code required.
	              </div>
	            </div>
	          </div>
	
	          <hr class="my-4">
	
	          <h4 class="mb-3">Payment</h4>
	
	          <div class="my-3">
	            <div class="form-check">
	              <input id="credit" name="paymentMethod" type="radio" class="form-check-input" checked="" required="">
	              <label class="form-check-label" for="credit">Credit card</label>
	            </div>
	            <div class="form-check">
	              <input id="debit" name="paymentMethod" type="radio" class="form-check-input" required="">
	              <label class="form-check-label" for="debit">Debit card</label>
	            </div>
	          </div>
	
	          <div class="row gy-3">
	            <div class="col-md-6">
	              <label for="cc-name" class="form-label">Name on card</label>
	              <input type="text" class="form-control" id="cc-name" placeholder="" required="">
	              <small class="text-muted">Full name as displayed on card</small>
	              <div class="invalid-feedback">
	                Name on card is required
	              </div>
	            </div>
	
	            <div class="col-md-6">
	              <label for="cc-number" class="form-label">Credit card number</label>
	              <input type="text" class="form-control" id="cc-number" placeholder="" required="">
	              <div class="invalid-feedback">
	                Credit card number is required
	              </div>
	            </div>
	
	            <div class="col-md-3">
	              <label for="cc-expiration" class="form-label">Expiration</label>
	              <input type="text" class="form-control" id="cc-expiration" placeholder="" required="">
	              <div class="invalid-feedback">
	                Expiration date required
	              </div>
	            </div>
	
	            <div class="col-md-3">
	              <label for="cc-cvv" class="form-label">CVV</label>
	              <input type="text" class="form-control" id="cc-cvv" placeholder="" required="">
	              <div class="invalid-feedback">
	                Security code required
	              </div>
	            </div>
	          </div>
	
	          <hr class="my-4">
	
	          <button class="w-100 btn btn-custom" type="submit">Place Order</button>
	        </form>
	      </div>
	</div>
	
	<div class="w-25 ms-4 me-auto my-3">
		<div class="card">
		  <h5 class="card-header fw-bold">Order Summary</h5>
		  <div class="card-body">
		    <ul class="list-group list-group-flush">
                <li class="list-group-item d-flex justify-content-between align-items-center border-0 px-0 pb-0">
                  Products
                  <span>$${total}</span>
                </li>
                <li class="list-group-item d-flex justify-content-between align-items-center px-0">
                  Shipping
                  <span>$${shipping}</span>
                </li>
                <li class="list-group-item d-flex justify-content-between align-items-center border-0 px-0 mb-3">
                  <div>
                    <strong>Total amount</strong>
                  </div>
                  <span><strong>$${total + shipping}</strong></span>
                </li>
              </ul>
             <a href="Cart" class="btn btn-custom ms-auto">&#8592 Back to Cart</a>
		  </div>
		</div>
	</div>
	
</div>

<%@ include file="/views/main-layout/footer.html" %>

</body>
</html>