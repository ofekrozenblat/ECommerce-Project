<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>REAGAIL - Login</title>

<%@ include file="/views/main-layout/head.html"%>
<link rel="StyleSheet" href="res/css/login.css" type="text/css"></link>
<script type="text/javascript" src="res/js/login.js"></script>

</head>
<body class="d-flex flex-column min-vh-100 bg-dark">

	<form id="login-form" class="login-form bg-light">
	<span class="material-symbols-outlined text-center account-icon">account_circle</span>
		<h1 class="h1 mb-3 fw-normal text-center">Login</h1>
		
		<div id="submit-error" class="alert alert-danger alert-dismissible fade hide" role="alert">
		  <strong>Failed to login!</strong> Please check credentials are correct.
		  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
		</div>

		<div class="form-floating mt-3">
			<input type="email" class="form-control" id="emailInput"
				placeholder="name@example.com" required> <label for="emailInput">Email
				address</label>
		</div>
		<div class="form-floating mt-3">
			<input type="password" class="form-control" id="passwordInput"
				placeholder="Password" required> <label for="passwordInput">Password</label>
		</div>

		<div
			class="d-flex justify-content-between align-items-center mt-3 mb-3">
			<div class="form-check mb-0">
				<input class="form-check-input me-2" type="checkbox" value=""
					id="remember-me-check" /> <label class="form-check-label"
					for="remember-me-check"> Remember me </label>
			</div>
			<a href="#!" class="text-body">Forgot password?</a>
		</div>

		<button class="w-100 btn btn-lg btn-danger" type="submit">Login</button>
		<p class="small fw-bold mt-3 pt-1 mb-0 text-center">Don't have an account? <a href="Register"
                class="link-danger">Sign up now!</a></p>
          
	</form>

</body>
</html>