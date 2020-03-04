<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--引入BootStrap核心CSS文件，地址先验证是否可用-->
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!--先引入jQuery文件-->
<script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
<!--再引入BootStramp的核心JS文件-->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
		<!--导航条-->
		<div class="hrms_brand_nav">
			<div class="navbar navbar-inverse navbar-static-top">
				<div class="container-fluid">
					<div class="navbar-header">
						<button class="navbar-toggle collapse" type="button" data-toggle="collapse" data-target="#hrms-navbar-collapse-1" aria-expanded="false">
							<span class="sr-only">鄢承志</span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
						</button>
						<a href="#" class="navbar-brand" id="company_logo">云鼎公司LOGO</a>
					</div>
					<div class="collapse navbar-collapse" id="hrms-navbar-collapse-1">
						<ul class="nav navbar-nav">
							<li class="active">
								<a href="#">
									公司介绍
									<span class="sr-only">(current)</span> 
								</a>
							</li>
							<li><a href="#">人力资源部</a></li>
							<li><a href="#">请假申请</a></li>
							<li><a href="#">报销申请</a></li>
							<li><a href="#">出勤记录</a></li>
						</ul>
						<form class="navbar-form navbar-left">
							<div class="form-group">
								<input type="text" class="form-control" placeholder="站内搜索"/>
							</div>
							<button class="btn btn-default" type="submit">搜索</button>
						</form>
						<ul class="nav navbar-nav navbar-right">
							<li class="dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
									账号管理
									<span class="caret"></span>
								</a>
								<ul class="dropdown-menu nav nav-pills nav-stacked">
									<li class="active">
										<a href="#">
											<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
											修改信息
										</a>
									</li>
									<li>
										<a href="#">
											<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
											头像更换
										</a>
									</li>
									<li>
										<a href="#" class="hrms_logout">
											<span class="glyphicon glyphicon-off" aria-hidden="true"></span>
											账号退出
										</a>
									</li>
								</ul>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
<!-- 添加脚本 -->
<script type="text/javascript">
   //主页面
   $("#company_logo").click(function(){
	   $(this).attr("href","/SSM_HRMS/hrms/main");
   });
   //账号退出
   $(".hrms_logout").click(function(){
	   window.location.href = "/SSM_HRMS/hrms/logout";
   });
</script>
</body>
</html>