<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>员工修改</title>
</head>
<body>
    <div class="modal fade emp-update-modal" tabindex="-1" role="dialog" arialabelledby="emp-update-modal">
    	<div class="modal-dialog" role="document">
    		<div class="modal-content">
    			<!--头部-->
    			<div class="modal-header">
     				<button class="close" type="button" data-dismiss="modal" aria-lable="close">
    					<span aria-hidden="true">&times;</span>
    				</button>
    				<h4 class="modal-title">员工修改</h4>   				
    			</div>
    			<!--主体部分-->
    			<div class="modal-body">
    				<form class="form-horizontal update_emp_form">
    					<div class="form-group">
    						<label for="update_empName" class="col-sm-2 control-label">员工姓名</label>
    						<div class="col-sm-8">
    							<input type="text" name="empName" class="form-control" id="update_empName" placeholder="输入员工姓名">
    						</div>				
    					</div>
    					<div class="form-group">
                            <label class="col-sm-2 control-label">性别</label>
                            <div class="col-sm-8">
                               <label class="radio-inline">
                                   <input type="radio" name="gender" id="update_empGender1" value="1"> 男
                               </label>
                               <label class="radio-inline">
                                   <input type="radio" name="gender" id="update_empGender2" value="0"> 女
                               </label>
                            </div>    						
    					</div>
   					    <div class="form-group">
  						    <label for="update_empEmail" class="col-sm-2 control-label">工作邮箱</label>
    						<div class="col-sm-8">
    							<input type="email" name="empEmail" class="form-control" id="update_empEmail" placeholder="@qq.com">
    							<span class="help-block" id="helpBlock_update_empEmail"></span>
    					    </div>     						
    					</div>    					
    					<div class="form-group">
    						<label for="update_empDep" class="col-sm-2 control-label">所在部门</label>
    						<div class="col-sm-8">
    							<div class="checkbox">
    								<select name="depId" class="form-control" id="update_empDep">
    								</select>
    							</div>
    						</div>    						
    					</div>
    				</form>
    			</div>
    			<!--脚部-->
    			<div class="modal-footer">
    				<button class="btn btn-default" type="button" data-dismiss="modal">关闭</button>
    				<button class="btn btn-primary emp_update_btn" type="button">保存</button>    				
    			</div>
    		</div>
    	</div>
    </div>
<!--添加脚本，修改员工信息-->
<script>
	$('.emp_edit_btn').one("click",(function(){
		//获取点击修改员工的id
		var empId = $(this).parent().parent().find("td:eq(0)").text();
		//根据id查询出对应员工信息进行数据回显
		$.ajax({
			url:"/SSM_HRMS/emp/findEmpById?id="+empId,
			type:"GET",
			success:function(result){
				if(result.code==100){
					var emp = result.extendInfo.emp;
					$('#update_empName').val(emp.empName);
					var sex = emp.gender.toString();
					$(":radio[name='gender'][value='"+sex+"']").prop("checked","checked");
					$('#update_empEmail').val(emp.email);
				}
			}
		});
		//部门回显列表
		$.ajax({
      	url:"/SSM_HRMS/dept/getDepName",
      	type:"GET",    	
      	success:function(result){
      		if(result.code==100){
      			$.each(result.extendInfo.depList,function(){
                    var optionEle = $("<option></option>").append(this.depName).attr("value",this.depId);
                    optionEle.appendTo('#update_empDep');
      			});
      		}
      	}
      });
	$('.emp_update_btn').attr("empId",empId);
	}));

	//进行保存操作
	$('.emp_update_btn').click(function(){
		var empId = Number($(this).attr("empId"));
		var empName = $('#update_empName').val();
		var empGender = Number($('input:radio:checked').val());
		//对修改的邮箱进行格式验证
		var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
		var empEmail = $('#update_empEmail').val();
		var empDep = Number($('#update_empDep option:selected').val());
		console.log(empDep);
		if(!regEmail.test(empEmail)){//如果邮箱不匹配
          //输入框变红
          $('#update_empEmail').parent().parent().addClass("has-error");
          //输入框下面显示红色提示信息
          $('#helpBlock_update_empEmail').text("输入的邮箱格式不正确！");
          return false;//停止继续往下执行
      }else{
      	  $('#update_empEmail').parent().parent().removeClass('has-error');
       	  $('#update_empEmail').parent().parent().addClass('has-success');
       	  $('#helpBlock_update_empEmail').hide();
      }

      //验证通过后，进行保存
      $.ajax({
      	url:"/SSM_HRMS/emp/modifyEmp?id="+empId+"&name="+empName+"&gender="+empGender+"&email="+empEmail+"&depId="+empDep,
      	type:"GET",
      	success:function(result){
      		if(result.code==100){
      			alert("修改成功！");
      			$(".emp-update-modal").modal("hide");
      			//定位到当前页面
      			window.location.href="/SSM_HRMS/emp/findEmpPaged2?page="+${page};
      		}else{
      			alert(result.extendInfo.emp_update_error);
      		}
      	}
      });
	});
</script>
</body>
</html>