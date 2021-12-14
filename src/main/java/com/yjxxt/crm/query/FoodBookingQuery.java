package com.yjxxt.crm.query;

import com.yjxxt.crm.base.BaseQuery;
import lombok.Data;

@Data
public class FoodBookingQuery extends BaseQuery {
    private String foodName;
    private String foodTypeName;
    private String orderer;
    private Integer roomId;
}
