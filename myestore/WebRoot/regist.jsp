<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript">
	function changeImage(){
		document.getElementById("image").src="/CheckImageServlet?"+new Date();
	}
</script>

<link href="jquery-ui-1.8.18.custom.css" rel="stylesheet"
	type="text/css" />

</head>

<body style="padding:50px;background-color:#F0F0F0;">
	<div class="container" style="padding: 30px; background-color: #C9DEF0">
		
		<h2 class="page-header">添加表单</h2>
		<div class="col-md-10 col-md-offset-2">
			<form action="/RegistServlet"
				method="post" class="form-horizontal">
				
				<div class="form-group">
					<label class="col-md-2 control-label" for="username">用户名：</label>
					<div class="col-md-4">
						<input type="text" name="username" class="form-control"
							placeholder="请输入用户名" id="username">
					</div>
				</div>


				<div class="form-group">
					<label for="password" class="col-md-2 control-label"
						placeholder="请输入密码">密码：</label>
					<div class="col-md-4">
						<input type="password" class="form-control" name="password">
					</div>
				</div>

				<div class="form-group">
					<label for="repassword" class="col-md-2 control-label">确认密码：</label>
					<div class="col-md-4">
						<input type="password" class="form-control" name="repassword">
					</div>
				</div>

				<div class="form-group">
					<label for="email" class="col-md-2 control-label">邮箱：</label>
					<div class="col-md-4">
						<input type="text" class="form-control" name="email">
					</div>
				</div>
				<div class="form-group">
					<label for="nickname" class="col-md-2 control-label">昵称：</label>
					<div class="col-md-4">
						<input type="text" class="form-control" name="nickname">
					</div>
				</div>
				
				
				<div class="form-group">
					<label for="checkcode" class="col-md-2 control-label">请输入验证码：</label>
					<div class="col-md-4">
						<input type="text" class="form-control col-md-6" name="checkcode">
						<img name="checkcode"  class="col-md-6" src="/CheckImageServlet" onclick="changeImage();" id="image" style="cursor: pointer;">
					</div>
				</div>
			
				<div class="col-md-4 col-md-offset-2">
					<input class="btn btn-danger btn-lg" type="submit" value="添加">
				</div>
			</form>
		</div>
	</div>
</body>
</html>