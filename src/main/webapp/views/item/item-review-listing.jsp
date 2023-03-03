<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.Review" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<%
 ArrayList<Review> reviews = (ArrayList<Review>) request.getAttribute("reviews");

	for(Review review: reviews)
	{%>
		<jsp:include page="item-review.jsp">
			<jsp:param name="rating" value="<%= review.getRating() %>" />
			<jsp:param name="date" value="<%= review.getDateString() %>" />
			<jsp:param name="description" value="<%= review.getDescription() %>" />
			<jsp:param name="title" value="<%= review.getTitle() %>" />
		</jsp:include>
	<%}
%>