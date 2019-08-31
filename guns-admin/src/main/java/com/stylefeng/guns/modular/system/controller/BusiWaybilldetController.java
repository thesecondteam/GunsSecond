package com.stylefeng.guns.modular.system.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.model.BusiRecord;
import com.stylefeng.guns.modular.system.model.BusiWaybill;
import com.stylefeng.guns.modular.system.service.IBusiRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.BusiWaybilldet;
import com.stylefeng.guns.modular.system.service.IBusiWaybilldetService;

import java.util.*;

/**
 * 运单详情管理控制器
 *
 * @author fengshuonan
 * @Date 2019-08-14 16:38:15
 */
@Controller
@RequestMapping("/busiWaybilldet")
public class BusiWaybilldetController extends BaseController {

    private String PREFIX = "/system/busiWaybilldet/";

    @Autowired
    private IBusiWaybilldetService busiWaybilldetService;
    /************/
    @Autowired
    private IBusiRecordService busiRecordService;
    /************/

    /**
     * 跳转到运单详情管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "busiWaybilldet.html";
    }

    /**
     * 跳转到添加运单详情
     */
    @RequestMapping("/busiWaybilldet_add")
    public String busiWaybilldetAdd() {
        return PREFIX + "busiWaybilldet_add.html";
    }
    /**
     * 跳转到双击添加运单详情
     */
    @RequestMapping("/busiWaybilldet_click_add")
    public String busiWaybilldetclickAdd() {
        return PREFIX + "busiWaybilldet_click_add.html";
    }

    /**
     * 跳转到生成运单详情
     */
    @RequestMapping("/busiWaybilldet_create")
    public String busiWaybilldetCreate() {
        return PREFIX + "busiWaybilldet_create.html";
    }

    /**
     * 跳转到修改运单详情管理
     */
    @RequestMapping("/busiWaybilldet_update/{busiWaybilldetId}")
    public String busiWaybilldetUpdate(@PathVariable Integer busiWaybilldetId, Model model) {
        BusiWaybilldet busiWaybilldet = busiWaybilldetService.selectById(busiWaybilldetId);
        model.addAttribute("item",busiWaybilldet);
        LogObjectHolder.me().set(busiWaybilldet);
        return PREFIX + "busiWaybilldet_edit.html";
    }

