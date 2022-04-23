package com.example.demo;


import com.example.demo.jdbc.Query;
import com.example.demo.jdbc.QuerySet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
//        insert();
//        select();
        update();
//        delete();
    }

    private static void insert() {
        Query query = new Query("insert into member values(?, ?, ?, ?, ?)");
        int resultColumn = query.getPreparedStatement()
                .setStringParameter("memberA", "memberA@naver.com", "USER")
                .setIntegerParameter(25, 100000000)
                .executePreparedStatement();

        System.out.println("resultColumn = " + resultColumn);
    }

    private static void update() {
        Query query = new Query("update member set age=? where name=?");
        query.getPreparedStatement()
                .setIntegerParameter(20)
                .setStringParameter("memberA")
                .executePreparedStatement();
    }

    private static void delete() {
        new Query("delete from member where name=?")
                .getPreparedStatement()
                .setStringParameter("memberA")
                .executePreparedStatement();
    }

    private static void select() {
        QuerySet query = new Query("select * from member where name=?")
                .getPreparedStatement()
                .setStringParameter("memberA");
        ResultSet rs = query.getResultSet();

        String name = null;
        String email = null;
        String role = null;
        int age = 0;
        int salary = 0;
        try {
            while (rs.next()) {
                name = rs.getString(1);
                email = rs.getString(2);
                role = rs.getString(3);
                age = rs.getInt(4);
                salary = rs.getInt(5);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("name = " + name);
        System.out.println("email = " + email);
        System.out.println("role = " + role);
        System.out.println("age = " + age);
        System.out.println("salary = " + salary);
        query.close(rs);
    }
}
