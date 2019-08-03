package handler;

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
@WebServlet(name = "ServletIndex", value = "/ServletIndex")
public class ServletIndex extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ServletIndex");
        request.setCharacterEncoding("UTF-8");
        String style = request.getParameter("style");
        String key = request.getParameter("key");
        System.out.println("style:" + style);
        System.out.println("key:" + key);
        if (key != null) {
            request.setAttribute("g_list", Search(key));
            request.getRequestDispatcher("index.jsp?styleshow=7").forward(request, response);
            System.out.println("request");
        }
        if (style == null || style.compareTo("1") == 0 && key == null) {
            ArrayList<Goods> g_list = new ArrayList<Goods>();
            Connection con = ConnectData.getCon();
            String sql = "select * from goods natural join sell natural join business";
            Statement statement = null;
            ResultSet rs = null;
            try {
                statement = con.createStatement();
                rs = statement.executeQuery(sql);
                while (rs.next()) {
                    Goods g = new Goods();
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
            } catch (SQLException e) {
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
            request.setAttribute("g_list", g_list);
            request.getRequestDispatcher("index.jsp?styleshow=1").forward(request, response);
        }
        if (style != null && key == null) {
            if (style.compareTo("2") == 0) {
                request.setAttribute("g_list", Classify("日用品"));
                request.getRequestDispatcher("index.jsp?styleshow=2").forward(request, response);
            }
            if (style.compareTo("3") == 0) {
                request.setAttribute("g_list", Classify("电子科技"));
                request.getRequestDispatcher("index.jsp?styleshow=3").forward(request, response);
            }
            if (style.compareTo("4") == 0) {
                request.setAttribute("g_list", Classify("零食小吃"));
                request.getRequestDispatcher("index.jsp?styleshow=4").forward(request, response);
            }
        }
    }

    public static ArrayList<Goods> Classify(String s) {
        System.out.println(s);
        ArrayList<Goods> g_list = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            Connection con = ConnectData.getCon();
            String sql = "select * from goods natural join sell natural join business where g_type=?";
            statement = con.prepareStatement(sql);
            statement.setString(1, s);
            rs = statement.executeQuery();
            while (rs.next()) {
                Goods g = new Goods();
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
        return g_list;
    }

    public static ArrayList<Goods> Search(String s) {
        ArrayList<Goods> g_list = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            Connection con = ConnectData.getCon();
            String sql = "select * from goods natural join sell natural join business where concat(g_name, g_type) like concat('%',?,'%')";
            statement = con.prepareStatement(sql);
            statement.setString(1, s);
            rs = statement.executeQuery();
            while (rs.next()) {
                Goods g = new Goods();
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
                System.out.println("Search:" + g.getG_name());
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
        System.out.println("Search end");
        return g_list;
    }

}
