<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<div class="card w-100 my-2 mx-5" data-rating="${param.rating}" data-date="${param.date}">
	<div class="card-header">
		<div class="d-flex flex-row flex-wrap align-items-center mb-2">
			<jsp:include page="star-rating.jsp">
				<jsp:param name="rating" value="${param.rating}" />
			</jsp:include>
		</div>
	</div>
	<div class="card-body">
		<h5 class="card-title fw-bold">${param.title}</h5>
		<p class="card-text">${param.description}</p>
	</div>
	<div class="card-footer text-muted">${param.date}</div>
</div>