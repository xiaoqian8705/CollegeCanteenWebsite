package com.jiangxiacollege.canteenwebsite.admin.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.jiangxiacollege.canteenwebsite.admin.model.Product;
import com.jiangxiacollege.canteenwebsite.admin.vo.ProductVO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {
    List<ProductVO> selectProductListPage(Pagination page , ProductVO ProductVO);
}
