package handler;

import entity.Car;
import entity.Goods;
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
 * @date 2019/6/3
 */
@WebServlet(name = "ServletCar", value = "/ServletCar")
public class ServletCar extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ServletCar");
        request.setCharacterEncoding("UTF-8");
        ArrayList<Car> cars = new ArrayList<>();
        String u_id = (String) request.getSession().getAttribute("User_id");
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            Connection con = ConnectData.getCon();
            if (con == null) {
                response.sendRedirect("ServletIndex");
            }
            String sql = "select *from car NATURAL join goods natural join sell natural join business where u_id=?";
            statement = con.prepareStatement(sql);
            statement.setString(1, u_id);
            rs = statement.executeQuery();
            while (rs.next()) {
                Car car = new Car();
                //查询购物车数据库
                car.setC_id(rs.getString("c_id"));
                car.setU_id(rs.getString("u_id"));
                car.setB_id(rs.getString("b_id"));
                car.setB_name(rs.getString("b_name"));
                car.setG_id(rs.getString("g_id"));
                car.setG_name(rs.getString("g_name"));
                car.setG_type(rs.getString("g_type"));
                car.setPicture(rs.getString("Picture"));
                car.setC_amount(rs.getInt("c_amount"));
                car.setPrice(rs.getDouble("g_price"));
                cars.add(car);
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
        request.setAttribute("cars", cars);
        request.getRequestDispatcher("car.jsp").forward(request, response);
    }

}
