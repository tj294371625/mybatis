package com.chinadaas.mapper;

import com.chinadaas.entity.Order;
import com.chinadaas.entity.User;

import java.util.List;

/*******************************************************************************
 * - Copyright (c)  2021  chinadaas.com
 * - File Name: OrderMapper
 * - @author: liubc - Initial implementation
 * - Description:
 *
 * - Function List:
 *
 * - History:
 * Date         Author          Modification
 * 2021/6/7      liubc           Create the current class
 *******************************************************************************/
public interface OrderMapper {

    // 一对一关系查询
    List<Order> findAll();

}
