<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>部门新增页面</title>
</head>
<body>
     <div class="modal fade dept-add-modal" tabindex="-1" role="dialog" aria-labelledby="dept-add-modal">
     	 <div class="modal-dialog" role="document">
     	 	 <div class="modal-content">
     	 	 	 <div class="modal-header">
     	 	 	 	 <button class="close" type="button" data-dismiss="modal" aria-label="close" >
     	 	 	 	 	 <span aria-hidden="true">&times;</span>
     	 	 	 	 </button>
     	 	 	 	 <h4 class="modal-title">部门新增</h4>
     	 	 	 </div>
     	 	 	 <div class="modal-body">
     	 	 	 	 <form class="form-horizontal add_dept_form">
     	 	 	 	      <div class="form-group">
     	 	 	 	          <label for="add_depId" class="col-sm-2 control-label">部门ID</label>
     	 	 	 	          <div class="col-sm-8">
     	 	 	 	              <input type="text" name="depId" class="form-control" id="add_depId" placeholder="输入部门ID">
     	 	 	 	          </div>
     	 	 	 	      </div>
     	 	 	 	 	  <div class="form-group">
     	 	 	 	 	  	  <label for="add_deptName" class="col-sm-2 control-label">部门名称</label>
     	 	 	 	 	  	  <div class="col-sm-8">
     	 	 	 	 	  	  	  <input type="text" name="depName" class="form-control" id="add_depName" placeholder="输入部门名称"/>
     	 	 	 	 	  	  </div>
     	 	 	 	 	  </div>
     	 	 	 	 	  <div class="form-group">
     	 	 	 	 	  	  <label for="add_deptLeader" class="col-sm-2 control-label">部门领导</label>
     	 	 	 	 	  	  <div class="col-sm-8">
     	 	 	 	 	  	  	  <input type="text" name="depLeader" class="form-control" id="add_depLeader" placeholder="输入部门领导"/>
     	 	 	 	 	  	  </div>
     	 	 	 	 	  </div>
     	 	 	 	 </form>
     	 	 	 </div>
     	 	 	 <div class="modal-footer">
     	 	 	 	  <button class="btn btn-default" type="button" data-dismiss="modal">关闭</button>
     	 	 	 	  <button class="btn btn-primary dept_save_btn">保存</button>
     	 	 	 </div>
     	 	 </div>
     	 </div>
     </div>
<!-- 添加脚本 -->
<script type="text/javascript">
//添加部门绑定到指定按钮
// 为简单操作，省去了输入名称的验证、错误信息提示等操作
// 该表记录较少，保存成功后无需跳转，留在首页
    //1、点击部门新增按钮，弹出模态框；
    $(".dept_add_btn").click(function () {
        $('.dept-add-modal').modal({
            backdrop:static,
            keyboard:true
        });
    });
   
  //2、输入新增部门信息，点击保存按钮，发送AJAX请求到后台进行保存；
   $(".dept_save_btn").click(function(){
	   var depId = Number($('#add_depId').val());
	   var depName = $('#add_depName').val();
	   var depLeader = $('#add_depLeader').val();
	   //为简化操作，验证省去
	   $.ajax({
		   url:"/SSM_HRMS/dept/addDepartment?id="+depId+"&name="+depName+"&leader="+depLeader,
		   type:"PUT",
	       data:$(".add_dept_form").serialize(),
	       success:function(result){
	    	   if(result.code==100){
	    		   alert("添加成功！");
	    		   $('.dept-add-modal').modal("hide");
	    	   }else{
	    		   alert(result.extendInfo.add_dept_error);
	    	   }
	       }
	   });
   });
</script>
</body>
</html>











