package com.yjxxt.crm.controller;

import com.yjxxt.crm.base.BaseController;
import com.yjxxt.crm.base.ResultInfo;
import com.yjxxt.crm.bean.FoodBooking;
import com.yjxxt.crm.bean.RoomBooking;
import com.yjxxt.crm.query.FoodBookingQuery;
import com.yjxxt.crm.query.RoomBookingQuery;
import com.yjxxt.crm.service.FoodBookingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("foodBooking")
public class FoodBookingController extends BaseController {
    @Resource
    private FoodBookingService foodBookingService;
    @RequestMapping("index")
    public String index(){
        return "foodBooking/foodBooking";
    }


    @RequestMapping("addOrUpdateDialog")
    public String addOrUpdateDialog(Integer id, Model model){
        System.out.println(id);
        //判断
        if(id!=null){
            //查询用户信息
            FoodBooking foodBooking = foodBookingService.selectByPrimaryKey(id);

            System.out.println(foodBooking);
            //存储
            model.addAttribute("foodBooking",foodBooking);
        }
        return "foodBooking/add_update";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> sayList(FoodBookingQuery foodBookingQuery){
        //调用方法获取数据
        Map<String, Object> map = foodBookingService.queryListByParams(foodBookingQuery);
        //map--json
        //返回目标map
        return  map;
    }

    @ResponseBody
    @RequestMapping("save")
    public ResultInfo sava(FoodBooking foodBooking){
        System.out.println(false);
        foodBookingService.insertBooking(foodBooking);
        return success("添加成功了");
    }

    @ResponseBody
    @RequestMapping("update")
    public ResultInfo update(FoodBooking foodBooking){
        foodBookingService.update(foodBooking);
        return success("更新成功了");
    }

    @RequestMapping("dels")
    @ResponseBody
    public ResultInfo deletes(Integer[] ids){
        //添加操作
        foodBookingService.removeIds(ids);
        //返回目标对象
        return success("删除成功了");
    }
}
