package com.example.demo.jdbc;

import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class QuerySet {
    private final Connection con;
    private final PreparedStatement pstmt;
    private int index = 1;

    public QuerySet(Connection con, PreparedStatement pstmt) {
        this.con = con;
        this.pstmt = pstmt;
    }

    public QuerySet setStringParameter(String... parameter) {
        for (String param : parameter) {
            try {
                pstmt.setString(this.index++, param);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    public QuerySet setIntegerParameter(int... parameter) {
        for (int param : parameter) {
            try {
                pstmt.setInt(this.index++, param);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    public ResultSet getResultSet() {
        ResultSet rs = null;
        try {
            rs = pstmt.executeQuery();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return rs;
    }

    public int executePreparedStatement() {
        try {
            int result = pstmt.executeUpdate();
            return result;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally{
            close(null);
        }
    }

    public void close(ResultSet rs) {
        System.out.println("close success");
        JDBCUtil.close(con, pstmt, rs);
    }
}
