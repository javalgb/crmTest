package com.yjxxt.crm.query;

import com.yjxxt.crm.base.BaseQuery;
import lombok.Data;

@Data
public class CustomerQuery extends BaseQuery {
    private String cName;
    private String cAddr;
    private String cPhone;
}
