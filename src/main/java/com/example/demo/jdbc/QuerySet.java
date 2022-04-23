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

    public QuerySet setStringParameter(List<String> parameter) {
        int listIndex = 0;
        int index = this.index;
        for (; this.index < index + parameter.size(); this.index++) {
            try {
                pstmt.setString(this.index, parameter.get(listIndex++));
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
        return this;
    }

    public QuerySet setIntegerParameter(List<Integer> parameter) {
        int listIndex = 0;
        int index = this.index;
        for (; this.index < index + parameter.size(); this.index++) {
            try {
                pstmt.setInt(this.index, parameter.get(listIndex++));
            } catch (SQLException e) {
                throw new IllegalStateException(e);
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
        } finally {
            JDBCUtil.close(con, pstmt, rs);
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
            JDBCUtil.close(con, pstmt, null);
        }
    }
}
