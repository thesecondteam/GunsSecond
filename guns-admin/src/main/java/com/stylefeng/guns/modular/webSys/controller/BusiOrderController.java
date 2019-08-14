package com.stylefeng.guns.modular.webSys.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.util.ToolUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.BusiOrder;
import com.stylefeng.guns.modular.webSys.service.IBusiOrderService;

/**
 * order控制器
 *
 * @author fengshuonan
 * @Date 2019-08-14 10:18:35
 */
@Controller
@RequestMapping("/busiOrder")
public class BusiOrderController extends BaseController {

    private String PREFIX = "/webSys/busiOrder/";

    @Autowired
    private IBusiOrderService busiOrderService;

    /**
     * 跳转到order首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "busiOrder.html";
    }

    /**
     * 跳转到添加order
     */
    @RequestMapping("/busiOrder_add")
    public String busiOrderAdd() {
        return PREFIX + "busiOrder_add.html";
    }

    /**
     * 跳转到修改order
     */
    @RequestMapping("/busiOrder_update/{busiOrderId}")
    public String busiOrderUpdate(@PathVariable Integer busiOrderId, Model model) {
        BusiOrder busiOrder = busiOrderService.selectById(busiOrderId);
        model.addAttribute("item",busiOrder);
        LogObjectHolder.me().set(busiOrder);
        return PREFIX + "busiOrder_edit.html";
    }

    /**
     * 获取order列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        if(ToolUtil.isEmpty(condition)) {
            return busiOrderService.selectList(null);
        }else
        {
            EntityWrapper<BusiOrder>entityWrapper=new EntityWrapper<>();
            Wrapper<BusiOrder>wrapper=entityWrapper.like("ordernumber",condition);
            return busiOrderService.selectList(wrapper);
        }
    }

    /**
     * 新增order
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BusiOrder busiOrder) {
        busiOrderService.insert(busiOrder);
        return SUCCESS_TIP;
    }

    /**
     * 删除order
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer busiOrderId) {
        busiOrderService.deleteById(busiOrderId);
        return SUCCESS_TIP;
    }

    /**
     * 修改order
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BusiOrder busiOrder) {
        busiOrderService.updateById(busiOrder);
        return SUCCESS_TIP;
    }

    /**
     * order详情
     */
    @RequestMapping(value = "/detail/{busiOrderId}")
    @ResponseBody
    public Object detail(@PathVariable("busiOrderId") Integer busiOrderId) {
        return busiOrderService.selectById(busiOrderId);
    }
}
