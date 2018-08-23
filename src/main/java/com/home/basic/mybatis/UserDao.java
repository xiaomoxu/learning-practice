package com.home.basic.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserDao {
//    @Results({
//            @Result(id = true, column = "id", property = "id"),
//            @Result(column = "total_stock", property = "totalStock"),
//            @Result(column = "current_price", property = "currentPrice"),
//            @Result(column = "belong_to", property = "belongTo")
//    })

    @Select("SELECT * FROM user")
    public List<User> getAll();
}
