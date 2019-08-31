package com.stylefeng.guns.modular.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.model.BusiRecord;
import com.stylefeng.guns.modular.system.service.IBusiRecordService;
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

import java.util.*;

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
    @Autowired
    private IBusiRecordService busiRecordService;

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
        List<DictStation>l=dictStationService.selectList(null);
        Set<String>s=new HashSet<String>();
        for(DictStation d:l) {
            s.add(d.getName());
        }
        if (s.contains(dictStation.getName()))
        {
            String str="{code:2333,message:\"添加错误，站点重复！\"}";
            System.out.println(str);
            JSONObject jsonObject=JSONObject.parseObject(str);
            return jsonObject;
        }
        /*********************操作记录****************************/
        DictStation dictStationOld=dictStationService.selectById(dictStation.getId());
        if(dictStationOld==null)
        {   BusiRecord busiRecord=new BusiRecord();
            busiRecord.setOldcontent("空");
            busiRecord.setNewcontent(dictStation.toString());
            busiRecord.setOprman(ShiroKit.getUser().getName());//得到操作人
            busiRecord.setOptype("新增站点");
            busiRecord.setOptime(new Date());
            busiRecordService.insert(busiRecord);
        }
        /*********************操作记录****************************/
        dictStationService.insert(dictStation);
        return SUCCESS_TIP;
    }

    /**
     * 删除站点
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer dictStationId) {
        /*********************操作记录****************************/
        DictStation dictStationOld=dictStationService.selectById(dictStationId);

        BusiRecord busiRecord=new BusiRecord();
        busiRecord.setOldcontent(dictStationOld.toString());
        busiRecord.setNewcontent("空");
        busiRecord.setOprman(ShiroKit.getUser().getName());//得到操作人
        busiRecord.setOptype("删除站点");
        busiRecord.setOptime(new Date());
        busiRecordService.insert(busiRecord);

        /*********************操作记录****************************/
        dictStationService.deleteById(dictStationId);
        return SUCCESS_TIP;
    }

    /**
     * 修改站点
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(DictStation dictStation) {
        /*********************操作记录****************************/
        DictStation dictStationOld=dictStationService.selectById(dictStation.getId());
        if(dictStationOld!=null)
        {
            Map<String,Object> mapNew=entityToMap(dictStation);
            Map<String,Object> mapOld=entityToMap(dictStationOld);
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
            busiRecord.setOldcontent(dictStationOld.toString());
            busiRecord.setNewcontent(dictStation.toString());
            busiRecord.setOprman(ShiroKit.getUser().getName());//得到操作人
            busiRecord.setOptype(op);
            busiRecord.setOptime(new Date());
            busiRecordService.insert(busiRecord);
        }
        /*********************操作记录****************************/
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

    /**
     * 获取所有站点
     */
    @RequestMapping(value="/getStationId")
    @ResponseBody
    public Object getStationId(String condition)
    {
        EntityWrapper<DictStation> dictStationEntityWrapper = new EntityWrapper<>();
        if (ToolUtil.isNotEmpty(condition) ) {
            dictStationEntityWrapper.like("id", condition);
        }
        List<Map<String, Object>> list = this.dictStationService.selectMaps(dictStationEntityWrapper);
        List<String> listStationId = new ArrayList<>();
        for(Map<String, Object> m:list)
        {
            if(!m.get("id").toString().equals("7")){
                listStationId.add(m.get("name").toString());
            }
        }
        return listStationId;
    }
}
