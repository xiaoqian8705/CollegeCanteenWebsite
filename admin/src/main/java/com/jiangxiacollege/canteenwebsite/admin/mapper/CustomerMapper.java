package com.jiangxiacollege.canteenwebsite.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.jiangxiacollege.canteenwebsite.admin.model.Customer;
import com.jiangxiacollege.canteenwebsite.admin.vo.CustomerVO;

@Mapper
public interface  CustomerMapper  extends BaseMapper<Customer>{
    //	 Integer listCount();
//	 User findUserByUsername(String username);
    List<CustomerVO> selectCustomerListPage(Pagination page ,CustomerVO CustomerVO);
}
