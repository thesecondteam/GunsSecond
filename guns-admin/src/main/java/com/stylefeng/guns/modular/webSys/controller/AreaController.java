package com.stylefeng.guns.modular.webSys.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.modular.webSys.service.IAreaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.Area;
import com.stylefeng.guns.modular.webSys.service.IAreaService;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2019-08-07 11:27:37
 */
@Controller
@RequestMapping("/area")
public class AreaController extends BaseController {

    private String PREFIX = "/WebSys/area/";

    @Autowired
    private IAreaService areaService;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "area.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/area_add")
    public String areaAdd() {
        return PREFIX + "area_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/area_update/{areaId}")
    public String areaUpdate(@PathVariable Integer areaId, Model model) {
        Area area = areaService.selectById(areaId);
        model.addAttribute("item",area);
        LogObjectHolder.me().set(area);
        return PREFIX + "area_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return areaService.selectList(null);
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Area area) {
        areaService.insert(area);
        return SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer areaId) {
        areaService.deleteById(areaId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Area area) {
        areaService.updateById(area);
        return SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{areaId}")
    @ResponseBody
    public Object detail(@PathVariable("areaId") Integer areaId) {
        return areaService.selectById(areaId);
    }
}
