package com.chinadaas.repository;

import com.chinadaas.entity.User;

import java.sql.*;

/*******************************************************************************
 * - Copyright (c)  2021  chinadaas.com
 * - File Name: UserRepository
 * - @author: liubc - Initial implementation
 * - Description:
 *
 * - Function List:
 *
 * - History:
 * Date         Author          Modification
 * 2021/6/8      liubc           Create the current class
 *******************************************************************************/
public class UserRepository {

    public User findById(int id) throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/lagou_mybatis";
        String username = "root";
        String password = "root";

        // 1.加载驱动程序
        Class.forName("com.mysql.jdbc.Driver");
        // 2.获取数据库连接
        Connection connection = DriverManager.getConnection(url, username, password);
        // 3.创建sql陈述
        Statement stmt = connection.createStatement();
        // sql注入
        ResultSet resultSet = stmt.executeQuery("select id, username, password from user where id = " + id);

        // 4.封装结果集
        User user = new User();
        while (resultSet.next()) {
            user.setId(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
        }

        // 4.关闭连接
        connection.close();
        return user;
    }
}
