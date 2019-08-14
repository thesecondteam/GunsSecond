package com.stylefeng.guns.modular.system.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.warpper.BoxtypeWarpper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.Boxtype;
import com.stylefeng.guns.modular.system.service.IBoxtypeService;

import java.util.List;
import java.util.Map;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2019-08-14 11:24:33
 */
@Controller
@RequestMapping("/boxtype")
public class BoxtypeController extends BaseController {

    private String PREFIX = "/system/boxtype/";

    @Autowired
    private IBoxtypeService boxtypeService;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "boxtype.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/boxtype_add")
    public String boxtypeAdd() {
        return PREFIX + "boxtype_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/boxtype_update/{boxtypeId}")
    public String boxtypeUpdate(@PathVariable Integer boxtypeId, Model model) {
        Boxtype boxtype = boxtypeService.selectById(boxtypeId);
        model.addAttribute("item",boxtype);
        LogObjectHolder.me().set(boxtype);
        return PREFIX + "boxtype_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition)
    {
        EntityWrapper<Boxtype> boxtypeEntityWrapper=new EntityWrapper<Boxtype>();
        List<Map<String, Object>> list;
        if (ToolUtil.isNotEmpty(condition) ) {
            boxtypeEntityWrapper.like("typecode", condition).or().like("type",condition);
            list= boxtypeService.selectMaps(boxtypeEntityWrapper);
        }
        else
        {
            list= boxtypeService.selectMaps(null);
        }
        return new BoxtypeWarpper(list).warp();
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Boxtype boxtype) {
        boxtypeService.insert(boxtype);
        return SUCCESS_TIP;
    }


    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer boxtypeId) {
        boxtypeService.deleteById(boxtypeId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Boxtype boxtype) {
        boxtypeService.updateById(boxtype);
        return SUCCESS_TIP;
    }

    /**
     * 启用
     */
    @RequestMapping(value = "/enable")
    @ResponseBody
    public Object enable(Boxtype boxtype) {
        boxtype.setStatecode(1);
        boxtypeService.updateById(boxtype);
        return SUCCESS_TIP;
    }
    /**
     * 禁用
     */
    @RequestMapping(value = "/disable")
    @ResponseBody
    public Object disable(Boxtype boxtype) {
        boxtype.setStatecode(0);
        boxtypeService.updateById(boxtype);
        return SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{boxtypeId}")
    @ResponseBody
    public Object detail(@PathVariable("boxtypeId") Integer boxtypeId) {
        return boxtypeService.selectById(boxtypeId);
    }
}
