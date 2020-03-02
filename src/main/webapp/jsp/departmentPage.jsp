<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- 引入taglib指令集标签 -->
    <%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>部门管理页面</title>
</head>
<body>
    <div class="hrms_dept_container">
       <!-- 将公共部分导航栏包含进来 -->
       <%@ include file = "./commons/head.jsp" %>
       <!-- 中间部分，左侧栏+表格内容 -->
       <!-- 将左侧边栏包含进来 -->
       <%@ include file="./commons/leftsidebar.jsp" %>
       <!-- 部门表格内容 -->
       <div class="dept_info col-sm-10">
          <div class="panel panel-success">
            <!--路径导航-->
            <div class="panel-heading">
               <ol class="breadcrumb">
                   <li><a href="#">部门管理</a></li>
                   <li class="active">部门信息</li>
               </ol>
            </div>
            <!--表格部分-->
            <table class="table table-bordered table-hover" id="dept_table">
                <thead>
                   <th style="text-align:center;">部门编号</th>
                   <th style="text-align:center;">部门名称</th>
                   <th style="text-align:center;">部门领导</th>
                   <th style="text-align:center;">操作</th>
                </thead>
                <tbody align="center">
                   <c:forEach items="${depList}" var="dep">
                      <tr>
                         <td>${dep.depId}</td>
                         <td>${dep.depName}</td>
                         <td>${dep.depLeader}</td>
                         <td>
                            <a href="#" class="btn btn-primary dept_edit_btn" role="button" data-toggle="modal"
                            data-target=".dept-update-modal">编辑</a>
                            <a href="#" class="btn btn-danger dept_delete_btn" role="button" >删除</a>
                         </td>
                      </tr>
                   </c:forEach>
                </tbody>
            </table>
            <div class="panel-body">
               <div class="table-items">
                   当前第<span class="badge">1</span>页，共<span class="badge">1</span>页，
                   共<span class="badge">${totalItems}</span>条记录
               </div>
               <!--制作翻页键-->
               <nav class="pull-right" aria-label="Page navigation">
                   <ul class="pagination">
                       <li><a href="/SSM_HRMS/dept/getDepList">首页</a></li>
                       <!--上一页按键-->
                       <li class="disabled">
                           <a href="#" class="prePage" aria-label="Previous">
                               <span aria-hidden="true">&laquo;</span>
                           </a>
                       </li>
                       <li>
                           <a href="#" class="prePage" aria-label="Previous">
                               <span aria-hidden="true">&laquo;</span>
                           </a>
                       </li> 
                       <!--下一页按键-->
                       <li>
                           <a href="/SSM_HRMS/dept/getDepList" class="nextPage" aira-label="next">
                               <span aria-hidden="true">&raquo;</span>
                           </a>
                       </li>
                       <li class="disabled">
                           <a href="/SSM_HRMS/dept/getDepList" class="nextPage" aira-label="next">
                               <span aria-hidden="true">&raquo;</span>
                           </a>
                       </li>   
                       <li><a href="/SSM_HRMS/dept/getDepList">尾页</a></li>                    
                   </ul>
               </nav>
            </div>
          </div>
       </div>
       <!-- 将部门新增页面添加进来 -->
       <%@ include file = "departmentAdd.jsp" %>
       <!-- 将部门修改页面添加进来 -->
       <%@ include file = "departmentUpdate.jsp" %>
       <!--将脚部包含进来-->
       <%@ include file="./commons/foot.jsp" %>
    </div>
<!--添加脚本，实现删除功能-->
<script>
    $('.dept_delete_btn').click(function(){
         var depId = $(this).parent().parent().find("td:eq(0)").text();
         var depName = $(this).parent().parent().find("td:eq(1)").text();
         if(confirm("确认要删除【"+depName+"】的全部信息吗？")){
          $.ajax({
              url:"/SSM_HRMS/dept/removeDepartment?id="+depId,
              type:"GET",
              success:function(result){
                if(result.code==100){
                  alert("删除成功！");
                  window.location.href = "/SSM_HRMS/dept/getDepList";
                }else{
                  alert(result.extendInfo.del_dept_error);
                }
              }
          });
        }
    });
</script>
</body>
</html>