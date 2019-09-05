package com.stylefeng.guns.modular.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.model.BusiRecord;
import com.stylefeng.guns.modular.system.service.IBusiRecordService;
import com.stylefeng.guns.modular.system.warpper.HarbourWarpper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.Harbour;
import com.stylefeng.guns.modular.system.service.IHarbourService;

import java.util.*;

/**
 * 港口控制器
 *
 * @author fengshuonan
 * @Date 2019-08-14 11:02:17
 */
@Controller
@RequestMapping("/harbour")
public class HarbourController extends BaseController {

    private String PREFIX = "/system/harbour/";

    @Autowired
    private IHarbourService harbourService;
    @Autowired
    private IBusiRecordService busiRecordService;

    /**
     * 跳转到港口首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "harbour.html";
    }

    /**
     * 跳转到添加港口
     */
    @RequestMapping("/harbour_add")
    public String harbourAdd() {
        return PREFIX + "harbour_add.html";
    }

    /**
     * 跳转到修改港口
     */
    @RequestMapping("/harbour_update/{harbourId}")
    public String harbourUpdate(@PathVariable Integer harbourId, Model model) {
        Harbour harbour = harbourService.selectById(harbourId);
        model.addAttribute("item",harbour);
        LogObjectHolder.me().set(harbour);
        return PREFIX + "harbour_edit.html";
    }

    /**
     * 获取港口列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        EntityWrapper<Harbour> harbourEntityWrapper = new EntityWrapper<>();
        if (ToolUtil.isNotEmpty(condition) ) {
            harbourEntityWrapper.like("harbourcode", condition);
        }
        List<Map<String, Object>> list = this.harbourService.selectMaps(harbourEntityWrapper);
        return new HarbourWarpper(list).warp();
    }

    /**
     * 新增港口
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Harbour harbour) {
        List<Harbour>l=harbourService.selectList(null);
        Set<String> s=new HashSet<String>();
        for(Harbour d:l) {
            s.add(d.getHarbourcode());
        }
        if (s.contains(harbour.getHarbourcode()))
        {
            String str="{code:2334,message:\"添加错误，港口重复！\"}";
            System.out.println(str);
            JSONObject jsonObject=JSONObject.parseObject(str);
            return jsonObject;
        }
        /*********************操作记录****************************/
        Harbour harbourOld=harbourService.selectById(harbour.getId());
        if(harbourOld==null)
        {   BusiRecord busiRecord=new BusiRecord();
            busiRecord.setOldcontent("空");
            busiRecord.setNewcontent(harbour.toString());
            busiRecord.setOprman(ShiroKit.getUser().getName());//得到操作人
            busiRecord.setOptype("新增港口");
            busiRecord.setOptime(new Date());
            busiRecordService.insert(busiRecord);
        }
        /*********************操作记录****************************/
        harbourService.insert(harbour);
        return SUCCESS_TIP;
    }

    /**
     * 删除港口
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer harbourId) {
        /*********************操作记录****************************/
        Harbour harbourOld=harbourService.selectById(harbourId);

        BusiRecord busiRecord=new BusiRecord();
        busiRecord.setOldcontent(harbourOld.toString());
        busiRecord.setNewcontent("空");
        busiRecord.setOprman(ShiroKit.getUser().getName());//得到操作人
        busiRecord.setOptype("删除港口");
        busiRecord.setOptime(new Date());
        busiRecordService.insert(busiRecord);

        /*********************操作记录****************************/
        harbourService.deleteById(harbourId);
        return SUCCESS_TIP;
    }

    /**
     * 修改港口
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Harbour harbour) {
        /*********************操作记录****************************/
        Harbour harbourOld=harbourService.selectById(harbour.getId());
        if(harbourOld!=null)
        {
            Map<String,Object> mapNew=entityToMap(harbour);
            Map<String,Object> mapOld=entityToMap(harbourOld);
            String op="修改了Order:@";
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
            busiRecord.setOldcontent(harbourOld.toString());
            busiRecord.setNewcontent(harbour.toString());
            busiRecord.setOprman(ShiroKit.getUser().getName());//得到操作人
            busiRecord.setOptype(op);
            busiRecord.setOptime(new Date());
            busiRecordService.insert(busiRecord);
        }
        /*********************操作记录****************************/
        harbourService.updateById(harbour);
        return SUCCESS_TIP;
    }

    /**
     * 禁用
     */
    @RequestMapping(value = "/disable")
    @ResponseBody
    public Object disable(Harbour harbour) {
        harbour.setStatecode(0);
        harbourService.updateById(harbour);
        return SUCCESS_TIP;
    }
    /**
     * 启用
     */
    @RequestMapping(value = "/enable")
    @ResponseBody
    public Object enable(Harbour harbour) {
        harbour.setStatecode(1);
        harbourService.updateById(harbour);
        return SUCCESS_TIP;
    }


    /**
     * 港口详情
     */
    @RequestMapping(value = "/detail/{harbourId}")
    @ResponseBody
    public Object detail(@PathVariable("harbourId") Integer harbourId) {
        return harbourService.selectById(harbourId);
    }

    /**
     * 获取所有港口
     */
    @RequestMapping(value="/getHarbourName")
    @ResponseBody
    public Object getHarbourName(String condition)
    {
        EntityWrapper<Harbour> harbourEntityWrapper = new EntityWrapper<>();
        if (ToolUtil.isNotEmpty(condition) ) {
            harbourEntityWrapper.like("id", condition);
        }
        List<Map<String, Object>> list = this.harbourService.selectMaps(harbourEntityWrapper);
        List<String> listHarbourName = new ArrayList<>();
        for(Map<String, Object> m:list)
        {
            if(!m.get("harbourcode").toString().equals("CNQHD")){
                listHarbourName.add(m.get("harbourname").toString());
            }
        }
        return listHarbourName;
    }
    /**
     * 获取所有港口id
     */
    @RequestMapping(value="/getHarbourId")
    @ResponseBody
    public Object getHarbourId(String condition)
    {
        EntityWrapper<Harbour> harbourEntityWrapper = new EntityWrapper<>();
        if (ToolUtil.isNotEmpty(condition) ) {
            harbourEntityWrapper.like("id", condition);
        }
        List<Map<String, Object>> list = this.harbourService.selectMaps(harbourEntityWrapper);
        List<String> listHarbourId = new ArrayList<>();
        for(Map<String, Object> m:list)
        {
            if(!m.get("harbourcode").toString().equals("CNQHD")){
                listHarbourId.add(m.get("id").toString());
            }
        }
        return listHarbourId;
    }
}
