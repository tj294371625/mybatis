package com.chinadaas.mapper;

import com.chinadaas.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/*******************************************************************************
 * - Copyright (c)  2021  chinadaas.com
 * - File Name: UserDAOTest
 * - @author: liubc - Initial implementation
 * - Description:
 *
 * - Function List:
 *
 * - History:
 * Date         Author          Modification
 * 2021/6/6      liubc           Create the current class
 *******************************************************************************/
public class UserDAOTest {


    @Test
    public void findAllTest() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<User> users = sqlSession.selectList("com.chinadaas.mapper.UserDAO.findAll");

        for (User user : users) {
            System.out.println(user);
        }
    }
}
