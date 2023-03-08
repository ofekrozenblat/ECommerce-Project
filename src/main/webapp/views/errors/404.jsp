<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>REAGAIL - Page Not Found</title>

<%@ include file="/views/main-layout/head.html" %>

</head>
<body class="d-flex flex-column min-vh-100">

<jsp:include page="/views/main-layout/nav.jsp"/>


<div class="page-wrapper overflow-hidden">
	  <div class="d-flex align-items-center justify-content-center vh-100">
            <div class="text-center">
                <h1 class="display-1 fw-bold">404</h1>
                <p class="fs-3"> <span class="text-danger">Oops!</span> Page not found.</p>
                <p class="lead">
                   The page you're looking for doesn't exist.
                  </p>
                <a href="Home" class="btn btn-custom">Go Home</a>
            </div>
        </div>
</div>

<%@ include file="/views/main-layout/footer.html" %>

</body>
</html>