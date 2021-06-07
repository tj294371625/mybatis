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

    List<User> findAll();

    int add();

    int update();

    int delete();

    User findByCondition(User condition);

    List<User> findByIds(List<Integer> ids);

    User findById(int id);
}
