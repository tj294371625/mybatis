package com.chinadaas.mapper;

import com.chinadaas.entity.User;

import java.util.List;

/*******************************************************************************
 * - Copyright (c)  2021  chinadaas.com
 * - File Name: UserMapper
 * - @author: liubc - Initial implementation
 * - Description:
 *
 * - Function List:
 *
 * - History:
 * Date         Author          Modification
 * 2021/6/6      liubc           Create the current class
 *******************************************************************************/
public interface UserMapper {

    // 一对多查询
    List<User> findAll();

    // 多对多查询，查询user关联role
    List<User> findAllUserRelRole();

    int add();

    int update();

    int delete();

    User findByCondition(User condition);

    List<User> findByIds(List<Integer> ids);

    User findById(int id);
}
