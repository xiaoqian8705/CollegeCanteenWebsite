package com.jiangxiacollege.canteenwebsite.customer.service.db;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiangxiacollege.canteenwebsite.customer.common.DataTableResult;
import com.jiangxiacollege.canteenwebsite.customer.common.ResponseBase;
import com.jiangxiacollege.canteenwebsite.customer.table.Address;
import com.jiangxiacollege.canteenwebsite.customer.table.CustomerUserInfo;

public interface AddressService extends IService<Address> {

    ResponseBase addressInfo(String customerId);//列表

    ResponseBase updateAddress(Address address);//改

    ResponseBase insertAddress(Address address);//增

    ResponseBase deleteAddress(String ids);//删

    ResponseBase getAddressById(String id);

    DataTableResult selectAddressListPage(Address address, int start, int length, String orderField, String orderDir);
}
