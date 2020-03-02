package com.jiangxiacollege.canteenwebsite.customer.service.db.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiangxiacollege.canteenwebsite.customer.common.ResponseBase;
import com.jiangxiacollege.canteenwebsite.customer.mapper.SellerUserMapper;
import com.jiangxiacollege.canteenwebsite.customer.service.db.SellerUserService;
import com.jiangxiacollege.canteenwebsite.customer.table.SellerUserInfo;
import org.hibernate.validator.internal.metadata.aggregated.rule.OverridingMethodMustNotAlterParameterConstraints;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerUserServiceImpl extends ServiceImpl<SellerUserMapper, SellerUserInfo> implements SellerUserService {

    //首页商家
    @Override
    public ResponseBase sellerList() {
        ResponseBase responseBase= new ResponseBase();
        try{
            LambdaQueryWrapper<SellerUserInfo> lqs=new QueryWrapper().lambda();
            lqs.in(SellerUserInfo::getId,new Integer[]{0, 1,2,3,4,5,6});
            List<SellerUserInfo> list= this.list(lqs);
            responseBase.setData(list);

        }catch(Exception e){
            log.error(e.getMessage());
            responseBase.setCode(1);
            responseBase.setMessage("商家查询列表错误");
        }
        return responseBase;
    }
//订餐商家列表
    @Override
    public ResponseBase sellerLists() {
        ResponseBase responseBase= new ResponseBase();
        try{
            LambdaQueryWrapper<SellerUserInfo> lqss=new QueryWrapper().lambda();
            lqss.isNotNull(SellerUserInfo::getId);
            List<SellerUserInfo> list= this.list(lqss);
            responseBase.setData(list);

        }catch(Exception e){
            log.error(e.getMessage());
            responseBase.setCode(1);
            responseBase.setMessage("商家查询列表错误");
        }

        return responseBase;
    }
//商家具体信息
    @Override
    public ResponseBase sellerInfo(String sellerId) {
        ResponseBase responseBase= new ResponseBase();
        try{
            LambdaQueryWrapper<SellerUserInfo> lqs=new QueryWrapper().lambda();
            lqs.eq(SellerUserInfo::getId,sellerId);
            SellerUserInfo sellerUserInfo= this.getOne(lqs);
            responseBase.setData(sellerUserInfo);

        }catch(Exception e){
            log.error(e.getMessage());
            responseBase.setCode(1);
            responseBase.setMessage("商家查询信息错误");
        }
        return responseBase;
    }
//搜索商家
    @Override
    public ResponseBase sellerList(String keyWord) {
        ResponseBase responseBase= new ResponseBase();
        try{
            LambdaQueryWrapper<SellerUserInfo> lqss=new QueryWrapper().lambda();
            lqss.like(SellerUserInfo::getShopName,keyWord);
            List<SellerUserInfo> list= this.list(lqss);
            responseBase.setCode(0);
            responseBase.setData(list);

        }catch(Exception e){
            log.error(e.getMessage());
            responseBase.setCode(1);
            responseBase.setMessage("商家查询列表错误");
        }

        return responseBase;
    }


}
