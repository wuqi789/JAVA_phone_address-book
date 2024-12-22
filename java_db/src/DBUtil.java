import java.sql.*;

//数据库工具类
public class DBUtil {
    //驱动
    static String driverClass = "com.mysql.jdbc.Driver";
    //地址
    static String url = "jdbc:mysql://localhost:3306/jk6db";
    //名称
    static String name = "root";
    //密码
    static String pass = "123456";
    //连接数据库的方法
    public static Connection getConnection() {
        Connection conn = null;
        try {
            //1 加载驱动
            Class.forName(driverClass);
            //2 获取连接
            conn = DriverManager.getConnection(url,name,pass);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    //关闭Connection
    public static void closeConn(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //关闭Statement
    public static void closeSt(Statement st) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //关闭ResultSet
    public static void closeRs(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
