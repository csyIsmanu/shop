<%--
  Created by IntelliJ IDEA.
  User: csy
  Date: 2020/5/16
  Time: 23:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0043)http://localhost:8080/mango/cart/list.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

    <title>我的订单页面</title>
    <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/cart.css" rel="stylesheet" type="text/css"/>

</head>
<body>

<div class="container header">
    <div class="span5">
        <div class="logo">
            <a href="${pageContext.request.contextPath}/网上商城/index.htm">
                <img src="${pageContext.request.contextPath}/image/r___________renleipic_01/logo.gif" alt="传智播客"/>
            </a>
        </div>
    </div>
    <div class="span9">

        <img src="${pageContext.request.contextPath}/image/header.jpg" width="320" height="50" alt="正品保障" title="正品保障"/>

    </div>
    <%@ include file="menu.jsp" %>


</div>

<div class="container cart">

    <div class="span24">

        <div class="step step1">
            <ul>

                <li class="current"></li>
                <li>我的订单的显示</li>
            </ul>
        </div>


        <table>
            <tbody>
            <c:forEach var="order" items="${pageBean.list}">
                <tr>
                    <th colspan="5">订单编号：${order.oid}
                        订单状态：
                        <c:if test="${order.state == 1}">
                            <a href="${pageContext.request.contextPath}/order/findByOid.do?oid=${order.oid}"><font style="color: red">未付款</font></a>
                        </c:if>
                        <c:if test="${order.state == 2}">
                            已付款
                        </c:if>
                        <c:if test="${order.state == 3}">
                            <a href="${pageContext.request.contextPath}/order/updateState.do?oid=${order.oid}"><font style="color: green">确认收货</font></a>
                        </c:if>
                        <c:if test="${order.state == 4}">
                            <font color="green">交易完成</font>
                        </c:if>
                    </th>
                </tr>
                    <tr>
                        <th>图片</th>
                        <th>商品</th>
                        <th>价格</th>
                        <th>数量</th>
                        <th>小计</th>
                    </tr>
                <c:forEach items="${order.orderItems}" var="orderItem">

                <tr>
                        <td width="60">
                            <img src="${pageContext.request.contextPath}/${orderItem.product.image}"/>
                        </td>
                        <td>
                            <a target="_blank">${orderItem.product.pname}</a>
                        </td>
                        <td>
                                ${orderItem.product.shop_price}
                        </td>
                        <td class="quantity" width="60">
                                ${orderItem.count}
                        </td>
                        <td width="140">
                            <span class="subtotal">￥${orderItem.subtotal}</span>
                        </td>
                    </tr>
                </c:forEach>
            </c:forEach>
            <tr>

                <td colspan="5">
                    <div class="pagination">
                        <span>第${pageBean.page}/${pageBean.totalPage}页</span>
                        <c:if test="${oid != null}">
                            <c:if test="${pageBean.page != 1}">
                                <a class="firstPage"
                                   href="${pageContext.request.contextPath}/order/findByUid.do?uid=${sessionScope.user.uid}&page=1"
                                   onclick="checkForm">&nbsp;</a>
                                <a class="previousPage"
                                   href="${pageContext.request.contextPath}/order/findByUid.do?uid=${sessionScope.user.uid}&&page=${pageBean.page-1}">&nbsp;</a>
                            </c:if>
                            <c:forEach var="i" begin="1" end="${pageBean.totalPage}">
                                <c:if test="${pageBean.page != i}">
                                    <a href="${pageContext.request.contextPath}/order/findByUid.do?uid=${sessionScope.user.uid}&&page=${i}">${i}</a>
                                </c:if>
                                <c:if test="${pageBean.page == i}">
                                    <span class="currentPage">${i}</span>
                                </c:if>
                            </c:forEach>
                            <c:if test="${pageBean.page != pageBean.totalPage}">
                                <a class="nextPage"
                                   href="${pageContext.request.contextPath}/order/findByUid.do?uid=${sessionScope.user.uid}&&page=${pageBean.page+1}">&nbsp;</a>
                                <a class="lastPage"
                                   href="${pageContext.request.contextPath}/order/findByUid.do?uid=${sessionScope.user.uid}&&page=${pageBean.totalPage}">&nbsp;</a>
                            </c:if>
                        </c:if>
                    </div>
                </td>
            </tr>
            </tbody>
        </table>

    </div>
    <div class="container footer">
        <div class="span24">
            <div class="footerAd">
                <img src="image\r___________renleipic_01/footer.jpg" alt="我们的优势" title="我们的优势" height="52" width="950">
            </div>
        </div>
        <div class="span24">
            <ul class="bottomNav">
                <li>
                    <a href="#">关于我们</a>
                    |
                </li>
                <li>
                    <a href="#">联系我们</a>
                    |
                </li>
                <li>
                    <a href="#">诚聘英才</a>
                    |
                </li>
                <li>
                    <a href="#">法律声明</a>
                    |
                </li>
                <li>
                    <a>友情链接</a>
                    |
                </li>
                <li>
                    <a target="_blank">支付方式</a>
                    |
                </li>
                <li>
                    <a target="_blank">配送方式</a>
                    |
                </li>
                <li>
                    <a>SHOP++官网</a>
                    |
                </li>
                <li>
                    <a>SHOP++论坛</a>

                </li>
            </ul>
        </div>
        <div class="span24">
            <div class="copyright">Copyright © 2005-2015 网上商城 版权所有</div>
        </div>
    </div>
</body>
</html>