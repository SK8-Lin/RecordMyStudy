package listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class connectionPoolListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Http服务器启动了，创建20个connection对象的连接池");
        Map<Connection, Boolean> map = new HashMap();
        for (int i = 0; i < 20; i++) {
            try {
                Connection conn = JdbcUtil.getConnection();
                map.put(conn,true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        ServletContext application = sce.getServletContext();
        application.setAttribute("connectionPool",map);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Http服务器启动了，创建20个connection对象的连接池");
        Map<Connection,Boolean> map = new HashMap<>();
        ServletContext application = sce.getServletContext();
        map = (Map<Connection, Boolean>) application.getAttribute("connectionPool");
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            Connection conn = (Connection) it.next();
            if (conn != null) {
                JdbcUtil.close(conn);
            }
        }
    }
}
