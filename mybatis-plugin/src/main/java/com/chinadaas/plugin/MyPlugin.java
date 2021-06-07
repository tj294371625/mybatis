package com.chinadaas.plugin;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

/*******************************************************************************
 * - Copyright (c)  2021  chinadaas.com
 * - File Name: MyPlugin
 * - @author: liubc - Initial implementation
 * - Description:
 *
 * - Function List:
 *
 * - History:
 * Date         Author          Modification
 * 2021/6/7      liubc           Create the current class
 *******************************************************************************/
@Intercepts({
        @Signature(type = Executor.class,
                method = "query",
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
})
public class MyPlugin implements Interceptor {

    /**
     * 每次执行Executor#query()方法，都要进入该方法进行增强
     *
     * @param invocation
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("对⽅法进⾏了增强....");
        // 执行原方法
        return invocation.proceed();
    }

    /**
     * 使用插件对组件生成⼀个代理，然后放到拦截器链中
     *
     * @param target
     * @return
     */
    @Override
    public Object plugin(Object target) {
        System.out.println("将要包装的⽬标对象：" + target);
        return Plugin.wrap(target, this);
    }

    /**
     * 获取插件配置中的属性
     *
     * @param properties
     */
    @Override
    public void setProperties(Properties properties) {
        System.out.println("插件配置的初始化参数：" + properties);
    }
}
