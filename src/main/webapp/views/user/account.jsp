<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>REAGAIL</title>

<%@ include file="/views/main-layout/head.html"%>
<link rel="StyleSheet" href="res/css/account.css" type="text/css"></link>
<script type="text/javascript" src="res/js/account.js"></script>
</head>
<body class="d-flex flex-column min-vh-100">

	<jsp:include page="/views/main-layout/nav.jsp" />

	<div
		class="page-wrapper overflow-hidden d-flex flex-row justify-content-center my-3">
		<div class="w-10"></div>
		<div class="d-flex align-items-start w-75">
			<div class="bg-light nav flex-column me-3" id="v-pills-tab"
				role="tablist" aria-orientation="vertical">
				<button class="nav-link-custom active"
					id="v-pills-account-information-tab" data-bs-toggle="pill"
					data-bs-target="#v-pills-account-information" type="button"
					role="tab" aria-controls="v-pills-account-information"
					aria-selected="true">
					<span class="material-icons-outlined">person</span>
					Account Information
					</button>
					
				<button class="nav-link-custom" id="v-pills-security-tab"
					data-bs-toggle="pill" data-bs-target="#v-pills-security"
					type="button" role="tab" aria-controls="v-pills-security"
					aria-selected="false">
					<span class="material-icons-outlined">shield</span>
					Security
					</button>
				<button class="nav-link-custom" id="v-pills-order-history-tab"
					data-bs-toggle="pill" data-bs-target="#v-pills-order-history"
					type="button" role="tab" aria-controls="v-pills-order-history"
					aria-selected="false">
					<span class="material-icons-outlined">history</span>
					Order History
					</button>
			</div>
			<div class="tab-content bg-light ms-3 px-3 py-3 w-50" id="v-pills-tabContent">
			
				<div class="tab-pane fade show active"
					id="v-pills-account-information" role="tabpanel"
					aria-labelledby="v-pills-account-information-tab" tabindex="0">
					<jsp:include page="account-information.jsp" /></div>
					
				<div class="tab-pane fade" id="v-pills-security" role="tabpanel"
					aria-labelledby="v-pills-security-tab" tabindex="0">	
					<jsp:include page="account-security.jsp" /></div>
					
				<div class="tab-pane fade" id="v-pills-order-history"
					role="tabpanel" aria-labelledby="v-pills-order-history-tab"
					tabindex="0"><jsp:include page="order-history.jsp" /></div>
			</div>
		</div>
	</div>

	<%@ include file="/views/main-layout/footer.html"%>

</body>
</html>