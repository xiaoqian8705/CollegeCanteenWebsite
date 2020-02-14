package com.jiangxiacollege.canteenwebsite.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jiangxiacollege.canteenwebsite.admin.mapper.CustomerMapper;
import com.jiangxiacollege.canteenwebsite.admin.model.Customer;
import com.jiangxiacollege.canteenwebsite.admin.util.Convert;
import com.jiangxiacollege.canteenwebsite.admin.util.SnowflakeIdWorker;
import com.jiangxiacollege.canteenwebsite.admin.vo.DataTableResult;
import com.jiangxiacollege.canteenwebsite.admin.vo.CustomerVO;


@Service
public class CustomerService {


    @Autowired
    private CustomerMapper customerMapper;//注入mapper进行数据操作

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
     * @param username
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
        return customerMapper.deleteBatchIds(listid);
    }

    public Customer selectById(String id) {
        //userMapper.selectOne(user),selectOne可以按照其他字段来查询一条记录
        return customerMapper.selectById(id);
    }

    public Customer selectByCustomer(Customer customer) {
        return customerMapper.selectOne(customer);
    }

    //对于执行数据修改的方法加上事务处理
    @Transactional
    public int updateById(Customer customer) {
        return customerMapper.updateById(customer);
    }

    //对于执行数据修改的方法加上事务处理
    @Transactional
    public int insert(Customer customer) {
        //添加雪花主键id
        customer.setId(SnowflakeIdWorker.getUUID());
        int n = customerMapper.insert(customer);
        //密码安全一点的话，不能原文保存，应该用MD5，也可以加盐处理
//		n=1/0; //事务测试
        return n;
    }
    /**
     * 采用集成的查询方法
     * @param username
     * @return
     */
    public List<Customer> selectList(String user_name) {
        EntityWrapper<Customer> wrapper = new EntityWrapper<Customer>();
        wrapper.like("user_name", user_name);
        return customerMapper.selectList(wrapper);
    }

    /**
     * 分页查询
     * @param user
     * @return
     */
    public DataTableResult selectCustomerListPage(CustomerVO customerVO,int start,int length,String orderField,String orderDir) {
        Page<CustomerVO> page = null;
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
        page.setRecords(customerMapper.selectCustomerListPage(page, customerVO));

        DataTableResult result = new DataTableResult();
        result.setRecordsTotal(page.getTotal());
        result.setRecordsFiltered(page.getTotal());
        result.setData(page.getRecords());
        return result;
    }

}
