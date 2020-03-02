package com.jiangxiacollege.canteenwebsite.admin.model;


import java.io.Serializable;


public class PageInfo implements Serializable {

    private  String totalNum; //数据总数量
    private  String totalPages;//总页数
    private  String pageNo; //第几页
    private  String pageSize; //一页几条

    public String getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(String totalNum) {
        this.totalNum = totalNum;
    }

    public String getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(String totalPages) {
        this.totalPages = totalPages;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }
}
