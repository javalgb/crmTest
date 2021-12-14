package com.yjxxt.crm.query;

import com.yjxxt.crm.base.BaseQuery;
import lombok.Data;

@Data
public class FoodTypeQuery extends BaseQuery {
    private String foodTypeName;
    private String foodIntro;
}
