package com.jiangxiacollege.canteenwebsite.customer.service.db;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiangxiacollege.canteenwebsite.customer.common.ResponseBase;
import com.jiangxiacollege.canteenwebsite.customer.table.SellerUserInfo;

public interface SellerUserService extends IService<SellerUserInfo> {
    ResponseBase sellerList( ); //首页商家展示
    ResponseBase sellerLists( );  //订餐商家列表
    ResponseBase sellerInfo(String sellerId);  //店铺里商家信息

    ResponseBase sellerList(String keyWord); //搜索店铺
   // ResponseBase detailList( );
}
