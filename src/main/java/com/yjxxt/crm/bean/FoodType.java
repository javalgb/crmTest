package com.yjxxt.crm.bean;

import java.util.Date;

public class FoodType {
    private Integer id;

    private String foodTypeName;

    private String foodIntro;

    private String foodImg;

    private Integer isHave;

    private Integer isValid;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFoodTypeName() {
        return foodTypeName;
    }

    public void setFoodTypeName(String foodTypeName) {
        this.foodTypeName = foodTypeName == null ? null : foodTypeName.trim();
    }

    public String getFoodIntro() {
        return foodIntro;
    }

    public void setFoodIntro(String foodIntro) {
        this.foodIntro = foodIntro == null ? null : foodIntro.trim();
    }

    public String getFoodImg() {
        return foodImg;
    }

    public void setFoodImg(String foodImg) {
        this.foodImg = foodImg == null ? null : foodImg.trim();
    }

    public Integer getIsHave() {
        return isHave;
    }

    public void setIsHave(Integer isHave) {
        this.isHave = isHave;
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
}