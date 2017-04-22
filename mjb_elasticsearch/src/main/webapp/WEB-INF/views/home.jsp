<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Home</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="${ctx}/resources/css/bootstrap.min.css">
<!-- Optional theme -->
<link rel="stylesheet" href="${ctx}/resources/css/bootstrap-theme.min.css">
</head>
<body>
	<div class="container">
		<div class="row">
			<h1>Spring MVC + Spring Data Elasticsearch Example</h1>
			<ol>
				<li><a href="${ctx}/productpage">Use <b>Page</b> Product Search </a></li>
				<li><a href="${ctx}/storepage">Use <b>Page</b> Store Search </a></li>
<%-- 				<li><a href="${ctx}/bookpage">Use <b>Page</b> Book Search </a></li> --%>
<%-- 				<li><a href="${ctx}/demopage">Use <b>Page</b> demo Search </a></li> --%>
			</ol>
		</div>
	</div>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="${ctx}/resources/js/jquery-1.10.2.min.js"></script>
	<!-- Latest compiled and minified JavaScript -->
	<script src="${ctx}/resources/js/bootstrap.min.js"></script>
</body>
</html>
