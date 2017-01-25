<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${empty orders }">
		对不起，当前没有任何订单信息
	</c:if>
	<c:if test="${not empty orders }">
		当前登录用户是： ${loginUser.nickname}
		<hr/>
		<h3>订单的信息列表</h3>
	
		<c:forEach items="${orders }" var="order">
			　【订单号：${order.id }】<br/>
			　【下单人：${order.nickname }】<br/>
			　【订单总金额：${order.money }】<br/>
			   支付状态:　 <c:if test="${order.paystate==0 }">
			  				<font color="red">未支付, <a href="/pay.jsp?id=${order.id }&money=${order.money }">在线支付</a></font>
			 		 </c:if>
				  	 <c:if test="${order.paystate==1 }">
				  			<font color="green">已支付  </font> <a href="#">点击查询 物流信息</a>
			 		 </c:if><br/>
			 　【收货人信息 ：${order.receiverinfo }】<br/>
					<h3>商品详情</h3>
			<table border="1" width="100%" style="text-align: center">
				<tr>
					<td>商品的名称</td>
					<td>商品价格</td>
					<td>商品数量</td>
				</tr>
				<c:forEach items="${order.orderItems }" var="orderitem">
					<tr>
						<td>${orderitem.name }</td>
						<td>${orderitem.price }</td>
						<td>${orderitem.buynum }</td>
					</tr>
				</c:forEach>
			</table>
			<hr/>
			<br/>
			<br/>
			<br/>
		</c:forEach>
	
	</c:if>

</body>
</html>