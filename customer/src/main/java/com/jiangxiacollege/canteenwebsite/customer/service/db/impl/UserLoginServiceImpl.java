package com.jiangxiacollege.canteenwebsite.customer.service.db.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiangxiacollege.canteenwebsite.customer.common.ResponseBase;
import com.jiangxiacollege.canteenwebsite.customer.mapper.UserLoginMapper;
import com.jiangxiacollege.canteenwebsite.customer.service.db.UserLoginService;
import com.jiangxiacollege.canteenwebsite.customer.table.CustomerUserInfo;
import com.jiangxiacollege.canteenwebsite.customer.utils.MD5Tools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(rollbackFor=Exception.class)
public class UserLoginServiceImpl extends ServiceImpl<UserLoginMapper, CustomerUserInfo> implements UserLoginService {


    @Override
    public ResponseBase doRegister(CustomerUserInfo customerUserInfo) {
        ResponseBase responseBase = new ResponseBase();
        try {
            LambdaQueryWrapper<CustomerUserInfo> lqw = new  QueryWrapper().lambda();
            lqw.eq(!StringUtils.isEmpty(customerUserInfo.getUserName()),CustomerUserInfo::getUserName,customerUserInfo.getUserName());
            CustomerUserInfo  result = this.getOne(lqw);
            if (result!=null){
                responseBase.setCode(1);
                responseBase.setMessage("存在相同用户名");
                return responseBase;
            }
            customerUserInfo.setPassword(MD5Tools.getMD5(customerUserInfo.getPassword()));
            this.save(customerUserInfo);
            responseBase.setCode(0);
        }catch (Exception e){
            log.error(e.getMessage());
            responseBase.setCode(1);
            responseBase.setMessage("新增用户错误");
        }
        return responseBase;
    }
}

