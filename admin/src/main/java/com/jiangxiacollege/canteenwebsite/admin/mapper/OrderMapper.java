package com.jiangxiacollege.canteenwebsite.admin.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.jiangxiacollege.canteenwebsite.admin.model.Order;
import com.jiangxiacollege.canteenwebsite.admin.vo.OrderVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper extends BaseMapper<Order>
{
    List<OrderVO> selectOrderListPage(Pagination page, OrderVO orderVO);
}
