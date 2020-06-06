<%@ page import = "com.controller.dto.UserDto" %>
<%@ page import = "java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Serach Result Page</title>
<meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
   <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<div  class="container">
<br/><br/><br/>
<h1>Search Results</h1>
	<hr/>
	<table class="table table-dark table-striped">
     <thead>
       <tr>
         <th>UserName</th>
         <th>Name</th>
         <th>Email</th>
         <th>Salutation</th>
         <th>Date Created</th>
         <th>Role</th>
       </tr>
     </thead>
     <tbody>

     <%
     @SuppressWarnings("unchecked")
     	List<UserDto> list =( List<UserDto>)request.getAttribute("resultList");
     
     try{
     	for(UserDto entity:list){
     %>
       
       <tr>
         <td><%=entity.getUserName() %></td>
         <td><%=entity.getName() %></td>
         <td><%=entity.getEmail() %></td>
           <td><%=entity.getSalutation() %></td>
              <td><%=entity.getDateCreated() %></td>
             <td><%=entity.getRole() %></td>
       </tr>

     <% }}
     catch (Exception e){
    	 e.printStackTrace();
     }%> 

     </tbody>
   </table>

</div>

</body>
</html>