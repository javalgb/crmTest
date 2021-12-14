package com.yjxxt.crm.mapper;

import com.yjxxt.crm.base.BaseMapper;
import com.yjxxt.crm.bean.Customer;

public interface CustomerMapper extends BaseMapper<Customer,Integer> {

    Customer selectCustomerByName(String cName);


}