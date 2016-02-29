<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>

this is login jsp ...
这是一个登录页面。。。
<%--将表单提交到登录校验页面，authentication-manager会拦截，login-page="/user/login" --%>
<form action="${pageContext.request.contextPath}/login" method="post">
    <c:if test="${param.login == false}"><p class="login-box-msg" style="color: red;">用户名或密码错误！</p></c:if>
    </br>
    <input type="text" name="username" id="username"/>
    </br>
    <input type="password" name="password" id="password"/>
    <input type="submit"/>
</form>


</body>
</html>
