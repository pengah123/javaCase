package Demo.web.servlet;

import Demo.doMain.User;
import Demo.service.userServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String verifycode = request.getParameter("verifycode");
        HttpSession session = request.getSession();
        String checkCode =(String) session.getAttribute("checkCode");
        session.removeAttribute("checkCode");
        if(!checkCode.equalsIgnoreCase(verifycode)){
            request.setAttribute("checkError","验证码输入错误");
            request.getRequestDispatcher(request.getContextPath()+"/login.jsp").forward(request,response);
            return;
        }
        Map<String, String[]> parameterMap = request.getParameterMap();
        User loginUser = new User();
        try {
            BeanUtils.populate(loginUser,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        userServiceImpl userService = new userServiceImpl();
        User user = userService.varifyLogin(loginUser);
        if(user==null){
            request.setAttribute("loginError","用户名或密码错误");
            request.getRequestDispatcher(request.getContextPath()+"/login.jsp").forward(request,response);
        }else {
            session.setAttribute("user",user);
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
