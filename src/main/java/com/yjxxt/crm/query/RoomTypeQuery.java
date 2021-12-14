package com.yjxxt.crm.query;

import com.yjxxt.crm.base.BaseQuery;
import lombok.Data;

@Data
public class RoomTypeQuery extends BaseQuery {
    private String roomType;
    private String roomRemark;
}
