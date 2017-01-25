<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>用户登陆的页面</h3>
	<font color="red"> ${message }</font>
	<form action="/LoginServlet" method="post">
		用户名:<input type="text" name="username"><br/>
		密码:<input type="password" name="password"><br/>
		自动登录  <input type="checkbox" name="autologin" value="on">
		<input type="submit" value="登录">
		
	</form>
</body>
</html>