package Demo.web.servlet;

import Demo.doMain.PageBean;
import Demo.doMain.User;
import Demo.service.userServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/userPageServlet")
public class userPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String currPage = request.getParameter("currPage");
        String rows=request.getParameter("rows");
        if(currPage==null||currPage.length()==0){
            currPage="1";
        }
        if(rows==null||rows.length()==0){
            rows="5";
        }
//        多条件查询的结果
        Map<String, String[]> condition = request.getParameterMap();
        userServiceImpl userService = new userServiceImpl();
        PageBean<User> upb= userService.userPage(currPage, rows,condition);


        request.setAttribute("pageBean",upb);
        request.setAttribute("condition",condition);
        request.getRequestDispatcher(request.getContextPath()+"/list.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
