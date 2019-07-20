package handler;

import entity.Car;
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
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * E-Shop
 * ${PACKAGE_NAME}
 *
 * @author GOLD
 * @date 2019/6/11
 */
@WebServlet(name = "ServletSelf", value = "/ServletSelf")
public class ServletSelf extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ServletSelf");
        request.setCharacterEncoding("UTF-8");
        User u = new User();
        String u_id = (String) request.getSession().getAttribute("User_id");
        String address = (String) request.getAttribute("address");
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            Connection con = ConnectData.getCon();
            if (con == null) {
                response.sendRedirect("ServletIndex");
            }
            String sql = "select *from user where u_id=?";
            statement = con.prepareStatement(sql);
            statement.setString(1, u_id);
            rs = statement.executeQuery();
            while (rs.next()) {
                u.setU_id(rs.getString("u_id"));
                u.setU_name(rs.getString("u_name"));
                u.setU_number(rs.getString("u_number"));
                u.setU_password(rs.getString("u_password"));
                u.setU_mail(rs.getString("u_mail"));
                u.setU_address(rs.getString("u_address"));
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
        request.setAttribute("self", u);
        request.getRequestDispatcher("self.jsp").forward(request, response);
    }

}
