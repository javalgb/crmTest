package com.yjxxt.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjxxt.crm.base.BaseService;
import com.yjxxt.crm.bean.FoodDetails;
import com.yjxxt.crm.bean.FoodType;
import com.yjxxt.crm.mapper.FoodDetailsMapper;
import com.yjxxt.crm.query.FoodDetailsQuery;
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
public class FoodDetailsService extends BaseService<FoodDetails,Integer> {
    @Resource
    private FoodDetailsMapper foodDetailsMapper;

    public Map<String, Object> queryListByParams(FoodDetailsQuery foodDetailsQuery) {
        Map<String,Object> map=new HashMap<>();
        //实例化分页单位
        PageHelper.startPage(foodDetailsQuery.getPage(), foodDetailsQuery.getLimit());
        //开始分页
        PageInfo<FoodDetails> plist=new PageInfo<>(foodDetailsMapper.selectByParams(foodDetailsQuery));
        //准备数据
        map.put("code",0);
        map.put("msg","success");
        map.put("count",plist.getTotal());
        map.put("data",plist.getList());
        //返回map
        return map;
    }

    public void removeFoodDetailsIds(Integer[] ids) {
        AssertUtil.isTrue(ids==null || ids.length==0,"请选择要删除的数据");
        //删除是否成功
        AssertUtil.isTrue(deleteBatch(ids)!=ids.length,"批量删除失败了");
    }

    public void insertFoodDetails(FoodDetails foodDetails) {
        AssertUtil.isTrue(StringUtils.isBlank(foodDetails.getFoodImg()),"请选择图片");
        AssertUtil.isTrue(StringUtils.isBlank(foodDetails.getFoodName()),"请填写餐品名称");
        AssertUtil.isTrue(StringUtils.isBlank(foodDetails.getFoodTypeName()),"请填写餐品类型");
        AssertUtil.isTrue(StringUtils.isBlank(foodDetails.getFoodIntro()),"请填写餐品介绍");
        AssertUtil.isTrue(foodDetails.getPrice()<=0,"价格参数出错");
        FoodDetails temp=foodDetailsMapper.selectByFoodName(foodDetails.getFoodName());
        AssertUtil.isTrue(temp!=null,"餐品名重复了，添加失败");
        foodDetails.setCreateTime(new Date());
        foodDetails.setUpdateTime(new Date());
        foodDetails.setIsValid(1);
        foodDetails.setFoodImg("/images/"+foodDetails.getFoodImg());
        AssertUtil.isTrue(insertSelective(foodDetails)<1,"添加出错了");
    }

    public void updateFoodDetails(FoodDetails foodDetails) {
        AssertUtil.isTrue(StringUtils.isBlank(foodDetails.getFoodImg()),"请选择图片");
        AssertUtil.isTrue(StringUtils.isBlank(foodDetails.getFoodName()),"请填写餐品名称");
        AssertUtil.isTrue(StringUtils.isBlank(foodDetails.getFoodTypeName()),"请填写餐品类型");
        AssertUtil.isTrue(StringUtils.isBlank(foodDetails.getFoodIntro()),"请填写餐品介绍");
        AssertUtil.isTrue(foodDetails.getPrice()<=0,"价格参数出错");
        foodDetails.setUpdateTime(new Date());
        foodDetails.setFoodImg("/images/"+foodDetails.getFoodImg());
        AssertUtil.isTrue(updateByPrimaryKeySelective(foodDetails)<1,"修改失败了");
    }


    public List<Map<String, Object>> queryFoodNames(String foodTypeName) {
        return foodDetailsMapper.selectFoodNames(foodTypeName);
    }

    public List<Map<String, Object>> queryPriceByfoodName(String foodName) {
        return foodDetailsMapper.selectPriceByFoodName(foodName);
    }

    public List<Map<String, Object>> queryFoodTypes() {
        return foodDetailsMapper.selectFoodTypes();
    }
}
