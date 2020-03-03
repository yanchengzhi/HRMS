<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>云鼎公司人力资源系统后台</title>
</head>
<body>
     <div class="hrms_container">
         <!-- 将头部导航条包含进来 -->
         <%@ include file = "commons/head.jsp" %>
         <!-- 中间部分 -->
         <div class="hrms_body" style="position: relative;top: -15px;"> 
         <!-- 将左侧边栏包含进来 -->
         <%@ include file = "commons/leftsidebar.jsp" %> 
			<!--中心展示内容-->
			<div class="hrms_main_ad col-sm-10">
				<div class="panel panel-success">
					<div class="panel-heading">
						<h3 style="text-align: center;">云鼎科技有限公司</h3>
					</div>
					 <div class="panel-body" style="position: relative;top: -15px;">
						<div class="carousel slide" id="hrms_carousel_1" data-ride="carousel">
							<ol class="carousel-indicators">
								<li data-target="#hrms_carousel_1" data-slide-to="0" class="active"></li>
								<li data-target="#hrms_carousel_1" data-slide-to="1"></li>
								<li data-target="#hrms_carousel_1" data-slide-to="2"></li>
							</ol>
							<div class="carousel-inner" role="listbox">
								<div class="item active" style="text-align: center;">
									<img src="./static/img/company1.jpg" alt="图片1" class="img-responsive center-block">
									<div class="carousel-caption">
										<h3>漂亮大气的办公楼</h3>
									</div>
								</div>
								<div class="item">
									<img src="./static/img/company2.jpg" alt="图片2" class="img-responsive center-block">
									<div class="carousel-caption">
										<h3>休闲的放松场所</h3>
									</div>
								</div>
								<div class="item">
									<img src="./static/img/company3.jpg" alt="图片3" class="img-responsive center-block">
									<div class="carousel-caption">
										<h3>舒适的办公环境</h3>
									</div>
								</div>
							</div>
							<a href="#hrms_carousel_1" class="left carousel-control" role="button" 
							data-slide="prev">
								<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
								<span class="sr-only">向前</span>
							</a>
							<a href="#hrms_carousel_1" class="right carousel-control" role="button"
							data-slide="next">
								<span class="glyphicon glyphicon-chevron-right"></span>
								<span class="sr-only">向后</span>
							</a>
						</div>
					</div>
				</div>
			</div>
		</div>  
		<!-- 将脚部包含进来 -->
		<%@ include file = "./commons/foot.jsp" %>      
     </div>
</body>
</html>