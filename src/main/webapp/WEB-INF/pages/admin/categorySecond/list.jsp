<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<HTML>
<HEAD>
    <meta http-equiv="Content-Language" content="zh-cn">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css"/>
    <script language="javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
    <script type="text/javascript">
        function addCategorySecond() {
            window.location.href = "${pageContext.request.contextPath}/admin/addSecond.do";
        }
    </script>
</HEAD>
<body>
<br>
<form id="Form1" name="Form1"  method="post">
    <table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
        <TBODY>
        <tr>
            <td class="ta_01" align="center" bgColor="#afd1f3">
                <strong>二级分类 列 表</strong>
            </TD>
        </tr>
        <tr>
            <td class="ta_01" align="right">
                <button type="button"  name="add" value="添加" class="button_add" onclick="addCategorySecond()">
                    &#28155;&#21152;
                </button>
            </td>
        </tr>
        <tr>
            <td class="ta_01" align="center" bgColor="#f5fafe">
                <table cellspacing="0" cellpadding="1" rules="all"
                       bordercolor="gray" border="1" id="DataGrid1"
                       style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
                    <tr
                            style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

                        <td align="center" width="18%">
                            序号
                        </td>
                        <td align="center" width="17%">
                            二级分类名称
                        </td>
                        <td width="7%" align="center">
                            编辑
                        </td>
                        <td width="7%" align="center">
                            删除
                        </td>
                    </tr>
                    <c:forEach items="${pageBean.list}" var="cs" varStatus="s">
                        <tr onmouseover="this.style.backgroundColor = 'white'"
                            onmouseout="this.style.backgroundColor = '#F5FAFE';">
                            <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                                width="18%">
                                    ${s.count}
                            </td>
                            <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                                width="17%">
                                    ${cs.csname}
                            </td>
                            <td align="center" style="HEIGHT: 22px">
                                <a href="${pageContext.request.contextPath}/admin/editSecond.do?csid=${cs.csid}&page=${pageBean.page}">
                                    <img src="${pageContext.request.contextPath}/images/i_edit.gif" border="0"
                                         style="CURSOR: hand">
                                </a>
                            </td>
                            <td align="center" style="HEIGHT: 22px">
                                <a href="${pageContext.request.contextPath}/admin/delectSecond.do?csid=${cs.csid}&page=${pageBean.page}">
                                    <img src="${pageContext.request.contextPath}/images/i_del.gif" width="16"
                                         height="16" border="0" style="CURSOR: hand">
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </td>
        </tr>
        <tr align="center">
            <td class="ta_01" align="center" bgColor="#f5fafe">
                第${pageBean.page}/${pageBean.totalPage}页&nbsp;&nbsp;&nbsp;&nbsp;
                <c:if test="${pageBean.page!=1}">
                    <a href="/admin/findAllCategorySecond.do?page=1">首页</a>|
                    <a href="/admin/findAllCategorySecond.do?page=${pageBean.page-1}">上一页</a>|
                </c:if>
                <c:if test="${pageBean.page!=pageBean.totalPage}">
                    <a href="/admin/findAllCategorySecond.do?page=${pageBean.page+1}">下一页</a>|
                    <a href="/admin/findAllCategorySecond.do?page=${pageBean.totalPage}">尾页</a>
                </c:if>
            </td>
        </tr>
        </TBODY>
    </table>
</form>
</body>
</HTML>
