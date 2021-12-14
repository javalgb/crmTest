package com.yjxxt.crm.mapper;

import com.yjxxt.crm.base.BaseMapper;
import com.yjxxt.crm.bean.Permission;
import com.yjxxt.crm.dto.TreeDto;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PermissionMapper extends BaseMapper<Permission,Integer> {


    int selectCountById(Integer roleId);

    int deleteByRoleId(Integer roleId);


    List<Integer> selectModulesByRoleId(Integer roleId);

    List<String> selectUserHasRolesHasPermissions(int userId);


    int countPermissionsByModuleId(Integer mid);

    int deletePermissionsByModuleId(Integer mid);
}