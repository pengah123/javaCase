package Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf8");
        response.setContentType("utf8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String checkCode = request.getParameter("checkCode");
        User loginuser=new User();
        loginuser.setName(username);
        loginuser.setPassword(password);
        userDoa userDoa = new userDoa();
        User resultUser = userDoa.logindoa(loginuser);
        HttpSession session = request.getSession();
        String checkCode1 = (String)session.getAttribute("checkCode");
        session.removeAttribute("checkCode");
        if(checkCode1!=null&&checkCode.equalsIgnoreCase(checkCode1)){
            if(resultUser==null){
                request.setAttribute("loginError","用户名或密码错误");
                request.getRequestDispatcher(request.getContextPath()+"/login.jsp").forward(request,response);
            }else{
                session.setAttribute("username",resultUser.getName());
                response.sendRedirect(request.getContextPath()+"/sucess.jsp");
            }
        }else{
            request.setAttribute("checkError","验证码输入错误");
            request.getRequestDispatcher(request.getContextPath()+"/login.jsp").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
