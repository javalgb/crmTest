package com.yjxxt.crm.mapper;

import com.yjxxt.crm.base.BaseMapper;
import com.yjxxt.crm.bean.FoodType;

import java.util.List;
import java.util.Map;

public interface FoodTypeMapper extends BaseMapper<FoodType,Integer> {

    FoodType selectByTypeName(String foodTypeName);

    List<Map<String, Object>> selectAllTypes();
}