    /**
     * 获取运单详情管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        if(ToolUtil.isEmpty(condition)) {
            return busiWaybilldetService.selectList(null);
        }else
        {
            EntityWrapper<BusiWaybilldet>entityWrapper=new EntityWrapper<>();
            Wrapper<BusiWaybilldet> wrapper=entityWrapper.like("Waybilldetid",condition);
            return busiWaybilldetService.selectList(wrapper);
        }
    }

    /**
     * 新增运单详情
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BusiWaybilldet busiWaybilldet) {
        /*********************操作记录****************************/
        BusiWaybilldet busiWaybilldetOld=busiWaybilldetService.selectById(busiWaybilldet.getId());
        if(busiWaybilldetOld==null)
        {   BusiRecord busiRecord=new BusiRecord();
            busiRecord.setOldcontent("空");
            busiRecord.setNewcontent(busiWaybilldet.toString());
            busiRecord.setOprman(ShiroKit.getUser().getName());//得到操作人
            busiRecord.setOptype("新增Waybilldet");
            busiRecord.setOptime(new Date());
            busiRecordService.insert(busiRecord);
        }
        /*********************操作记录****************************/
        busiWaybilldetService.insert(busiWaybilldet);
        return SUCCESS_TIP;
    }
    /**
     * 双击新增运单详情
     */
    @RequestMapping(value = "/click_add")
    @ResponseBody
    public Object click_add(BusiWaybilldet busiWaybilldet) {
        /*********************操作记录****************************/
        BusiWaybilldet busiWaybilldetOld=busiWaybilldetService.selectById(busiWaybilldet.getId());
        if(busiWaybilldetOld==null)
        {   BusiRecord busiRecord=new BusiRecord();
            busiRecord.setOldcontent("空");
            busiRecord.setNewcontent(busiWaybilldet.toString());
            busiRecord.setOprman(ShiroKit.getUser().getName());//得到操作人
            busiRecord.setOptype("新增Waybilldet");
            busiRecord.setOptime(new Date());
            busiRecordService.insert(busiRecord);
        }
        /*********************操作记录****************************/
        busiWaybilldetService.insert(busiWaybilldet);
        return SUCCESS_TIP;
    }
    /**
     * 生成运单详情
     */
    @RequestMapping(value = "/create")
    @ResponseBody
    public Object create(BusiWaybilldet busiWaybilldet) {
        /*********************操作记录****************************/
        BusiWaybilldet busiWaybilldetOld=busiWaybilldetService.selectById(busiWaybilldet.getId());
        if(busiWaybilldetOld==null)
        {   BusiRecord busiRecord=new BusiRecord();
            busiRecord.setOldcontent("空");
            busiRecord.setNewcontent(busiWaybilldet.toString());
            busiRecord.setOprman(ShiroKit.getUser().getName());//得到操作人
            busiRecord.setOptype("新增Waybilldet");
            busiRecord.setOptime(new Date());
            busiRecordService.insert(busiRecord);
        }
        /*********************操作记录****************************/
        busiWaybilldetService.insert(busiWaybilldet);
        return SUCCESS_TIP;
    }

    /**
     * 删除运单详情
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer busiWaybilldetId) {
        /*********************操作记录****************************/
        BusiWaybilldet busiWaybilldetOld=busiWaybilldetService.selectById(busiWaybilldetId);

        BusiRecord busiRecord=new BusiRecord();
        busiRecord.setOldcontent(busiWaybilldetOld.toString());
        busiRecord.setNewcontent("空");
        busiRecord.setOprman(ShiroKit.getUser().getName());//得到操作人
        busiRecord.setOptype("删除Waybilldet");
        busiRecord.setOptime(new Date());
        busiRecordService.insert(busiRecord);

        /*********************操作记录****************************/
        busiWaybilldetService.deleteById(busiWaybilldetId);
        return SUCCESS_TIP;
    }

    /**
     * 修改运单详情
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BusiWaybilldet busiWaybilldet) {
        /*********************操作记录****************************/
        BusiWaybilldet busiWaybilldetOld=busiWaybilldetService.selectById(busiWaybilldet.getId());
        if(busiWaybilldetOld!=null)
        {
            Map<String,Object> mapNew=entityToMap(busiWaybilldet);
            Map<String,Object> mapOld=entityToMap(busiWaybilldetOld);
            String op="修改了Waybilldet:@";
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
            busiRecord.setOldcontent(busiWaybilldetOld.toString());
            busiRecord.setNewcontent(busiWaybilldet.toString());
            busiRecord.setOprman(ShiroKit.getUser().getName());//得到操作人
            busiRecord.setOptype(op);
            busiRecord.setOptime(new Date());
            busiRecordService.insert(busiRecord);
        }
        /*********************操作记录****************************/
        busiWaybilldetService.updateById(busiWaybilldet);
        return SUCCESS_TIP;
    }

    /**
     * 运单详情管理详情
     */
    @RequestMapping(value = "/detail/{busiWaybilldetId}")
    @ResponseBody
    public Object detail(@PathVariable("busiWaybilldetId") Integer busiWaybilldetId) {
        return busiWaybilldetService.selectById(busiWaybilldetId);
    }

    /**
    *  获取所有运单详情号
    */
    @RequestMapping(value = "/getWaybilldetId")
    @ResponseBody
    public Object getWaybilldetId(String condition)
    {
        EntityWrapper<BusiWaybilldet> trainEntityWrapper = new EntityWrapper<>();
        if (ToolUtil.isNotEmpty(condition) ) {
            trainEntityWrapper.like("waybilldetid", condition);
        }
        List<Map<String, Object>> list = this.busiWaybilldetService.selectMaps(trainEntityWrapper);
        List<String> listWaybilldetId = new ArrayList<>();
        for(Map<String, Object> m:list)
        {
            listWaybilldetId.add(m.get("waybilldetid").toString());
        }
        return listWaybilldetId;
    }
}
