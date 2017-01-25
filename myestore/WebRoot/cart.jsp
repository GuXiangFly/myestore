<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function updateCart(id,count){
		
		if(isNaN(count)){
			
			alert("对不起,您输入的不是数值,不要瞎搞...");
			return;
		}
		
		//去访问 一个 servlet程序, 去更新 购物车中的商品的 名称和数量的关系
		location.href="/UpdateCartServlet?id="+id+"&count="+count;
		
	}
</script>
</head>
<body>
	<c:if test="${empty cart }">
		购物车中没有 任何 商品
		<a href="/ListProductsServlet">去购买</a>
	</c:if>
	<c:if test="${not empty cart }">
		<h3>购物车中的商品列表</h3>
		<table width="100%" border="1" align="center" style="text-align: center">
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
					<td>
						<img src="/img/minus.png" style="cursor: pointer" onclick="updateCart('${entry.key.id}','${entry.value-1 }');">
						<input type="text" value="${entry.value }" size="1" style="text-align: center;" onblur="updateCart('${entry.key.id}',this.value);"> 
						<img src="/img/plus.png" style="cursor: pointer" onclick="updateCart('${entry.key.id}','${entry.value+1 }');">
					</td>
					<td>
						${entry.key.price*entry.value }
					</td>
					<c:set var="totalprice" value="${totalprice+entry.key.price*entry.value }"></c:set>
				</tr>
			</c:forEach>
		</table>
		<div align="right">
			合计: ${totalprice }
			<img src="/img/gotoorder.png" style="cursor: pointer" onclick="javascript:location.href='/generateorder.jsp'">
		</div>
	</c:if>
</body>
</html>