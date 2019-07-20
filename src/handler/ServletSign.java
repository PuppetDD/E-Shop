package handler;

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
import java.sql.SQLException;

/**
 * E-Shop
 * ${PACKAGE_NAME}
 *
 * @author GOLD
 * @date 2019/6/8
 */
@WebServlet(name = "ServletSign", value = "/ServletSign")
public class ServletSign extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("User");
        response.sendRedirect("ServletIndex");
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ServletSign");
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("User_id");
        String password = request.getParameter("User_password");
        System.out.println("User_id:" + id);
        System.out.println("User_password:" + password);
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            Connection con = ConnectData.getCon();
            String sql = "select * from user where u_id =? and u_password=?";
            statement = con.prepareStatement(sql);
            statement.setString(1, id);
            statement.setString(2, password);
            rs = statement.executeQuery();
            if (rs.next()) {
                request.getSession().setAttribute("User", rs.getString("u_name"));
                request.getSession().setAttribute("User_id", rs.getString("u_id"));
                response.sendRedirect("ServletIndex");
            } else {
                System.out.println("error");
                response.sendRedirect("ServletIndex?error=yes");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
