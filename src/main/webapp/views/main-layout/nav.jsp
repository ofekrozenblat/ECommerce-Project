<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page import="utill.SessionManager" %>
<!DOCTYPE html>
<nav class="navbar sticky-top navbar-expand-lg navbar-dark bg-dark">
	<div class="container-fluid">
		<a class="navbar-brand" href="Home"> REAGAIL </a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNavDropdown">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link active main-link"
					aria-current="page" href="Home">Home</a></li>
				<li class="nav-item"><a class="nav-link active main-link"
					href="#">About</a></li>
				<li class="nav-item"><a class="nav-link active main-link"
					href="Catalog">Shop</a></li>
			</ul>
			<div class="input-group search-bar">
				<input type="text" class="form-control" type="search"
					placeholder="Search" aria-label="Search"> <span
					class="input-group-text material-symbols-outlined text-light">search</span>
			</div>
			<% 
				SessionManager sm = (SessionManager) request.getSession().getAttribute(SessionManager.SESSION_MANAGER);
				boolean is_Auth = sm.isAuth();
				boolean is_Admin = sm.isAdmin();
				String username = sm.getUsername();
				
				int cart_size = sm.getCart().getSize();
				if(is_Auth){
					%>
			<ul class="navbar-nav ms-auto d-flex">
				<li class="nav-item">
					<a class="nav-link active icon-nav-item position-relative" href="Cart">
							<span class="material-icons-outlined icon-30">shopping_cart
						</span> <span value="<%= cart_size %>" id="nav-cart-size" class="custom-badge"><%= cart_size %></span> <span
							class="icon-nav-item-text">Cart</span>
					</a>
				</li>

				<li class="nav-item">
					<div class="dropdown">
						<a class="nav-link active icon-nav-item dropdown-toggle"
							type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown"
							aria-expanded="false"> <span
							class="material-icons-outlined icon-30">account_circle</span> <span
							class="icon-nav-item-text"><%= username %></span>
						</a>
						<div class="dropdown-menu dropdown-menu-end"
							aria-labelledby="dropdownMenuLink">
							<a class="dropdown-item" href="Account"> <i
								class="material-icons-outlined icon-22">person</i> <span
								class="ml-3">Your Account</span>
							</a>
							<% if(is_Admin){
                        	%>
							<a class="dropdown-item" href=""> <i
								class="material-icons-outlined icon-22">admin_panel_settings</i>
								<span class="ml-3">Admin</span></a>
							<%
                        }
                        %>
							<hr>
							<a onclick="logout()" class="dropdown-item" href=""><i
								class="material-icons-outlined icon-22">logout</i> <span
								class="ml-3">Logout </span> </a>

						</div>
					</div>
				</li>

			</ul>
			<%
				}else{
					%>
			<ul class="navbar-nav ms-auto">
				<li class="nav-item"><a class="nav-link active" href="Login"><span
						class="login">Login</span></a></li>
				<li class="nav-item"><a class="nav-link active" href="Register"><span
						class="sign-up">Sign Up</span></a></li>
			</ul>
			<%
				}
			%>
		</div>
	</div>
</nav>