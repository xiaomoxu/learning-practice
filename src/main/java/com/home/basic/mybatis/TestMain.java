package com.home.basic.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * MyBatis测试类
 */
public class TestMain {

    public static void main(String[] args) throws IOException {
        String resouce = "com/home/basic/mybatis/config/conf.xml";
        InputStream is = Resources.getResourceAsStream(resouce);

        // 构建sqlSession工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        // 获取sqlSession
        SqlSession session = sqlSessionFactory.openSession();
        session.getConfiguration().addMapper(UserDao.class);

        User user;

//        try {
//            /**
//             * 第一种方式: 直接执行已映射的 SQL 语句
//             */
//            String statement = "com.home.basic.mybatis.getById";
//            user = session.selectOne(statement, 1);
//            System.out.println(user);
//        } finally {
//            session.close();
//        }

        /**
         * 第二种方式: 执行更清晰和类型安全的代码
         */
        UserDao userDao = session.getMapper(UserDao.class);
        List<User> users = userDao.getAll();
        System.out.println(users.get(0));
    }

}