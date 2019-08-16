package com.stylefeng.guns.modular.system.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.model.BusiWaybill;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.BusiWaybilldet;
import com.stylefeng.guns.modular.system.service.IBusiWaybilldetService;

import java.util.List;
import java.util.Map;

/**
 * 运单详情管理控制器
 *
 * @author fengshuonan
 * @Date 2019-08-14 16:38:15
 */
@Controller
@RequestMapping("/busiWaybilldet")
public class BusiWaybilldetController extends BaseController {

    private String PREFIX = "/system/busiWaybilldet/";

    @Autowired
    private IBusiWaybilldetService busiWaybilldetService;

    /**
     * 跳转到运单详情管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "busiWaybilldet.html";
    }

    /**
     * 跳转到添加运单详情管理
     */
    @RequestMapping("/busiWaybilldet_add")
    public String busiWaybilldetAdd() {
        return PREFIX + "busiWaybilldet_add.html";
    }

    /**
     * 跳转到修改运单详情管理
     */
    @RequestMapping("/busiWaybilldet_update/{busiWaybilldetId}")
    public String busiWaybilldetUpdate(@PathVariable Integer busiWaybilldetId, Model model) {
        BusiWaybilldet busiWaybilldet = busiWaybilldetService.selectById(busiWaybilldetId);
        model.addAttribute("item",busiWaybilldet);
        LogObjectHolder.me().set(busiWaybilldet);
        return PREFIX + "busiWaybilldet_edit.html";
    }

    /**
     * 获取运单详情管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        if(ToolUtil.isEmpty(condition)) {
            return busiWaybilldetService.selectList(null);
        }else
        {
            EntityWrapper<BusiWaybilldet>entityWrapper=new EntityWrapper<>();
            Wrapper<BusiWaybilldet> wrapper=entityWrapper.like("Waybilldetid",condition);
            return busiWaybilldetService.selectList(wrapper);
        }
    }

    /**
     * 新增运单详情管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BusiWaybilldet busiWaybilldet) {
        busiWaybilldetService.insert(busiWaybilldet);
        return SUCCESS_TIP;
    }

    /**
     * 删除运单详情管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer busiWaybilldetId) {
        busiWaybilldetService.deleteById(busiWaybilldetId);
        return SUCCESS_TIP;
    }

    /**
     * 修改运单详情管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BusiWaybilldet busiWaybilldet) {
        busiWaybilldetService.updateById(busiWaybilldet);
        return SUCCESS_TIP;
    }

    /**
     * 运单详情管理详情
     */
    @RequestMapping(value = "/detail/{busiWaybilldetId}")
    @ResponseBody
    public Object detail(@PathVariable("busiWaybilldetId") Integer busiWaybilldetId) {
        return busiWaybilldetService.selectById(busiWaybilldetId);
    }
}
