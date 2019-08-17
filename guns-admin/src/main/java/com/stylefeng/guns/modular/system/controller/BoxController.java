package com.stylefeng.guns.modular.system.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.modular.system.warpper.BoxWarpper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.Box;
import com.stylefeng.guns.modular.system.service.IBoxService;

import java.util.List;
import java.util.Map;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2019-08-15 09:19:26
 */
@Controller
@RequestMapping("/box")
public class BoxController extends BaseController {

    private String PREFIX = "/system/box/";

    @Autowired
    private IBoxService boxService;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "box.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/box_add")
    public String boxAdd() {
        return PREFIX + "box_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/box_update/{boxId}")
    public String boxUpdate(@PathVariable Integer boxId, Model model) {
        Box box = boxService.selectById(boxId);
        model.addAttribute("item",box);
        LogObjectHolder.me().set(box);
        return PREFIX + "box_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        List<Map<String, Object>> list;
        list=boxService.selectMaps(null);
        return new BoxWarpper(list).warp() ;
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Box box) {
        boxService.insert(box);
        return SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer boxId) {
        boxService.deleteById(boxId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Box box) {
        boxService.updateById(box);
        return SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{boxId}")
    @ResponseBody
    public Object detail(@PathVariable("boxId") Integer boxId) {
        return boxService.selectById(boxId);
    }
}
