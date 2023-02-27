<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<h5 class="mb-3 fw-bold">Account Information</h5>
<hr>
<form>
	<div class="mb-3">
    <label for="firstName" class="form-label">First Name</label>
    <input type="text" class="form-control" id="firstName">
  </div>
  <div class="mb-3">
     <label for="LastName" class="form-label">Last Name</label>
    <input type="text" class="form-control" id="LastName">
  </div>
  <div class="mb-3">
    <label for="emailAddress" class="form-label">Email Address</label>
    <input type="email" class="form-control" id="emailAddress">
  </div>
  <button type="submit" class="btn btn-custom">Update Information</button>
</form>