<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%
	String APP_ROOT = request.getContextPath();
%>
<meta charset="UTF-8">
<link href="static/bootstrap-4.4.1-dist/css/bootstrap.css"  rel="stylesheet"/>
<title>欢迎,Welcome</title>
<style>
	.btn{margin-left: 5px;}
</style>
</head>
<body>
	<div class="container">
		<h2>员工列表 <button type="button" class="btn btn-primary btn-sm" data-action="add">新增</button></h2>
		<!-- 数据区 -->
		<table class="table table-bordered">
			<thead>
				<tr><th>员工id</th><th>员工姓名</th><th>员工性别</th><th>员工邮箱</th><th>部门id</th><th>部门名称</th><th>操作</th></tr>
			</thead>
			<tbody id="list">
			</tbody>
		</table>
		
		<!--  分页 -->
		<div class="row">
			<div class="col-9">
				<nav aria-label="Page navigation example">
				  <ul class="pagination">
				    <!-- <li class="page-item"><a class="page-link" href="#">上一页</a></li>
				    <li class="page-item"><a class="page-link" href="#">1</a></li>
				    <li class="page-item"><a class="page-link" href="#">2</a></li>
				    <li class="page-item"><a class="page-link" href="#">3</a></li>
				    <li class="page-item"><a class="page-link" href="#">下一页</a></li> -->
				  </ul>
				</nav>
			</div>
			<div class="col-3">
				<strong>当前第<span id="current_page"></span>/<span id="total_page"></span>页,总共<span id="total"></span>条数据</strong>
			</div>
		</div>	
	</div>
	
	<!-- 模态框 新增/编辑 -->
	<div class="modal fade" id="modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">新增员工</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form id="action_form">
          <div class="form-group">
            <label for="recipient-name" class="col-form-label">员工ID:</label>
            <input type="text" class="form-control" id="id" disabled>
          </div>
           <div class="form-group">
            <label for="recipient-name" class="col-form-label">员工姓名:</label>
            <input type="text" class="form-control" id="name">
          </div>
           <div class="form-group">
            <label for="recipient-name" class="col-form-label">员工性别:</label>
             <select class="form-control" id="gender">
		      <option value="男">男</option>
		      <option value="女">女</option>
		    </select>
          </div>
           <div class="form-group">
            <label for="recipient-name" class="col-form-label">员工邮箱:</label>
            <input type="text" class="form-control" id="email">
          </div>
          <div class="form-group">
            <label for="message-text" class="col-form-label">员工部门:</label>
            <select class="form-control" id="dept_id">
		    </select>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary" id="submit">确定</button>
      </div>
    </div>
  </div>
