package com.stylefeng.guns.modular.system.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.model.BusiWaybill;
import com.stylefeng.guns.modular.system.service.ITrainService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.service.IBusiWaybillService;


/**
 * 运单管理控制器
 *
 * @author fengshuonan
 * @Date 2019-08-14 11:24:01
 */
@Controller
@RequestMapping("/busiWaybill")
public class BusiWaybillController extends BaseController {

    private String PREFIX = "/system/busiWaybill/";

    @Autowired
    private IBusiWaybillService busiWaybillService;
    @Autowired
    private ITrainService trainService;

    /**
     * 跳转到运单管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "busiWaybill.html";
    }

    /**
     * 跳转到生成运单管理
     */
    @RequestMapping("/busiWaybill_create")
    public String busiWaybillCreate() {
        return PREFIX + "busiWaybill_create.html";
    }

    /**
     * 跳转到添加运单管理
     */
    @RequestMapping("/busiWaybill_add")
    public String busiWaybillAdd() {
        return PREFIX + "busiWaybill_add.html";
    }
    /**
     * 跳转到修改运单管理
     */
    @RequestMapping("/busiWaybill_update/{busiWaybillId}")
    public String busiWaybillUpdate(@PathVariable Integer busiWaybillId, Model model) {
        BusiWaybill busiWaybill = busiWaybillService.selectById(busiWaybillId);
        model.addAttribute("item",busiWaybill);
        LogObjectHolder.me().set(busiWaybill);
        return PREFIX + "busiWaybill_edit.html";
    }

    /**
     * 获取运单管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        if(ToolUtil.isEmpty(condition)) {
            return busiWaybillService.selectList(null);
        }else
        {
            EntityWrapper<BusiWaybill> entityWrapper=new EntityWrapper<>();
            Wrapper<BusiWaybill> wrapper=entityWrapper.like("Waybillid",condition);
            return busiWaybillService.selectList(wrapper);
        }
    }

    /**
     * 新增运单管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BusiWaybill busiWaybill) {
        busiWaybillService.insert(busiWaybill);
        return SUCCESS_TIP;
    }
    /**
     * 生成运单管理
     */
    @RequestMapping(value = "/create")
    @ResponseBody
    public Object create(BusiWaybill busiWaybill) {
        busiWaybillService.insert(busiWaybill);
        return SUCCESS_TIP;
    }
    /**
     * 删除运单管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer busiWaybillId) {
        busiWaybillService.deleteById(busiWaybillId);
        return SUCCESS_TIP;
    }

    /**
     * 修改运单管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BusiWaybill busiWaybill) {
        busiWaybillService.updateById(busiWaybill);
        return SUCCESS_TIP;
    }

    /**
     * 运单管理详情
     */
    @RequestMapping(value = "/detail/{busiWaybillId}")
    @ResponseBody
    public Object detail(@PathVariable("busiWaybillId") Integer busiWaybillId) {
        return busiWaybillService.selectById(busiWaybillId);
    }

}
