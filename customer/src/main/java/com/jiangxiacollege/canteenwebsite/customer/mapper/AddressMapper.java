package com.jiangxiacollege.canteenwebsite.customer.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiangxiacollege.canteenwebsite.customer.table.Address;
import java.util.List;


public interface AddressMapper extends BaseMapper<Address> {

          List<Address>   selectAddressListPage(Page page, Address address);
        }
