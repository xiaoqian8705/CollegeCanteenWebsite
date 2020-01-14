package com.jiangxiacollege.canteenwebsite.customer.controller;

import com.jiangxiacollege.canteenwebsite.customer.common.ResponseBase;
import com.jiangxiacollege.canteenwebsite.customer.service.db.UserLoginService;
import com.jiangxiacollege.canteenwebsite.customer.table.CustomerUserInfo;
import com.jiangxiacollege.canteenwebsite.customer.utils.JsonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RequestMapping("/login")
@Controller
public class LoginController {


    @Resource
    private UserLoginService userLoginService;


    @RequestMapping(value = "/doRegister",method= RequestMethod.POST)
    @ResponseBody
    public ResponseBase doRegister(@RequestBody CustomerUserInfo customerUserInfo){
      return userLoginService.doRegister(customerUserInfo);

    }

 /*   @RequestMapping(value = "/doRegister",method= RequestMethod.POST)
    @ResponseBody
    public ResponseBase doLogin(@RequestBody String param){
        Map<String, Object> paramMap = JsonUtils.jsonToMap(param);
        return userLoginService.doRegister(paramMap);
    }
*/
}
