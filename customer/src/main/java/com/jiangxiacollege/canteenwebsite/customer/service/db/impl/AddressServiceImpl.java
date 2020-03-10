package com.jiangxiacollege.canteenwebsite.customer.service.db.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiangxiacollege.canteenwebsite.customer.common.DataTableResult;
import com.jiangxiacollege.canteenwebsite.customer.common.ResponseBase;
import com.jiangxiacollege.canteenwebsite.customer.mapper.AddressMapper;
import com.jiangxiacollege.canteenwebsite.customer.service.db.AddressService;
import com.jiangxiacollege.canteenwebsite.customer.table.Address;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {

    @Resource
    private  AddressMapper addressMapper;
    @Override
    public ResponseBase addressInfo(String customerId) {
        ResponseBase responseBase = new ResponseBase();
        try {
            LambdaQueryWrapper<Address> lqs = new QueryWrapper().lambda();
            lqs.eq(Address::getCustomerId, customerId);
//                Address address = this.getOne(lqs);
            List<Address> list = this.list(lqs);
            responseBase.setData(list);

        } catch (Exception e) {
            log.error(e.getMessage());
            responseBase.setCode(1);
            responseBase.setMessage("地址信息错误");
        }
        return responseBase;
    }


    @Override
    @Transactional
    public ResponseBase insertAddress(Address address) {
        ResponseBase responseBase = new ResponseBase();
        Boolean flag = this.save(address);
        if (flag){
            responseBase.setCode(0);
        }else {
            responseBase.setCode(1);
            responseBase.setMessage("新增失败");
        }

        return responseBase;
    }
    @Override
    @Transactional
    public ResponseBase deleteAddress(String ids) {
        ResponseBase responseBase = new ResponseBase();
        List listId = Arrays.asList(ids.split(","));
        Boolean flag= this.removeByIds(listId);
        if (flag) {
            responseBase.setCode(0);
        }else {
            responseBase.setCode(1);
            responseBase.setMessage("批量删除失败");
        }
        return responseBase;
    }

    @Override
    @Transactional //事务管理加了就不要TRY -CATCH  不然代码错sql不会回滚
    public ResponseBase updateAddress(Address address) {
        ResponseBase responseBase = new ResponseBase();
        Boolean flag = this.updateById(address);
        if (flag) {
            responseBase.setCode(0);
        }else {
        responseBase.setCode(1);
        responseBase.setMessage("修改失败");
    }
        return responseBase;
    }

    @Override
    public DataTableResult selectAddressListPage(Address address, int start, int length, String orderField, String orderDir) {
        Page<Address> page = null;

        //排序
            page = new Page(start / length + 1, length, true);
            page.setRecords(addressMapper.selectAddressListPage(page, address));
            DataTableResult result = new DataTableResult();
            result.setRecordsTotal(page.getTotal());
            result.setRecordsFiltered(page.getTotal());
            result.setData(page.getRecords());
            return result;
    }
    @Override
    public ResponseBase getAddressById(String id) {
        ResponseBase responseBase = new ResponseBase();
        try {
            LambdaQueryWrapper<Address> lqw = new  QueryWrapper().lambda();
            lqw.eq(Address::getId,id);
            Address post = this.getOne(lqw);
            responseBase.setCode(0);
            responseBase.setData(post);
        }catch (Exception e){
            log.error(e.getMessage());
            responseBase.setCode(1);
            responseBase.setMessage("系统错误");
        }
        return responseBase;
    }






    }













