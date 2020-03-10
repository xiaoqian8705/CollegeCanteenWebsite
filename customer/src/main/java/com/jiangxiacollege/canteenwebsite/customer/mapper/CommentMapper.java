package com.jiangxiacollege.canteenwebsite.customer.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiangxiacollege.canteenwebsite.customer.table.Comment;
import com.jiangxiacollege.canteenwebsite.customer.vo.CommentVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CommentMapper extends BaseMapper<Comment> {

        @Select("SELECT cm.id,cm.writing,cui.user_name customer   FROM `comment` cm LEFT JOIN customer_user_info cui ON cm.customer_id = cui.id where cm.product_id = #{productId}")
        List<CommentVo> commentInfo(String productId);

        }
