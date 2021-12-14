package com.yjxxt.crm.query;

import com.yjxxt.crm.base.BaseQuery;
import lombok.Data;

@Data
public class RoomDetailsQuery extends BaseQuery {
    private Integer roomArea;
    private String roomType;
    private String roomIntro;
}
