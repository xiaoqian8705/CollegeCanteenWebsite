package com.jiangxiacollege.canteenwebsite.admin.model;

import java.sql.Timestamp;

/**
 * 与数据库中的物理表映射，增删改一般都用这个模型实现
 */
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

@TableName("customer_user_info")//数据库对应表名
public class Customer {

    @TableId
    private String id;//@TableId表示主键，与字段名称大小写一致

    private String user_name;//与字段名称大小写一致
    private String password;//与字段名称大小写一致

    private String phone;//与字段名称大小写一致
    private String sex;//与字段名称大小写一致
    private String school;
    private String grade;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUser_name() {
        return user_name;
    }
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getSchool() {
        return school;
    }
    public void setSchool(String school) {
        this.school = school;
    }
    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }


}
