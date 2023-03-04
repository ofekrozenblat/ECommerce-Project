<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>REAGAIL - Sign Up</title>

<%@ include file="/views/main-layout/head.html"%>
<link rel="StyleSheet" href="res/css/login.css" type="text/css"></link>
<script type="text/javascript" src="res/js/register.js"></script>

</head>
<body class="d-flex flex-column min-vh-100 bg-dark">

	<form id="register-form" class="login-form bg-light">
		<span class="material-symbols-outlined text-center account-icon">account_circle</span>
		<h1 class="h1 mb-3 fw-normal text-center">Sign Up</h1>
		
		<div id="submit-error" class="alert alert-danger alert-dismissible fade hide" role="alert">
		  <strong>Something went wrong!</strong> Please try again later.
		  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
		</div>
		
		<div class="d-flex flex-row justify-content-between mt-3 mb-3">
			
			<div class="form-floating">
				<input type="text" class="form-control" id="firstNameInput"
					placeholder="First name" required> <label for="firstNameInput">First
					name</label>
			</div>
			<div class="form-floating">
				<input type="text" class="form-control" id="lastNameInput"
					placeholder="Last name" required> <label for="lastNameInput">Last
					name</label>
			</div>
		</div>
		<div class="form-floating mt-3">
			<input type="email" class="form-control" id="emailInput"
				placeholder="name@example.com" required> <label for="emailInput">Email
				address</label>
		</div>
		<div class="form-floating mt-3 mb-3">
			<input type="password" class="form-control" id="passwordInput"
				placeholder="Password" required> <label for="passwordInput">Password</label>
		</div>


		<button class="w-100 btn btn-lg btn-danger" type="submit">Sign Up</button>
			<p class="small fw-bold mt-3 pt-1 mb-0 text-center">Already have an account? <a href="Login"
                class="link-danger">Login Here!</a></p>
	</form>

</body>
</html>