package Demo.web.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

@WebServlet("/CheckCodeImg")
public class CheckCodeImg extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OutputStream os = response.getOutputStream();
        response.setContentType("image/jpeg");
        int width=100;
        int hight=40;

        BufferedImage bfImg=new BufferedImage(width,hight,BufferedImage.TYPE_INT_RGB);
        Graphics graphics = bfImg.getGraphics();
//        填充
        graphics.setColor(Color.pink);
        graphics.fillRect(0,0,width,hight);
//        边框
        graphics.setColor(Color.blue);
        graphics.drawRect(0,0,width-1,hight-1);
//        加字母
        String s="qwertyuiopalksdjfhgzmxncbvQWERTYUIOPLKJHGFDSAZXCVBNM0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < 5; i++) {
            int x=i*20;
            int y=20;
            int i1 = random.nextInt(s.length());
            char ch=s.charAt(i1);
            sb.append(ch);
            graphics.drawString(ch+"",x,y);
        }
        String checkCode=sb.toString();
//        将字符串保存到session
        HttpSession session = request.getSession();
        session.setAttribute("checkCode",checkCode);
//        划线
        graphics.setColor(Color.green);
        for (int i = 0; i < 5; i++) {
            int x1=random.nextInt(width);
            int y1=random.nextInt(hight);
            int x2=random.nextInt(width);
            int y2=random.nextInt(hight);
            graphics.drawLine(x1,y1,x2,y2);
        }
        ImageIO.write(bfImg,"jpeg",os);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
