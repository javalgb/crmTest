package com.yjxxt.crm.controller;

import com.yjxxt.crm.base.BaseController;
import com.yjxxt.crm.bean.User;
import com.yjxxt.crm.mapper.PermissionMapper;
import com.yjxxt.crm.service.PermissionService;
import com.yjxxt.crm.service.UserService;
import com.yjxxt.crm.utils.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.util.List;

@Controller
public class IndexController extends BaseController {
    @Autowired(required = false)
    private UserService service;

    @Autowired
    private PermissionService permissionService;
    @RequestMapping("index")
    public String index(){
        return "index2";
    }

    @RequestMapping("main")
    public String main(HttpServletRequest request){
        //使用工具类查询cookie中的id信息
        int userId = LoginUserUtil.releaseUserIdFromCookie(request);
        //根据id查询用户
        User user = service.selectByPrimaryKey(userId);
        //将用户存入req
        request.setAttribute("user",user);
        //将用户的权限码存到Session
        System.out.println(userId);
        List<String> permissions = permissionService.queryUserHasRolesHasPermissions(userId);
        for (String code:permissions) {
            System.out.println(code+"权限码");
        }
        //将用户的权限存到session作用域
        request.getSession().setAttribute("permissions",permissions);
        return "main";
    }

    @RequestMapping("welcome")
    public String welcome(){
        return "welcome";
    }

    @RequestMapping("user/toPasswordPage")
    public String toPassWordPage(){
        return "/user/password";
    }
}
