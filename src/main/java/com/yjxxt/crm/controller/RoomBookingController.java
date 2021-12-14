package com.yjxxt.crm.controller;

import com.yjxxt.crm.base.BaseController;
import com.yjxxt.crm.base.ResultInfo;
import com.yjxxt.crm.bean.Customer;
import com.yjxxt.crm.bean.RoomBooking;
import com.yjxxt.crm.query.CustomerQuery;
import com.yjxxt.crm.query.RoomBookingQuery;
import com.yjxxt.crm.service.RoomBookingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
@Controller
@RequestMapping("roomBooking")
public class RoomBookingController extends BaseController {
    @Resource
    private RoomBookingService roomBookingService;

    @RequestMapping("index")
    public String index(){
        return "roomBooking/roomBooking";
    }


    @RequestMapping("addOrUpdateDialog")
    public String addOrUpdateDialog(Integer id, Model model){
        System.out.println(id);
        //判断
        if(id!=null){
            //查询用户信息
            RoomBooking roomBooking = roomBookingService.selectByPrimaryKey(id);

            System.out.println(roomBooking);
            //存储
            model.addAttribute("roomBooking",roomBooking);
        }
        return "roomBooking/add_update";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> sayList(RoomBookingQuery roomBookingQuery){
        //调用方法获取数据
        Map<String, Object> map = roomBookingService.queryListByParams(roomBookingQuery);
        //map--json
        //返回目标map
        return  map;
    }

    @ResponseBody
    @RequestMapping("save")
    public ResultInfo sava(RoomBooking roomBooking){
        System.out.println(roomBooking);
        roomBookingService.insertRoomBooking(roomBooking);
        return success("添加成功了");
    }

    @ResponseBody
    @RequestMapping("update")
    public ResultInfo update(RoomBooking roomBooking){
        roomBookingService.update(roomBooking);
        return success("修改成功了");
    }

    @RequestMapping("dels")
    @ResponseBody
    public ResultInfo deletes(Integer[] ids){
        //添加操作
        roomBookingService.removeIds(ids);
        //返回目标对象
        return success("删除成功了");
    }
    @ResponseBody
    @RequestMapping("findOrder")
    public List<Map<String,Object>> findOrderByRoomId(Integer roomId){
        return roomBookingService.queryOrderByRoomId(roomId);
    }
}
