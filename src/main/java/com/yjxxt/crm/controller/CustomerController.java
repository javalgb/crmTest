package com.yjxxt.crm.controller;

import com.yjxxt.crm.base.BaseController;
import com.yjxxt.crm.base.ResultInfo;
import com.yjxxt.crm.bean.Customer;
import com.yjxxt.crm.bean.FoodDetails;
import com.yjxxt.crm.query.CustomerQuery;
import com.yjxxt.crm.query.FoodDetailsQuery;
import com.yjxxt.crm.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("customer")
public class CustomerController extends BaseController {
    @Resource
    private CustomerService customerService;
    @RequestMapping("index")
    public String index(){
        return "customer/customer";
    }

    @RequestMapping("addOrUpdateDialog")
    public String addOrUpdateDialog(Integer id, Model model){
        System.out.println(id);
        //判断
        if(id!=null){
            //查询用户信息
            Customer customer = customerService.selectByPrimaryKey(id);

            System.out.println(customer);
            //存储
            model.addAttribute("customer",customer);
        }
        return "customer/add_update";
    }


    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> sayList(CustomerQuery customerQuery){
        //调用方法获取数据
        Map<String, Object> map = customerService.queryListByParams(customerQuery);
        //map--json
        //返回目标map
        return  map;
    }

    @RequestMapping("dels")
    @ResponseBody
    public ResultInfo deletes(Integer[] ids){
        //添加操作
        customerService.removeCustomerIds(ids);
        //返回目标对象
        return success("删除成功了");
    }


    @ResponseBody
    @RequestMapping("save")
    public ResultInfo sava(Customer customer){
        System.out.println(customer);
        customerService.insertCustomer(customer);
        return success("添加成功了");
    }

    @ResponseBody
    @RequestMapping("update")
    public ResultInfo update(Customer customer){
        customerService.updateCustomer(customer);
        return success("修改成功了");
    }
}
