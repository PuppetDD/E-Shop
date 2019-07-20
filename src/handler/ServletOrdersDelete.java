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
 * @date 2019/6/10
 */
@WebServlet(name = "ServletOrdersDelete", value = "/ServletOrdersDelete")
public class ServletOrdersDelete extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ServletOrdersDelete");
        request.setCharacterEncoding("UTF-8");
        String o_id = request.getParameter("OrdersDelete");
        System.out.println("OrdersDelete:" + o_id);
        PreparedStatement statement = null;
        try {
            Connection con = ConnectData.getCon();
            if (con == null) {
                response.sendRedirect("ServletIndex");
            }
            String sql = "delete from orders where o_id=?";
            statement = con.prepareStatement(sql);
            statement.setString(1, o_id);
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
        request.getRequestDispatcher("ServletOrders").forward(request, response);
    }

}
