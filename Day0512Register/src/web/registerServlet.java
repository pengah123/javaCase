package web;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        Map<String, Object> map = new HashMap<String, Object>();

        if (name.equals("tom")) {
            map.put("userExist", true);
            map.put("msg", "该用户名已被注册");
        } else {
            map.put("userExist", false);
            map.put("msg", "注册成功");
        }
        response.setContentType("application/json;charset=utf-8");
        ObjectMapper omap = new ObjectMapper();
        omap.writeValue(response.getWriter(), map);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
