package com.example.demo.jdbc;

import java.sql.*;

public class JDBCUtil {

    public static Connection getConnection() {
        try {
            Connection con = DriverManager.getConnection(SessionConst.URL, SessionConst.USERNAME, SessionConst.PASSWORD);
            return con;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void close(Connection con, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
