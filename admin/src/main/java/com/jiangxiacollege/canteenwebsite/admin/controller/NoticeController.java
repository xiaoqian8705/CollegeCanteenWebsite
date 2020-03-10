package com.jiangxiacollege.canteenwebsite.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jiangxiacollege.canteenwebsite.admin.model.Notice;
import com.jiangxiacollege.canteenwebsite.admin.service.NoticeService;
import com.jiangxiacollege.canteenwebsite.admin.vo.DataTableResult;
import com.jiangxiacollege.canteenwebsite.admin.vo.Json;
import com.jiangxiacollege.canteenwebsite.admin.vo.NoticeVO;

@Controller
@RequestMapping("/NoticeController")
public class NoticeController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private String prefix = "admin/notice";// 页面的路径，注意admin前面不要有/


    @Autowired
    private NoticeService noticeService;// 注入业务层的service

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
    public DataTableResult list(HttpServletRequest request, NoticeVO noticeVO) {
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
        result = noticeService.selectNoticeListPage(noticeVO, start, length, orderField, orderDir);
//		result.setDraw(noticeVO.getDraw());
        return result;
    }
    // @ResponseBody，直接通过js异步返回数据给页面
    @RequestMapping("insert")
    @ResponseBody
    public Json insert(Notice notice) {
        Json j = new Json();
        if (noticeService.insert(notice) > 0) {
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
    public Json updateById(Notice notice) {
        Json j = new Json();
        if (noticeService.updateById(notice) > 0) {
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
    public Json selectById(Notice notice) {
        Json j = new Json();
        j.setSuccess(true);
        j.setObj(noticeService.selectById(notice.getId()));
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
            j.setObj("成功删除" + noticeService.delete(ids) + "条记录");
        } else {
            j.setSuccess(false);
            j.setMsg("没有需要删除的记录！");
        }
        return j;
    }


    /**
     * 注销

     */
    // 未加入@ResponseBody用来返回数据给页面
    @RequestMapping("logout")
    public String logout(HttpServletRequest request,Model model) {
        HttpSession session = request.getSession();
        session.removeAttribute("notice");
        return "admin/login";
    }

}
