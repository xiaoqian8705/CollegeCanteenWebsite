package com.jiangxiacollege.canteenwebsite.customer.common;

import lombok.Data;
import java.io.Serializable;

@Data
public class ResponseBase implements Serializable {
    private Integer code;
    private String message;
    private Object data;
    private PageInfo pageInfo;
}
