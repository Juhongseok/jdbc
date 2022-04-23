package com.example.demo;


import com.example.demo.jdbc.Query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        insert();
        select();
        update();
        delete();
    }

    private static void insert() {
        Query query = new Query("insert into member values(?, ?, ?, ?, ?)");
        int resultColumn = query.getPreparedStatement()
                .setStringParameter(Arrays.asList("jinni", "jinni@naver.com", "USER"))
                .setIntegerParameter(Arrays.asList(25, 100000000))
                .executePreparedStatement();

        System.out.println("resultColumn = " + resultColumn);
    }

    private static void update() {
        Query query = new Query("update member set age=? where name=?");
        query.getPreparedStatement()
                .setIntegerParameter(Arrays.asList(20))
                .setStringParameter(Arrays.asList("jinni"))
                .executePreparedStatement();
    }

    private static void delete() {
        new Query("delete from member where name=?")
                .getPreparedStatement()
                .setStringParameter(Arrays.asList("jinni"))
                .executePreparedStatement();
    }

    private static void select() {
        ResultSet rs = new Query("select * from member where name=?")
                .getPreparedStatement()
                .setStringParameter(Arrays.asList("jinni"))
                .getResultSet();

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
    }
}
