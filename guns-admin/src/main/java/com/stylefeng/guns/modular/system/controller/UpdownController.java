package com.stylefeng.guns.modular.system.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.modular.system.model.*;
import com.stylefeng.guns.modular.system.service.*;
import com.stylefeng.guns.modular.webSys.service.IBusiOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.text.html.parser.Entity;
import java.util.*;


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

    @Autowired
    private IAreaService areaService;

    @Autowired
    private IBoxService boxService;


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
    public Object pload(String orders, String boxcode, Date optime,String oppeople,Integer areaid) {
        List<String> ordernumbers = JSON.parseArray(orders,String.class);
        EntityWrapper<Box> boxwrapper=new EntityWrapper<Box>();

        Updown updown=new Updown();
        boxwrapper.eq("boxcode",boxcode);
        Box deletebox=boxService.selectOne(boxwrapper);
        boxService.delete(boxwrapper);
        for(int i=0;i<ordernumbers.size();i++)
        {
            updown.setOrdernumber(ordernumbers.get(i));
            updown.setOppeople(oppeople);
            updown.setOptype(1);
            updown.setAreaid(areaid);
            updown.setOptime(optime);
            updown.setRecpeople("无");
            updown.setRecphone("无");
            updownService.insert(updown);

            EntityWrapper<BusiOrder> orderwrapper=new EntityWrapper<BusiOrder>();
            orderwrapper.eq("ordernumber",ordernumbers.get(i));
            BusiOrder order=orderService.selectOne(orderwrapper);
            order.setOrdercode(1);
            orderService.updateById(order);

            Box box=new Box();
            box.setOrdernumber(order.getOrdernumber());
            box.setBoxcode(boxcode);
            box.setBoxsize(deletebox.getBoxsize());
            box.setBoxtype(deletebox.getBoxtype());
            box.setGoodstype(order.getGoodstype());
            box.setGoodsname(order.getGoodsname());
            box.setTrantype(order.getTrantype());
            box.setStartpoint(order.getStartpoint());
            box.setEndpoint(order.getEndpoint());
            box.setEmptycode(1);
            box.setAreaid(areaid);
            box.setStatecode(1);
            boxService.insert(box);
        }
        return SUCCESS_TIP;
    }

    /**
     * 拆单装箱
     */
    @RequestMapping(value = "/cload")
    @ResponseBody
    public Object cload(String order, String boxcodes, Date optime,String oppeople,Integer areaid) {
        List<String> boxs = JSON.parseArray(boxcodes,String.class);
        EntityWrapper<BusiOrder> orderwrapper=new EntityWrapper<BusiOrder>();

        Updown updown=new Updown();
        updown.setOrdernumber(order);
        updown.setOppeople(oppeople);
        updown.setOptype(1);
        updown.setAreaid(areaid);
        updown.setOptime(optime);
        updown.setRecpeople("无");
        updown.setRecphone("无");
        updownService.insert(updown);

        orderwrapper.eq("ordernumber",order);
        BusiOrder Order=orderService.selectOne(orderwrapper);
        Order.setOrdercode(1);
        orderService.updateById(Order);


        for(int i=0;i<boxs.size();i++)
        {
            EntityWrapper<Box> boxwrapper=new EntityWrapper<Box>();
            boxwrapper.eq("boxcode",boxs.get(i));
            Box box=boxService.selectOne(boxwrapper);
            box.setOrdernumber(order);
            box.setGoodstype(Order.getGoodstype());
            box.setGoodsname(Order.getGoodsname());
            box.setTrantype(Order.getTrantype());
            box.setStartpoint(Order.getStartpoint());
            box.setEndpoint(Order.getEndpoint());
            box.setEmptycode(1);
            box.setAreaid(areaid);
            box.setStatecode(1);
            boxService.updateById(box);
        }
        return SUCCESS_TIP;
    }

    /**
     * 整箱装箱
     */
    @RequestMapping(value = "/load")
    @ResponseBody
    public Object load(String order, String boxcode, Date optime,String oppeople,Integer areaid) {
        EntityWrapper<BusiOrder> orderwrapper=new EntityWrapper<BusiOrder>();
        EntityWrapper<Box> boxwrapper=new EntityWrapper<Box>();

        Updown updown=new Updown();
        updown.setOrdernumber(order);
        updown.setOppeople(oppeople);
        updown.setOptype(1);
        updown.setAreaid(areaid);
        updown.setOptime(optime);
        updown.setRecpeople("无");
        updown.setRecphone("无");
        updownService.insert(updown);

        orderwrapper.eq("ordernumber",order);
        BusiOrder Order=orderService.selectOne(orderwrapper);
        Order.setOrdercode(1);
        orderService.updateById(Order);

        boxwrapper.eq("boxcode",boxcode);
        Box box=boxService.selectOne(boxwrapper);
        box.setOrdernumber(order);
        box.setGoodstype(Order.getGoodstype());
        box.setGoodsname(Order.getGoodsname());
        box.setTrantype(Order.getTrantype());
        box.setStartpoint(Order.getStartpoint());
        box.setEndpoint(Order.getEndpoint());
        box.setEmptycode(1);
        box.setAreaid(areaid);
        box.setStatecode(1);
        boxService.updateById(box);
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
            EntityWrapper<DictStation> wrapper=new EntityWrapper<DictStation>();
            wrapper.eq("statecode",1);
            list=dictStationService.selectMaps(wrapper);
        }else {
            EntityWrapper<Harbour> wrapper=new EntityWrapper<Harbour>();
            wrapper.eq("statecode",1);
            list=harbourService.selectMaps(wrapper);
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
        wrapper.eq("trantype",trantype).and().eq("endpoint",endpoint).and().eq("ordercode",0);
        List<Map<String, Object>> list=orderService.selectMaps(wrapper);
        return list;
    }

    /**
     * 获取箱型
     */
    @RequestMapping(value = "/getboxtypes")
    @ResponseBody
    public Object getboxtypes() {
        EntityWrapper<Boxtype> wrapper=new EntityWrapper<Boxtype>();
        wrapper.eq("statecode",1);
        List<Map<String, Object>> list=boxtypeService.selectMaps(wrapper);
        return list;
    }

    /**
     * 获取集装箱
     */
    @RequestMapping(value = "/getboxcodes")
    @ResponseBody
    public Object getboxcodes(Integer boxtype) {
        EntityWrapper<Box> wrapper=new EntityWrapper<Box>();
        wrapper.eq("boxtype",boxtype).and().eq("emptycode",0).and().eq("statecode",1);
        List<Map<String, Object>> list=boxService.selectMaps(wrapper);
        return list;
    }

    /**
     * 获取场地类型
     */
    @RequestMapping(value = "/getareas")
    @ResponseBody
    public Object getareas() {
        EntityWrapper<Area> wrapper=new EntityWrapper<Area>();
        wrapper.eq("statecode",1);
        List<Map<String, Object>> list=areaService.selectMaps(wrapper);
        return list;
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
