package com.jiangxiacollege.canteenwebsite.customer.table;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;

@Data
@TableName("customer_user_info")
public class CustomerUserInfo implements Serializable {

    private int id; //id

    private String userName; //用户名

    private String password; //密码

    private String phone; //手机号

    private int sex; //性别 0;女 1：男

    private String school; //学校

    private String grade; //年级


}
