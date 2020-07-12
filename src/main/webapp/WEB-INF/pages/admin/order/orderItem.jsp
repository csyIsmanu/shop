<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style type="text/css">
	img:hover{ transform:scale(5,5)}
</style>
<table width="100%">
	<c:forEach var="orderItem" items="${list}">
	<tr>
		<td><img width="40" height="45"  src="${ pageContext.request.contextPath }/${orderItem.product.image}"></td>
		<td>名称：${orderItem.product.pname}</td>
		<td>数量：${orderItem.count}</td>
		<td>价格:${orderItem.subtotal}</td>
	</tr>
	</c:forEach>
</table>