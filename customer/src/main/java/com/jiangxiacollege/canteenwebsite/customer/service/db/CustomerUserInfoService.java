package com.jiangxiacollege.canteenwebsite.customer.service.db;

import com.baomidou.mybatisplus.extension.service.IService;

import com.jiangxiacollege.canteenwebsite.customer.common.ResponseBase;
import com.jiangxiacollege.canteenwebsite.customer.table.CustomerUserInfo;

public interface CustomerUserInfoService extends IService<CustomerUserInfo> {

    ResponseBase customerUserInfo(String customerId); //用户中心信息

    ResponseBase updateCustomer(CustomerUserInfo customerUserInfo); //更新客户信息
}
