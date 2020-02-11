package com.jiangxiacollege.canteenwebsite.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.jiangxiacollege.canteenwebsite.admin.model.User;
import com.jiangxiacollege.canteenwebsite.admin.vo.UserVO;

@Mapper
public interface  UserMapper  extends BaseMapper<User>{
//	 Integer listCount();
//	 User findUserByUsername(String username);
	 List<UserVO> selectUserListPage(Pagination page ,UserVO userVO);
}
