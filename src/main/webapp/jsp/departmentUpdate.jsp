<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>部门修改页面</title>
</head>
<body>
    <div class="modal fade dept-update-modal" tabindex="-1" role="dialog" aria-labelledby="dept-update-modal">
     	 <div class="modal-dialog" role="document">
     	 	 <div class="modal-content">
     	 	 	 <div class="modal-header">
     	 	 	 	 <button class="close" type="button" data-dismiss="modal" aria-label="close" >
     	 	 	 	 	 <span aria-hidden="true">&times;</span>
     	 	 	 	 </button>
     	 	 	 	 <h4 class="modal-title">部门修改</h4>
     	 	 	 </div>
     	 	 	 <div class="modal-body">
     	 	 	 	 <form class="form-horizontal update_dept_form">
     	 	 	 	 	<!--
     	 	 	 	      <div class="form-group">
     	 	 	 	          <label for="update_depId" class="col-sm-2 control-label">部门ID</label>
     	 	 	 	          <div class="col-sm-8">
     	 	 	 	              <input type="text" name="depId" class="form-control" id="update_depId" placeholder="输入要修改的部门ID">
     	 	 	 	          </div>
     	 	 	 	      </div>
     	 	 	 	  -->
     	 	 	 	 	  <div class="form-group">
     	 	 	 	 	  	  <label for="update_deptName" class="col-sm-2 control-label">部门名称</label>
     	 	 	 	 	  	  <div class="col-sm-8">
     	 	 	 	 	  	  	  <input type="text" name="depName" class="form-control" id="update_depName" placeholder="输入部门名称"/>
     	 	 	 	 	  	  </div>
     	 	 	 	 	  </div>
     	 	 	 	 	  <div class="form-group">
     	 	 	 	 	  	  <label for="update_deptLeader" class="col-sm-2 control-label">部门领导</label>
     	 	 	 	 	  	  <div class="col-sm-8">
     	 	 	 	 	  	  	  <input type="text" name="depLeader" class="form-control" id="update_depLeader" placeholder="输入部门领导"/>
     	 	 	 	 	  	  </div>
     	 	 	 	 	  </div>
     	 	 	 	 </form>
     	 	 	 </div>
     	 	 	 <div class="modal-footer">
     	 	 	 	  <button class="btn btn-default" type="button" data-dismiss="modal">关闭</button>
     	 	 	 	  <button class="btn btn-primary dept_update_btn">保存</button>
     	 	 	 </div>
     	 	 </div>
     	 </div>
     </div> 
<!--添加脚本-->
<script>
//1、点击编辑按钮，发送AJAX请求查询对应id的部门信息，进行回显；
  var edit_depId = 0;
  $(".dept_edit_btn").click(function(){
  	edit_depId = Number($(this).parent().parent().find("td:eq(0)").text());
  	//查询对应deptId的部门信息
  	$.ajax({
  		url:"/SSM_HRMS/dept/findDepById?id="+edit_depId,
  		type:"GET",
  		success:function(result){
  			if(result.code==100){
  				var depData = result.extendInfo.dep;
  				//数据回显
  				$('#update_depName').val(depData.depName);
  				$('#update_depLeader').val(depData.depLeader);
  			}else{
  				alert(result.extendInfo.get_dept_error);
  			}
  		}
  	});  	
  });

//2、进行修改，点击更新按钮发送AJAX请求，将更改后的信息保存到数据库中；
   $(".dept_update_btn").click(function(){
   	  var depName = $('#update_depName').val();
      var depLeader = $('#update_depLeader').val();
      $.ajax({
      	url:"/SSM_HRMS/dept/modifyDepartment?id="+edit_depId+"&name="+depName+"&lead="+depLeader,
      	type:"GET",
      	data:$(".update_dept_form").serialize(),
      	success:function(result){
      		if(result.code==100){
      			alert("更新成功！");
      			window.location.href = "/SSM_HRMS/dept/getDepList";
      		}else{
      			alert(result.extendInfo.update_dept_error);
      		}
      	}
      })
   });
</script>       
</body>
</html>











