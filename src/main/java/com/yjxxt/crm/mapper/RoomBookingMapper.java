package com.yjxxt.crm.mapper;

import com.yjxxt.crm.base.BaseMapper;
import com.yjxxt.crm.base.BaseService;
import com.yjxxt.crm.bean.RoomBooking;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface RoomBookingMapper extends BaseMapper<RoomBooking,Integer> {
    @MapKey("")
    List<Map<String, Object>> selectOrderByRoomId(Integer roomId);
}