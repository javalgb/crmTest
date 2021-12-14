package com.yjxxt.crm.mapper;

import com.yjxxt.crm.base.BaseMapper;
import com.yjxxt.crm.bean.Role;
import org.apache.ibatis.annotations.MapKey;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface RoleMapper extends BaseMapper<Role,Integer> {
    @MapKey("")
    List<Map<String,Object>> selectRoles(Integer userId);

    Role selectRoleByName(String roleName);


}