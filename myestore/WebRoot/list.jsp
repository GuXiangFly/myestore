<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
	function add2Cart(id){
		//页面跳转
		location.href = "/Add2CartServlet?id="+id;
	}
	
</script>
</head>
<body>
	<c:if test="${empty products }">
		对不起，网站未出售 任何商品
	</c:if>
	<c:if test="${not empty products }">
		<h3>商品出售列表</h3>
		<c:forEach items="${products }" var="product">
			<div><img src="${product.imageurl}" align="left" height="100" width="100"></div>
			<h5>商品名称：${product.name }</h5>
			<h5>商品价格:${product.price}</h5>
			<h5>库存状况：<c:if test="${product.count>=1 }">
						<font color="blue">库存充足</font>
					</c:if>
					<c:if test="${product.count<1 }">
						<font color="red">暂时无货 <a href="#">等待到货通知</a></font>
					</c:if></h5>
					<img src="/img/add2cart.png" style="cursor: pointer;" onclick="add2Cart('${product.id}')">
				 <p> 商品的描述 : ${product.description }</p>
				 <br clear="left">	
				 <hr>
		</c:forEach>
	</c:if>
</body>
</html>