package com.stylefeng.guns.modular.system.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.model.Harbour;
import com.stylefeng.guns.modular.system.warpper.ShipWarpper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.Ship;
import com.stylefeng.guns.modular.system.service.IShipService;

import java.util.List;
import java.util.Map;

/**
 * 船舶控制器
 *
 * @author fengshuonan
 * @Date 2019-08-14 15:33:03
 */
@Controller
@RequestMapping("/ship")
public class ShipController extends BaseController {

    private String PREFIX = "/system/ship/";

    @Autowired
    private IShipService shipService;

    /**
     * 跳转到船舶首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "ship.html";
    }

    /**
     * 跳转到添加船舶
     */
    @RequestMapping("/ship_add")
    public String shipAdd() {
        return PREFIX + "ship_add.html";
    }

    /**
     * 跳转到修改船舶
     */
    @RequestMapping("/ship_update/{shipId}")
    public String shipUpdate(@PathVariable Integer shipId, Model model) {
        Ship ship = shipService.selectById(shipId);
        model.addAttribute("item",ship);
        LogObjectHolder.me().set(ship);
        return PREFIX + "ship_edit.html";
    }

    /**
     * 获取船舶列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        EntityWrapper<Ship> shipEntityWrapper = new EntityWrapper<>();
        if (ToolUtil.isNotEmpty(condition) ) {
            shipEntityWrapper.like("imo", condition);
        }
        List<Map<String, Object>> list = this.shipService.selectMaps(shipEntityWrapper);
        return new ShipWarpper(list).warp();
    }

    /**
     * 新增船舶
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Ship ship) {
        shipService.insert(ship);
        return SUCCESS_TIP;
    }

    /**
     * 删除船舶
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer shipId) {
        shipService.deleteById(shipId);
        return SUCCESS_TIP;
    }

    /**
     * 修改船舶
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Ship ship) {
        shipService.updateById(ship);
        return SUCCESS_TIP;
    }
    /**
     * 禁用
     */
    @RequestMapping(value = "/disable")
    @ResponseBody
    public Object disable(Ship ship) {
        ship.setStatecode(0);
        shipService.updateById(ship);
        return SUCCESS_TIP;
    }
    /**
     * 启用
     */
    @RequestMapping(value = "/enable")
    @ResponseBody
    public Object enable(Ship ship) {
        ship.setStatecode(1);
        shipService.updateById(ship);
        return SUCCESS_TIP;
    }

    /**
     * 船舶详情
     */
    @RequestMapping(value = "/detail/{shipId}")
    @ResponseBody
    public Object detail(@PathVariable("shipId") Integer shipId) {
        return shipService.selectById(shipId);
    }
}
