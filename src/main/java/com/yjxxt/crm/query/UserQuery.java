package com.yjxxt.crm.query;

import com.yjxxt.crm.base.BaseQuery;
import lombok.Data;

@Data
public class UserQuery extends BaseQuery {
    private String userName;
    private String email;
    private String phone;
}
