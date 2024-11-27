<%--
  Created by IntelliJ IDEA.
  User: 35335
  Date: 2024/11/13
  Time: 17:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面</title>
</head>
<style>
    * {
        margin: 0;
        padding: 0;
    }
    form {
        display: block;
        height: auto;
        width: 450px;
        margin: 100px auto;
    }
    form table tr {
        height: 40px;
    }
    form table tr td {
        height: 40px;
        width: 280px;
        line-height: 40px;
    }
    form table tr td input {
        height: 32px;
        border: 1px solid #BABABA;
        border-radius: 6px;
    }
    .alignRight {
        text-align: right;
        line-height: 40px;
        font-size: 16px;
        font-family: "Monaco";
        width: 200px;
    }
    .submit, .register {
        display: block;
        height: 40px;
        width: 250px;
        color: white;
        font-weight: bold;
        font-size: 18px;
        background-color: #98ECAC;
        border-radius: 8px;
        margin: 15px auto;
    }
    .register {
        background-color: #74C0FC;
    }
</style>
<body>
<center><h1>图书管理系统</h1></center>

<form action="LoginServlet" method="post">
    <table>
        <tr>
            <td class="alignRight">
                Username:
            </td>
            <td>
                <input type="text" name="username" />
            </td>
        </tr>
        <tr>
            <td class="alignRight">
                Password:
            </td>
            <td>
                <input type="password" name="password" />
            </td>
        </tr>
        <tr>
            <td class="alignRight">
                User Type:
            </td>
            <td>
                <input type="radio" name="userType" value="user" checked /> 普通用户
                <input type="radio" name="userType" value="admin" /> 管理员
            </td>
        </tr>
    </table>
    <!-- 检查是否有错误信息，如果有则显示 -->
    <c:if test="${not empty errorMessage}">
        <div class="error-message">
            <p>${errorMessage}</p>
        </div>
    </c:if>
    <input type="submit" value="登 录" class="submit" />
</form>
    <a href="register.jsp" class="register">注 册</a>
</body>
</html>
