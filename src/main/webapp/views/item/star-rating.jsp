<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<div class="d-flex flex-row" style="color: rgba(255,51,56,1.00)">
<%
int rating = Integer.parseInt(request.getParameter("rating")) - 1;
for(int i = 0; i <= 4; i++){
	if(i <= rating){
	{%>
		<span class="material-icons">grade</span>
	<%}
	}else{
		{%>
		<span class="material-icons-outlined">grade</span>
	<%}
	}
}
%>
</div>