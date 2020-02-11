package com.jiangxiacollege.canteenwebsite.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.jiangxiacollege.canteenwebsite.admin.model.Role;
import com.jiangxiacollege.canteenwebsite.admin.vo.RoleVO;

@Mapper
public interface  RoleMapper  extends BaseMapper<Role>{
	 List<RoleVO> selectRoleListPage(Pagination page ,RoleVO roleVO);
}
