package com.yjxxt.crm.mapper;

import com.yjxxt.crm.base.BaseMapper;
import com.yjxxt.crm.bean.RoomDetails;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface RoomDetailsMapper extends BaseMapper<RoomDetails,Integer> {

    RoomDetails selectByRoomId(String roomId);

    @Select("select * from t_room_details where id=#{id}")
    RoomDetails selectById(Integer id);
    @MapKey("")
    List<Map<String, Object>> selectIds(String roomType);
    @MapKey("")
    List<Map<String, Object>> selectPriceByRoomId(String roomId);

    Integer updateStatusByRoomId(Integer roomId);

    Integer updateStatusByRoomIdZora(Integer roomId);
    @MapKey("")
    List<Map<String, Object>> selectRoomIdsByUsed(String roomType);
}