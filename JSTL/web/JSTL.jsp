<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="Demo.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--在request域中有一个存着User对象的list集合，需要使用JSTL+EL将list集合数据展示到jsp页面的表格table中--%>
<%

    List list = new ArrayList();
    list.add(new User("张三", 23, new Date()));
    list.add(new User("李四", 20, new Date()));
    list.add(new User("王五", 22, new Date()));
    request.setAttribute("list", list);
%>
<table border="1px">
    <tr>
    <th>编号</th>
    <th>姓名</th>
    <th>年龄</th>
    <th>生日</th>
    </tr>
    <c:forEach items="${list}" var="user" step="1" varStatus="s">
        <c:if test="${s.count%2==0}">
            <tr bgcolor="#a9a9a9">
                <td>${s.count}</td>
                <td> ${user.name}</td>
                <td> ${user.age}</td>
                <td> ${user.birthday}</td>
            </tr>
        </c:if>
        <c:if test="${s.count%2!=0}">
            <tr bgcolor="#e9967a">
                <td>${s.count}</td>
                <td> ${user.name}</td>
                <td> ${user.age}</td>
                <td> ${user.birthday}</td>
            </tr>
        </c:if>

    </c:forEach>
</table>

</body>
</html>
