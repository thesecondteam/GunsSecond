package com.stylefeng.guns.modular.system.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.model.BusiRecord;
import com.stylefeng.guns.modular.system.model.BusiWaybill;
import com.stylefeng.guns.modular.system.service.IBusiRecordService;
import com.stylefeng.guns.modular.system.service.IBusiWaybillService;
import com.stylefeng.guns.modular.system.service.ITrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.lang.String;
import java.util.List;
import java.util.Map;

/**
 * 运单管理控制器
 *
 * @author fengshuonan
 * @Date 2019-08-14 11:24:01
 */
@Controller
@RequestMapping("/busiWaybill")
public class BusiWaybillController extends BaseController {

    private String PREFIX = "/system/busiWaybill/";

    @Autowired
    private IBusiWaybillService busiWaybillService;
    /************/
    @Autowired
    private IBusiRecordService busiRecordService;
    /************/

    /**
     * 跳转到运单管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "busiWaybill.html";
    }

    /**
     * 跳转到生成运单管理
     */
    @RequestMapping("/busiWaybill_create")
    public String busiWaybillCreate() {
        return PREFIX + "busiWaybill_create.html";
    }

    /**
     * 跳转到添加运单管理
     */
    @RequestMapping("/busiWaybill_add")
    public String busiWaybillAdd() {
        return PREFIX + "busiWaybill_add.html";
    }
    /**
     * 跳转到修改运单管理
     */
    @RequestMapping("/busiWaybill_update/{busiWaybillId}")
    public String busiWaybillUpdate(@PathVariable Integer busiWaybillId, Model model) {
        BusiWaybill busiWaybill = busiWaybillService.selectById(busiWaybillId);
        model.addAttribute("item",busiWaybill);
        LogObjectHolder.me().set(busiWaybill);
        return PREFIX + "busiWaybill_edit.html";
    }

