package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/*
连接数据库的类
 */
public class DBUtil {
    private static String username;
    private static String password;
    private static String url;
    private static String driver;

    static {
        try {
           // InputStream in = DBUtil.class.getClassLoader().getResourceAsStream("db.properties");
            FileInputStream fis=new FileInputStream("db.properties");
            Properties p = new Properties();
            p.load(fis);

            username = p.getProperty("db.username");
            password = p.getProperty("db.password");
            url = p.getProperty("db.url");
            driver = p.getProperty("db.driver");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //封装一个连接对象
    public static Connection getConn(){
        Connection conn=null;
        //加载驱动
        try {
            Class.forName(driver);
             conn = DriverManager.getConnection(url, username, password);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

}
