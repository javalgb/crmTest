package com.yjxxt.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjxxt.crm.base.BaseService;
import com.yjxxt.crm.bean.RoomDetails;
import com.yjxxt.crm.bean.RoomType;
import com.yjxxt.crm.mapper.RoomDetailsMapper;
import com.yjxxt.crm.query.RoomDetailsQuery;
import com.yjxxt.crm.query.RoomTypeQuery;
import com.yjxxt.crm.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoomDetailsService extends BaseService<RoomDetails,Integer> {
    @Autowired(required = false)
    private RoomDetailsMapper roomDetailsMapper;

    public Map<String, Object> queryListByParams(RoomDetailsQuery roomDetailsQuery) {
        Map<String,Object> map=new HashMap<>();
        //实例化分页单位
        PageHelper.startPage(roomDetailsQuery.getPage(),roomDetailsQuery.getLimit());
        //开始分页
        PageInfo<RoomDetails> plist=new PageInfo<>(selectByParams(roomDetailsQuery));
        //准备数据
        map.put("code",0);
        map.put("msg","success");
        map.put("count",plist.getTotal());
        map.put("data",plist.getList());
        //返回map
        return map;
    }

    public void addRoom(RoomDetails roomDetails) {
        AssertUtil.isTrue(roomDetails.getRoomArea()<0,"房间大小有问题");
        AssertUtil.isTrue(roomDetails.getRoomPrice()<0,"房间价格有问题");
        AssertUtil.isTrue(StringUtils.isBlank(roomDetails.getRoomIntro()),"房间介绍有问题");
        AssertUtil.isTrue(StringUtils.isBlank(roomDetails.getRoomType()),"房间类型有问题");
        AssertUtil.isTrue(StringUtils.isBlank(roomDetails.getRoomImg()),"房间图片有问题");
        RoomDetails temp=roomDetailsMapper.selectByRoomId(roomDetails.getRoomId());
        AssertUtil.isTrue(temp!=null,"房间已存在");
        roomDetails.setIsValid(1);
        roomDetails.setRoomImg("/images/"+roomDetails.getRoomImg());
        roomDetails.setCreateTime(new Date());
        roomDetails.setUpdateTime(new Date());
        AssertUtil.isTrue(insertSelective(roomDetails)<1,"添加失败");
    }

    public RoomDetails selectById(Integer id) {
        return roomDetailsMapper.selectById(id);
    }

    public void removeRoomTypeIds(Integer[] ids) {
        AssertUtil.isTrue(ids==null || ids.length==0,"请选择要删除的数据");
        //删除是否成功
        AssertUtil.isTrue(deleteBatch(ids)!=ids.length,"批量删除失败了");
    }

    public void changeRoomType(RoomDetails roomDetails) {
        AssertUtil.isTrue(roomDetails.getRoomArea()<0,"房间大小有问题");
        AssertUtil.isTrue(roomDetails.getRoomPrice()<0,"房间价格有问题");
        AssertUtil.isTrue(StringUtils.isBlank(roomDetails.getRoomIntro()),"房间介绍有问题");
        AssertUtil.isTrue(StringUtils.isBlank(roomDetails.getRoomType()),"房间类型有问题");
        AssertUtil.isTrue(StringUtils.isBlank(roomDetails.getRoomImg()),"房间图片有问题");
        System.out.println(roomDetails);
        if(StringUtils.isBlank(roomDetails.getRoomImg())){
            RoomDetails temp=roomDetailsMapper.selectByRoomId(roomDetails.getRoomId());
            roomDetails.setRoomImg(temp.getRoomImg());
        }else {
            roomDetails.setRoomImg("/images/"+roomDetails.getRoomImg());
        }
        roomDetails.setUpdateTime(new Date());
        AssertUtil.isTrue(updateByPrimaryKeySelective(roomDetails)<1,"添加失败");
    }

    public List<Map<String, Object>> findIDs(String roomType) {
        return roomDetailsMapper.selectIds(roomType);

    }

    public List<Map<String, Object>> findPrice(String roomId) {
        return roomDetailsMapper.selectPriceByRoomId(roomId);
    }

    public Integer updateStatusByRoomId(Integer roomId) {
        return roomDetailsMapper.updateStatusByRoomId(roomId);
    }

    public Integer updateStatusByRoomIdZora(Integer roomId) {
        return roomDetailsMapper.updateStatusByRoomIdZora(roomId);
    }

    public List<Map<String, Object>> findRoomIdsByUsed(String roomType) {
        return roomDetailsMapper.selectRoomIdsByUsed(roomType);
    }
}
