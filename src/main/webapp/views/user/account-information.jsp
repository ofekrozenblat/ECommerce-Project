<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<h5 class="mb-3 fw-bold">Account Information</h5>
<hr>
<div id="submit-error" class="alert alert-danger alert-dismissible fade hide" role="alert">
  <strong>Failed to update!</strong> <span>Please try again later.</span>
  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
</div>

<% 
	if(request.getParameter("updatedSuccess") != null){
		%>
		<div class="alert alert-success alert-dismissible" role="alert">
		  <strong>Account Information Updated!</strong></span>
		  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
		</div>
		<%
	}
%>

<form id="account-information-form">
	<div class="mb-3">
    <label for="firstName" class="form-label">First Name</label>
    <input name="firstname" type="text" class="form-control" id="firstName" value="${firstname}" required>
  </div>
  <div class="mb-3">
     <label for="LastName" class="form-label">Last Name</label>
    <input name="lastname" type="text" class="form-control" id="LastName" value="${lastname}" required>
  </div>
  <div class="mb-3">
    <label for="emailAddress" class="form-label">Email Address</label>
    <input name="email" type="email" class="form-control" id="emailAddress" value="${email}" required>
  </div>
  <button id="submit-accountinfo" type="submit" class="btn btn-custom">Update Information</button>
</form>