package com.yjxxt.crm.mapper;

import com.yjxxt.crm.base.BaseMapper;
import com.yjxxt.crm.bean.FoodDetails;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface FoodDetailsMapper extends BaseMapper<FoodDetails,Integer> {

    FoodDetails selectByFoodName(String foodName);
    @MapKey("")
    List<Map<String, Object>> selectFoodNames(String foodTypeName);

    List<Map<String, Object>> selectPriceByFoodName(String foodName);

    List<Map<String, Object>> selectFoodTypes();
}