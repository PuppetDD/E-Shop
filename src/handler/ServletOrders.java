package handler;

import entity.Car;
import entity.Goods;
import entity.Order;
import jdbc.ConnectData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

/**
 * E-Shop
 * ${PACKAGE_NAME}
 *
 * @author GOLD
 * @date 2019/6/10
 */
@WebServlet(name = "ServletOrders", value = "/ServletOrders")
public class ServletOrders extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ServletOrders");
        request.setCharacterEncoding("UTF-8");
        ArrayList<Order> orders = new ArrayList<>();
        String u_id = (String) request.getSession().getAttribute("User_id");
        System.out.println("Orders u_id:" + u_id);
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            Connection con = ConnectData.getCon();
            if (con == null) {
                response.sendRedirect("ServletIndex");
            }
            String sql = "select *from orders NATURAL join goods where u_id=?";
            statement = con.prepareStatement(sql);
            statement.setString(1, u_id);
            rs = statement.executeQuery();
            while (rs.next()) {
                Order o = new Order();
                //查询购物车数据库
                o.setO_id(rs.getString("o_id"));
                o.setU_id(rs.getString("u_id"));
                o.setB_id(rs.getString("b_id"));
                o.setG_id(rs.getString("g_id"));
                o.setG_name(rs.getString("g_name"));
                o.setMethod(rs.getString("method"));
                o.setStatus(rs.getString("status"));
                o.setO_amount(rs.getInt("o_amount"));
                o.setO_price(rs.getDouble("o_price"));
                orders.add(o);
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
        request.setAttribute("orders", orders);
        request.getRequestDispatcher("orders.jsp").forward(request, response);
    }

}

