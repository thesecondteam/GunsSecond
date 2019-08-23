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
import com.stylefeng.guns.modular.system.model.BusiMove;
import com.stylefeng.guns.modular.system.service.IBusiMoveService;

/**
 * 业务操作记录控制器
 *
 * @author fengshuonan
 * @Date 2019-08-22 16:33:05
 */
@Controller
@RequestMapping("/busiMove")
public class BusiMoveController extends BaseController {

    private String PREFIX = "/system/busiMove/";

    @Autowired
    private IBusiMoveService busiMoveService;

    /**
     * 跳转到业务操作记录首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "busiMove.html";
    }

    /**
     * 跳转到添加业务操作记录
     */
    @RequestMapping("/busiMove_add")
    public String busiMoveAdd() {
        return PREFIX + "busiMove_add.html";
    }

    /**
     * 跳转到修改业务操作记录
     */
    @RequestMapping("/busiMove_update/{busiMoveId}")
    public String busiMoveUpdate(@PathVariable Integer busiMoveId, Model model) {
        BusiMove busiMove = busiMoveService.selectById(busiMoveId);
        model.addAttribute("item",busiMove);
        LogObjectHolder.me().set(busiMove);
        return PREFIX + "busiMove_edit.html";
    }

    /**
     * 获取业务操作记录列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return busiMoveService.selectList(null);
    }

    /**
     * 新增业务操作记录
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BusiMove busiMove) {
        busiMoveService.insert(busiMove);
        return SUCCESS_TIP;
    }

    /**
     * 删除业务操作记录
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete() {
        busiMoveService.delete(null);
        return SUCCESS_TIP;
    }

    /**
     * 修改业务操作记录
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BusiMove busiMove) {
        busiMoveService.updateById(busiMove);
        return SUCCESS_TIP;
    }

    /**
     * 业务操作记录详情
     */
    @RequestMapping(value = "/detail/{busiMoveId}")
    @ResponseBody
    public Object detail(@PathVariable("busiMoveId") Integer busiMoveId) {
        return busiMoveService.selectById(busiMoveId);
    }
}
