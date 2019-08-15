package com.stylefeng.guns.modular.system.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.warpper.DictStationWarpper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.DictStation;
import com.stylefeng.guns.modular.system.service.IDictStationService;

import java.util.List;
import java.util.Map;

/**
 * 站点控制器
 *
 * @author fengshuonan
 * @Date 2019-08-13 17:06:19
 */
@Controller
@RequestMapping("/dictStation")
public class DictStationController extends BaseController {

    private String PREFIX = "/system/dictStation/";

    @Autowired
    private IDictStationService dictStationService;

    /**
     * 跳转到站点首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "dictStation.html";
    }

    /**
     * 跳转到添加站点
     */
    @RequestMapping("/dictStation_add")
    public String dictStationAdd() {
        return PREFIX + "dictStation_add.html";
    }

    /**
     * 跳转到修改站点
     */
    @RequestMapping("/dictStation_update/{dictStationId}")
    public String dictStationUpdate(@PathVariable Integer dictStationId, Model model) {
        DictStation dictStation = dictStationService.selectById(dictStationId);
        model.addAttribute("item",dictStation);
        LogObjectHolder.me().set(dictStation);
        return PREFIX + "dictStation_edit.html";
    }

    /**
     * 获取站点列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        EntityWrapper<DictStation> dictStationEntityWrapper = new EntityWrapper<>();
        if (ToolUtil.isNotEmpty(condition) ) {
            dictStationEntityWrapper.like("name", condition);
        }

        List<Map<String, Object>> list = this.dictStationService.selectMaps(dictStationEntityWrapper);
        return new DictStationWarpper(list).warp();
    }

    /**
     * 新增站点
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(DictStation dictStation) {
        dictStationService.insert(dictStation);
        return SUCCESS_TIP;
    }

    /**
     * 删除站点
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer dictStationId) {
        dictStationService.deleteById(dictStationId);
        return SUCCESS_TIP;
    }

    /**
     * 修改站点
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(DictStation dictStation) {
        dictStationService.updateById(dictStation);
        return SUCCESS_TIP;
    }
    /**
     * 禁用
     */
    @RequestMapping(value = "/disable")
    @ResponseBody
    public Object disable(DictStation dictStation) {
        dictStation.setStatecode(0);
        dictStationService.updateById(dictStation);
        return SUCCESS_TIP;
    }
    /**
     * 启用
     */
    @RequestMapping(value = "/enable")
    @ResponseBody
    public Object enable(DictStation dictStation) {
        dictStation.setStatecode(1);
        dictStationService.updateById(dictStation);
        return SUCCESS_TIP;
    }


    /**
     * 站点详情
     */
    @RequestMapping(value = "/detail/{dictStationId}")
    @ResponseBody
    public Object detail(@PathVariable("dictStationId") Integer dictStationId) {
        return dictStationService.selectById(dictStationId);
    }
}