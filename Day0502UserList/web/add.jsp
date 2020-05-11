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
    <title>添加用户</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
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
                s_username.className="error";
            }
            return flag;
        }

        function checkAge() {
            var age= document.getElementById("age").value;
            var reg_age = /^\w{1,2}$/;
            var flag = reg_age.test(age);
            var s_age = document.getElementById("s_age");
            if (!flag) {
                s_age.innerHTML = "年龄格式错误";
                s_age.className="error";
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
                s_email.className="error";
            }
            return flag;
        }

    </script>
</head>
<body>
<div class="container">
    <center><h3>添加联系人页面</h3></center>
    <form action="${pageContext.request.contextPath}/addUserServlet" id="form" method="post">
        <div class="form-group">
            <label for="username">姓名：</label>
            <input type="text" class="form-control" id="username" name="username" placeholder="请输入姓名">
            <span id="s_username"></span>
        </div>

        <div class="form-group">
            <label>性别：</label>
            <input type="radio" name="gender" value="男" checked="checked"/>男
            <input type="radio" name="gender" value="女"/>女
        </div>

        <div class="form-group">
            <label for="age">年龄：</label>
            <input type="text" class="form-control" id="age" name="age" placeholder="请输入年龄">
            <span id="s_age"></span>
        </div>

        <div class="form-group">
            <label for="address">籍贯：</label>
            <select name="hometown" class="form-control" id="address">
                <option value="陕西">陕西</option>
                <option value="北京">北京</option>
                <option value="上海">上海</option>
            </select>
        </div>

        <%--        <div class="form-group">
                    <label for="qq">QQ：</label>
                    <input type="text" class="form-control" id="qq" name="qq" placeholder="请输入QQ号码"/>
                </div>--%>

        <div class="form-group">
            <label for="email">Email：</label>
            <input type="text" class="form-control" id="email" name="email" placeholder="请输入邮箱地址"/>
            <span id="s_email"></span>
        </div>

        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="提交"/>
            <input class="btn btn-default" type="reset" value="重置"/>
            <input class="btn btn-default" type="button" onclick="window.history.back()" value="返回"/>
        </div>
    </form>
</div>
</body>
</html>