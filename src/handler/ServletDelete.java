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

/**
 * E-Shop
 * ${PACKAGE_NAME}
 *
 * @author GOLD
 * @date 2019/6/8
 */
@WebServlet(name = "ServletDelete", value = "/ServletDelete")
public class ServletDelete extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet");
        request.setCharacterEncoding("UTF-8");
        String c_id = request.getParameter("Delete");
        System.out.println("Delete:" + c_id);
        PreparedStatement statement = null;
        try {
            Connection con = ConnectData.getCon();
            if (con == null) {
                response.sendRedirect("ServletIndex");
            }
            String sql = "delete from car where c_id=?";
            statement = con.prepareStatement(sql);
            statement.setString(1, c_id);
            statement.executeLargeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        request.getRequestDispatcher("ServletCar").forward(request, response);
    }

}
