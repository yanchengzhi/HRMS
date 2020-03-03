<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>员工添加</title>
</head>
<body>
    <div class="modal fade emp-add-modal" tabindex="-1" role="dialog" arialabelledby="emp-add-modal">
    	<div class="modal-dialog" role="document">
    		<div class="modal-content">
    			<!--头部-->
    			<div class="modal-header">
    				<button class="close" type="button" data-dismiss="modal" aria-lable="close">
    					<span aria-hidden="true">&times;</span>
    				</button>
    				<h4 class="modal-title">员工新增</h4>
    			</div>
    			<!--主体部分-->
    			<div class="modal-body">
    				<form class="form-horizontal add_emp_form">
    					<div class="form-group">
    						<label for="add_empId" class="col-sm-2 control-label">员工ID</label>
    						<div class="col-sm-8">
    							<input type="text" name="empId" class="form-control" id="add_empId" placeholder="输入员工编号">
    					    </div>
    					</div>
    					<div class="form-group">
    						<label for="add_empName" class="col-sm-2 control-label">员工姓名</label>
    						<div class="col-sm-8">
    							<input type="text" name="empName" class="form-control" id="add_empName" placeholder="输入员工姓名">
    							<span class="help-block" id="helpBlock_add_empName"></span>
    					    </div>    						
    					</div>
    					<div class="form-group">
    						<label class="col-sm-2 control-label">员工性别</label>
    						<div class="col-sm-8">
    							<label class="radio-inline">
    								<input type="radio" name="gender" id="addEmpGender" value="1" checked />男
    							</label>
    							<label class="radio-inline">
    								<input type="radio" name="gender" id="addEmpGender" value="0"/>女
    							</label>
    						</div>
    					</div>
    					<div class="form-group">
    						<label for="add_empEmail" class="col-sm-2 control-label">工作邮箱</label>
    						<div class="col-sm-8">
    							<input type="text" name="empEmail" class="form-control" id="add_empEmail" placeholder="@qq.com">
    							<span class="help-block" id="helpBlock_add_empEmail"></span>
    					    </div>    						
    					</div>
    					<div class="form-group">
    						<label for="add_empDep" class="col-sm-2 control-label">所在部门</label>
    						<div class="col-sm-8">
    							<div class="checkbox">
    								<select name="depId" class="form-control" id="add_empDep">
    								</select>
    							</div>
    						</div>
    					</div>
    				</form>
    			</div>
    			<!--脚部-->
    			<div class="modal-footer">
    				<button class="btn btn-default" type="button" data-dismiss="modal">关闭</button>
    				<button class="btn btn-primary emp_save_btn" type="button">保存</button>
    			</div>
    		</div>
    	</div>
    </div>
 <!--添加脚本-->
 <script>
//点击员工新增按钮，发送AJAX请求查询部门列表信息，弹出模态框，
// 1、将查询得到的部门列表信息显示在对应模态框中部门信息处以下拉框的形式供用户选择，不必输入
  $('.emp_add_btn').one("click",(function(){
      $.ajax({
      	url:"/SSM_HRMS/dept/getDepName",
      	type:"GET",    	
      	success:function(result){
      		if(result.code==100){
      			$.each(result.extendInfo.depList,function(){
                    var optionEle = $("<option></option>").append(this.depName).attr("value",this.depId);
                    optionEle.appendTo('#add_empDep');
      			});
      		}
      	}
      });
  }));
  
  $('.emp_add_btn').click(function(){
      $('.emp-add-modal').modal({
           backdrop:"static",
           keyboard:true
      });
    });

  //2、当鼠标从姓名输入框移开的时候，判断姓名输入框内的姓名是否重复
  $('#add_empName').change(function(){
       var empName = $('#add_empName').val();
       $.ajax({
       	   url:"/SSM_HRMS/emp/checkEmpExist?name="+empName,
       	   type:"GET",
       	   success:function(result){
       	   	  if(result.code==100){
       	   	  	$('#add_empName').parent().parent().removeClass('has-error');
       	   	  	$('#add_empName').parent().parent().addClass('has-success');
       	   	  	$('#helpBlock_add_empName').text("用户名可用！");
       	   	  	$(".emp_save_btn").attr("btn_name_exists", "success");
       	   	  }else{
       	   	  	$('#add_empName').parent().parent().removeClass('has-success');
       	   	  	$('#add_empName').parent().parent().addClass('has-error');
       	   	  	$('#helpBlock_add_empName').text(result.extendInfo.name_reg_error);
       	   	  	$(".emp_save_btn").attr("btn_name_exists", "error");
       	   	  }
       	   }
       });
  });

  //3、保存操作
  $('.emp_save_btn').click(function(){
  	  //先获取btn_name_exists的值
      //btn_name_exists = error，就是姓名重复
      //btn_name_exists = success，就是姓名可用
      if($('.emp_save_btn').attr("btn_name_exists")=="error"){//姓名重复时，点击保存，函数停止，不会往下执行
      	 return false;
      }

      //验证姓名和邮箱格式
      var empName = $('#add_empName').val();
      var empEmail = $('#add_empEmail').val();
      //姓名的指定格式
      var regName = /(^[a-zA-Z0-9_-]{3,16}$)|(^[\u2E80-\u9FFF]{2,5})/;
      //邮箱的指定格式
      var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
      if(!regName.test(empName)){//如果姓名不匹配
          alert("姓名格式不正确，请重新输入！");
          //输入框变红
          $('#add_empName').parent().parent().addClass("has-error");
          //输入框下面显示红色提示信息
          $('#helpBlock_add_empName').text("输入姓名为2-5位中文或6-16位英文和数字组合:");
          return false;//停止继续往下执行
      }else{
      	  $('#add_empName').parent().parent().removeClass('has-error');
       	  $('#add_empName').parent().parent().addClass('has-success');
       	  $('#helpBlock_add_empName').hide();
      }

      if(!regEmail.test(empEmail)){//如果邮箱不匹配
          //输入框变红
          $('#add_empEmail').parent().parent().addClass("has-error");
          //输入框下面显示红色提示信息
          $('#helpBlock_add_empEmail').text("输入的邮箱格式不正确！");
          return false;//停止继续往下执行
      }else{
      	  $('#add_empEmail').parent().parent().removeClass('has-error');
       	  $('#add_empEmail').parent().parent().addClass('has-success');
       	  $('#helpBlock_add_empEmail').hide();
      }

      //验证通过后执行保存操作
      var empId = Number($('#add_empId').val());
      var empGender = Number($('input:radio:checked').val())
      var empDep = Number($('#add_empDep option:selected').val());
      $.ajax({
      	url:"/SSM_HRMS/emp/addEmp?id="+empId+"&name="+empName+"&gender="+empGender+"&email="+empEmail+"&depId="+empDep,
      	type:"GET",
      	success:function(result){
      		if(result.code==100){
      			alert("员工添加成功！");
            //添加成功后，重新查询页数
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
      			alert("添加失败！");
      		}
      	}
      });
  });
 </script>
</body>
</html>