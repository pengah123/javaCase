<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆</title>
    <script>
        window.onload=function(){
            document.getElementById("image").onclick=function () {
                var time=new Date().getMilliseconds();
                this.src="/CheckCodeImg?"+time;
            }
        }
    </script>
    <style>
        div{
            color: red;
        }
    </style>
</head>
<body>
<form action="/loginServlet">
    <table>
        <tr>
            <td>用户名</td>
            <td><input type="text" name="username"></td>
        </tr>
        <tr>
            <td>密码</td>
            <td><input type="text" name="password"></td>
        </tr>
        <tr>
            <td>验证码</td>
            <td><input type="text" name="checkCode"></td>
        </tr>
        <tr>
            <td colspan="2">
                <a><img id="image" src="/CheckCodeImg" alt=""></a></td>
        </tr>
        <tr>
            <td><input type="submit" value="登陆"></td>
        </tr>
    </table>
</form>
<div><%=request.getAttribute("loginError")==null?"":request.getAttribute("loginError")%></div>
<div><%=request.getAttribute("checkError")==null?"":request.getAttribute("checkError")%></div>
</body>
</html>
