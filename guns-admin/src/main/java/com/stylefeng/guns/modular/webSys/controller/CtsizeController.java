package com.stylefeng.guns.modular.webSys.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.Ctsize;
import com.stylefeng.guns.modular.webSys.service.ICtsizeService;

/**
 * qwe控制器
 *
 * @author fengshuonan
 * @Date 2019-08-07 11:42:04
 */
@Controller
@RequestMapping("/ctsize")
public class CtsizeController extends BaseController {

    private String PREFIX = "/webSys/ctsize/";

    @Autowired
    private ICtsizeService ctsizeService;

    /**
     * 跳转到qwe首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "ctsize.html";
    }

    /**
     * 跳转到添加qwe
     */
    @RequestMapping("/ctsize_add")
    public String ctsizeAdd() {
        return PREFIX + "ctsize_add.html";
    }

    /**
     * 跳转到修改qwe
     */
    @RequestMapping("/ctsize_update/{ctsizeId}")
    public String ctsizeUpdate(@PathVariable Integer ctsizeId, Model model) {
        Ctsize ctsize = ctsizeService.selectById(ctsizeId);
        model.addAttribute("item",ctsize);
        LogObjectHolder.me().set(ctsize);
        return PREFIX + "ctsize_edit.html";
    }

    /**
     * 获取qwe列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return ctsizeService.selectList(null);
    }

    /**
     * 新增qwe
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Ctsize ctsize) {
        ctsizeService.insert(ctsize);
        return SUCCESS_TIP;
    }

    /**
     * 删除qwe
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer ctsizeId) {
        ctsizeService.deleteById(ctsizeId);
        return SUCCESS_TIP;
    }

    /**
     * 修改qwe
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Ctsize ctsize) {
        ctsizeService.updateById(ctsize);
        return SUCCESS_TIP;
    }

    /**
     * qwe详情
     */
    @RequestMapping(value = "/detail/{ctsizeId}")
    @ResponseBody
    public Object detail(@PathVariable("ctsizeId") Integer ctsizeId) {
        return ctsizeService.selectById(ctsizeId);
    }
}
