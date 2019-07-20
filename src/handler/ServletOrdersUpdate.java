package handler;

import entity.Car;
import jdbc.ConnectData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 * E-Shop
 * ${PACKAGE_NAME}
 *
 * @author GOLD
 * @date 2019/6/11
 */
@WebServlet(name = "ServletOrdersUpdate", value = "/ServletOrdersUpdate")
public class ServletOrdersUpdate extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ServletOrdersUpdate");
        request.setCharacterEncoding("UTF-8");
        String m = request.getParameter("method");
        System.out.println("method:" + m);
        String method = m == null ? " " : m;
        String status = "已完成";
        Object object = request.getSession().getAttribute("updateOrders");
        ArrayList<Car> orders = (ArrayList<Car>) object;
        PreparedStatement statement = null;
        try {
            for (Car o : orders) {
                System.out.println(o.getO_id());
                Connection con = ConnectData.getCon();
                String sql = "update orders set method=?,status=? where o_id=?";
                statement = con.prepareStatement(sql);
                statement.setString(1, method);
                statement.setString(2, status);
                statement.setString(3, o.getO_id());
                int res = statement.executeUpdate();
                if (res > 0) {
                    System.out.println("更新数据成功");
                }
            }
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
        response.sendRedirect("ServletIndex");
    }

}
