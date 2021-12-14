package com.yjxxt.crm.controller;

import com.yjxxt.crm.base.BaseController;
import com.yjxxt.crm.base.ResultInfo;
import com.yjxxt.crm.bean.RoomType;
import com.yjxxt.crm.query.RoomTypeQuery;

import com.yjxxt.crm.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("roomType")
public class RoomTypeController extends BaseController {
    @Autowired
    private RoomTypeService roomTypeService;

    @RequestMapping("index")
    public String index(){
        return "roomType/roomType";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> sayList(RoomTypeQuery roomTypeQuery){
        //调用方法获取数据
        Map<String, Object> map = roomTypeService.queryListByParams(roomTypeQuery);
        //map--json
        //返回目标map
        return  map;
    }
    @RequestMapping("addOrUpdateDialog")
    public String addOrUpdateDialog(Integer id, Model model){
        //判断
        if(id!=null){
            //查询用户信息
            RoomType roomType = roomTypeService.selectByPrimaryKey(id);
            //存储
            model.addAttribute("roomType",roomType);
        }
        return "roomType/add_update";
    }
    @ResponseBody
    @RequestMapping("save")
    public ResultInfo sava(RoomType roomType){
        roomTypeService.insertRoomType(roomType);
        return success("添加成功了");
    }

    @ResponseBody
    @RequestMapping("update")
    public ResultInfo update(RoomType roomType){
        roomTypeService.changeRoomType(roomType);
        return success("修改成功了");
    }

    @RequestMapping("dels")
    @ResponseBody
    public ResultInfo deletes(Integer[] ids){
        //添加操作
        roomTypeService.removeRoomTypeIds(ids);
        //返回目标对象
        return success("删除成功了");
    }
    @RequestMapping("roomTypes")
    @ResponseBody
    public List<Map<String, Object>> roomTypes(){
        //添加操作
       return roomTypeService.queryRoomTypes();
    }
}
