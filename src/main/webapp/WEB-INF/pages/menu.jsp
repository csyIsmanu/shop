<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: csy
  Date: 2020/5/10
  Time: 9:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="span10 last">
    <div class="topNav clearfix">
        <ul>
            <c:if test="${empty user}">
                <li id="headerLogin" class="headerLogin" style="display: list-item;">
                    <a href="/shop/login.do">登录</a>|
                </li>
                <li id="headerRegister" class="headerRegister"
                    style="display: list-item;"><a href="/shop/regist.do">注册</a>|
                </li>
            </c:if>
            <c:if test="${not empty user}">
                <li id="headerLogin" class="headerLogin" style="display: list-item;">
                        <a href="/user/userUpdate.do?uid=${sessionScope.user.uid}">${sessionScope.user.name}</a>
                    |
                </li>
                <li id="headerLogin" class="headerLogin" style="display: list-item;">
                    <a href="${pageContext.request.contextPath}/order/findByUid.do?page=1&uid=${sessionScope.user.uid}">我的订单</a>
                    |
                </li>
                <li id="headerRegister" class="headerRegister" style="display: list-item;">
                    <a href="/user/quit.do">退出</a>|
                </li>
            </c:if>
            <li>
                <a href="/admin/index.do">管理员中心</a>
                |
            </li>
            <li>
                <a>购物指南</a>
                |
            </li>
            <li>
                <a>关于我们</a>

            </li>
        </ul>
    </div>
    <div class="cart">
        <a href="/shop/cat.do">购物车</a>
    </div>
    <div class="phone">
        客服热线:
        <strong>96008/53277764</strong>
    </div>
</div>
<div class="span24">
    <ul class="mainNav">
        <li><a href="/shop/index.do">首页</a> |</li>
        <c:forEach var="c" items="${categoryList}">
            <li>
            <a href="${pageContext.request.contextPath}/product/findByCid.do?cid=${c.cid}&page=1">
                    ${c.cname}
            </a>
            |</li>
        </c:forEach>
    </ul>
</div>