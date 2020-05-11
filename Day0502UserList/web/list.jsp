<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户信息管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
    <script>
        window.onload = function () {
            document.getElementById("deleteAll").onclick = function () {
                if (confirm("确定删除此用户吗？")) {
                    var listOf = document.getElementsByName("cb");
                    var flag=false;
                    for(var i=0;i<listOf.length;i++){
                        if(listOf[i].checked){
                            flag=true;
                            break;
                        }
                    }
                    if(flag){
                        document.getElementById("form").submit();
                    }
                }
            }
            document.getElementById("headCheckBox").onclick=function () {
                var listOf = document.getElementsByName("cb");
                for(var i=0;i<listOf.length;i++){
                    listOf[i].checked=this.checked;
                }
            }
        };

        function deleteUser(id) {
            if (confirm("确定删除此用户吗？")) {
                location.href = "${pageContext.request.contextPath}/deleteUserServlet?id=" + id;
            }
        }
    </script>
</head>
<body>
<div class="container">
    <h3 style="text-align: center">用户信息列表</h3>
    <div style="float: left">
        <form action="${pageContext.request.contextPath}/userPageServlet" class="form-inline">
            <div class="form-group">
                <label for="exampleInputName2">姓名</label>
                <input type="text" class="form-control" id="exampleInputName2" name="username" value="${condition.username[0]}">
            </div>
            <div class="form-group">
                <label for="exampleInputName3">籍贯</label>
                <input type="text" class="form-control" id="exampleInputName3" name="hometown" value="${condition.hometown[0]}">
            </div>
            <div class="form-group">
                <label for="exampleInputEmail2">邮箱</label>
                <input type="email" class="form-control" id="exampleInputEmail2" name="email" value="${condition.email[0]}">
            </div>
            <button type="submit" class="btn btn-default">查询</button>
        </form>
    </div>
    <div style="float: right">
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/add.jsp">添加联系人</a>
        <a class="btn btn-primary" id="deleteAll" href="javascript:void(0);">删除选中</a>
    </div>


    <table border="1" class="table table-bordered table-hover">
        <tr class="success">
            <th><input type="checkbox" id="headCheckBox" name="headCheckBox"></th>
            <th>编号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>年龄</th>
            <th>籍贯</th>
            <th>邮箱</th>
            <th>操作</th>
        </tr>
        <form id="form" action="${pageContext.request.contextPath}/deleteAllUserServlet" method="post">
            <c:forEach items="${pageBean.list}" var="user" varStatus="s">
                <c:if test="${user.id!=null}">
                    <tr>
                        <td><input type="checkbox" id="${user.id}" name="cb" value="${user.id}"></td>
                        <td>${s.count}</td>
                        <td>${user.username}</td>
                        <td>${user.gender}</td>
                        <td>${user.age}</td>
                        <td>${user.hometown}</td>
                        <td>${user.email}</td>
                        <td><%--<a class="btn btn-default btn-sm"
                           href="${pageContext.request.contextPath}/update.jsp?username=${user.username} & id=${user.id} & hometown=${user.hometown}">修改</a>&nbsp;--%>
                            <a class="btn btn-default btn-sm"
                               href="${pageContext.request.contextPath}/findUserServlet?id=${user.id}">修改</a>&nbsp;
                            <a class="btn btn-default btn-sm" onclick="deleteUser(${user.id})" href="">删除</a></td>
                    </tr>
                </c:if>
            </c:forEach>
        </form>
    </table>

    <div style="float: left">
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <c:if test="${pageBean.currPage==1}">
                    <li class="disabled">
                            <span aria-hidden="true">&laquo;</span>
                    </li>
                </c:if>
                <c:if test="${pageBean.currPage!=1}">
                    <li>
                        <a href="${pageContext.request.contextPath}/userPageServlet?currPage=${pageBean.currPage-1}&rows=5&username=${condition.username[0]}&hometown=${condition.hometown[0]}&email=${condition.email[0]}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:if>

                <c:forEach var="i" begin="1" end="${pageBean.pageCount}">
                    <c:if test="${pageBean.currPage==i}">
                        <li class="active">
                    </c:if>
                    <c:if test="${pageBean.currPage!=i}">
                        <li>
                    </c:if>
                    <a href="${pageContext.request.contextPath}/userPageServlet?currPage=${i}&rows=5&username=${condition.username[0]}&hometown=${condition.hometown[0]}&email=${condition.email[0]}">${i}</a></li>
                </c:forEach>

                <c:if test="${pageBean.currPage==pageBean.pageCount}">
                    <li class="disabled">
                        <span aria-hidden="true">&raquo;</span>
                    </li>
                </c:if>
                <c:if test="${pageBean.currPage!=pageBean.pageCount}">
                    <li>
                        <a href="${pageContext.request.contextPath}/userPageServlet?currPage=${pageBean.currPage+1}&rows=5&username=${condition.username[0]}&hometown=${condition.hometown[0]}&email=${condition.email[0]}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:if>
                <span style="font-size: 30px">共${pageBean.count}条记录，共${pageBean.pageCount}页</span>
            </ul>
        </nav>
    </div>
</div>
</body>
</html>
