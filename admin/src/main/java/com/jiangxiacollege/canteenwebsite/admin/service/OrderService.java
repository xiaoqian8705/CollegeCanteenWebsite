package com.jiangxiacollege.canteenwebsite.admin.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jiangxiacollege.canteenwebsite.admin.mapper.NoticeMapper;
import com.jiangxiacollege.canteenwebsite.admin.mapper.OrderMapper;
import com.jiangxiacollege.canteenwebsite.admin.model.Notice;
import com.jiangxiacollege.canteenwebsite.admin.model.Order;
import com.jiangxiacollege.canteenwebsite.admin.util.Convert;
import com.jiangxiacollege.canteenwebsite.admin.util.SnowflakeIdWorker;
import com.jiangxiacollege.canteenwebsite.admin.vo.DataTableResult;
import com.jiangxiacollege.canteenwebsite.admin.vo.NoticeVO;
import com.jiangxiacollege.canteenwebsite.admin.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;


@Service
public class OrderService {


    @Autowired
    private OrderMapper orderMapper;//注入mapper进行数据操作



    //对于执行数据修改的方法加上事务处理
    @Transactional
    public int delete(String ids) {
        //ids,逗号隔开的主键
        List<String> listid=Convert.toListStrArray(ids);
        return orderMapper.deleteBatchIds(listid);
    }

    public Order selectById(String id) {
        //noticeMapper.selectOne(notice),selectOne可以按照其他字段来查询一条记录
        return orderMapper.selectById(id);
    }

    public Order selectByOrder(Order order) {
        return orderMapper.selectOne(order);
    }

    //对于执行数据修改的方法加上事务处理
    @Transactional
    public int updateById(Order order) {
        return orderMapper.updateById(order);
    }

    //对于执行数据修改的方法加上事务处理
    @Transactional
    public int insert(Order order) {
        //添加雪花主键id
        order.setId(Long.parseLong(SnowflakeIdWorker.getUUID()));
       // order.setId(SnowflakeIdWorker.getUUID());
        int n = orderMapper.insert(order);
        //密码安全一点的话，不能原文保存，应该用MD5，也可以加盐处理
//		n=1/0; //事务测试
        return n;
    }
    /**
     * 采用集成的查询方法

     */
    public List<Order> selectList(String content) {
        EntityWrapper<Order> wrapper = new EntityWrapper<Order>();
        wrapper.like("content",content);
        return orderMapper.selectList(wrapper);
    }

    /**
     * 分页查询

     */
    public DataTableResult selectOrderListPage(OrderVO orderVO, int start, int length, String orderField, String orderDir) {
        Page<OrderVO> page = null;
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
        page.setRecords(orderMapper.selectOrderListPage(page, orderVO));

        DataTableResult result = new DataTableResult();
        result.setRecordsTotal(page.getTotal());
        result.setRecordsFiltered(page.getTotal());
        result.setData(page.getRecords());
        return result;
    }

}
