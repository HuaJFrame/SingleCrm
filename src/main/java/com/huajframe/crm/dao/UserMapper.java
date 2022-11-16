package com.huajframe.crm.dao;

import com.huajframe.base.BaseMapper;
import com.huajframe.crm.vo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户数据的dao层
 */
@Mapper
public interface UserMapper extends BaseMapper<User,Integer> {

    /**
     * 根据用户名查询用户
     * @param userName
     * @return
     */
    User queryByUserName(@Param("userName") String userName);
}