<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
<title>网上商城管理中心</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${pageContext.request.contextPath }/css/general.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/css/main.css" rel="stylesheet" type="text/css" />

<style type="text/css">
body {
  color: white;
}
</style>
<body style="background: #278296">
<form method="post" action="${pageContext.request.contextPath }/admin/login.do" target="_parent" name='theForm'>
  <table cellspacing="0" cellpadding="0" style="margin-top: 100px" align="center">
  <tr>
    <td style="padding-left: 50px">
      <table>
        <tr> <h2><span style="color: red">${error}</span></h2></tr>
        <tr>
        <td>管理员姓名：</td>
        <td><input type="text" name="username" /></td>
      </tr>
      <tr>
        <td>管理员密码：</td>
        <td><input type="password" name="password" /></td>
      </tr>

      <tr>
        <td>&nbsp;</td><td><input type="submit" value="进入管理中心" class="button"/></td>
      </tr>
      </table>
    </td>
  </tr>
  </table>
  <input type="hidden" name="act" value="signin" />
</form>

</body>