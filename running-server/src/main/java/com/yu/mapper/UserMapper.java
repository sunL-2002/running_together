package com.yu.mapper;

import com.yu.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    /**
     * 根据openID来查询用户id
     * @param openid
     * @return
     */
    @Select("select user_id from user where openid = #{openid}")
    Integer getUserIdByOpenid(String openid);

    /**
     * 根据openID来查询用户
     * @param openid
     * @return
     */
    @Select("select * from user where openid = #{openid}")
    User getByOpenid(String openid);

    /**
     * 插入数据
     * @param user
     */
    void insert(User user);
}
