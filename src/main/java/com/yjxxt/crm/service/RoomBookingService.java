package com.yjxxt.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjxxt.crm.base.BaseService;
import com.yjxxt.crm.bean.Customer;
import com.yjxxt.crm.bean.RoomBooking;
import com.yjxxt.crm.mapper.RoomBookingMapper;
import com.yjxxt.crm.query.RoomBookingQuery;
import com.yjxxt.crm.utils.AssertUtil;
import com.yjxxt.crm.utils.PhoneUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoomBookingService extends BaseService<RoomBooking,Integer> {
    @Resource
    private RoomBookingMapper roomBookingMapper;

    @Resource
    private CustomerService customerService;

    @Resource
    private RoomDetailsService roomDetailsService;

    public Map<String, Object> queryListByParams(RoomBookingQuery roomBookingQuery) {
        Map<String,Object> map=new HashMap<>();
        //实例化分页单位
        PageHelper.startPage(roomBookingQuery.getPage(), roomBookingQuery.getLimit());
        //开始分页
        PageInfo<RoomBooking> plist=new PageInfo<>(roomBookingMapper.selectByParams(roomBookingQuery));
        //准备数据
        map.put("code",0);
        map.put("msg","success");
        map.put("count",plist.getTotal());
        map.put("data",plist.getList());
        //返回map
        return map;
    }

    public void insertRoomBooking(RoomBooking roomBooking) {
        AssertUtil.isTrue(roomBooking.getRoomId()==null,"房间号不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(roomBooking.getCustomerName()),"顾客名不能为空");
        Customer temp=customerService.selectByName(roomBooking.getCustomerName());
        AssertUtil.isTrue(temp==null,"入住失败，请先注册会员");
        AssertUtil.isTrue(!PhoneUtil.isMobile(roomBooking.getCustomerPhone()),"手机号输入有误");

        AssertUtil.isTrue(roomBooking.getBookingDay()==null,"入住天数不能为空");
        Date date = new Date();
//        System.out.println(date.getTime()+roomBooking.getBookingDay()*24 * 60 * 60 * 1000);
        long a=date.getTime()+roomBooking.getBookingDay()*24 * 60 * 60 * 1000;
        roomBooking.setBookTime(new Date());
        roomBooking.setEndTime(new Date(a));
        AssertUtil.isTrue(roomBooking.getPrice()==null,"价格不能为空");
        roomBooking.setPrice(roomBooking.getPrice()*roomBooking.getBookingDay());
        roomBooking.setIsValid(1);
        roomBooking.setCreateTime(new Date());
        roomBooking.setUpdateTime(new Date());
        AssertUtil.isTrue(roomDetailsService.updateStatusByRoomId(roomBooking.getRoomId())<1,"修改状态失败");

        AssertUtil.isTrue(insertSelective(roomBooking)<1,"添加失败");

    }


    public void update(RoomBooking roomBooking) {
        AssertUtil.isTrue(roomBooking.getRoomId()==null,"房间号不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(roomBooking.getCustomerName()),"顾客名不能为空");
        Customer temp=customerService.selectByName(roomBooking.getCustomerName());
        AssertUtil.isTrue(temp==null,"修改名称失败，请先注册会员");
        AssertUtil.isTrue(!PhoneUtil.isMobile(roomBooking.getCustomerPhone()),"手机号输入有误");
        AssertUtil.isTrue(roomBooking.getBookingDay()==null,"入住天数不能为空");
        Date date = new Date();
//        System.out.println(date.getTime()+roomBooking.getBookingDay()*24 * 60 * 60 * 1000);
        long a=date.getTime()+roomBooking.getBookingDay()*24 * 60 * 60 * 1000;
        roomBooking.setBookTime(new Date());
        roomBooking.setEndTime(new Date(a));
        AssertUtil.isTrue(roomBooking.getPrice()==null,"价格不能为空");
        roomBooking.setPrice(roomBooking.getPrice()*roomBooking.getBookingDay());
        roomBooking.setIsValid(1);
        roomBooking.setCreateTime(new Date());
        roomBooking.setUpdateTime(new Date());
        AssertUtil.isTrue(roomDetailsService.updateStatusByRoomId(roomBooking.getRoomId())<1,"修改状态失败");
        AssertUtil.isTrue(roomBookingMapper.updateByPrimaryKeySelective(roomBooking)<1,"更新失败");
    }

    public void removeIds(Integer[] ids) {
        AssertUtil.isTrue(ids==null || ids.length==0,"请选择要删除的数据");
        //删除是否成功
        AssertUtil.isTrue(deleteBatch(ids)!=ids.length,"批量删除失败了");

        //改变房间状态
        for (Integer id : ids) {
            RoomBooking temp = selectByPrimaryKey(id);
            AssertUtil.isTrue(roomDetailsService.updateStatusByRoomIdZora(temp.getRoomId())<1,"改变状态失败");
        }
    }

    public List<Map<String, Object>> queryOrderByRoomId(Integer roomId) {
        return roomBookingMapper.selectOrderByRoomId(roomId);
    }
}
