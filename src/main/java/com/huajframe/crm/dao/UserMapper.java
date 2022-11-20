package com.huajframe.crm.dao;

import com.huajframe.base.BaseMapper;
import com.huajframe.crm.vo.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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

    /**
     * 查询所有营销人员的id和姓名键值对
     * @return
     */
    @MapKey("")
    List<Map<String, Object>> queryAllSales();
}