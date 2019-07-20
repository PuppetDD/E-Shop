package handler;

import entity.Car;
import entity.User;
import jdbc.ConnectData;
import jdk.net.SocketFlow;

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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * E-Shop
 * ${PACKAGE_NAME}
 *
 * @author GOLD
 * @date 2019/6/8
 */
@WebServlet(name = "ServletOrder", value = "/ServletOrder")
public class ServletOrder extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ServletOrder");
        request.setCharacterEncoding("UTF-8");
        Object object = request.getSession().getAttribute("car_list");
        ArrayList<Car> c_list = (ArrayList<Car>) object;
        ArrayList<User> users = new ArrayList<>();
        User u = new User();
        String u_id = (String) request.getSession().getAttribute("User_id");
        String m = request.getParameter("method");
        String method = m == null ? " " : m;
        String status = request.getParameter("status") == null ? "未完成" : "已完成";
        System.out.println("method:" + method);
        System.out.println("c_list:" + c_list);
        System.out.println("User_id:" + u_id);
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
                u.setU_id(u_id);
                u.setU_name(rs.getString("u_name"));
                u.setU_number(rs.getString("u_number"));
                u.setU_address(rs.getString("u_address"));
            }
            users.add(u);
            //添加订单
            for (Car c : c_list) {
                //添加订单
                String sql1 = "insert into orders(o_id,u_id,b_id,g_id,o_amount,o_price,method,status) values(?,?,?,?,?,?,?,?)";
                SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
                statement = con.prepareStatement(sql1);
                c.setO_id(time.format(new Date()) + c.getG_id());
                System.out.println("order_id:" + c.getO_id());
                statement.setString(1, c.getO_id());
                statement.setString(2, u.getU_id());
                statement.setString(3, c.getB_id());
                statement.setString(4, c.getG_id());
                statement.setInt(5, c.getC_amount());
                statement.setDouble(6, c.getPrice());
                statement.setString(7, method);
                statement.setString(8, status);
                int rows = statement.executeUpdate();
                if (rows > 0) {
                    System.out.println("添加订单成功");
                }
                if (c.getC_id() != null) {
                    deleteCar(c.getC_id());
                }
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
        request.setAttribute("car_list", c_list);
        request.setAttribute("user_address", users);
        request.getRequestDispatcher("order.jsp").forward(request, response);
    }

    public void deleteCar(String c_id) {
        PreparedStatement statement = null;
        try {
            Connection con = ConnectData.getCon();
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
    }

}
