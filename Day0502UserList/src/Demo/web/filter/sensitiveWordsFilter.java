package Demo.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

@WebFilter("/*")
public class sensitiveWordsFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        /*1.创建代理对象，增强getParameter 方法*/
        ServletRequest proxy_request = (ServletRequest) Proxy.newProxyInstance(req.getClass().getClassLoader(), req.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                判断时候是getParameter方法
                if (method.getName().equals("getParameter")) {
//                    增强返回值
                    String value = (String) method.invoke(req, args);
                    if(value!=null){
                        for (String s : list) {
                            if(value.contains(s)){
                                value=value.replaceAll(s,"***");
                            }
                        }
                    }
                    return value;
                }
                return method.invoke(req, args);
            }
        });
        /*2.放行*/
        chain.doFilter(proxy_request, resp);
    }

    private List<String> list = new ArrayList<>();//敏感词汇集合

    public void init(FilterConfig config) throws ServletException {

        try {  //        1.加载文件
            ServletContext servletContext = config.getServletContext();
            String realPath = servletContext.getRealPath("/WEB-INF/classes/敏感词汇.txt");

//        2.读取文件
            BufferedReader br = new BufferedReader(new FileReader(realPath));
            //        3.将文件中的每一行添加到list中
            String line = null;
            while ((line=br.readLine())!=null){
                list.add(line);
            }
            br.close();
            System.out.println(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
