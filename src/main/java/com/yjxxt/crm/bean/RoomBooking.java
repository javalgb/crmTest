package com.yjxxt.crm.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class RoomBooking {
    private Integer id;

    private Integer roomId;

    private String customerName;

    private String customerPhone;

    private Integer bookingDay;

    private Integer price;

    private Integer isValid;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm",
            timezone = "GMT+8"
    )
    private Date bookTime;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm",
            timezone = "GMT+8"
    )
    private Date endTime;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone == null ? null : customerPhone.trim();
    }

    public Integer getBookingDay() {
        return bookingDay;
    }

    public void setBookingDay(Integer bookingDay) {
        this.bookingDay = bookingDay;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
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
        return "RoomBooking{" +
                "id=" + id +
                ", roomId=" + roomId +
                ", customerName='" + customerName + '\'' +
                ", customerPhone='" + customerPhone + '\'' +
                ", bookingDay=" + bookingDay +
                ", price=" + price +
                ", isValid=" + isValid +
                ", bookTime=" + bookTime +
                ", endTime=" + endTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}