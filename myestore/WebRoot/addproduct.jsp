<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta charset="utf-8">
		<title>Insert title here</title>
		<meta name="viewport" content="width=device-width,initial-scale=1">

		<link href="css/bootstrap.min.css" rel="stylesheet">
		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<link href="css/jquery-ui-1.8.18.custom.css" rel="stylesheet"
		type="text/css" />

	</head>

	<body style="padding:50px;background-color:#F0F0F0;">
		<div class="container" style="padding: 30px; background-color: #C9DEF0">

			<h2 class="page-header">这是添加商品的界面</h2>
			<div class="col-md-10 col-md-offset-2">
				<form action="/AddProductServlet" method="post"
				enctype="multipart/form-data" class="form-horizontal">

					<div class="form-group">
						<label class="col-md-2 control-label" for="name">商品名称：</label>
						<div class="col-md-4">
							<input type="text" name="name" class="form-control"
							placeholder="请输入商品名称" id="name">
						</div>
					</div>

					<div class="form-group">
						<label for="price" class="col-md-2 control-label">商品价格：</label>
						<div class="col-md-4">
							<input type="text" placeholder="请输入商品价格" class="form-control"
							name="price">
						</div>
					</div>

					<div class="form-group">
						<label for="count" class="col-md-2 control-label">商品数量：</label>
						<div class="col-md-4">
							<input type="text" placeholder="请输入商品数量" class="form-control" name="count">
						</div>
					</div>

					<div class="form-group">
						<label for="category" class="col-md-2 control-label">商品种类：</label>
						<div class="col-md-4">
							<select class="form-control" name="category">
								<option value="家用电器">家用电器</option>
								<option value="手机、数码、通信">手机、数码、通信</option>
								<option value="电脑、办公">电脑、办公</option>
								<option value="男装、女装、内衣、珠宝">男装、女装、内衣、珠宝</option>
								<option value="营养保健">营养保健</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="nickname" class="col-md-2 control-label">商品图片：</label>
						<div class="col-md-4">
							<input type="file" class="form-control" name="image">
						</div>
					</div>

					<div class="form-group">
						<label for="description" class="col-md-2 control-label">请输入商品描述：</label>
						<div class="col-md-6">
							<textarea class="form-control" name="description" rows="5"></textarea>
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