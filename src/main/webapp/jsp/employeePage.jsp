<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- 引入taglib指令集标签 -->
    <%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>员工管理页面</title>
</head>
<body>
    <div class="hrms_container">
    	<!--将导航条包含进来-->
    	<%@ include file = "commons/head.jsp" %>
    	<!-- 中间部分（包括左边栏和员工/部门表单显示部分） -->
    	<div class="hrms_body" style="position:relative; top:-15px;">
    		<!--将左侧边栏包含进来-->
    		<%@ include file = "commons/leftsidebar.jsp" %>
    		<!--中间员工表格内容信息展示-->
    		<div class="col-sm-10 emp_info">
    			<div class="panel panel-success">
    				<!--路径导航-->
    				<div class="panel-heading">
    					<ol class="breadcrumb">
    						<li><a href="#">员工管理</a></li>
    						<li class="active">员工信息</li>
    					</ol>
    				</div>
    				<!--以下是表格内容-->
    				<table class="table table-bordered table-hover" id="emp_table">
    					<thead>
    						<th style="text-align:center;">员工编号</th>
    						<th style="text-align:center;">员工姓名</th>
    						<th style="text-align:center;">员工性别</th>
    						<th style="text-align:center;">员工邮箱</th>
    						<th style="text-align:center;">员工部门</th>
    						<th style="text-align:center;">其他操作</th>
    					</thead>
    					<tbody style="text-align: center;">
    					   <c:forEach items="${empList}" var="emp">
    					       <tr>
    					       	   <td>${emp.empId}</td>
    					       	   <td>${emp.empName}</td>
    					       	   <td>
    					       	   	  ${emp.gender == 0 ? "女" : "男"}
    					       	   </td>
    					       	   <td>${emp.email}</td>
    					       	   <td>
    					       	     <c:choose>
    					       	        <c:when test="${emp.depId eq 1001}">研发部</c:when>
    					       	        <c:when test="${emp.depId eq 1002}">UI部</c:when>
    					       	        <c:when test="${emp.depId eq 1003}">市场部</c:when>
    					       	        <c:when test="${emp.depId eq 1004}">销售部</c:when>
    					       	        <c:when test="${emp.depId eq 1005}">决策部</c:when>
    					       	        <c:when test="${emp.depId eq 1006}">外交部</c:when>
    					       	        <c:when test="${emp.depId eq 1007}">安保部</c:when>
    					       	        <c:when test="${emp.depId eq 1008}">分析部</c:when>
    					       	     </c:choose>
    					       	   </td>
    					       	   <td>
    					       	   	   <a href="#" class="btn btn-primary emp_edit_btn" data-toggle="modal" data-target=".emp-update-modal" role="button">编辑</a>
    					       	   	   <a href="#" class="btn btn-danger emp_delete_btn" role="button">删除</a>
    					       	   </td>
    					       </tr>
    					   </c:forEach>
    					</tbody>
    				</table>
    				<div class="panel-body">
    					<div class="table_items">
    						当前第<span class="badge">${page}</span>页，
    						共有<span class="badge">${pageSum}</span>页，
    						一共<span class="badge">${totalItems}</span>条记录
    					</div>
    					<nav class="pull-right" aria-label="Page navigation">
    						<ul class="pagination">
    							<li><a href="/SSM_HRMS/emp/findEmpPaged2?page=1">首页</a></li>
    							<c:if test="${page==1}">
    							    <li class="disabled">
    							    	<a href="#" class="prePage" aria-label="Previous" >
    							    		<span aria-hidden="true">&laquo;</span>
    							    	</a>
    							    </li>
    							</c:if>
    							<c:if test="${page!=1}">
   							        <li>
    							    	<a href="#" class="prePage" aria-label="Previous" >
    							    		<span aria-hidden="true">&laquo;</span>
    							    	</a>
    							    </li>    							
    							</c:if>
    							<c:forEach begin="1" end="${pageSum<5?pageSum:5}" step="1" var="itemPage">
    							     <c:if test="${page==itemPage}">
    							         <li class="active">
    							         	<a href="/SSM_HRMS/emp/findEmpPaged2?page=${itemPage}">
    							         		${itemPage}
    							         	</a>
    							         </li>
    							     </c:if>
    							      <c:if test="${page!=itemPage}">
    							         <li>
    							         	<a href="/SSM_HRMS/emp/findEmpPaged2?page=${itemPage}">
    							         		${itemPage}
    							         	</a>
    							         </li>    							      
    							     </c:if>
    							</c:forEach>
    							<c:if test="${page==pageSum}">
    								<li class="disabled">
    									<a href="#" class="nextPage" aria-label="Next">
    										<span aria-hidden="true">&raquo;</span>
    									</a>
    								</li>
    							</c:if>
    							<c:if test="${page!=pageSum}">
    								<li>
    									<a href="#" class="nextPage" aria-label="Next">
    										<span aria-hidden="true">&raquo;</span>
    									</a>
    								</li>    								
    							</c:if>
    							<li><a href="/SSM_HRMS/emp/findEmpPaged2?page=${pageSum}">尾页</a></li>
    						</ul>
    						
    					</nav>
    				</div>
    			</div>
    		</div>
    		<!-- 将脚部包含进来 -->
    		<%@ include file = "commons/foot.jsp" %>
    	</div>
    </div>
    <!--将员工添加页面包含进来-->
    <%@ include file = "employeeAdd.jsp" %>
    <!-- 将员工修改页面包含进来 -->
    <%@ include file = "employeeUpdate.jsp" %>
<!--添加脚本实现翻页功能-->
<script>
	$(function(){
		var cruPage = ${page};
		var pageSum = ${pageSum};
		//上一页
		$('.prePage').click(function(){
			if(cruPage>1){//当前页码大于1，允许往前翻页
				var page = cruPage - 1;
				$(this).attr("href","/SSM_HRMS/emp/findEmpPaged2?page="+page);
			}
		});
		//下一页
		$('.nextPage').click(function(){//当前页码不在最后一页时，允许往后翻页
			if(cruPage<pageSum){
				var page = cruPage + 1;
				$(this).attr("href","/SSM_HRMS/emp/findEmpPaged2?page="+page);
			}
		});
	})

	//添加删除功能
	$('.emp_delete_btn').click(function(){
		var curPage = ${page};//获取当前页页码
		var items = ${totalItems};
		var empId = $(this).parent().parent().find("td:eq(0)").text();
		var empName = $(this).parent().parent().find("td:eq(1)").text();
		if(confirm("确认删除【"+empName+"】的所有信息吗？")){
			$.ajax({
				url:"/SSM_HRMS/emp/removeEmp?id="+empId,
				type:"GET",
				success:function(result){
					if(result.code==100){
						alert("删除成功！");
                        //由于数据数量变化，会影响到页数，所以页数需要重新查询
                        $.ajax({
                            url:"/SSM_HRMS/emp/getPageSum",
                            type:"GET",
                            success:function(result){
                                if(result.code==100){
                                   var pageSum = result.extendInfo.pageSum;
                                   window.location.href="/SSM_HRMS/emp/findEmpPaged2?page="+pageSum;//定位到最后一页 
                                }
                            }
                        });		
					}else{
						alert(result.extendInfo.emp_del_error);
					}
				}
			});
		}
	});
	
</script>
</body>
</html>