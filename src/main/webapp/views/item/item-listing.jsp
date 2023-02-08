<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<div class="item-listing d-flex flex-row flex-wrap justify-content-evenly">
<%
	int list_amount = Integer.parseInt(request.getParameter("list_amount"));
	for(int i = 0; i < list_amount; i++)
	{%>
		<jsp:include page="listed-item.jsp" />
	<%}
%>

</div>