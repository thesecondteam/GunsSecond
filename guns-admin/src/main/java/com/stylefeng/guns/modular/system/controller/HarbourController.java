package com.stylefeng.guns.modular.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.util.ToolUtil;
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

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        harbourService.insert(harbour);
        return SUCCESS_TIP;
    }

    /**
     * 删除港口
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer harbourId) {
        harbourService.deleteById(harbourId);
        return SUCCESS_TIP;
    }

    /**
     * 修改港口
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Harbour harbour) {
        List<Harbour>l=harbourService.selectList(null);
        Set<String> s=new HashSet<String>();
        for(Harbour d:l) {
            s.add(d.getHarbourcode());
        }
        if (s.contains(harbour.getHarbourcode()))
        {
            String str="{code:2334,message:\"修改错误，港口重复！\"}";
            System.out.println(str);
            JSONObject jsonObject=JSONObject.parseObject(str);
            return jsonObject;
        }
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
}
