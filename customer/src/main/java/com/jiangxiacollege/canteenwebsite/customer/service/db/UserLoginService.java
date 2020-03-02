package com.jiangxiacollege.canteenwebsite.customer.service.db;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiangxiacollege.canteenwebsite.customer.common.ResponseBase;
import com.jiangxiacollege.canteenwebsite.customer.table.CustomerUserInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


public interface UserLoginService extends IService<CustomerUserInfo> {

    ResponseBase doRegister(CustomerUserInfo customerUserInfo);

    ResponseBase doLogin(CustomerUserInfo customerUserInfo, HttpServletRequest request);


}
