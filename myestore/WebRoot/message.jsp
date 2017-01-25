<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="refresh" content="5;url=http://www.myestore.com">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	var i = 5;
	function init(){
		document.getElementById("timer").innerHTML=i;
		i--;
		window.setTimeout("init",1000);
	}
	function TEST () {
	  document.
	}
</script>

<body onload="init();">
	${message }
	页面将在<span id="timer"></span>秒内跳转到网站的首页，<a href="/index.jsp">如未跳转，请手动点击</a>
</body>
</html>