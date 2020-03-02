package com.jiangxiacollege.canteenwebsite.customer.controller;

import com.jiangxiacollege.canteenwebsite.customer.common.DataTableResult;
import com.jiangxiacollege.canteenwebsite.customer.common.ResponseBase;
import com.jiangxiacollege.canteenwebsite.customer.service.db.AddressService;
import com.jiangxiacollege.canteenwebsite.customer.table.Address;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class AddressController {

    @Resource
    private AddressService addressService;

    @RequestMapping("/user_address")
    public  String addressList(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        String  userId =  session.getAttribute("userId").toString();
        List<Address> addressList = (List) addressService.addressInfo(userId).getData();
        model.addAttribute("addressInfo", addressList);
        return "/user_address";
    }

    @RequestMapping("/addressList")
    @ResponseBody
    public DataTableResult list(HttpServletRequest request, Address address) {
        // DataTableResult返回给datatables控件的数据格式
        DataTableResult result = new DataTableResult();
        // 获取分页参数
        int start = Integer.parseInt(request.getParameter("start"));
        int length = Integer.parseInt(request.getParameter("length"));
        // 获取排序字段
        String orderIdx = request.getParameter("order[0][column]");
        // 获取排序字段名
        String orderField = request.getParameter("columns[" + orderIdx + "][name]");
        // 获取排序方式，降序desc或者升序asc
        String orderDir = request.getParameter("order[0][dir]");
        // 调用分页查询方法
        result = addressService.selectAddressListPage(address, start, length, orderField, orderDir);
//		result.setDraw(userVO.getDraw());
        return result;
    }

    @RequestMapping("/insertAddress")
    @ResponseBody
    public ResponseBase insertAddress(HttpServletRequest request,@RequestBody Address address ){
        HttpSession session = request.getSession();
        String  userId =  session.getAttribute("userId").toString();
        address.setCustomerId(Long.parseLong(userId));
        return  addressService.insertAddress( address);
    }

    @RequestMapping("/deleteAddress")
    @ResponseBody
    public ResponseBase deleteAddress(String ids) {
        return addressService.deleteAddress(ids);
    }



    @RequestMapping("/updateAddress")
    @ResponseBody
    public ResponseBase updateAddress(@RequestBody Address address ){

        return  addressService.updateAddress( address);
    }


    /*@RequestMapping("/updateAddress")
    @ResponseBody
    public ResponseBase updateAddress(@RequestBody Address address ){
        return  addressService.updateAddress( address);
    }*/








    }



