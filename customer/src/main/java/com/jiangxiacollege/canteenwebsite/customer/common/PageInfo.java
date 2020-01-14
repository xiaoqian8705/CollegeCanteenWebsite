package com.jiangxiacollege.canteenwebsite.customer.common;

import lombok.Data;
import java.io.Serializable;

@Data
public class PageInfo implements Serializable {

    private  String totalNum; //数据总数量
    private  String totalPages;//总页数
    private  String pageNo; //第几页
    private  String pageSize; //一页几条

}
