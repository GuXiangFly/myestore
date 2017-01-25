<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>订单的基本信息</h3>
	<form action="GenerateOrderServlet" method="post">

		<table>
			<tr>
				<td>收货人信息</td>
				<td><textarea cols="65" rows="5" name="receiverinfo"> </textarea></td>
			</tr>
			<tr>
				<td>支付方式</td>
				<td><input type="radio" name="paymethod" value="onlinepay" />在线支付
					<input type="radio" name="paymethod" value="huodaofukuan" />货到付款 <input
					type="radio" name="paymethod" value="youjuhuikuan" />邮局汇款</td>
			</tr>
		</table>
		<h3>订单中商品的详情</h3>
		<table width="100%" border="1" align="center"
			style="text-align: center">
			<tr>
				<th>商品名称</th>
				<th>单价</th>
				<th>数量</th>
				<th>小计</th>
			</tr>
			<!-- map 
				Map<Product,Integer>
			 -->
			<c:set var="totalprice"></c:set>
			<c:forEach items="${cart }" var="entry">
				<tr>
					<td>${entry.key.name }</td>
					<td>${entry.key.price }</td>
					<td><input type="text" value="${entry.value }" size="1"
						style="text-align: center;"></td>
					<td>${entry.key.price*entry.value }</td>
					<c:set var="totalprice"
						value="${totalprice+entry.key.price*entry.value }"></c:set>
				</tr>
			</c:forEach>
		</table>
		<div align="right">合计：${totalprice }
			<input type = "hidden" name="money" value="${totalprice }">
		</div>
		<div align="right">
			<input type="submit" value="生成订单" />
		</div>
	</form>
</body>
</html>