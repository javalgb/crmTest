package com.yjxxt.crm.controller;

import com.yjxxt.crm.base.BaseController;
import com.yjxxt.crm.base.ResultInfo;
import com.yjxxt.crm.bean.FoodType;
import com.yjxxt.crm.bean.RoomType;
import com.yjxxt.crm.query.FoodTypeQuery;
import com.yjxxt.crm.service.FoodTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RequestMapping("foodType")
@Controller
public class FoodTypeController extends BaseController {
    @Resource
    private FoodTypeService foodTypeService;
    @RequestMapping("index")
    public String index(){
        return "foodType/foodType";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> sayList(FoodTypeQuery foodTypeQuery){
        //调用方法获取数据
        Map<String, Object> map = foodTypeService.queryListByParams(foodTypeQuery);
        //map--json
        //返回目标map
        return  map;
    }

    @RequestMapping("addOrUpdateDialog")
    public String addOrUpdateDialog(Integer id, Model model){
        //判断
        if(id!=null){
            //查询用户信息
            FoodType foodType = foodTypeService.selectByPrimaryKey(id);
            //存储
            model.addAttribute("foodType",foodType);
        }
        return "foodType/add_update";
    }

    @ResponseBody
    @RequestMapping("save")
    public ResultInfo sava(FoodType foodType){
        foodTypeService.insertRoomType(foodType);
        return success("添加成功了");
    }

    @RequestMapping("dels")
    @ResponseBody
    public ResultInfo deletes(Integer[] ids){
        //添加操作
        foodTypeService.removeFoodTypeIds(ids);
        //返回目标对象
        return success("删除成功了");
    }
    @ResponseBody
    @RequestMapping("update")
    public ResultInfo update(FoodType foodType){
        foodTypeService.changeFoodType(foodType);
        return success("修改成功了");
    }
    @RequestMapping("foodTypes")
    @ResponseBody
    public List<Map<String, Object>> foodTypes(){
        return foodTypeService.queryTypes();
    }
}
