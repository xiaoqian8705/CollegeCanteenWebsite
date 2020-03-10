package com.jiangxiacollege.canteenwebsite.admin.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jiangxiacollege.canteenwebsite.admin.mapper.UserMapper;
import com.jiangxiacollege.canteenwebsite.admin.model.User;
import com.jiangxiacollege.canteenwebsite.admin.util.Convert;
import com.jiangxiacollege.canteenwebsite.admin.util.SnowflakeIdWorker;
import com.jiangxiacollege.canteenwebsite.admin.vo.DataTableResult;
import com.jiangxiacollege.canteenwebsite.admin.vo.UserVO;


@Service
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserMapper userMapper;//注入mapper进行数据操作

	/*@Autowired
	private SellerUserInfoMapper sellerMapper;*/
	/**
	 * 自定义xml形式的查询
	 * @return
	 */
//	public Integer listCount() {
//
//		return userMapper.listCount();
//
//	}
	/**
	 * 自定义xml形式的查询
	 * @param
	 * @return
	 */
//	public User findUserByUsername(String username) {
//		return userMapper.findUserByUsername(username);
//	}

	//对于执行数据修改的方法加上事务处理
	@Transactional
	public int delete(String ids) {
		//ids,逗号隔开的主键
		List<String> listid=Convert.toListStrArray(ids);
		return userMapper.deleteBatchIds(listid);
	}

	public User selectById(String id) {
		//userMapper.selectOne(user),selectOne可以按照其他字段来查询一条记录
		return userMapper.selectById(id);
	}

	public User selectByUser(User user) {
		return userMapper.selectOne(user);
	}

	//对于执行数据修改的方法加上事务处理
	@Transactional
	public int updateById(User user) {
		return userMapper.updateById(user);
	}

	//对于执行数据修改的方法加上事务处理
	@Transactional
	public int insert(User user) {
		//添加雪花主键id
		user.setId(SnowflakeIdWorker.getUUID());
		int n = userMapper.insert(user);
		//密码安全一点的话，不能原文保存，应该用MD5，也可以加盐处理
//		n=1/0; //事务测试
		return n;
	}
	/**
	 * 采用集成的查询方法
	 * @param username
	 * @return
	 */
	public List<User> selectList(String username) {
		EntityWrapper<User> wrapper = new EntityWrapper<User>();
		wrapper.like("username", username);
		return userMapper.selectList(wrapper);
	}

	/**
	 * 分页查询
	 * @param
	 * @return
	 */
	public DataTableResult selectUserListPage(UserVO userVO,int start,int length,String orderField,String orderDir) {
		Page<UserVO> page = null;
		//排序
		if(!StringUtils.isEmpty(orderDir)&&!StringUtils.isEmpty(orderField)) {
			if(orderDir.equals("asc")) {
				page = new Page<>(start/length + 1, length,orderField,true);// 当前页，每页总条数 构造 page 对象
			}else {
				page = new Page<>(start/length + 1, length,orderField,false);// 当前页，每页总条数 构造 page 对象
			}
		}else {
			page = new Page<>(start/length + 1, length,"id",false);//默认id降序
		}
		page.setRecords(userMapper.selectUserListPage(page, userVO));

		DataTableResult result = new DataTableResult();
		result.setRecordsTotal(page.getTotal());
		result.setRecordsFiltered(page.getTotal());
		result.setData(page.getRecords());
		return result;
	}

}
