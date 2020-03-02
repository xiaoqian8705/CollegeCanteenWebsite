package com.jiangxiacollege.canteenwebsite.customer.service.db.impl;



import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiangxiacollege.canteenwebsite.customer.common.ResponseBase;
import com.jiangxiacollege.canteenwebsite.customer.mapper.CustomerUserInfoMapper;
import com.jiangxiacollege.canteenwebsite.customer.service.db.CustomerUserInfoService;
import com.jiangxiacollege.canteenwebsite.customer.table.CustomerUserInfo;
import org.springframework.stereotype.Service;


@Service
public class CustomerUserInfoServiceImpl extends ServiceImpl<CustomerUserInfoMapper, CustomerUserInfo> implements CustomerUserInfoService {


    @Override
    public ResponseBase customerUserInfo(String customerId) {
        ResponseBase responseBase = new ResponseBase();
        try {
            LambdaQueryWrapper< CustomerUserInfo> lqs = new QueryWrapper().lambda();
            lqs.eq( CustomerUserInfo::getId, customerId);
            CustomerUserInfo customerUserInfo = this.getOne(lqs);
            responseBase.setData(customerUserInfo);

        } catch (Exception e) {
            log.error(e.getMessage());
            responseBase.setCode(1);
            responseBase.setMessage("顾客信息错误");
        }
        return responseBase;
    }

    @Override
    public ResponseBase updateCustomer(CustomerUserInfo customerUserInfo) {
        ResponseBase responseBase = new ResponseBase();
        try {
            Boolean flag = this.updateById(customerUserInfo); //只有当where条件只为ID时用这个就行
            /*LambdaUpdateWrapper<CustomerUserInfo> luw = new UpdateWrapper().lambda();
            luw.eq(CustomerUserInfo::getId,customerId);
            this.update(luw);*/ //除了id条件外还有其它条件 就要用此条件构造器
            if(flag){
                responseBase.setCode(0);
                responseBase.setMessage("顾客信息更新成功");
            }else {
                responseBase.setCode(1);
                responseBase.setMessage("顾客信息更新失败");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            responseBase.setCode(1);
            responseBase.setMessage("顾客信息更新错误");
        }
        return responseBase;
    }


}
