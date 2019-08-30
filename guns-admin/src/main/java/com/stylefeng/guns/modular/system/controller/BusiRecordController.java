package com.stylefeng.guns.modular.system.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.BusiRecord;
import com.stylefeng.guns.modular.system.service.IBusiRecordService;

/**
 * 操作记录控制器
 *
 * @author fengshuonan
 * @Date 2019-08-29 10:44:04
 */
@Controller
@RequestMapping("/busiRecord")
public class BusiRecordController extends BaseController {

    private String PREFIX = "/system/busiRecord/";

    @Autowired
    private IBusiRecordService busiRecordService;

    /**
     * 跳转到操作记录首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "busiRecord.html";
    }

    /**
     * 跳转到添加操作记录
     */
    @RequestMapping("/busiRecord_add")
    public String busiRecordAdd() {
        return PREFIX + "busiRecord_add.html";
    }

    /**
     * 跳转到修改操作记录
     */
    @RequestMapping("/busiRecord_update/{busiRecordId}")
    public String busiRecordUpdate(@PathVariable Integer busiRecordId, Model model) {
        BusiRecord busiRecord = busiRecordService.selectById(busiRecordId);
        model.addAttribute("item",busiRecord);
        LogObjectHolder.me().set(busiRecord);
        return PREFIX + "busiRecord_edit.html";
    }

    /**
     * 获取操作记录列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return busiRecordService.selectList(null);
    }

    /**
     * 新增操作记录
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BusiRecord busiRecord) {
        busiRecordService.insert(busiRecord);
        return SUCCESS_TIP;
    }

    /**
     * 删除操作记录
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer busiRecordId) {
        busiRecordService.deleteById(busiRecordId);
        return SUCCESS_TIP;
    }

    /**
     * 修改操作记录
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BusiRecord busiRecord) {
        busiRecordService.updateById(busiRecord);
        return SUCCESS_TIP;
    }

    /**
     * 操作记录详情
     */
    @RequestMapping(value = "/detail/{busiRecordId}")
    @ResponseBody
    public Object detail(@PathVariable("busiRecordId") Integer busiRecordId) {
        return busiRecordService.selectById(busiRecordId);
    }
}
