package com.yjxxt.crm.mapper;

import com.yjxxt.crm.base.BaseMapper;
import com.yjxxt.crm.bean.User;
import com.yjxxt.crm.query.UserQuery;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper extends BaseMapper<User,Integer> {
    @Select("select * from t_user")
    List<User> selectALL();

    User selectByName(String username);
    @MapKey("")
    List<Map<String, Object>> selectSales();

    List<User> selectAllByParams(UserQuery query);
}