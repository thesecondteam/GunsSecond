package com.stylefeng.guns.modular.system.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.modular.system.model.BusiOrder;
import com.stylefeng.guns.modular.system.service.IBoxtypeService;
import com.stylefeng.guns.modular.system.service.IDictStationService;
import com.stylefeng.guns.modular.system.service.IHarbourService;
import com.stylefeng.guns.modular.webSys.service.IBusiOrderService;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.Updown;
import com.stylefeng.guns.modular.system.service.IUpdownService;
import java.util.List;
import java.util.Map;


/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2019-08-28 08:36:01
 */
@Controller
@RequestMapping("/updown")
public class UpdownController extends BaseController {

    private String PREFIX = "/system/updown/";

    @Autowired
    private IUpdownService updownService;

    @Autowired
    private IDictStationService dictStationService;

    @Autowired
    private IHarbourService harbourService;

    @Autowired
    private IBoxtypeService boxtypeService;

    @Autowired
    private IBusiOrderService orderService;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "updown.html";
    }

    /**
     * 跳转到拼单装箱
     */
    @RequestMapping("/updown_pload")
    public String updownPload() {
        return PREFIX + "updown_pload.html";
    }
    /**
     * 跳转到拼单装箱
     */
    @RequestMapping("/updown_cload")
    public String updownCload() {
        return PREFIX + "updown_cload.html";
    }
    /**
     * 跳转到拼单装箱
     */
    @RequestMapping("/updown_load")
    public String updownLoad() {
        return PREFIX + "updown_load.html";
    }


    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return updownService.selectList(null);
    }

    /**
     * 拼单装箱
     */
    @RequestMapping(value = "/pload")
    @ResponseBody
    public Object pload(Updown obj,String orders) {
        List<String> ordernumbers = JSON.parseArray(orders,String.class);

        return SUCCESS_TIP;
    }
    /**
     * 拆单装箱
     */
    @RequestMapping(value = "/cload")
    @ResponseBody
    public Object cload(Updown obj,String orders) {
        List<String> ordernumbers = JSON.parseArray(orders,String.class);

        return SUCCESS_TIP;
    }
    /**
     * 整箱装箱
     */
    @RequestMapping(value = "/load")
    @ResponseBody
    public Object load(Updown obj,String orders) {
        List<String> ordernumbers = JSON.parseArray(orders,String.class);

        return SUCCESS_TIP;
    }

    /**
     * 获取终点
     */
    @RequestMapping(value = "/getendpoints")
    @ResponseBody
    public Object getendpoints(Integer trantype) {
        List<Map<String, Object>> list;
        if(trantype==1)
        {
            list=dictStationService.selectMaps(null);
        }else {
            list=harbourService.selectMaps(null);
        }

        return list;
    }

    /**
     * 获取订单
     */
    @RequestMapping(value = "/getordernumbers")
    @ResponseBody
    public Object getordernumbers(Integer trantype, Integer endpoint) {
        EntityWrapper<BusiOrder> wrapper=new EntityWrapper<BusiOrder>();
        wrapper.eq("trantype",trantype).and().eq("endpoint",endpoint);
        List<Map<String, Object>> list=orderService.selectMaps(wrapper);
        return list;
    }

    /**
     * 获取箱型
     */
    @RequestMapping(value = "/getboxtypes")
    @ResponseBody
    public Object getboxtypes() {
        List<Map<String, Object>> list=boxtypeService.selectMaps(null);
        return list;
    }

    /**
     * 获取集装箱
     */
    @RequestMapping(value = "/getboxcodes")
    @ResponseBody
    public Object getboxcodes() {

        return SUCCESS_TIP;
    }
    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer updownId) {
        updownService.deleteById(updownId);
        return SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{updownId}")
    @ResponseBody
    public Object detail(@PathVariable("updownId") Integer updownId) {
        return updownService.selectById(updownId);
    }
}
