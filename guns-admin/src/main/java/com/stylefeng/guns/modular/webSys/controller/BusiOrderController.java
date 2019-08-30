package com.stylefeng.guns.modular.webSys.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.google.common.collect.Maps;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.annotion.BussinessLog;
import com.stylefeng.guns.core.common.constant.state.Order;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.factory.DicFactory;
import com.stylefeng.guns.modular.system.model.*;

import com.stylefeng.guns.modular.system.service.IBusiRecordService;
import com.stylefeng.guns.modular.system.service.IDictStationService;
import com.stylefeng.guns.modular.system.service.IHarbourService;
import com.stylefeng.guns.modular.system.warpper.DictGoodstypeWrapper;
import com.stylefeng.guns.modular.system.warpper.HarbourWarpper;
import com.stylefeng.guns.modular.system.warpper.OrderWarpper;
import org.beetl.ext.fn.Json;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.webSys.service.IBusiOrderService;

import java.lang.reflect.Field;
import java.util.*;

/**
 * order控制器
 *
 * @author fengshuonan
 * @Date 2019-08-14 10:18:35
 */
@Controller
@RequestMapping("/busiOrder")
public class BusiOrderController extends BaseController {

    private String PREFIX = "/webSys/busiOrder/";

    @Autowired
    private IBusiOrderService busiOrderService;
    @Autowired
    private IDictStationService dictStationService;
    @Autowired
    private IHarbourService harbourService;
    @Autowired
    private IBusiRecordService busiRecordService;
    /**
     * 跳转到order首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "busiOrder.html";
    }

    /**
     * 跳转到添加order
     */
    @RequestMapping("/busiOrder_add")


    public String busiOrderAdd(String condition)
    {
        return PREFIX + "busiOrder_add.html";
    }

    @RequestMapping("/getInfoS")
    @ResponseBody
    public Object getInfoS()
    {
        EntityWrapper<Harbour> harbourEntityWrapper = new EntityWrapper<>();
        EntityWrapper<DictStation> stationEntityWrapper = new EntityWrapper<>();

        List<Map<String, Object>> liststation = this.dictStationService.selectMaps(stationEntityWrapper);
        List<Map<String, Object>> listharbour = this.harbourService.selectMaps(harbourEntityWrapper);
        Map<String,Object> m=new HashMap<>();
        m.put("station",liststation);
        m.put("harbour",listharbour);
        return m;
    }


    /**
     * 跳转到修改order
     */
    @RequestMapping("/busiOrder_update/{busiOrderId}")
    public String busiOrderUpdate(@PathVariable Integer busiOrderId, Model model) {

        BusiOrder busiOrder = busiOrderService.selectById(busiOrderId);
        Map<String,Object> map=new HashMap<>();
        BeanMap beanMap=BeanMap.create(busiOrder);

        for(Object key:beanMap.keySet())
        {
            map.put(key+"",beanMap.get(key));
        }
        List<Map<String,Object>> list=new ArrayList<>();
        list.add(map);
        System.out.println("__________________List"+list);
        Object object=new OrderWarpper(list).warp();
        System.out.println("__________________OBJECT"+object);
//        Map<String,Object> map2=new HashMap<>();
//        BeanMap beanMap2=BeanMap.create(object);
//
//        for(Object key:beanMap2.keySet())
//        {
//            map2.put(key+"",beanMap2.get(key));
//        }
//        System.out.println("__________________"+map2);
        model.addAttribute("item",object);
//        LogObjectHolder.me().set(busiOrder);
        return PREFIX + "busiOrder_edit.html";
    }