</div>
	<script src="static/jQuery.js"></script>
	<script src="static/bootstrap-4.4.1-dist/js/bootstrap.js"></script>
	
	<script type="text/javascript">
		var current_action = ""; // 请求类型
		var action_prefix = "<%= APP_ROOT %>/emp"; // 请求路径
		var current_row = null; // 当前操作的行
		
		var pageSize = 1; // 每页的条数
		var pageNum = 1; // 当前页码
	
		// 获取所有部门
		$.get(action_prefix.replace("emp", "dept"), function(res) {
				var data = res.data;
				var _options = "";
				data.map(option => {
					_options += "<option value='"+option.deptId+"'>"+option.deptName+"</option>";
				})
				$("#dept_id").html(_options);
			})
		// 分页获取数据
		getEmpList(pageNum, pageSize);
		
		// 点击操作按钮显示哪个模态框
		$('#list, h2').on("click", "button",function(el) {
			var action = $(this).attr("data-action")
			var id = $(this).parents("tr").children().eq(0).text();
			current_action = action;
			switch(action) {
				case 'check': // 查看
					// 这里不需要查，因为要的数据本地都有(这里只做演示)
					$("#exampleModalLabel").text("员工详情");
					getEmp(id, 'check');
					break;
				case 'add': // 新增
					$("#exampleModalLabel").text("添加员工");
					$('.form-control').val("").not("#id").removeAttr("disabled");
					$('#modal').modal();
					break;
				case 'edit': // 编辑
					$("#exampleModalLabel").text("编辑员工信息");
					current_row = $(this).parents("tr");
					getEmp(id, 'edit');
					break;
				case 'delete': // 删除
				current_row = $(this).parents("tr");
				$.ajax({
					url: action_prefix + "/" + id,
					type: "DELETE",
					success: function(res) {
						if (res.code === 0) {
							alert("删除成功");
							current_row.remove();
						} else {
							alert("删除失败")
						}
					}
				})
				break;
				default: 
				break;
			}
		})
		
		// 增改查
		$("#submit").click(function(el) {
			// 查看
			if (current_action == 'check') {
				$("#modal").modal("hide");
			}
			// 新增
			if (current_action == 'add') {
				var data = { "name": $("#name").val(), "gender": $("#gender").val(),"email": $("#email").val(),"dept_id": $("#dept_id").val()};
				// $.post(action_prefix, data, function(res){}, 'json'); 改变不了头
				$.ajax({
					url: action_prefix,
					type: "POST",
					data: JSON.stringify(data),
					dataType: "JSON",
					contentType: "application/json",
					success: function(res){
						if (res.code === 0) {
							alert("新增成功");
							getEmpList(1, pageSize);
							$("#modal").modal("hide");
						} else {
							alert(res.msg);
						}
					}
				})
			}
			
			if (current_action === "edit") {
				// 更新
				var data = { "name": $("#name").val(), "gender": $("#gender").val(),"email": $("#email").val(),"dept_id": $("#dept_id").val()};
				// $.post(action_prefix, data, function(res){}, 'json'); 改变不了头
				$.ajax({
					url: action_prefix + "/" + $("#id").val(),
					type: "PUT",
					data: JSON.stringify(data),
					dataType: "JSON",
					contentType: "application/json",
					success: function(res){
						if (res.code === 0) {
							alert("更新成功");
							current_row.find("td").eq(1).text(data.name);
							current_row.find("td").eq(2).text(data.gender);
							current_row.find("td").eq(3).text(data.email);
							current_row.find("td").eq(4).text(data.dept_id);
							var _opts = $("#dept_id").find("option");
							for(var i = 0; i < _opts.length; i++) {
								if (_opts.eq(i).attr("value") == data.dept_id) {
									current_row.find("td").eq(5).text(_opts.eq(i).text());
									break;
								}
							}
							$("#modal").modal("hide");
						} else {
							alert(res.msg);
						}
					}
				})
			}
		})
		
			// 点击不同页
		$(".pagination").on("click", "li", function() {
			var text = $(this).text();
			if (text === "上一页") { // 上一页
				pageNum -= 1;
			} else if(text === "下一页"  ) { /// 下一页
				pageNum += 1;
			} else { // 具体某页
				pageNum = +text;
			}
			getEmpList(pageNum, pageSize);
		})
		
		// 获取分页数据
		function getEmpList(pageNum, pageSize) {
			$.get(action_prefix + "?pageNum=" + pageNum + "&pageSize=" + pageSize, function(res) {
				var _list = "";
				res.data.list.map(item => {
					_list += "<tr><td>" + item.empId + "</td><td>"+ item.empName+"</td><td>"+item.empGender+"</td><td>"+item.empEmail+"</td><td>"+item.dept.deptId+"</td><td>"+item.dept.deptName+"</td><td><button class='btn btn-primary btn-sm' type='button' data-toggle=‘modal’ data-target=‘#exampleModal’ data-whatever=‘@mdo’ data-action='check'>查看</button><button type='button' class='btn btn-primary btn-sm' data-action='edit'>编辑</button><button type='button' class='btn btn-danger btn-sm' data-action='delete'>删除</button></td></tr>"
				})
				$("#list").html(_list);
				
				var total = res.data.total; //  总条数
				var pages = res.data.pages; // 总页数
				$("#current_page").text(pageNum);
				$("#total").text(total);
				$("#total_page").text(pages);
				
				var _page = "";
				var page_show_size = 7; // 显示页码的个数
				var start,end;
				
				// 不是第一页要显示上一页
				if (pageNum > 1) {
					_page = '<li class="page-item"><a class="page-link" href="#">上一页</a></li>';
				}
				
				// 确定循环的起始位置
				if(pageNum <= Math.ceil(page_show_size/2)) {
					start = 1;
					end = pages < page_show_size ? pages : page_show_size;
				} else {
					var half_page_show_size = Math.floor(page_show_size/2);
					start = pageNum - half_page_show_size;
					end = pages < (pageNum + half_page_show_size) ? pages : (pageNum + half_page_show_size);
					_page += "<li>...</li>";
				}
				
				// console.log(start, end);
				
				// 循环页码
				for (var i = start; i <= end; i++) {
					var isActive = (pageNum === i) ? "active" : "";
					_page += '<li class="page-item '+isActive+'"><a class="page-link" href="#">'+ i + '</a></li>';
				}
				
				// 尾部是否能显示完全
				if (pageNum <= Math.floor(page_show_size/2) + 1 || pages > pageNum + half_page_show_size) {
					_page += "<li>...</li>";
				}
				
				if (pageNum < pages) {
					_page += '<li class="page-item"><a class="page-link" href="#">下一页</a></li>';
				}
				
				$(".pagination").html(_page);
			})
		}
			
		// 获取单个员工
		function getEmp(id, action) {
			$.get(action_prefix+ "/" + id, function(res) {
				var data = res.data;
				$("#id").val(data.empId);
				$("#name").val(data.empName).attr("disabled","true");
				$("#gender").val(data.empGender).attr("disabled","true");
				$("#email").val(data.empEmail).attr("disabled","true");
				$("#dept_id").val(data.dept.deptId).attr("disabled","true");
				if(action==="edit") {
					$('.form-control').not("#id").removeAttr("disabled");
				}
				$('#modal').modal();
			})
		}
	</script>
</body>
</html>