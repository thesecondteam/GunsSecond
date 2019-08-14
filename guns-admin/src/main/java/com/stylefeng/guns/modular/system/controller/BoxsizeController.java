package com.stylefeng.guns.modular.system.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.warpper.BoxsizeWarpper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.Boxsize;
import com.stylefeng.guns.modular.system.service.IBoxsizeService;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Map;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2019-08-13 15:08:47
 */
@Controller
@RequestMapping("/boxsize")
public class BoxsizeController extends BaseController {

    private String PREFIX = "/system/boxsize/";


    @Autowired
    private IBoxsizeService boxsizeService;


    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "boxsize.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/boxsize_add")
    public String boxsizeAdd() {
        return PREFIX + "boxsize_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/boxsize_update/{boxsizeId}")
    public String boxsizeUpdate(@PathVariable Integer boxsizeId, Model model) {
        Boxsize boxsize = boxsizeService.selectById(boxsizeId);
        model.addAttribute("item",boxsize);
        LogObjectHolder.me().set(boxsize);
        return PREFIX + "boxsize_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        EntityWrapper<Boxsize> boxsizeEntityWrapper=new EntityWrapper<Boxsize>();
        List<Map<String, Object>> list;
        if (ToolUtil.isNotEmpty(condition) ) {
            boxsizeEntityWrapper.like("sizecode", condition).or().like("sizetype",condition);
             list= boxsizeService.selectMaps(boxsizeEntityWrapper);
        }
        else
        {
             list= boxsizeService.selectMaps(null);
        }
        return new BoxsizeWarpper(list).warp();
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Boxsize boxsize) {
        boxsizeService.insert(boxsize);
        return SUCCESS_TIP;
    }

    /**
     * 启用
     */
    @RequestMapping(value = "/enable")
    @ResponseBody
    public Object enable(Boxsize boxsize) {
        boxsize.setStatecode(1);
        boxsizeService.updateById(boxsize);
        return SUCCESS_TIP;
    }
    /**
     * 禁用
     */
    @RequestMapping(value = "/disable")
    @ResponseBody
    public Object disable(Boxsize boxsize) {
        boxsize.setStatecode(0);
        boxsizeService.updateById(boxsize);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Boxsize boxsize) {
        boxsizeService.updateById(boxsize);
        return SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{boxsizeId}")
    @ResponseBody
    public Object detail(@PathVariable("boxsizeId") Integer boxsizeId) {
        return boxsizeService.selectById(boxsizeId);
    }
}
