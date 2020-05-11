<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>修改用户</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-2.1.0.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <style>
        .error{
            color: red;
        }
    </style>
    <script>
        window.onload = function () {
            document.getElementById("form").onsubmit = function () {
                return checkUsername() && checkEmail() && checkAge();
            };
            document.getElementById("username").onblur = checkUsername;
            document.getElementById("age").onblur = checkAge;
            document.getElementById("email").onblur = checkEmail;
        };

        function checkUsername() {
            var username = document.getElementById("username").value;
            var reg_username = /^[\u4e00-\u9fa5·0-9A-z]{1,12}$/;
            var flag = reg_username.test(username);
            var s_username = document.getElementById("s_username");
            if (!flag) {
                s_username.innerHTML = "用户名格式错误";
                s_username.className = "error";
            }
            return flag;
        }

        function checkAge() {
            var age = document.getElementById("age").value;
            var reg_age = /^\w{1,2}$/;
            var flag = age.test(age);
            var s_age = document.getElementById("s_age");
            if (!flag) {
                s_age.innerHTML = "年龄格式错误";
                s_age.className = "error";
            }
            return flag;
        }

        function checkEmail() {
            var email = document.getElementById("email").value;
            var reg_email = /^[a-zA-Z\d]+([-_\.][a-zA-Z\d]+)*@[a-zA-Z\d]+\.[a-zA-Z\d]{2,4}$/;
            var flag = reg_email.test(email);
            var s_email = document.getElementById("s_email");
            if (!flag) {
                s_email.innerHTML = "邮箱格式错误";
                s_email.className = "error";
            }
            return flag;
        }

    </script>
</head>
<body>
<div class="container" style="width: 400px;">
    <h3 style="text-align: center;">修改联系人</h3>
    <form action="${pageContext.request.contextPath}/updateServlet" id="form" method="post">
        <%--隐藏域传入id--%>
        <input type="hidden" name="id" value="${user.id}">
        <div class="form-group">
            <label for="username">姓名：</label>
            <input type="text" class="form-control" id="username" name="username" value="${user.username}"
                   readonly="readonly" placeholder="请输入姓名"/>
            <span id="s_username"></span>
        </div>

        <div class="form-group">
            <label>性别：</label>
            <c:if test="${user.gender=='男'}">
                <input type="radio" name="gender" value="男" checked/>男
                <input type="radio" name="gender" value="女"/>女
            </c:if>
            <c:if test="${user.gender=='女'}">
                <input type="radio" name="gender" value="男" />男
                <input type="radio" name="gender" value="女" checked/>女
            </c:if>
            <c:if test="${user.gender!='女'&&user.gender!='男'}">
                <input type="radio" name="gender" value="男" />男
                <input type="radio" name="gender" value="女" />女
            </c:if>
            <span id="s_gender"></span>
        </div>

        <div class="form-group">
            <label for="age">年龄：</label>
            <input type="text" class="form-control" id="age" value="${user.age}" name="age" placeholder="请输入年龄"/>
            <span id="s_age"></span>
        </div>

        <div class="form-group">
            <label for="address">籍贯：</label>
            <select name="hometown" id="address" class="form-control">
                <option value="${user.hometown}"></option>
                <option value="陕西">陕西</option>
                <option value="北京">北京</option>
                <option value="上海">上海</option>
            </select>
        </div>
        <%--
                  <div class="form-group">
                    <label for="qq">QQ：</label>
                    <input type="text" id="qq" class="form-control" name="qq" placeholder="请输入QQ号码"/>
                  </div>--%>

        <div class="form-group">
            <label for="email">Email：</label>
            <input type="text" id="email" class="form-control" value="${user.email}" name="email"
                   placeholder="请输入邮箱地址"/>
            <span id="s_email"></span>
        </div>

        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="提交"/>
            <input class="btn btn-default" type="reset" value="重置"/>
            <input class="btn btn-default" type="button" value="返回"/>
        </div>
    </form>
</div>
</body>
</html>