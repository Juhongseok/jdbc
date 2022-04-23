package com.example.demo.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Query {
    private final String sql;
    public Query(String sql) {
        this.sql = sql;
    }

    public QuerySet getPreparedStatement() {
        try {
            Connection con = JDBCUtil.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            return new QuerySet(con, pstmt);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
