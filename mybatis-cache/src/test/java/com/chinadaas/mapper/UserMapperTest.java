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
    public void test() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User user = mapper.findById(1);

        User user0 = mapper.findById(1);

        System.out.println(user == user0);
    }

    @Test
    public void test0() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User user = mapper.findById(1);

        // zs: 添加一步更新操作，可以看到缓存刷新了
        // zs: 对于commit的代码，都会刷新缓存
        User condition = new User();
        condition.setId(1);
        condition.setUsername("lucy");
        condition.setPassword("123456");
        mapper.update(condition);

        User user0 = mapper.findById(1);

        System.out.println(user == user0);
    }


}
