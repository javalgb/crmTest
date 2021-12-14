package com.yjxxt.crm.controller;

import com.yjxxt.crm.base.BaseController;
import com.yjxxt.crm.base.ResultInfo;
import com.yjxxt.crm.bean.FoodDetails;
import com.yjxxt.crm.bean.FoodType;
import com.yjxxt.crm.bean.RoomDetails;
import com.yjxxt.crm.query.FoodDetailsQuery;
import com.yjxxt.crm.query.FoodTypeQuery;
import com.yjxxt.crm.service.FoodDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("foodDetails")
public class FoodDetailsController extends BaseController {
    @Resource
    private FoodDetailsService foodDetailsService;
    @RequestMapping("index")
    public String index(){
        return "foodDetails/foodDetails";
    }

    @RequestMapping("addOrUpdateDialog")
    public String addOrUpdateDialog(Integer id, Model model){
        System.out.println(id);
        //判断
        if(id!=null){
            //查询用户信息
            FoodDetails foodDetails = foodDetailsService.selectByPrimaryKey(id);

            System.out.println(foodDetails);
            //存储
            model.addAttribute("foodDetails",foodDetails);
        }
        return "foodDetails/add_update";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> sayList(FoodDetailsQuery foodDetailsQuery){
        //调用方法获取数据
        Map<String, Object> map = foodDetailsService.queryListByParams(foodDetailsQuery);
        //map--json
        //返回目标map
        return  map;
    }


    @RequestMapping("dels")
    @ResponseBody
    public ResultInfo deletes(Integer[] ids){
        //添加操作
        foodDetailsService.removeFoodDetailsIds(ids);
        //返回目标对象
        return success("删除成功了");
    }

    @ResponseBody
    @RequestMapping("save")
    public ResultInfo sava(FoodDetails foodDetails){
        System.out.println(foodDetails);
        foodDetailsService.insertFoodDetails(foodDetails);
        return success("添加成功了");
    }

    @ResponseBody
    @RequestMapping("update")
    public ResultInfo update(FoodDetails foodDetails){
        foodDetailsService.updateFoodDetails(foodDetails);
        return success("修改成功了");
    }

    @ResponseBody
    @RequestMapping("foodNames")
    public List<Map<String,Object>> findFoodNames(String foodTypeName){
        return foodDetailsService.queryFoodNames(foodTypeName);
    }
    @ResponseBody
    @RequestMapping("findFoodPriceByFoodName")
    public List<Map<String,Object>> findPriceNyName(String foodName){
        return foodDetailsService.queryPriceByfoodName(foodName);
    }

    @ResponseBody
    @RequestMapping("foodTypes")
    public List<Map<String,Object>> findFoodTypes(){
        return foodDetailsService.queryFoodTypes();
    }



}
