package com.jiangxiacollege.canteenwebsite.customer.service.db.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiangxiacollege.canteenwebsite.customer.common.ResponseBase;
import com.jiangxiacollege.canteenwebsite.customer.mapper.ProductMapper;
import com.jiangxiacollege.canteenwebsite.customer.service.db.ProductService;
import com.jiangxiacollege.canteenwebsite.customer.table.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

//首页商品
    @Override
    public ResponseBase productList() {
        ResponseBase responseBase = new ResponseBase();
        try {
            LambdaQueryWrapper<Product> lq = new QueryWrapper().lambda();
            lq.eq(Product::getSellerId,1);
            List<Product> list = this.list(lq);
            responseBase.setData(list);

        }catch (Exception e){
            log.error(e.getMessage());
            responseBase.setCode(1);
            responseBase.setMessage("菜品查询列表错误");
        }
        return responseBase;
    }

    //店铺商品列表
    @Override
    public ResponseBase productList(String sellId) {
        ResponseBase responseBase = new ResponseBase();
        try {
            LambdaQueryWrapper<Product> lq = new QueryWrapper().lambda();
            lq.eq(Product::getSellerId,sellId);
            List<Product> list = this.list(lq);
            responseBase.setData(list);

        }catch (Exception e){
            log.error(e.getMessage());
            responseBase.setCode(1);
            responseBase.setMessage("菜品查询列表错误");
        }
        return responseBase;
    }

    //商品详情信息
    @Override
    public ResponseBase productInfo(String productId) {
        ResponseBase responseBase = new ResponseBase();
        try {
            LambdaQueryWrapper<Product> lq = new QueryWrapper().lambda();
            lq.eq(Product::getId,productId);
            Product product = this.getOne(lq);
            responseBase.setData(product);

        }catch (Exception e){
            log.error(e.getMessage());
            responseBase.setCode(1);
            responseBase.setMessage("菜品查询错误");
        }
        return responseBase;
    }

    @Override
    public List<Product> getList(List productIdList) {
        List<Product> list = (List<Product>) this.listByIds(productIdList);
       return list;

    }
}
