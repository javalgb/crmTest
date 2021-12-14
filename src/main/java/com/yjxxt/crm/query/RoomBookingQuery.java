package com.yjxxt.crm.query;

import com.yjxxt.crm.base.BaseQuery;
import lombok.Data;

@Data
public class RoomBookingQuery extends BaseQuery {
    private String roomId;
    private String customerName;
    private String customerPhone;
}
