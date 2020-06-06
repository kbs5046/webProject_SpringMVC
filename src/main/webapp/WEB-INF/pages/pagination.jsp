<%@ page import = "com.dao.entity.UserEntity" %>
<%@ page import = "java.lang.Math" %>
<%@ page import = "java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%  
@SuppressWarnings("unchecked")
    List<UserEntity> signupList =( List<UserEntity>)request.getAttribute("signupList");
    int pageLimit = 3;
    int userCount = signupList.size();
    int pages = (int)Math.ceil(userCount/pageLimit);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Users List</title>
<meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
   <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<header style="height: 30px;background-color: #2196f3;">
   </header>

   <div  class="container">
         <br/>
   		<img src="img/student.png"  style="height: 160px;">
   		 <h3>User Sign List</h3>
   		 <hr/>
   		 	<a href="logOut">
   		 		<button type="submit" class="btn btn-danger" style="background-color: Black">LogOut</button>
   		 	</a>
   		 	<br/>
   		 	Current Role : - ${sessionScope.role}
   		 	<br/>
   		   <hr/>
   		 Username :  ${username}
   		 <br/>
   		 Email :  ${email}<br/>
   		 Name :  ${name}<br/>
   		 Salutation :  ${salutation}
   		<br/>
   		  Total Logged in User : =  ${activeSessions}
   		  <hr/>
   		<span style="background-color: red;font-weight: bold;"> ${param.message}</span>
  		
  		<br/>
  		<a href="pagination.jsp?page=<?= $Previous; ?>" aria-label="Previous">
				        <span aria-hidden="true">&laquo; Previous</span>
		</a>
		
		<%
			for(int i=0;i<pages;i++){%>
				<a href="pagination.jsp">
<%-- 					<button type="button" class="btn btn-danger"> <% i; %> </button> --%>
				</a>
			<%}
		%>
		
		<a href="index.php?page=<?= $Next; ?>" aria-label="Next">
				        <span aria-hidden="true">Next &raquo;</span>
   		 </a>
   		 <hr/>
   		  <table class="table table-bordered">
     <thead>
       <tr>
         <th>UserName</th>
         <th>Name</th>
         <th>Email</th>
         <th>Salutation</th>
         <th>Date Created</th>
         <th>Role</th>
         <th>Action</th>
       </tr>
     </thead>
     <tbody>

     <%  
     try{
     	for(UserEntity entity:signupList){
     %>
       
       <tr>
         <td><%=entity.getUserName() %></td>
         <td><%=entity.getName() %></td>
         <td><%=entity.getEmail() %></td>
           <td><%=entity.getSalutation() %></td>
            <td><%=entity.getDateCreated() %></td>
             <td><%=entity.getRole() %></td>
             <td>
             <a href="deleteSignup?sid=<%=entity.getuID() %>">
                    <img src="img/del.png"  style="height: 40px;">
             </a>
             <a href="selectSignup?sid=<%=entity.getuID() %>">
                 <img src="img/edit.png"  style="height: 40px;">
                </a> 
             </td>
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