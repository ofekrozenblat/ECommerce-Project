<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>REAGAIL - Admin</title>

<%@ include file="/views/main-layout/head.html" %>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.13.3/css/jquery.dataTables.css">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/1.13.3/js/jquery.dataTables.min.js"></script>

<script src="res/js/admin.js"></script>

</head>
<body class="d-flex flex-row min-vh-100">

<jsp:include page="/views/admin/admin-nav.jsp"/>

<div class="page-wrapper overflow-hidden w-100 mt-3 me-3">
  <div class="d-flex align-items-center justify-content-center">
            <div class="text-center">
                <h1 class="display-3 fw-bold">REAGAIL - Admin Panel</h1>
                <p class="fs-3">To run reports, select one of the options on the sidebar.</p>
                <a href="Home" class="btn btn-custom">Go Back Home</a>
            </div>
        </div>
</div>

</body>
</html>