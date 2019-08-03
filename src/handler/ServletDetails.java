package handler;

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
import java.util.ArrayList;

/**
 * E-Shop
 * ${PACKAGE_NAME}
 *
 * @author GOLD
 * @date 2019/6/8
 */
@WebServlet(name = "ServletDetails", value = "/ServletDetails")
public class ServletDetails extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ServletDetails 详细信息");
        request.setCharacterEncoding("UTF-8");
        //获取传进来的参数
        String g_id = request.getParameter("g_id");
        System.out.println(g_id);
        ArrayList<Goods> g_list = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            Connection con = ConnectData.getCon();
            String sql = "select * from goods natural join sell natural join business where g_id=?;";
            statement = con.prepareStatement(sql);
            statement.setString(1, g_id);
            rs = statement.executeQuery();
            while (rs.next()) {
                Goods g = new Goods();
                //查询商品信息
                g.setB_id(rs.getString("b_id"));
                g.setB_name(rs.getString("b_name"));
                g.setG_id(rs.getString("g_id"));
                g.setG_name(rs.getString("g_name"));
                g.setG_price(rs.getDouble("g_price"));
                g.setG_type(rs.getString("g_type"));
                g.setManufacturer(rs.getString("manufacturer"));
                g.setStock(rs.getInt("stock"));
                g.setWeight(rs.getDouble("weight"));
                g.setPicture(rs.getString("picture"));
                g_list.add(g);
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
        request.setAttribute("Details", g_list);
        request.getRequestDispatcher("details.jsp").forward(request, response);
    }

}
