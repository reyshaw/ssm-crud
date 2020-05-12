<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%
	request.setAttribute("APP_ROOT", request.getServletContext().getContextPath());
%>
<link href="${APP_ROOT}/static/bootstrap-4.4.1-dist/css/bootstrap.css"  rel="stylesheet"/>
<link href="${APP_ROOT}/static/bootstrap-datepicker/css/bootstrap-datepicker.min.css" rel="stylesheet">
<title>注册</title>
</head>
<body style="background: #e9ecef">

<div class="jumbotron">
  
  <div class="container">
  		<h1 class="display-4">用户注册</h1>
  <hr class="my-4">
		<div class="row">
			<div class="col">
			<form id="form"  onsubmit="return false;" enctype="multipart/form-data" method="post">
		  <!-- <div class="form-row"> -->
		    <div class="form-group">
		      <label for="inputEmail4">用户名</label>
		      <input type="text" class="form-control" id="username">
		    </div>
		    <div class="form-group">
		      <label for="inputPassword4">密码</label>
		      <input type="password" class="form-control" id="password">
		    </div>
		 <!--   </div>-->
		   <div class="form-group">
		    <label for="inputAddress">性别</label>
		    <div class="form-check form-check-inline">
			  <input class="form-check-input" type="radio" checked name="gender" id="inlineRadio1" value="男">
			  <label class="form-check-label" for="inlineRadio1">男</label>
			</div>
			<div class="form-check form-check-inline">
			  <input class="form-check-input" type="radio" name="gender" id="inlineRadio2" value="女">
			  <label class="form-check-label" for="inlineRadio2">女</label>
			</div>
		  </div>
		  <div class="form-group">
		      <label for="address">出生地</label>
		      <select id="address" class="form-control">
		        <option value="北京市" selected>北京市</option>
		        <option value="天津市">天津市</option>
		        <option value="上海市">上海市</option>
		        <option value="重庆市">重庆市</option>
		      </select>
		    </div>
		     <div class="form-group">
		    <label for="birthday">出生日期</label>
		    <div class="input-group date" data-provide="datepicker">
			    <input type="text" class="form-control" id="datetimepicker">
			    <div class="input-group-addon">
			        <span class="glyphicon glyphicon-th"></span>
			    </div>
			</div>
		  </div>
		    <div class="form-group">
		    <label for="avatar">头像</label>
		    <input type="file" class="form-control-file" id="avatar" name="avatar">
		  </div>
		 
		  <div class="form-group">
		    <div class="form-check form-check-inline">
		      <input class="form-check-input" type="checkbox" id="gridCheck1" value="编程">
		      <label class="form-check-label" for="gridCheck1">
		        编程
		      </label>
		    </div>
		     <div class="form-check form-check-inline">
		      <input class="form-check-input" type="checkbox" id="gridCheck2" value="有用">
		      <label class="form-check-label" for="gridCheck2">
		        游泳
		      </label>
		    </div>
		     <div class="form-check form-check-inline">
		      <input class="form-check-input" type="checkbox" id="gridCheck3" value="看比赛">
		      <label class="form-check-label" for="gridCheck3">
		        看比赛
		      </label>
		    </div>
		  </div>
		  <button type="submit" class="btn btn-primary" id="submit">注册</button>
		</form>
			</div>
			<div class="col"></div>
		</div>
	</div>
</div>
	
	<script src="static/jQuery.js"></script>
	<script src="static/bootstrap-4.4.1-dist/js/bootstrap.js"></script>
	<script src="static/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>
	<script>
	$('#datetimepicker').datepicker({
		format: 'yyyy-mm',
	    startView: 1, maxViewMode: 1,minViewMode:1,
	    autoclose :true
	});
		var _url ="<%= request.getContextPath()%>/user/register";
		$("#submit").click(function(e){
			e.preventDefault();
			var formData = new FormData();
			formData.append("username",  $("#username").val())
			formData.append("password",  $("#password").val())
			formData.append("gender",  $("input:radio:checked").val())
			formData.append("address",$("select option:selected").val())
			var date = $("#datetimepicker").val().split("/");
			var newDate = [date[2],date[0],date[1]].join("-") + " 00:00:00";
			formData.append("birthday",  newDate)
			formData.append("file",  $("#avatar")[0].files[0])
			var checked = []
			$("input:checkbox").each(function(){
				if(this.checked) {
					checked.push($(this).val());
				}
			})
			formData.append("hobbies",  checked.join(","))
			
			$.ajax({
				url: _url,
				type: "POST",
				dataType: "json",
				data: formData,
				cache: false,
				processData: false,
				contentType: false,
				success: function(res) {
					if (res.code == 0) {
						alert("注册成功")
					} else {
						alert(res.msg);
					}
				}
			})
		})
	</script>
	
</body>
</html>