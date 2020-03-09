package com.jiangxiacollege.canteenwebsite.customer.service.db;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiangxiacollege.canteenwebsite.customer.common.ResponseBase;
import com.jiangxiacollege.canteenwebsite.customer.table.Product;

import java.util.List;

public interface ProductService extends IService<Product> {
    ResponseBase productList( );  //首页菜品展示

    ResponseBase productList(String sellId ); //店铺里商品展示

    ResponseBase productInfo(String productId);   //商品详情页面

    List<Product> getList(List productIdList);   //根据产品idList获取

}
