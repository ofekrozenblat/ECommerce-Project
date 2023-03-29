<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.Item" %>
<%@ page import="java.util.ArrayList" %>

<div class="item-listing d-flex flex-row flex-wrap">
<%
	String list_name = "catalog_list";

	if(request.getParameter("list_name") != null){
		list_name = request.getParameter("list_name");
	}
	
    ArrayList<Item> item_list = (ArrayList<Item>) request.getAttribute(list_name);
    for(Item item: item_list)
    {
%>
        <jsp:include page="listed-item.jsp" >
          <jsp:param name="name" value="<%= item.getName() %>" />
          <jsp:param name="price" value="<%= item.getPrice() %>" />
          <jsp:param name="category" value="<%= item.getCategory() %>" />
          <jsp:param name="brand" value="<%= item.getBrand() %>" />
          <jsp:param name="color" value="<%= item.getColor() %>" />
          <jsp:param name="rating" value="<%= item.getRating() %>" />
          <jsp:param name="description" value="<%= item.getDescription() %>" />
          <jsp:param name="item_id" value="<%= item.getId() %>" />
           <jsp:param name="img" value="<%= item.getImg() %>" />
        </jsp:include>
<%
    }
%>

</div>