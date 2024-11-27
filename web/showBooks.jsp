<%--
  Created by IntelliJ IDEA.
  User: 35335
  Date: 2024/11/21
  Time: 10:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>所有书籍</title>
    <style>
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #f5f5f5;
        }
    </style>
</head>
<body>
<h1 style="text-align: center;">书籍列表</h1>
<table>
    <thead>
    <tr>
        <th>图书 ID</th>
        <th>ISBN</th>
        <th>标题</th>
        <th>作者</th>
        <th>出版社</th>
        <th>分类</th>
        <th>状态</th>
    </tr>
    </thead>
    <tbody>
    <c:if test="${empty books}">
        <p style="text-align: center; color: #ff0000;">暂无书籍数据。</p>
    </c:if>
    <c:forEach var="book" items="${books}">
        <tr>
            <td>${book.bookId}</td>
            <td>${book.isbn}</td>
            <td>${book.title}</td>
            <td>${book.author}</td>
            <td>${book.publisher}</td>
            <td>${book.category}</td>
            <td>${book.bookStatus}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>


