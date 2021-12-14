package com.yjxxt.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjxxt.crm.base.BaseService;
import com.yjxxt.crm.bean.Permission;
import com.yjxxt.crm.bean.Role;
import com.yjxxt.crm.mapper.ModuleMapper;
import com.yjxxt.crm.mapper.PermissionMapper;
import com.yjxxt.crm.mapper.RoleMapper;
import com.yjxxt.crm.query.RoleQuery;
import com.yjxxt.crm.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoleService extends BaseService<Role,Integer> {
    @Autowired(required = false)
    private RoleMapper roleMapper;

    @Autowired(required = false)
    private PermissionMapper permissionMapper;

    @Autowired(required = false)
    private ModuleMapper moduleMapper;


    /**
     * 根据对应用户id查询对应角色
     *
     * @param userId
     * @return
     */
    public List<Map<String, Object>> selectRoles(Integer userId) {
        return roleMapper.selectRoles(userId);
    }

    /**
     * 角色信息条件分页查询
     *
     * @param query
     * @return
     */
    public Map<String, Object> queryRolesByParams(RoleQuery query) {
        HashMap<String, Object> map = new HashMap<>();
        PageHelper.startPage(query.getPage(), query.getLimit());
        PageInfo<Role> info = new PageInfo<>(roleMapper.selectByParams(query));
        map.put("code", 0);
        map.put("msg", "success");
        map.put("count", info.getTotal());
        map.put("data", info.getList());
        return map;
    }

    /**
     * 一.验证：
     * 1.角色名非空
     * 2.角色名唯一
     * 二.默认参数
     * is_valid=1
     * createDate
     * updateDate
     * 三.添加成功与否
     *
     * @param role
     */
    public void addRole(Role role) {
        //1.角色名非空
        AssertUtil.isTrue(StringUtils.isBlank(role.getRoleName()), "请输入角色名称");
        //2.角色名唯一
        Role temp = roleMapper.selectRoleByName(role.getRoleName());
        AssertUtil.isTrue(temp != null, "角色已经存在");
        //3.设定默认值
        role.setIsValid(1);
        role.setCreateDate(new Date());
        role.setUpdateDate(new Date());
        //4.添加成功与否
        AssertUtil.isTrue(insertHasKey(role) < 1, "添加失败了");

    }

    /**
     * 一.验证：
     * id验证
     * 1.角色名非空
     * 2.角色名唯一
     * 二.默认参数
     * is_valid=1
     * createDate
     * updateDate
     * 三.添加成功与否
     * @param role
     */
    public void changeRole(Role role) {
        //验证当前对象是否存在
        Role temp = roleMapper.selectByPrimaryKey(role.getId());
        AssertUtil.isTrue(temp == null || role.getId()==null, "待修改记录不存在");
        //1.角色名非空
        AssertUtil.isTrue(StringUtils.isBlank(role.getRoleName()), "角色名称不能为空");
        //2.角色名唯一
        Role temp2 = roleMapper.selectRoleByName(role.getRoleName());
        AssertUtil.isTrue(temp2 != null && !(temp2.getId().equals(role.getId())), "角色已经存在");
        //3.设定默认值
        role.setUpdateDate(new Date());
        //4.修改是否成功
        AssertUtil.isTrue(updateByPrimaryKeySelective(role) < 1, "修改失败了");
    }

    /**
     * 删除角色
     * @param role
     */
    public void removeRoleById(Role role) {
        //验证
        AssertUtil.isTrue(role.getId()==null || selectByPrimaryKey(role.getId())==null,"请选择数据");
        //设定默认值
        role.setIsValid(0);
        role.setUpdateDate(new Date());
        //判断是否成功
        AssertUtil.isTrue(roleMapper.updateByPrimaryKeySelective(role)<1,"删除失败了");
    }

    /**
     *  授权：
     *      统计当前角色有多少资源，删除后重新添加
     *
     * @param roleId
     * @param mIDs
     */
    public void addGrant(Integer roleId, Integer[] mIDs) {
        //1.判空
        AssertUtil.isTrue(roleId==null,"请选择授权的角色");
        //2.统计当前角色的资源个数后删除
        int count=permissionMapper.selectCountById(roleId);
        System.out.println(count);
        for (Integer mID : mIDs) {
            System.out.println(mID);
        }
        if(count>0){
            //删除
            AssertUtil.isTrue(permissionMapper.deleteByRoleId(roleId)!=count,"资源分配失败");
        }
        ArrayList<Permission> list = new ArrayList<>();

        if(mIDs!=null && mIDs.length>0){
            for (Integer mID : mIDs) {
                Permission temp = new Permission();
                temp.setRoleId(roleId);
                temp.setModuleId(mID);
                temp.setAclValue(moduleMapper.selectByPrimaryKey(mID).getOptValue());
                temp.setCreateDate(new Date());
                temp.setUpdateDate(new Date());
                list.add(temp);
            }
            //3.使用集合装配数据，批量添加
            AssertUtil.isTrue(permissionMapper.insertBatch(list)!=list.size(),"授权失败");
        }


    }
}