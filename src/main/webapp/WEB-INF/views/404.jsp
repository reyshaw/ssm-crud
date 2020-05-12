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
<title>欢迎,Welcome</title>
</head>
<body>
	<div>
		404页面,页面不存在
	</div>
	<script src="static/jQuery.js"></script>
	<script src="static/bootstrap-4.4.1-dist/js/bootstrap.js"></script>
</body>
</html>