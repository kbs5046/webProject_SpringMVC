<%@ page import = "com.controller.dto.UserDto" %>
<%@ page import = "java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<!DOCTYPE html>
 <html>
 <head>
 <title>Users Page</title>
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
   		<img src="${pageContext.request.contextPath}/img/student.png"  style="height: 160px;">
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
  		<span style="color:red;font-weight: bold;">${message}</span>
   		 <hr/>
 
   		  ${recordStarts} -  ${recordStarts+pageSize-1}  of  ${totalRecords}

   		  	<a href="${pageContext.request.contextPath}/paginateUsers??pageid=${pageid-1}&plimit=${pageSize}"><img src="${pageContext.request.contextPath}/img/previous.png" style="height: 40px;"></a>
   		  	<a href="${pageContext.request.contextPath}/paginateUsers?pageid=${pageid+1}&plimit=${pageSize}"><img src="${pageContext.request.contextPath}/img/next.png" style="height: 40px;"></a>
   		  		<br/>
   		  	<form action ="paginateUsers">
   		  		<b>PageLimit</b>
  		   		<select name="plimit"  style="width: 10%;">
  		   			<option ${pageSize == 3 ? 'selected':''}>3</option>
  		      		<option ${pageSize == 5 ? 'selected':''}>5</option>
  		      		<option ${pageSize == 10 ? 'selected':''}>10</option>
  		   		</select>
  		   		<button type="submit" class="btn btn-primary">go</button>

  		   	</form>
   		  <table class="table table-bordered">
     <thead>
       <tr>
         <th>UserName</th>
         <th>Name</th>
         <th>Email</th>
         <th>Salutation</th>
         <th>Date Created</th>
         <th>Role</th>
         <th>Picture</th>
         <th>Action</th>
       </tr>
     </thead>
     <tbody>

     <%
     @SuppressWarnings("unchecked")
     	List<UserDto> signupList =( List<UserDto>)request.getAttribute("signupList");
     
     try{
     	for(UserDto entity:signupList){
     %>
       
       <tr>
         <td><%=entity.getUserName() %></td>
         <td><%=entity.getName() %></td>
         <td><%=entity.getEmail() %></td>
           <td><%=entity.getSalutation() %></td>
              <td><%=entity.getDateCreated() %></td>
             <td><%=entity.getRole() %></td>
   
             <td>
             	<img src="${pageContext.request.contextPath}/displayImage?uid=<%=entity.getuID() %>"  style="height: 100px;">
             </td>
             
             <td>
             <a href="deleteSignup?sid=<%=entity.getuID() %>">
                    <img src="${pageContext.request.contextPath}/img/del.png"  style="height: 40px;">
             </a>
             <a href="selectSignup?sid=<%=entity.getuID() %>">
                 <img src="${pageContext.request.contextPath}/img/edit.png"  style="height: 40px;">
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