<%--
  Created by IntelliJ IDEA.
  User: 35335
  Date: 2024/11/21
  Time: 22:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册页面</title>
    <style>
        * {
            margin: 0;
            padding: 0;
        }
        body {
            font-family: "Arial", sans-serif;
            background-color: #f8f8f8;
            color: #333;
        }
        form {
            display: block;
            height: auto;
            width: 450px;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            border: 1px solid #dcdcdc;
            border-radius: 10px;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            margin-bottom: 20px;
            font-size: 28px;
            color: #333;
        }
        form table {
            width: 100%;
        }
        form table tr {
            height: 50px;
        }
        form table tr td {
            padding: 5px 10px;
        }
        form table tr td input[type="text"],
        form table tr td input[type="password"] {
            width: calc(100% - 20px);
            height: 35px;
            border: 1px solid #bababa;
            border-radius: 6px;
            padding: 0 10px;
            font-size: 16px;
        }
        form table tr td input[type="radio"] {
            margin-left: 10px;
        }
        form table tr td span {
            font-size: 14px;
            color: red; /* 提示信息改为红色 */
            margin-left: 5px;
        }
        .alignRight {
            text-align: right;
            font-size: 16px;
            color: #555;
            width: 120px;
        }
        .submit {
            display: block;
            width: 100%;
            height: 45px;
            color: white;
            font-weight: bold;
            font-size: 18px;
            background-color: #5cb85c;
            border: none;
            border-radius: 8px;
            margin-top: 20px;
            cursor: pointer;
        }
        .submit:hover {
            background-color: #4cae4c;
        }
    </style>
</head>
<body>
<center><h1>注册页面</h1></center>
<form action="RegisterServlet" method="post">
    <table>
        <tr>
            <td class="alignRight">
                Username:
            </td>
            <td>
                <input type="text" name="username" value="${formBean.name}" required />
                <span>${formBean.errors.name}${DBMes}</span>
            </td>
        </tr>
        <tr>
            <td class="alignRight">
                Password:
            </td>
            <td>
                <input type="password" name="password" required />
                <span>${formBean.errors.password}</span>
            </td>
        </tr>
        <tr>
            <td class="alignRight">
                Confirm Password:
            </td>
            <td>
                <input type="password" name="confirmPassword" required />
                <span>${formBean.errors.password2}</span>
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
    <input type="submit" value="注 册" class="submit" />
</form>
</body>
</html>
