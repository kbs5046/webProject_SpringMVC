<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search Page</title>
<meta charset="utf-8">
  <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
   <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container"> 
	<form action="searchByName" >
	<br/><br/><br/>
		<b>Search Profile</b>
  		   <input type="text"  name="searchName"  class="form-control"  required="required" placeholder="search by name" style="width: 50%;">
  		   <br/>
  		   <button type="submit" class="btn btn-primary" style ="background-color: #2196f3;">Go</button>
  		   <br/>
  		   <span style="color:red;font-weight: bold;">${message}</span>
	
	</form>
</div>
</body>
</html>