    /**
     * 获取运单管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        if(ToolUtil.isEmpty(condition)) {
            return busiWaybillService.selectList(null);
        }else
        {
            EntityWrapper<BusiWaybill> entityWrapper=new EntityWrapper<>();
            Wrapper<BusiWaybill> wrapper=entityWrapper.like("Waybillid",condition);
            return busiWaybillService.selectList(wrapper);
        }
    }

    /**
     * 新增运单管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BusiWaybill busiWaybill) {
        /*********************操作记录****************************/
        BusiWaybill busiWaybill1Old=busiWaybillService.selectById(busiWaybill.getId());
        if(busiWaybill1Old==null)
        {   BusiRecord busiRecord=new BusiRecord();
            busiRecord.setOldcontent("空");
            busiRecord.setNewcontent(busiWaybill.toString());
            busiRecord.setOprman(ShiroKit.getUser().getName());//得到操作人
            busiRecord.setOptype("新增Waybill");
            busiRecord.setOptime(new Date());
            busiRecordService.insert(busiRecord);
        }
        /*********************操作记录****************************/
        busiWaybillService.insert(busiWaybill);
        return SUCCESS_TIP;
    }
    /**
     * 生成运单管理
     */
    @RequestMapping(value = "/create")
    @ResponseBody
    public Object create(BusiWaybill busiWaybill) {
        /*********************操作记录****************************/
        BusiWaybill busiWaybill1Old=busiWaybillService.selectById(busiWaybill.getId());
        if(busiWaybill1Old==null)
        {   BusiRecord busiRecord=new BusiRecord();
            busiRecord.setOldcontent("空");
            busiRecord.setNewcontent(busiWaybill.toString());
            busiRecord.setOprman(ShiroKit.getUser().getName());//得到操作人
            busiRecord.setOptype("新增Waybill");
            busiRecord.setOptime(new Date());
            busiRecordService.insert(busiRecord);
        }
        /*********************操作记录****************************/
        busiWaybillService.insert(busiWaybill);
        return SUCCESS_TIP;
    }
    /**
     * 删除运单管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer busiWaybillId) {
        /*********************操作记录****************************/
        BusiWaybill busiWaybillOld=busiWaybillService.selectById(busiWaybillId);

        BusiRecord busiRecord=new BusiRecord();
        busiRecord.setOldcontent(busiWaybillOld.toString());
        busiRecord.setNewcontent("空");
        busiRecord.setOprman(ShiroKit.getUser().getName());//得到操作人
        busiRecord.setOptype("删除Waybill");
        busiRecord.setOptime(new Date());
        busiRecordService.insert(busiRecord);

        /*********************操作记录****************************/
        busiWaybillService.deleteById(busiWaybillId);
        return SUCCESS_TIP;
    }

    /**
     * 修改运单管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BusiWaybill busiWaybill) {
        /*********************操作记录****************************/
        BusiWaybill busiWaybill1Old=busiWaybillService.selectById(busiWaybill.getId());
        if(busiWaybill1Old!=null)
        {
            Map<String,Object> mapNew=entityToMap(busiWaybill);
            Map<String,Object> mapOld=entityToMap(busiWaybill1Old);
            String op="修改了Waybill:@";
            for(Map.Entry<String, Object> m : mapOld.entrySet())
            {
                if(m.getValue()!=null&&mapNew.get(m.getKey())!=null)//比较两个字符串，不等的时候才插入
                {      if(!m.getValue().equals(mapNew.get(m.getKey())))
                    op+="属性："+m.getKey()+"@由\""+m.getValue()+"\"-->\""+mapNew.get(m.getKey())+"\"@";
                }
                else if(m.getValue()==null&&mapNew.get(m.getKey())==null)
                {
                    continue;
                }
                else if(m.getValue()==null&&mapNew.get(m.getKey())!=null)
                {
                    op+="属性："+m.getKey()+"@由\""+"空"+"\"-->\""+mapNew.get(m.getKey())+"\"@";
                }
                else if(m.getValue()!=null&&mapNew.get(m.getKey())!=null)
                {
                    op+="属性："+m.getKey()+"@由\""+m.getValue()+"\"-->\""+"空"+"\"@";
                }

            }
            BusiRecord busiRecord=new BusiRecord();
            busiRecord.setOldcontent(busiWaybill1Old.toString());
            busiRecord.setNewcontent(busiWaybill.toString());
            busiRecord.setOprman(ShiroKit.getUser().getName());//得到操作人
            busiRecord.setOptype(op);
            busiRecord.setOptime(new Date());
            busiRecordService.insert(busiRecord);
        }
        /*********************操作记录****************************/
        busiWaybillService.updateById(busiWaybill);
        return SUCCESS_TIP;
    }

    /**
     * 结束运单;返回状态码和结束时间；
     */
    @RequestMapping(value = "/finish")
    @ResponseBody
    public Object finish(BusiWaybill busiWaybill) {
        busiWaybill.setStatecode(1);
        busiWaybill.setEndtime(new Date());
        busiWaybillService.updateById(busiWaybill);
        return SUCCESS_TIP;
    }

    /**
     * 运单管理详情
     */
    @RequestMapping(value = "/detail/{busiWaybillId}")
    @ResponseBody
    public Object detail(@PathVariable("busiWaybillId") Integer busiWaybillId) {
        return busiWaybillService.selectById(busiWaybillId);
    }
    /**
     * 获取所有运单号
     */
    @RequestMapping(value="/getWaybillId")
    @ResponseBody
    public Object getWaybillId(String condition)
    {
        EntityWrapper<BusiWaybill> busiWaybillEntityWrapper = new EntityWrapper<>();
        if (ToolUtil.isNotEmpty(condition) ) {
            busiWaybillEntityWrapper.like("id", condition);
        }
        List<Map<String, Object>> list = this.busiWaybillService.selectMaps(busiWaybillEntityWrapper);
        List<String> listWaybillId = new ArrayList<>();
        for(Map<String, Object> m:list)
        {
            listWaybillId.add(m.get("waybillid").toString());
        }
        return listWaybillId;
    }
}
