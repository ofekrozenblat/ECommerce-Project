<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<div class="card w-100 my-2 mx-5">
	<div class="card-header">
		<div class="d-flex flex-row flex-wrap align-items-center mb-2">
			<jsp:include page="star-rating.jsp">
				<jsp:param name="rating" value="${param.rating}" />
			</jsp:include>
		</div>
	</div>
	<div class="card-body">
		<h5 class="card-title fw-bold">Special title treatment</h5>
		<p class="card-text">With supporting text below as a natural
			lead-in to additional content.</p>
	</div>
	<div class="card-footer text-muted">${param.date}</div>
</div>