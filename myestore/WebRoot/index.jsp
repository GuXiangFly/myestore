<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'index.jsp' starting page</title>
  </head>
  
  <body>
    	<c:if test="${empty loginUser }">
    		 <a href="/login.jsp">去登录</a>
    		 <a href="/regist.jsp">去注册</a>
    	</c:if>
    	<c:if test="${not empty loginUser }">
    		 欢迎您, ${loginUser.nickname }  &nbsp;&nbsp;
    		 <a href="/LogoutServlet">注销</a>
    		 <a href="/addproduct.jsp">添加商品</a>
    		 <a href="/ListOrdersServlet">查询订单性信息</a>
    	</c:if>
    	<a href="/ListProductsServlet">查看商品</a>
  </body>
</html>
