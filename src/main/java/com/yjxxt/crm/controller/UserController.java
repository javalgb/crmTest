package com.yjxxt.crm.controller;

import com.yjxxt.crm.base.BaseController;
import com.yjxxt.crm.base.ResultInfo;
import com.yjxxt.crm.bean.User;
import com.yjxxt.crm.model.UserModel;
import com.yjxxt.crm.query.UserQuery;
import com.yjxxt.crm.service.UserService;
import com.yjxxt.crm.utils.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {

    @Autowired
    UserService service;
    @ResponseBody
    @RequestMapping("findAll")
    public List<User> findAll(){
        return  service.queryAll();
    }

//    @ResponseBody
    @RequestMapping("toSettingPage")
    public String toSettingPage(HttpServletRequest req){
        //获取用户的ID
        int userId = LoginUserUtil.releaseUserIdFromCookie(req);
        //调用方法
        User user = service.selectByPrimaryKey(userId);
        //存储
        req.setAttribute("user", user);
        //转发
        return "user/setting";
    }

    @ResponseBody
    @RequestMapping("logon")
    public ResultInfo login(User user){
        UserModel model = service.userLogin(user.getUserName(), user.getUserPwd());
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(200);
        resultInfo.setMsg("登录成功");
        resultInfo.setResult(model);
        return resultInfo;
    }

    @RequestMapping("/index")
    public String index(){
        return "user/user";
    }



    @RequestMapping("setting")
    @ResponseBody
    public ResultInfo sayUpdate(User user) {
        ResultInfo resultInfo = new ResultInfo();
        //修改信息
//        System.out.println(user);
        if(!service.updateUser(user)){
            resultInfo.setCode(300);
            resultInfo.setMsg("修改重复");
        }
        //返回目标数据对象
        return resultInfo;
//        return null;
    }

    @ResponseBody
    @RequestMapping("changePwd")
    public ResultInfo changePwd(HttpServletRequest req, String oldPassword, String newPassword, String confirmPwd){
        ResultInfo resultInfo = new ResultInfo();
        System.out.println(oldPassword);
        System.out.println(newPassword);
        System.out.println(confirmPwd);
        //从cookie获取id
        int id = LoginUserUtil.releaseUserIdFromCookie(req);
        System.out.println(id);
//        int id=10;
        //通过id调用service修改密码
        service.updateUserPassword(id,oldPassword,newPassword,confirmPwd);
        return resultInfo;
    }


    @RequestMapping("sales")
    @ResponseBody
    public List<Map<String, Object>> sales(Integer[] ids){
        return service.findSales();
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> list(UserQuery query){
        return service.selectAllByParams(query);
    }

    @RequestMapping("save")
    @ResponseBody
    public ResultInfo save(User user) {
        //用户的添加
        service.addUser(user);
        //返回目标数据对象
        return success("用户添加OK");
    }

    @RequestMapping("login")
    @ResponseBody
    public ResultInfo register(User user) {
        //用户的添加
        service.registerUser(user);
        //返回目标数据对象
        return success("用户添加OK");
    }

    @RequestMapping("addOrUpdatePage")
    public String addOrUpdatePage(Integer id, Model model) {
        if(id!=null){
            User user = service.selectByPrimaryKey(id);
            model.addAttribute("user",user);
        }
        return "user/add_update";
    }

    @RequestMapping("update")
    @ResponseBody
    public ResultInfo update(User user) {
        //用户的添加
        service.changeUser(user);
        System.out.println(user);
        //返回目标数据对象
        return success("用户修改OK");
    }


    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo delete(Integer[] ids) {
        //用户的添加
        service.removeUserIds(ids);
        //返回目标数据对象
        return success("用户删除OK");
    }

}
