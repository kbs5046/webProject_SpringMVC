<!DOCTYPE html>
<html>
<head>
<title>Success</title>
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
  		<img src="${pageContext.request.contextPath}/img/congrates.png"  style="height: 120px;">
  		<div class="media">
		    <div class="media-left">
		      <img src="${pageContext.request.contextPath}/img/user.png" class="media-object" style="width:100px; height: 80px;">
		    </div>
		    <div class="media-body">
		      <h4 class="media-heading">You are a valid user and your details are</h4>
	  		 Username :  ${param.username}
	  		 <br/>
	  		 Email :  ${email}<br/>
	  		 Name :  ${name}<br/>
	  		 Salutation :  ${salutation}
		    </div>
		</div>
  		 <br/>
  		 Total Logged in User : =  ${activeSessions}<br>
  		  <hr/>
  		 <a href="paginateUsers">
  		   <button type="button" class="btn btn-danger" style ="background-color: Green"> Goto Users </button>
  		   </a>
  		   <a href="${pageContext.request.contextPath}/search">
   		 		<button type="submit" class="btn btn-danger" style="background-color: #2196f3">Search User</button>
   		 	</a>
  		 <hr/>
   		 	<a href="${pageContext.request.contextPath}/logOut">
   		 		<button type="submit" class="btn btn-danger" style="background-color: Black">LogOut</button>
   		 	</a>
   		 	
   		 	
   		
  	</div>	
</body>
</html>