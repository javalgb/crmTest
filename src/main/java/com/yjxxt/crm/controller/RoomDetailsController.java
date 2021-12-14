package com.yjxxt.crm.controller;

import com.yjxxt.crm.base.BaseController;
import com.yjxxt.crm.base.ResultInfo;
import com.yjxxt.crm.bean.RoomDetails;
import com.yjxxt.crm.bean.RoomType;
import com.yjxxt.crm.query.RoomDetailsQuery;
import com.yjxxt.crm.query.RoomTypeQuery;
import com.yjxxt.crm.service.RoomDetailsService;
import com.yjxxt.crm.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("roomDetails")
public class RoomDetailsController extends BaseController {
    @Autowired
    private RoomDetailsService roomDetailsService;

    @RequestMapping("index")
    public String index(){
        return "roomDetails/roomDetails";
    }

    @RequestMapping("addOrUpdateDialog")
    public String addOrUpdateDialog(Integer id,Model model){
        System.out.println(id);
        //判断
        if(id!=null){
            //查询用户信息
            RoomDetails roomDetails = roomDetailsService.selectById(id);

            System.out.println(roomDetails);
            //存储
            model.addAttribute("roomDetails",roomDetails);
        }
        return "roomDetails/add_update";
    }
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> sayList(RoomDetailsQuery roomDetailsQuery){
        //调用方法获取数据
        Map<String, Object> map = roomDetailsService.queryListByParams(roomDetailsQuery);
        //map--json
        //返回目标map
        return  map;
    }

    @RequestMapping("save")
    @ResponseBody
    public ResultInfo sayList(RoomDetails roomDetails){
        System.out.println(roomDetails);
        roomDetailsService.addRoom(roomDetails);
        //调用方法获取数据
        return success("添加成功");
    }

    @RequestMapping("dels")
    @ResponseBody
    public ResultInfo deletes(Integer[] ids){
        //添加操作
        roomDetailsService.removeRoomTypeIds(ids);
        //返回目标对象
        return success("删除成功了");
    }

    @ResponseBody
    @RequestMapping("update")
    public ResultInfo update(RoomDetails roomDetails){
        roomDetailsService.changeRoomType(roomDetails);
        return success("修改成功了");
    }

    @RequestMapping("roomIds")
    @ResponseBody
    public List<Map<String,Object>> findIDs(String roomType){
        return roomDetailsService.findIDs(roomType);
    }
    @RequestMapping("roomIdsByUsed")
    @ResponseBody
    public List<Map<String,Object>> findRoomIdsByUsed(String roomType){
        return roomDetailsService.findRoomIdsByUsed(roomType);
    }

    @RequestMapping("findPrice")
    @ResponseBody
    public List<Map<String,Object>> findPrice(String roomId){
        return roomDetailsService.findPrice(roomId);

    }
}
