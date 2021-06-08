package com.chinadaas.mapper;

import com.chinadaas.entity.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
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
    public void testPlugin() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User user = mapper.findById(1);
        System.out.println(user);
    }

    @Test
    public void testFindAll() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        PageHelper.startPage(1, 1);
        List<User> allUsers = mapper.findAll();
        for (User user : allUsers) {
            System.out.println(user);
        }

        //其他分⻚的数据
        PageInfo<User> pageInfo = new PageInfo<>(allUsers);
        System.out.println("总条数："+pageInfo.getTotal());
        System.out.println("总⻚数："+pageInfo. getPages ());
        System.out.println("当前⻚："+pageInfo. getPageNum());
        System.out.println("每页显示条数："+pageInfo.getPageSize());
        System.out.println("是否第⼀⻚："+pageInfo.isIsFirstPage());
        System.out.println("是否最后⼀⻚："+pageInfo.isIsLastPage());
    }

}
