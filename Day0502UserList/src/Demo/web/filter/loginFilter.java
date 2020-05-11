package Demo.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class loginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request=(HttpServletRequest)req;
        HttpServletResponse response=(HttpServletResponse)resp;
//获取请求uri地址
        String uri = request.getRequestURI();
//        如果是登陆相关信息就放行
        if(uri.contains("/login.jsp")||uri.contains("loginServlet")||uri.contains("/css/")||uri.contains("/fonts/")
                ||uri.contains("/images/")||uri.contains("/js/")||uri.contains("/CheckCodeImg")){
            chain.doFilter(req,resp);
        }else{
//            如果是登陆不相关信息就验证
            Object user = request.getSession().getAttribute("user");
//            已经登陆了，放行
            if(user!=null){
                chain.doFilter(req,resp);
            }else{
//                没登陆，跳转到登陆页面
                request.setAttribute("loginError","您尚未登陆，请登陆");
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
