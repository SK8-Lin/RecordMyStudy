package util;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.*;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;

public class JdbcUtil {
    private JdbcUtil() {
    }

    //打通连接
    static {
        //利用资源绑定器从配置文件中获取与数据库连接的相关参数
        ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
        String driver = bundle.getString("driver");
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //建立通道1.0
    public static Connection getConnection() throws SQLException {
        ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
        String url = bundle.getString("url");
        String user = bundle.getString("user");
        String passwd = bundle.getString("passwd");
        return DriverManager.getConnection(url,user,passwd);
    }

    //建立通道2.0
    public static Connection getConnection(HttpServletRequest req) throws SQLException {
        //从连接池中获取Connection
        ServletContext application = req.getServletContext();
        Map<Connection,Boolean> map = (Map<Connection, Boolean>) application.getAttribute("connectionPool");
        Connection conn = null;
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            conn = (Connection) it.next();
            boolean flag = map.get(conn);
            if (flag) {
                map.put(conn,false);
                break;
            }
        }
        return conn;
    }

    //建立交通工具
    public static PreparedStatement getPreparedStatement(Connection conn,String sql) throws SQLException{
        return conn.prepareStatement(sql);
    }

    //关闭通道1.0
    public static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //关闭通道2.0
    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //关闭通道3.0
    public static void close(HttpServletRequest req, Connection conn, PreparedStatement ps, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        ServletContext application = req.getServletContext();
        Map<Connection,Boolean> map = (Map<Connection, Boolean>) application.getAttribute("connectionPool");
        map.put(conn,true);
    }
}