    /**
     * 获取order列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        EntityWrapper<BusiOrder>entityWrapper=new EntityWrapper<>();
        List<BusiOrder>odList= new ArrayList<>();
        if(ToolUtil.isEmpty(condition)) {

            List<Map<String,Object>>reList=new ArrayList<>();
            List<BusiOrder>reList1=new ArrayList<>();
            List<BusiOrder>reList2=new ArrayList<>();
            for(BusiOrder bo:odList)
            {
                if(bo.getTrantype()==0)//海运
                {
                    reList1.add(bo);
                }else//陆运
                {
                    reList2.add(bo);
                }
            }

            List<Map<String, Object>> list = this.busiOrderService.selectMaps(entityWrapper);
            return  new OrderWarpper(list).warp();
        }else
        {

            Wrapper<BusiOrder>wrapper=entityWrapper.like("ordernumber",condition);
           odList= busiOrderService.selectList(wrapper);
        }
        List<Map<String,Object>>reList=new ArrayList<>();
        List<BusiOrder>reList1=new ArrayList<>();
        List<BusiOrder>reList2=new ArrayList<>();
        for(BusiOrder bo:odList)
        {
            if(bo.getTrantype()==0)//海运
            {
                reList1.add(bo);
            }else//陆运
            {
                reList2.add(bo);
            }
        }
        System.out.println(reList1);
        List<Map<String, Object>> list = this.busiOrderService.selectMaps(entityWrapper);
        return  new OrderWarpper(list).warp();
    }

    /**
     * 新增order
     */
    @BussinessLog(value = "新增order", key = "orderId")
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BusiOrder busiOrder) {
        /*********************操作记录****************************/
        BusiOrder busiOrderOld=busiOrderService.selectById(busiOrder.getId());
        if(busiOrderOld==null)
        {   BusiRecord busiRecord=new BusiRecord();
        busiRecord.setOldcontent("空");
        busiRecord.setNewcontent(busiOrder.toString());
        busiRecord.setOprman(ShiroKit.getUser().getName());//得到操作人
        busiRecord.setOptype("新增order");
        busiRecord.setOptime(new Date());
        busiRecordService.insert(busiRecord);
        }
        /*********************操作记录****************************/
        busiOrderService.insert(busiOrder);
        return SUCCESS_TIP;
    }

    /**
     * 删除order
     */
    @BussinessLog(value = "删除order", key = "orderId")
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer busiOrderId) {
        /*********************操作记录****************************/
        BusiOrder busiOrderOld=busiOrderService.selectById(busiOrderId);

        BusiRecord busiRecord=new BusiRecord();
        busiRecord.setOldcontent(busiOrderOld.toString());
        busiRecord.setNewcontent("空");
        busiRecord.setOprman(ShiroKit.getUser().getName());//得到操作人
        busiRecord.setOptype("删除order");
        busiRecord.setOptime(new Date());
        busiRecordService.insert(busiRecord);

        /*********************操作记录****************************/

        busiOrderService.deleteById(busiOrderId);
        return SUCCESS_TIP;
    }

    /**
     * 修改order
     */
    @BussinessLog(value = "修改order", key = "orderId")
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BusiOrder busiOrder) {
        /*********************操作记录****************************/
        BusiOrder busiOrderOld=busiOrderService.selectById(busiOrder.getId());
        if(busiOrderOld!=null)
        {
            Class clsOld=busiOrderOld.getClass();
            Class clsNew=busiOrder.getClass();
            Field[] fieldsOld=clsOld.getDeclaredFields();
            Field[] fieldsNew=clsNew.getDeclaredFields();
            String op="修改了Order:@";
            for(int i=0;i<fieldsOld.length;i++)
            {
                if(fieldsOld[i]!=fieldsNew[i])
                {
                    op+="属性："+fieldsOld[i].getName()+",由\""+fieldsOld[i]+"\"-->\""+fieldsNew[i]+"\"@"   ;
                }
            }
            BusiRecord busiRecord=new BusiRecord();
            busiRecord.setOldcontent(busiOrderOld.toString());
            busiRecord.setNewcontent(busiOrder.toString());
            busiRecord.setOprman(ShiroKit.getUser().getName());//得到操作人
            busiRecord.setOptype(op);
            busiRecord.setOptime(new Date());
            busiRecordService.insert(busiRecord);
        }
        /*********************操作记录****************************/
        busiOrderService.updateById(busiOrder);
        return SUCCESS_TIP;
    }

    /**
     * order详情
     */
    @RequestMapping(value = "/detail/{busiOrderId}")
    @ResponseBody
    public Object detail(@PathVariable("busiOrderId") Integer busiOrderId) {
        return busiOrderService.selectById(busiOrderId);
    }


//    /**
//     * 禁用
//     */
//    @RequestMapping(value = "/disable")
//    @ResponseBody
//    public Object disable(BusiOrder busiOrder) {
//        busiOrder.setOrdercode(0);
//        busiOrderService.updateById(busiOrder);
//        return SUCCESS_TIP;
//    }
//    /**
//     * 启用
//     */
//    @RequestMapping(value = "/enable")
//    @ResponseBody
//    public Object enable(BusiOrder busiOrder) {
//        busiOrder.setOrdercode(1);
//       busiOrderService.updateById(busiOrder);
//        return SUCCESS_TIP;
//    }
//public static <T> Map<String, Object> beanToMap(T bean) {
//    Map<String, Object> map = Maps.newHashMap();
//    if (bean != null) {
//        BeanMap beanMap = BeanMap.create(bean);
//        for (Object key : beanMap.keySet()) {
//            map.put(key+"", beanMap.get(key));
//        }
//    }
//    return map;
//}






}
