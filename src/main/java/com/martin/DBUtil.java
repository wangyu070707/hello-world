package com.martin;

import java.sql.*;
import java.util.Map;

/**
 * Created by Martin on 2016/4/8.
 */
public class DBUtil {
    /**
     * 定义一个Connection 用来连接数据库
     */
    private static Connection conn = null;
    private static Map<String, String> xmlMap;
    /**
     * 连接数据库的URL
     */
//    private static final String url = "";
    /**
     * 指定数据的用户名和密码
     */
//    private static final String username = "PARTNER";
//    private static final String password = "dev031-123";

    private static ResultSet resultSet = null;
    private static PreparedStatement pstmt = null;

    public DBUtil() throws Exception {
        conn = connectionDB();
    }

    /**
     * 建立数据的连接
     *
     * @throws
     */
    @SuppressWarnings("finally")
    public static Connection connectionDB() {
        try {
            XmlRead xmlRead = new XmlRead();
            xmlMap = xmlRead.getXml();
            Class.forName(xmlMap.get("driverClassName"));
            conn = DriverManager.getConnection(xmlMap.get("url"), xmlMap.get("username"), xmlMap.get("password"));
            System.out.println("DB connect success!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("DB connect failed！");
        } finally {
            return conn;
        }
    }

    /**
     * 关闭连接
     */
    public static boolean close() {
        boolean isClose = false;
        if (resultSet != null) {
            try {
                resultSet.close();
                resultSet = null;
                isClose = true;
            } catch (SQLException e) {
                isClose = false;
                e.printStackTrace();
                System.out.println("关闭结果集发生错误");
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
                pstmt = null;
                isClose = true;
            } catch (SQLException e) {
                isClose = false;
                e.printStackTrace();
                System.out.println("关闭pstmt发生异常");
            }
        }
        if (conn != null) {
            try {
                conn.close();
                conn = null;
                isClose = true;
            } catch (Exception e) {
                isClose = false;
                e.printStackTrace();
                System.out.println("关闭conn发生异常");
            }
        }
        return isClose;
    }

}
