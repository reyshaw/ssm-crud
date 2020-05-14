<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%
	request.setAttribute("APP_ROOT", request.getServletContext().getContextPath());
%>
<link href="/bootstrap-4.4.1-dist/css/bootstrap.css"  rel="stylesheet"/>
<title>登录</title>
</head>
<body style="background: #e9ecef">
<div class="jumbotron">
	<div class="container">
	<h1 class="display-4">用户登录</h1>
  <hr class="my-4">
		<form id="form">
		  <div class="form-group">
		    <label for="exampleInputEmail1">用户名</label>
		    <input type="text" class="form-control" id="username" aria-describedby="emailHelp">
		    <!-- <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small> -->
		  </div>
		  <div class="form-group">
		    <label for="exampleInputPassword1">密码</label>
		    <input type="password" class="form-control" id="password">
		  </div>
		  <div class="form-group form-check">
		    <input type="checkbox" class="form-check-input" id="exampleCheck1">
		    <label class="form-check-label" for="exampleCheck1">记住密码</label>
		  </div>
		  <button type="submit" class="btn btn-primary" id="submit">登录</button>
		</form>
	</div>
</div>
	
	<script src="/jQuery.js"></script>
	<script src="/bootstrap-4.4.1-dist/js/bootstrap.js"></script>
	<script>
		$("#form").submit(function(e){
			e.preventDefault();
			var data = {username: $("#username").val(), password: $("#password").val()};
			var _url = "<%=request.getContextPath()%>/user";
			$.ajax({
				url:_url,
				type: "POST",
				data: JSON.stringify(data),
				contentType: "application/json",
				success: function(res){
					if (res.code == 0) {
						sessionStorage.setItem("user",data.username);
						location.href = "/list";
					} else {
						alert(res.message);
					}
				}
			})
		})
	</script>
</body>
</html>