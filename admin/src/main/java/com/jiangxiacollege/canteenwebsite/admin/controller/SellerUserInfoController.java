package com.jiangxiacollege.canteenwebsite.admin.controller;
import com.jiangxiacollege.canteenwebsite.admin.model.User;

import com.jiangxiacollege.canteenwebsite.admin.model.SellerUserInfo;

import com.jiangxiacollege.canteenwebsite.admin.model.User;
import com.jiangxiacollege.canteenwebsite.admin.service.SellerUserInfoService;
import com.jiangxiacollege.canteenwebsite.admin.util.SnowflakeIdWorker;
import com.jiangxiacollege.canteenwebsite.admin.vo.DataTableResult;
import com.jiangxiacollege.canteenwebsite.admin.vo.Json;
import com.jiangxiacollege.canteenwebsite.admin.vo.SellerUserInfoVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/SellerUserInfoController")
public class SellerUserInfoController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private String prefix = "admin/sellerUserInfo";// 页面的路径，注意admin前面不要有/

    // 图片存放根路径，从application.yml中读取upload
    @Value("${upload}")
    private String UPLOAD_PATH;

    @Autowired
    private SellerUserInfoService sellerUserInfoService;// 注入业务层的service

    // 未加入@ResponseBody用来返回数据给页面
    @RequestMapping("view")
    public String view(Model model) {
//		String str="个人";
//		model用来回传数据给前端页面
//		setTitle(model, new TitleVo("列表", str+"管理", true,"欢迎进入"+str+"页面", true, false));
        return prefix + "/view";
    }

    // @ResponseBody，直接通过js异步返回数据给页面
    @RequestMapping("list")
    @ResponseBody
    public DataTableResult list(HttpServletRequest request, SellerUserInfoVO sellerUserInfoVO) {
        // DataTableResult返回给datatables控件的数据格式
        int roleId = Integer.parseInt(String.valueOf(request.getSession().getAttribute("roleId")));
        String userId = ((User)request.getSession().getAttribute("user")).getId();
        if(roleId ==1){
            sellerUserInfoVO.setUserId("");
        }else if(roleId ==3){
            sellerUserInfoVO.setUserId(userId);
        }
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
        result = sellerUserInfoService.selectSellerUserInfoListPage(sellerUserInfoVO, start, length, orderField, orderDir);
//		result.setDraw(userVO.getDraw());
        return result;
    }
    // @ResponseBody，直接通过js异步返回数据给页面
    @RequestMapping("insert")
    @ResponseBody
    public Json insert(SellerUserInfo sellerUserInfo) {
        Json j = new Json();
        if (sellerUserInfoService.insert(sellerUserInfo) > 0) {
            j.setSuccess(true);
            j.setMsg("添加成功！");
        } else {
            j.setSuccess(false);
            j.setMsg("添加失败！");
        }
        return j;
    }

    // @ResponseBody，直接通过js异步返回数据给页面
    @RequestMapping("update")
    @ResponseBody
    public Json updateById(SellerUserInfo sellerUserInfo) {
        Json j = new Json();
        if (sellerUserInfoService.updateById(sellerUserInfo) > 0) {
            j.setSuccess(true);
            j.setMsg("修改成功！");
        } else {
            j.setSuccess(false);
            j.setMsg("修改失败！");
        }
        return j;
    }

    // @ResponseBody，直接通过js异步返回数据给页面
    @RequestMapping("select")
    @ResponseBody
    public Json selectById(SellerUserInfo sellerUserInfo) {
        Json j = new Json();
        j.setSuccess(true);
        j.setObj(sellerUserInfoService.selectById(String.valueOf(sellerUserInfo.getId())));
        return j;
    }

    // @ResponseBody，直接通过js异步返回数据给页面
    @RequestMapping("delete")
    @ResponseBody
    public Json delete(HttpServletRequest request) {
        Json j = new Json();
        String ids = request.getParameter("ids");
        if (!StringUtils.isEmpty(ids)) {
            j.setSuccess(true);
            j.setObj("成功删除" + sellerUserInfoService.delete(ids) + "条记录");
        } else {
            j.setSuccess(false);
            j.setMsg("没有需要删除的记录！");
        }
        return j;
    }

    // @ResponseBody，直接通过js异步返回数据给页面
    // @RequestParam("file[]") MultipartFile[] file 多个文件
    @RequestMapping("upload")
    @ResponseBody
    public Json upload(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        Json j = new Json();
        if (!file.isEmpty()) {
            try {
                String originalFilename = file.getOriginalFilename();
                // 随机文件名
                String newFileName = SnowflakeIdWorker.getUUID()
                        + originalFilename.substring(originalFilename.lastIndexOf("."));
                // 上传文件路径
                File upload = new File(UPLOAD_PATH, "images/");
                if (!upload.exists())
                    upload.mkdirs();
                String uploadPath = upload + "\\";
                logger.info("uploadPath = " + uploadPath);
                File uploadfile = new File(uploadPath + newFileName);
                // 将上传文件保存到一个目标文件当中
                file.transferTo(uploadfile);
                j.setSuccess(true);
                j.setObj("/upload/images/" + newFileName);
            } catch (IllegalStateException | IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                j.setSuccess(false);
                j.setObj("上传异常");
            }

        } else {
            j.setSuccess(false);
            j.setObj("上传失败");
        }
        return j;
    }



    /**
     * 注销
     * @param request
     * @param model
     * @return
     */
    // 未加入@ResponseBody用来返回数据给页面
    @RequestMapping("logout")
    public String logout(HttpServletRequest request,Model model) {
        HttpSession session = request.getSession();
        session.removeAttribute("sellerUserInfo");
        return "admin/login";
    }

}
