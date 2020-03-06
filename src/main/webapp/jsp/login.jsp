<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录界面</title>
   <!--引入bootstrap的css文件-->
   <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap.min.css">
   <!--字体样式引用-->
   <link rel="stylesheet" href="http://cdn.bootcss.com/font-awesome/4.4.0/css/font-awesome.min.css">
   <!--图标样式引用-->
   <link rel="stylesheet" href="http://cdn.bootcss.com/ionicons/2.0.1/css/ionicons.min.css">
   <!--引入bootstrap脚本文件之前先引入jquery文件-->
   <script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
   <!--引入bootstrap脚本文件-->
   <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container">
    	<div class="row">
    		<div class="col-md-4 col-md-offset-4" style="top:150px;">
    			<div class="panel login-panel panel-default">
    				<div class="panel-heading">
    					<h3 class="panel-title" style="text-align: center;">登录</h3>
    				</div>
    				<div class="panel-body">
    					<form role="form" id="login_form" method="POST">
    						<fieldset>
    							<div class="form-group">
    								<input type="text" class="form-control" name="username" id="username" placeholder="用户名：admin" autofocus="">
    							</div>
    							<div class="form-group">
    								<input type="password" class="form-control" name="password" id="password" placeholder="密码：admin">
    							</div>
    							<button class="btn btn-lg btn-success btn-block" id="login_btn" type="submit">登录</button>
    						</fieldset>
    					</form>
    				</div>
    			</div>
    		</div>
    	</div>
    </div>
<!--添加脚本实现点击登录按钮实现跳转-->
<script>
	$(function(){
		$('#login_btn').click(function(){
			var username = $('#username').val();
			var password = $('#password').val();
			console.log(username);
			$.ajax({
				url:"/SSM_HRMS/hrms/doLogin?username="+username+"&password="+password,
				type:"POST",
				success:function(result){
					if(result.code==100){
						window.location.href="/SSM_HRMS/jsp/main.jsp";//验证通过定位到主页
					}else{
						window.location.href="/SSM_HRMS/jsp/login.jsp";//没通过定位到当前登录页面
					}
				}
			});
		});
	});
</script>
</body>
</html>