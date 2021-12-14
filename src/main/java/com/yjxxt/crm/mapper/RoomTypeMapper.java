package com.yjxxt.crm.mapper;

import com.yjxxt.crm.base.BaseMapper;
import com.yjxxt.crm.bean.RoomType;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface RoomTypeMapper extends BaseMapper<RoomType,Integer> {

    RoomType selectByRoomTypeId(String roomTypeId);
    @MapKey("")
    List<Map<String, Object>> selectRoomTypes();

    RoomType selectByTypeName(String roomType);
}