package com.yjxxt.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjxxt.crm.base.BaseService;
import com.yjxxt.crm.bean.FoodType;
import com.yjxxt.crm.bean.RoomType;
import com.yjxxt.crm.mapper.FoodTypeMapper;
import com.yjxxt.crm.query.FoodTypeQuery;
import com.yjxxt.crm.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FoodTypeService extends BaseService<FoodType,Integer> {
    @Resource
    private FoodTypeMapper foodTypeMapper;

    public Map<String, Object> queryListByParams(FoodTypeQuery foodTypeQuery) {
        Map<String,Object> map=new HashMap<>();
        //实例化分页单位
        PageHelper.startPage(foodTypeQuery.getPage(), foodTypeQuery.getLimit());
        //开始分页
        PageInfo<FoodType> plist=new PageInfo<FoodType>(selectByParams(foodTypeQuery));
        //准备数据
        map.put("code",0);
        map.put("msg","success");
        map.put("count",plist.getTotal());
        map.put("data",plist.getList());
        //返回map
        return map;
    }

    public void insertRoomType(FoodType foodType) {
        AssertUtil.isTrue(StringUtils.isBlank(foodType.getFoodImg()),"餐品图片不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(foodType.getFoodTypeName()),"餐品类型名不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(foodType.getFoodIntro()),"餐品介绍不能为空");
        FoodType temp=foodTypeMapper.selectByTypeName(foodType.getFoodTypeName());
        AssertUtil.isTrue(temp!=null,"菜品类型重复");
        foodType.setFoodImg("/images/"+foodType.getFoodImg());
        foodType.setCreateTime(new Date());
        foodType.setUpdateTime(new Date());
        foodType.setIsValid(1);
        AssertUtil.isTrue(insertSelective(foodType)<1,"添加失败");
    }

    public void removeFoodTypeIds(Integer[] ids) {
        AssertUtil.isTrue(ids==null || ids.length==0,"请选择要删除的数据");
        //删除是否成功
        AssertUtil.isTrue(deleteBatch(ids)!=ids.length,"批量删除失败了");
    }

    public void changeFoodType(FoodType foodType) {
        AssertUtil.isTrue(StringUtils.isBlank(foodType.getFoodImg()),"餐品图片不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(foodType.getFoodTypeName()),"餐品类型名不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(foodType.getFoodIntro()),"餐品介绍不能为空");
        FoodType temp=foodTypeMapper.selectByTypeName(foodType.getFoodTypeName());

        AssertUtil.isTrue(temp!=null&&temp.getFoodImg().equals(foodType.getFoodImg()),"菜品类型重复");
        foodType.setFoodImg("/images/"+foodType.getFoodImg());
        foodType.setUpdateTime(new Date());
        AssertUtil.isTrue(updateByPrimaryKeySelective(foodType)<1,"添加失败");

    }

    public List<Map<String, Object>> queryTypes() {

        return foodTypeMapper.selectAllTypes();
    }
}
