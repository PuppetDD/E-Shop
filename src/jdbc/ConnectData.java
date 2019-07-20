package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * E-Shop
 * jdbc
 *
 * @author GOLD
 * @date 2019/5/31
 */
public class ConnectData {

    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/shop?characterEncoding=UTF-8";
    private static final String user = "root";
    private static final String password = "JH247563";
    private static Connection con;

    public static Connection getCon() {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return con;
    }

}
