<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Books Search</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="${ctx}/resources/css/bootstrap.min.css">
<!-- Optional theme -->
<link rel="stylesheet" href="${ctx}/resources/css/bootstrap-theme.min.css">
</head>
<body>
	<div class="container">
		<div class="row">
			<form id="myForm" class="navbar-form navbar-left" data-keyboard="false" role="search">
				<div class="form-group">
					<input id="search" type="text" class="form-control" placeholder="Search">
				</div>
				<button id="btn-query" type="button" class="btn btn-default">Message Query</button>
			</form>
		</div>

		<div class="row">
			<table id="table-data" class="table table-hover">
				<thead>
					<tr>
						<th>ID</th>
						<th>demo</th>
						<th>type</th>
						<th>delflag</th>
					</tr>
				</thead>
				<tbody id='tbody'></tbody>
			</table>
		</div>

		<div class="text-center">
			<div id="page-selection" class="pagination-sm"></div>
		</div>
	</div>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="${ctx}/resources/js/jquery-1.10.2.min.js"></script>
	<!-- Latest compiled and minified JavaScript -->
	<script src="${ctx}/resources/js/bootstrap.min.js"></script>
	<script src="${ctx}/resources/js/jquery.bootpag.js"></script>
	<script>
		$(function() {
			$('#btn-query').on('click', function(){
				var query = $('#search').val();
				$('#page-selection').bootpag({'page': '1'});
				refreshTable(query, 1);
			});
			
			$('#page-selection').bootpag({
				total : 10,
				visiblePages : 10
			}).on("page", function(event, num) {
				var query = $('#search').val();
				refreshTable(encodeURIComponent(query), num);
			});
			
			var refreshTable = function(query, page) {
			    $.ajax({
			        url: '${url}',
			        data: "query=" + query + "&page=" + (page - 1),
			        type: "GET",
			        dataType: 'json',
			      	success: function(data) {
						var content = data.content;
						var array = [];
						if(content != null ){
							content.forEach(function(entry){
								array.push('<tr><td>' + entry._source.id 
										+ '</td><td>' + entry._source.demo 
										+ '</td><td>' + entry._source.type 
										+ '</td><td>' + entry._source.delflag 
										+ '</td></tr>');
							});
						}
	
						$('#tbody').html(array.join (''));
						$('#page-selection').bootpag({total: data.totalPages, visiblePages: data.totalPages > 10 ? 10 : data.totalPages});
			      },
			      error: function(error){
			    	  alert(error);
			      }
			    });
			}
			
			$('#myForm').bind('keydown', function(e) {
			    if (e.keyCode == 13) {
			        e.preventDefault();
			        $('#btn-query').click();
			    }
			});
			
			//refreshTable('', 1);
		});
	</script>
</body>

</html>
