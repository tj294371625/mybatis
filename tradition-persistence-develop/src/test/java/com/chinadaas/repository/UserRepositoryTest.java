package com.chinadaas.repository;

import com.chinadaas.entity.User;
import org.junit.Test;

import java.sql.SQLException;

/*******************************************************************************
 * - Copyright (c)  2021  chinadaas.com
 * - File Name: UserRepositoryTest
 * - @author: liubc - Initial implementation
 * - Description:
 *
 * - Function List:
 *
 * - History:
 * Date         Author          Modification
 * 2021/6/8      liubc           Create the current class
 *******************************************************************************/
public class UserRepositoryTest {

    @Test
    public void test() throws SQLException, ClassNotFoundException {
        UserRepository userRepository = new UserRepository();
        User user = userRepository.findById(1);
        System.out.println(user);
    }
}
