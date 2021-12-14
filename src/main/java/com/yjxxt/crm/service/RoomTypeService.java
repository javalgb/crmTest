package com.yjxxt.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjxxt.crm.base.BaseService;
import com.yjxxt.crm.bean.RoomType;
import com.yjxxt.crm.mapper.RoomTypeMapper;
import com.yjxxt.crm.query.RoomTypeQuery;
import com.yjxxt.crm.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoomTypeService extends BaseService<RoomType,Integer> {
    @Autowired(required = false)
    private RoomTypeMapper roomTypeMapper;

    public Map<String, Object> queryListByParams(RoomTypeQuery roomTypeQuery) {
        Map<String,Object> map=new HashMap<>();
        //实例化分页单位
        PageHelper.startPage(roomTypeQuery.getPage(),roomTypeQuery.getLimit());
        //开始分页
        PageInfo<RoomType> plist=new PageInfo<RoomType>(selectByParams(roomTypeQuery));
        //准备数据
        map.put("code",0);
        map.put("msg","success");
        map.put("count",plist.getTotal());
        map.put("data",plist.getList());
        //返回map
        return map;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void insertRoomType(RoomType roomType) {
        AssertUtil.isTrue(StringUtils.isBlank(roomType.getRoomTypeId()),"房间类型编号不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(roomType.getRoomType()),"房间类型不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(roomType.getRoomRemark()),"房间介绍不能为空");
        RoomType temp = roomTypeMapper.selectByRoomTypeId(roomType.getRoomTypeId());
        RoomType temp2=roomTypeMapper.selectByTypeName(roomType.getRoomType());

        AssertUtil.isTrue(temp!=null,"房间类型编号重复");
        AssertUtil.isTrue(temp2!=null,"房间类型重复");
        roomType.setCreateTime(new Date());
        roomType.setUpdateTime(new Date());
        roomType.setIsValid(1);
        AssertUtil.isTrue(insertSelective(roomType)<1,"添加失败");
    }

    public void changeRoomType(RoomType roomType) {
        AssertUtil.isTrue(StringUtils.isBlank(roomType.getRoomTypeId()),"房间类型编号不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(roomType.getRoomType()),"房间类型不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(roomType.getRoomRemark()),"房间介绍不能为空");
        RoomType temp = roomTypeMapper.selectByRoomTypeId(roomType.getRoomTypeId());
        AssertUtil.isTrue(temp!=null,"房间类型编号重复");
        roomType.setUpdateTime(new Date());
        AssertUtil.isTrue(updateByPrimaryKeySelective(roomType)<1,"修改失败");

    }

    public void removeRoomTypeIds(Integer[] ids) {
        AssertUtil.isTrue(ids==null || ids.length==0,"请选择要删除的数据");
        //删除是否成功
        AssertUtil.isTrue(deleteBatch(ids)!=ids.length,"批量删除失败了");
    }

    public List<Map<String, Object>> queryRoomTypes() {
        return roomTypeMapper.selectRoomTypes();
    }
}
