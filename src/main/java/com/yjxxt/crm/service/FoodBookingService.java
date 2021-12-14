package com.yjxxt.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjxxt.crm.base.BaseService;
import com.yjxxt.crm.base.ResultInfo;
import com.yjxxt.crm.bean.FoodBooking;
import com.yjxxt.crm.bean.RoomBooking;
import com.yjxxt.crm.query.FoodBookingQuery;
import com.yjxxt.crm.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class FoodBookingService extends BaseService<FoodBooking,Integer> {
    @Resource
    private FoodBookingService foodBookingService;

    public Map<String, Object> queryListByParams(FoodBookingQuery foodBookingQuery) {
        Map<String,Object> map=new HashMap<>();
        //实例化分页单位
        PageHelper.startPage(foodBookingQuery.getPage(), foodBookingQuery.getLimit());
        //开始分页
        PageInfo<FoodBooking> plist=new PageInfo<>(foodBookingService.selectByParams(foodBookingQuery));
        //准备数据
        map.put("code",0);
        map.put("msg","success");
        map.put("count",plist.getTotal());
        map.put("data",plist.getList());
        //返回map
        return map;
    }

    public void insertBooking(FoodBooking foodBooking) {
        AssertUtil.isTrue(StringUtils.isBlank(foodBooking.getFoodName()),"餐品名为空");
        AssertUtil.isTrue(StringUtils.isBlank(foodBooking.getFoodTypeName()),"餐品类型为空");
        AssertUtil.isTrue(StringUtils.isBlank(foodBooking.getOrderer()),"点餐人为空");
        AssertUtil.isTrue(foodBooking.getFoodCount()==null,"点餐份数为空");
        AssertUtil.isTrue(foodBooking.getFoodCount()<0,"点餐份数异常");
        AssertUtil.isTrue(foodBooking.getAllPrice()==null,"点餐价格为空");
        AssertUtil.isTrue(foodBooking.getFoodPrice()==null,"餐品价格为空");
        foodBooking.setBookingTime(new Date());
        foodBooking.setIsValid(1);
        foodBooking.setCreateTime(new Date());
        foodBooking.setUpdateTime(new Date());
        AssertUtil.isTrue(insertSelective(foodBooking)<1,"添加失败");
    }


    public void removeIds(Integer[] ids) {
        AssertUtil.isTrue(ids==null || ids.length==0,"请选择要删除的数据");
        //删除是否成功
        AssertUtil.isTrue(deleteBatch(ids)!=ids.length,"批量删除失败了");
    }

    public void update(FoodBooking foodBooking) {
        AssertUtil.isTrue(StringUtils.isBlank(foodBooking.getFoodName()),"餐品名为空");
        AssertUtil.isTrue(StringUtils.isBlank(foodBooking.getFoodTypeName()),"餐品类型为空");
        AssertUtil.isTrue(StringUtils.isBlank(foodBooking.getOrderer()),"点餐人为空");
        AssertUtil.isTrue(foodBooking.getFoodCount()==null,"点餐份数为空");
        AssertUtil.isTrue(foodBooking.getFoodCount()<0,"点餐份数异常");
        AssertUtil.isTrue(foodBooking.getAllPrice()==null,"点餐价格为空");
        AssertUtil.isTrue(foodBooking.getFoodPrice()==null,"餐品价格为空");
        foodBooking.setBookingTime(new Date());
        foodBooking.setUpdateTime(new Date());
        AssertUtil.isTrue(updateByPrimaryKeySelective(foodBooking)<1,"更新失败了");
    }
}
