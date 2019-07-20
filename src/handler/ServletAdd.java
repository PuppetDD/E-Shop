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
@WebServlet(name = "ServletAdd", value = "/ServletAdd")
public class ServletAdd extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ServletAdd");
        request.setCharacterEncoding("UTF-8");
        ArrayList<Car> cars = new ArrayList<>();
        Car car = (Car) request.getSession().getAttribute("addCar");

        String u_id = car.getU_id();
        String g_id = car.getG_id();
        int c_amount = Integer.parseInt(request.getParameter("c_amount"));

        car.setC_amount(c_amount);
        cars.add(car);

        System.out.println("c_amount:" + c_amount);

        if (request.getParameter("flag").equals("1")) {
            SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
            car.setC_id(time.format(new Date()) + u_id);
            if (!checkAdd(car.getC_id(), u_id, g_id, c_amount)) {
                PreparedStatement statement = null;
                try {
                    Connection con = ConnectData.getCon();
                    if (con == null) {
                        response.sendRedirect("ServletIndex");
                    }
                    String sql = "insert into car(c_id,u_id,b_id,g_id,c_amount,c_price) values(?,?,?,?,?,?)";
                    statement = con.prepareStatement(sql);
                    statement.setString(1, car.getC_id());
                    statement.setString(2, car.getU_id());
                    statement.setString(3, car.getB_id());
                    statement.setString(4, car.getG_id());
                    statement.setInt(5, car.getC_amount());
                    statement.setDouble(6, car.getPrice());
                    int rows = statement.executeUpdate();
                    if (rows > 0) {
                        response.sendRedirect("ServletDetails?g_id=" + g_id + "&insert=1");
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
            } else {
                response.sendRedirect("ServletDetails?g_id=" + g_id + "&insert=1");
            }
        } else {
            //立即购买，请求转发到ServletOrder
            System.out.println("立即购买");
            request.getSession().setAttribute("car_list", cars);
            request.setAttribute("User_id", u_id);
            request.getRequestDispatcher("ServletOrder").forward(request, response);
        }
    }

    public static boolean checkAdd(String c_id, String u_id, String g_id, int amount) {
        System.out.println("CheckAdd:" + amount);
        int count = 0;
        PreparedStatement statement = null;
        ResultSet rs = null;
        int s = 0;
        try {
            Connection con = ConnectData.getCon();
            String sql = "select * from car where u_id=? and g_id=?";
            statement = con.prepareStatement(sql);
            statement.setString(1, u_id);
            statement.setString(2, g_id);
            rs = statement.executeQuery();
            if (rs.next()) {
                count = rs.getInt("c_amount");
                s = 1;
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
        if (s == 1) {
            count += amount;
            System.out.println("count:" + count);
            try {
                Connection con = ConnectData.getCon();
                String sql = "update car set c_id=?,c_amount=? where u_id=? and g_id=?";
                statement = con.prepareStatement(sql);
                statement.setString(1, c_id);
                statement.setInt(2, count);
                statement.setString(3, u_id);
                statement.setString(4, g_id);
                int res = statement.executeUpdate();
                if (res > 0) {
                    System.out.println("更新数据成功");
                }
            } catch (SQLException e) {
                System.out.println("error");
                e.printStackTrace();
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
            return true;
        } else {
            return false;
        }
    }

}
