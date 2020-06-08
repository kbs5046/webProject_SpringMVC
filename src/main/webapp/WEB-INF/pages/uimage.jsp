<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Upload Image Page</title>
 <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
  <script>
	$(document).ready(function(){
		$("#imagemodel").modal('show');
	});

	var loadFile = function(event) {
	var output = document.getElementById('output');
	output.src = URL.createObjectURL(event.target.files[0]);
	output.onload = function() {
	URL.revokeObjectURL(output.src) // free memory
	}
	};
</script>
</head>
<body>

 <div  class="container">
             	 <!-- The Modal -->
			  <div class="modal" id="imagemodel">
			    <div class="modal-dialog">
			      <div class="modal-content">
			        <div class="modal-header">
			          <h4 class="modal-title">Upload New Image</h4>
			          <button type="button" class="close" data-dismiss="modal">&times;</button>
			        </div>
			        <div class="modal-body">			        				          
			        	<img src="${pageContext.request.contextPath}/displayImage?uid=${entityForImage.getuID()}" style="height: 200px; width: 260px;">
				       <hr/>
<%-- 				       <form action ="${pageContext.request.contextPath}/updateImageByID?uid=${entityForImage.getuID()}" method="post" enctype="multipart/form-data"> --%>
				       <form action ="editSignUp" method="post" enctype="multipart/form-data">
				       	<input type="hidden" name="uID" value="${entityForImage.uID }">
				       	<input type="file"  name="photo"  class="form-control" onchange="loadFile(event)"style="width: 50%;background-color: #ecffde;height: 44px">
				       	<img id="output" style="height: 200px; width:260px"/><span class="spinner-border spinner-border-sm"></span>
				        	<br/>
				        	
				        	<button type="submit" class="btn btn-info btn-lg">Upload</button>
				        	
				        </form>
			
			        </div>
			        <div class="modal-footer">
			          <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
			        </div>  
			      </div>
			    </div>
			  </div>
 
 	
 </div>

</body>
</html>