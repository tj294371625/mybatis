package com.chinadaas.mapper;

import com.chinadaas.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/*******************************************************************************
 * - Copyright (c)  2021  chinadaas.com
 * - File Name: UserMapperTest
 * - @author: liubc - Initial implementation
 * - Description:
 *
 * - Function List:
 *
 * - History:
 * Date         Author          Modification
 * 2021/6/6      liubc           Create the current class
 *******************************************************************************/
public class UserMapperTest {

    private SqlSession sqlSession;

    @Before
    public void init() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession = sqlSessionFactory.openSession(true);
    }


    @Test
    public void findAllTest() throws IOException {

        List<User> users = sqlSession.selectList("com.chinadaas.mapper.UserMapper.findAll");

        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void addTest() {
        User user = new User();
        user.setUsername("liubc");
        user.setPassword("123456");

        sqlSession.insert("com.chinadaas.mapper.UserMapper.add", user);
    }

    @Test
    public void updateTest() {
        User user = new User();
        user.setId(3);
        user.setUsername("naruto");
        user.setPassword("123");

        sqlSession.update("com.chinadaas.mapper.UserMapper.update", user);
    }

    @Test
    public void deleteTest() {
        sqlSession.delete("com.chinadaas.mapper.UserMapper.delete", 3);
    }

    @Test
    public void proxyTest() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        List<User> allUsers = mapper.findAll();
        for (User user : allUsers) {
            System.out.println(user);
        }
    }

    @Test
    public void findByConditionTest() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User condition = new User();
        condition.setId(2);
        condition.setUsername("tom");
        User user = mapper.findByCondition(condition);

        System.out.println(user);
    }

    @Test
    public void findByIdsTest() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        List<User> users = mapper.findByIds(ids);

        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void findById() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User user = mapper.findById(1);

        System.out.println(user);
    }

    @Test
    public void findAllUserRelRoleTest() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        List<User> relRoles = mapper.findAllUserRelRole();
        for (User user : relRoles) {
            System.out.println(user);
        }
    }
}
