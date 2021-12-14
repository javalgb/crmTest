package com.yjxxt.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjxxt.crm.base.BaseService;
import com.yjxxt.crm.bean.User;
import com.yjxxt.crm.bean.UserRole;
import com.yjxxt.crm.mapper.UserMapper;
import com.yjxxt.crm.mapper.UserRoleMapper;
import com.yjxxt.crm.model.UserModel;
import com.yjxxt.crm.query.UserQuery;
import com.yjxxt.crm.utils.AssertUtil;
import com.yjxxt.crm.utils.Md5Util;
import com.yjxxt.crm.utils.PhoneUtil;
import com.yjxxt.crm.utils.UserIDBase64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class UserService extends BaseService<User,Integer> {
    @Autowired(required = false)
    UserMapper mapper;

    @Autowired(required = false)
    UserRoleMapper userRoleMapper;

    public List<User> queryAll(){
        return mapper.selectALL();
    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    public UserModel userLogin(String username,String password){
        //1.验证参数
        checkLoginParam(username,password);
        //2.根据用户名查询用户
        User user = mapper.selectByName(username);
        //3. 判断用户是否存在 (用户对象为空，记录不存在，方法结束)
        AssertUtil.isTrue(user==null,"用户不存在或已注销");
        //4. 用户对象不为空（用户存在，校验密码。密码不正确，方法结束）
        checkPassword(password,user.getUserPwd());
        //5. 密码正确（用户登录成功，返回用户的相关信息）

        return buildUserInfo(user);
    }

    private UserModel buildUserInfo(User user) {
        UserModel userModel = new UserModel();
        userModel.setUserIdStr(UserIDBase64.encoderUserID(user.getId()));
        userModel.setTrueName(user.getTrueName());
        userModel.setUserName(user.getUserName());
        return userModel;
    }

    /**
     * 解密密码判断是否正确
     * @param password
     * @param userPwd
     */
    private void checkPassword(String password, String userPwd) {
        //数据库中的密码是加密后的，前台密码加密后与数据库比较
        password= Md5Util.encode(password);
        AssertUtil.isTrue(!password.equals(userPwd),"用户密码不正确");
    }

    /**
     * 用户名和密码校验
     * @param username
     * @param password
     */
    private void checkLoginParam(String username, String password) {
        AssertUtil.isTrue(StringUtils.isBlank(username),"用户名不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(password),"密码不能为空");
    }

    /**
     * 修改密码
     * @param userId
     * @param oldPassword
     * @param newPassword
     * @param confirmPwd
     */
    public void updateUserPassword(Integer userId, String oldPassword, String newPassword, String confirmPwd){
        //用户登录了修改，userId
        User user = mapper.selectByPrimaryKey(userId);
        System.out.println(userId);
        //密码验证
        checkPasswordParams(user,oldPassword, newPassword,confirmPwd);
        //修改密码
        user.setUserPwd(Md5Util.encode(newPassword));
        //确认密码修改是否成功
        AssertUtil.isTrue(mapper.updateByPrimaryKeySelective(user) < 1, "修改失败了");
    }


    /**
     * 修改密码的验证
     *
     * @param user
     * @param oldPassword
     * @param newPassword
     * @param confirmPwd
     */
    private void checkPasswordParams(User user, String oldPassword, String newPassword, String confirmPwd) {
        AssertUtil.isTrue(user == null, "用户未登录或者不存在");
        //原始密码非空
        AssertUtil.isTrue(StringUtils.isBlank(oldPassword), "请输入原始密码");
        //原始密码是否正确
        AssertUtil.isTrue(!(user.getUserPwd().equals(Md5Util.encode(oldPassword))), "原始密码不正确");
        //新密码非空
        AssertUtil.isTrue(StringUtils.isBlank(newPassword), "新密码不能为空");
        //新密码不能和原始密码一致
        AssertUtil.isTrue(newPassword.equals(oldPassword), "新密码和原始密码不能相同");
        //确认密码非空
        AssertUtil.isTrue(StringUtils.isBlank(confirmPwd), "确认密码不能为空");
        //确认密码和新密码一致
        AssertUtil.isTrue(!confirmPwd.equals(newPassword), "确认密码和新密码要一致");
    }

    public boolean updateUser(User user){
        System.out.println(user);
        User temp = mapper.selectByPrimaryKey(user.getId());
        System.out.println(temp);
        boolean flag=false;
        if(!user.getEmail().equals(temp.getEmail()))
            flag=true;
        if(!user.getPhone().equals(temp.getPhone()))
            flag=true;
        if(!user.getTrueName().equals(temp.getTrueName()))
            flag=true;
        if(flag)
            mapper.updateByPrimaryKeySelective(user);
        return flag;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void removeUserIds(Integer[] ids){
        //验证
        AssertUtil.isTrue(ids==null|| ids.length==0,"请选择删除数据");
        //遍历对象
        for (Integer userId: ids) {
            //统计当前用户有多少个角色
            int count=userRoleMapper.countById(userId);
            //删除当前用户的角色
            if(count>0){
                AssertUtil.isTrue( userRoleMapper.deleteByUserId(userId)!=count,"用户角色删除失败");
            }
        }
        //判断删除成功与否
        AssertUtil.isTrue(mapper.deleteBatch(ids)<1,"删除失败了");
    }

    /**
     * 查询分配的销售人员
     * @return
     */
    public List<Map<String,Object>> findSales(){
       return mapper.selectSales();
    }

    /**
     * 用户多条件查询
     * @param query
     * @return
     */
    public Map<String, Object> selectAllByParams(UserQuery query){
        PageHelper.startPage(query.getPage(),query.getLimit());
        PageInfo<User> pageInfo=new PageInfo<>(mapper.selectAllByParams(query));
        HashMap<String, Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    };

    /**
     * 用户注册
     * @param user
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void registerUser(User user) {
        //验证
        checkUser(user.getUserName(), user.getEmail(), user.getPhone());
        //用户名唯一
        User temp = mapper.selectByName(user.getUserName());
        AssertUtil.isTrue(temp != null, "用户名已经存在");
        //设定默认
        user.setIsValid(1);
        user.setCreateDate(new Date());
        user.setUpdateDate(new Date());
        //密码加密
        user.setUserPwd(Md5Util.encode(user.getUserPwd()));
        //验证是否成功
        //AssertUtil.isTrue(insertSelective(user) < 1, "添加失败了");
        AssertUtil.isTrue(insertSelective(user) < 1, "添加失败了");
        //****
    }


    /**
     * 一。验证：
     * 1：用户非空，且唯一
     * 2:邮箱 非空，
     * 3:手机号非空，格式正确
     * 二。设定默认值
     * is_valid=1
     * createDate 系统时间
     * updateDate 系统时间
     * 密码：
     * 123456---加密
     * 三：添加是否成功
     *
     * @param user
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addUser(User user) {
        //验证
        checkUser(user.getUserName(), user.getEmail(), user.getPhone());
        //用户名唯一
        User temp = mapper.selectByName(user.getUserName());
        AssertUtil.isTrue(temp != null, "用户名已经存在");
        //设定默认
        user.setIsValid(1);
        user.setCreateDate(new Date());
        user.setUpdateDate(new Date());
        //密码加密
        user.setUserPwd(Md5Util.encode("123456"));
        //验证是否成功
        AssertUtil.isTrue(insertSelective(user) < 1, "添加失败了");
//        AssertUtil.isTrue(insertHasKey(user) < 1, "添加失败了");
        System.out.println(user.getId()+"<<<"+user.getRoleIds());
        System.out.println(user);
//        //****
        relationUserRole(user.getId(),user.getRoleIds());

    }

    /**
     * @param userName
     * @param email
     * @param phone
     */
    private void checkUser(String userName, String email, String phone) {
        AssertUtil.isTrue(StringUtils.isBlank(userName), "用户名不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(email), "邮箱不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(phone), "手机号不能为空");
        AssertUtil.isTrue(!PhoneUtil.isMobile(phone), "输入合法的手机号");
    }


    /**
     * 一。验证：
     * 当前用户的ID 有否则不能修改
     * 1：用户非空，且唯一
     * 2:邮箱 非空，
     * 3:手机号非空，格式正确
     * 二。设定默认值
     * is_valid=1
     * updateDate 系统时间
     * <p>
     * 三：添加是否成功
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void changeUser(User user) {
        //根据ID获取用户的信息
        User temp = mapper.selectByPrimaryKey(user.getId());
        //判断
        AssertUtil.isTrue(temp == null, "待修改的记录不存在");
        //验证参数
        checkUser(user.getUserName(), user.getEmail(), user.getPhone());
        //修改用户名已经存在问题
        User temp2 = mapper.selectByName(user.getUserName());
        AssertUtil.isTrue(temp2 != null && !(temp2.getId().equals(user.getId())), "用户名称已经存在");
        //设定默认值
        user.setUpdateDate(new Date());
        //判断修改是否成功
        AssertUtil.isTrue(updateByPrimaryKeySelective(user) < 1, "修改失败了");

        System.out.println(user.getId()+">>");
        System.out.println(user.getRoleIds()+">>");
        //
        relationUserRole(user.getId(),user.getRoleIds());
    }

    /**
     * 操作中间表
     * @param userId 用户id
     * @param roleIds 角色id 1,2,4;
     *                统计原来是否有角色
     *                      删除
     *                  新添加角色
     *
     */
    private void relationUserRole(Integer id, String roleIds) {
        //rileIds空判断
        AssertUtil.isTrue(StringUtils.isBlank(roleIds),"请选择角色信息");
        //统计用户id拥有的角色
        int count=userRoleMapper.countById(id);
        //删除当前用户id拥有的角色
        AssertUtil.isTrue(userRoleMapper.deleteByUserId(id)!=count,"用户角色删除出错");
        //根据参数批量批量添加角色
        ArrayList<UserRole> list = new ArrayList<>();
        String[] split = roleIds.split(",");
        for (String s : split) {
            UserRole temp = new UserRole();
            temp.setUserId(id);
            temp.setRoleId(Integer.parseInt(s));
            temp.setCreateDate(new Date());
            temp.setUpdateDate(new Date());
            list.add(temp);
        }
        System.out.println(list);
        AssertUtil.isTrue(userRoleMapper.insertBatch(list)!=list.size(),"用户角色添加失败");
    }



}
