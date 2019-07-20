package handler;

import entity.User;
import jdbc.ConnectData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * E-Shop
 * ${PACKAGE_NAME}
 *
 * @author GOLD
 * @date 2019/6/8
 */
@WebServlet(name = "ServletRegister", value = "/ServletRegister")
public class ServletRegister extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String u_id = request.getParameter("User_id");
        String u_name = request.getParameter("User_name");
        String u_password = request.getParameter("User_password");
        String u_number = request.getParameter("User_number");
        String u_mail = request.getParameter("User_mail");
        String u_sex = request.getParameter("User_sex");
        String u_address = request.getParameter("User_address");

        System.out.println("u_id:" + u_id);
        System.out.println("u_name:" + u_name);
        System.out.println("u_password:" + u_password);
        System.out.println("u_number:" + u_number);
        System.out.println("u_sex:" + u_sex);
        System.out.println("u_mail:" + u_mail);
        System.out.println("u_address:" + u_address);

        User user = new User();
        user.setU_id(u_id);
        user.setU_name(u_name);
        user.setU_password(u_password);
        user.setU_number(u_number);
        user.setU_mail(u_mail);
        user.setU_sex(u_sex);
        user.setU_address(u_address);
        PreparedStatement statement = null;
        try {
            Connection con = ConnectData.getCon();
            if (con == null) {
                response.sendRedirect("register.jsp");
            }
            String sql1 = "select * from user where u_id=?";
            statement = con.prepareStatement(sql1);
            statement.setString(1, user.getU_id());
            ResultSet rs = statement.executeQuery();
            if (!rs.next()) {
                String sql = "insert into user values(?,?,?,?,?,?,?)";
                statement = con.prepareStatement(sql);
                statement.setString(1, user.getU_id());
                statement.setString(2, user.getU_name());
                statement.setString(3, user.getU_password());
                statement.setString(4, user.getU_number());
                statement.setString(5, user.getU_sex());
                statement.setString(6, user.getU_mail());
                statement.setString(7, user.getU_address());
                int rows = statement.executeUpdate();
                if (rows > 0) {
                    request.setAttribute("User_id", user.getU_id());
                    request.setAttribute("User_password", user.getU_password());
                    request.getRequestDispatcher("ServletSign").forward(request, response);
                    //response.sendRedirect("ServletIndex");
                } else {
                    //注册失败
                }
            } else {
                //用户已存在
                response.sendRedirect("register.jsp?userExist=1");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            // 释放数据集对象
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

}
