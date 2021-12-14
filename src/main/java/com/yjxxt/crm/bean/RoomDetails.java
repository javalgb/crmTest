package com.yjxxt.crm.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class RoomDetails {
    private Integer id;

    private String roomId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public Integer getRoomArea() {
        return roomArea;
    }

    public void setRoomArea(Integer roomArea) {
        this.roomArea = roomArea;
    }

    public String getRoomIntro() {
        return roomIntro;
    }

    public void setRoomIntro(String roomIntro) {
        this.roomIntro = roomIntro;
    }

    public Integer getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(Integer roomPrice) {
        this.roomPrice = roomPrice;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Integer getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Integer isUsed) {
        this.isUsed = isUsed;
    }

    public Date getBookTime() {
        return bookTime;
    }

    public void setBookTime(Date bookTime) {
        this.bookTime = bookTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getRoomImg() {
        return roomImg;
    }

    public void setRoomImg(String roomImg) {
        this.roomImg = roomImg;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "RoomDetails{" +
                "id=" + id +
                ", roomId='" + roomId + '\'' +
                ", roomArea=" + roomArea +
                ", roomIntro='" + roomIntro + '\'' +
                ", roomPrice=" + roomPrice +
                ", roomType='" + roomType + '\'' +
                ", isUsed=" + isUsed +
                ", bookTime=" + bookTime +
                ", endTime=" + endTime +
                ", roomImg='" + roomImg + '\'' +
                ", isValid=" + isValid +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    private Integer roomArea;

    private String roomIntro;

    private Integer roomPrice;

    private String roomType;

    private Integer isUsed;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date bookTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date endTime;

    private String roomImg;

    private Integer isValid;

    private Date createTime;

    private Date updateTime;


}