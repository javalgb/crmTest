package com.yjxxt.crm.query;

import com.yjxxt.crm.base.BaseQuery;
import lombok.Data;

@Data
public class FoodDetailsQuery extends BaseQuery {
    private String foodName;
    private String foodIntro;
    private String foodTypeName;
}
