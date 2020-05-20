package Web.servlet;

import Domain.Province;
import Service.ProvinceServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/provinceServlet")
public class ProvinceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProvinceServiceImpl service = new ProvinceServiceImpl();
/*        查询数据库的方式
List<Province> list = service.findAll();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(list);*/
//使用redis的方式
        String json = service.findAllJson();

        response.setContentType("application/json;charset=utf-8");
        System.out.println(json);
        response.getWriter().write(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
