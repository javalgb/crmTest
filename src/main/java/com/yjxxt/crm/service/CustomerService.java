package com.yjxxt.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjxxt.crm.base.BaseService;
import com.yjxxt.crm.bean.Customer;
import com.yjxxt.crm.bean.FoodDetails;
import com.yjxxt.crm.mapper.CustomerMapper;
import com.yjxxt.crm.query.CustomerQuery;
import com.yjxxt.crm.utils.AssertUtil;
import com.yjxxt.crm.utils.PhoneUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerService extends BaseService<Customer,Integer> {
    @Resource
    private CustomerMapper customerMapper;

    public Map<String, Object> queryListByParams(CustomerQuery customerQuery) {
        Map<String,Object> map=new HashMap<>();
        //实例化分页单位
        PageHelper.startPage(customerQuery.getPage(), customerQuery.getLimit());
        //开始分页
        PageInfo<Customer> plist=new PageInfo<>(customerMapper.selectByParams(customerQuery));
        //准备数据
        map.put("code",0);
        map.put("msg","success");
        map.put("count",plist.getTotal());
        map.put("data",plist.getList());
        //返回map
        return map;
    }

    public void removeCustomerIds(Integer[] ids) {
        AssertUtil.isTrue(ids==null || ids.length==0,"请选择要删除的数据");
        //删除是否成功
        AssertUtil.isTrue(deleteBatch(ids)!=ids.length,"批量删除失败了");
    }

    public void insertCustomer(Customer customer) {
        checkCustomer(customer);
        Customer temp=customerMapper.selectCustomerByName(customer.getcName());


        AssertUtil.isTrue(temp!=null,"用户信息相同");

        customer.setIsValid(1);
        customer.setCreateTime(new Date());
        customer.setUpdateTime(new Date());
        customerMapper.insertSelective(customer);
    }

    private void checkCustomer(Customer customer) {
        AssertUtil.isTrue(StringUtils.isBlank(customer.getcName()),"名称不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(customer.getcAddr()),"地址不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(customer.getcEmai()),"邮箱不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(customer.getcPhone()),"手机号不能为空");
        AssertUtil.isTrue(!PhoneUtil.isMobile(customer.getcPhone()),"手机号格式不正确");
    }

    public void updateCustomer(Customer customer) {
        checkCustomer(customer);

        customer.setUpdateTime(new Date());
        AssertUtil.isTrue(updateByPrimaryKeySelective(customer)<1,"更新失败");

    }

    public Customer selectByName(String customerName) {
        return customerMapper.selectCustomerByName(customerName);
    }
}
