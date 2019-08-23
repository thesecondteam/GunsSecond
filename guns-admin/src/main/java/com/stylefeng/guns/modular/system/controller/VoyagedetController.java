package com.stylefeng.guns.modular.system.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.warpper.VoyagedetWarpper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.Voyagedet;
import com.stylefeng.guns.modular.system.service.IVoyagedetService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * voyagedet控制器
 *
 * @author fengshuonan
 * @Date 2019-08-14 17:05:39
 */
@Controller
@RequestMapping("/voyagedet")
public class VoyagedetController extends BaseController {

    private String PREFIX = "/system/voyagedet/";

    @Autowired
    private IVoyagedetService voyagedetService;

    /**
     * 跳转到voyagedet首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "voyagedet.html";
    }

    /**
     * 跳转到生成voyagedet
     */
    @RequestMapping("/voyagedet_create")
    public String voyagedetCreate() {
        return PREFIX + "voyagedet_create.html";
    }

    /**
     * 跳转到添加voyagedet
     */
    @RequestMapping("/voyagedet_add")
    public String voyagedetAdd() {
        return PREFIX + "voyagedet_add.html";
    }

    /**
     * 跳转到修改voyagedet
     */
    @RequestMapping("/voyagedet_update/{voyagedetId}")
    public String voyagedetUpdate(@PathVariable Integer voyagedetId, Model model) {
        Voyagedet voyagedet = voyagedetService.selectById(voyagedetId);
        model.addAttribute("item",voyagedet);
        LogObjectHolder.me().set(voyagedet);
        return PREFIX + "voyagedet_edit.html";
    }

    /**
     * 获取voyagedet列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        /**
         * 声明条件构造器
         */
        EntityWrapper<Voyagedet> voyageEntityWrapper = new EntityWrapper<>();

        if(ToolUtil.isNotEmpty(condition)){
            voyageEntityWrapper.like("voyagenum",condition);
        }
        List<Map<String,Object>> list = this.voyagedetService.selectMaps(voyageEntityWrapper);
        return super.warpObject(new VoyagedetWarpper(list));
    }

    /**
     * 新增voyagedet
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Voyagedet voyagedet) {
        voyagedetService.insert(voyagedet);
        return SUCCESS_TIP;
    }

    /**
     * 生成voyagedet
     */
    @RequestMapping(value = "/create")
    @ResponseBody
    public Object create(Voyagedet voyagedet) {
        voyagedetService.insert(voyagedet);
        return SUCCESS_TIP;
    }

    /**
     * 删除voyagedet
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer voyagedetId) {
        voyagedetService.deleteById(voyagedetId);
        return SUCCESS_TIP;
    }

    /**
     * 修改voyagedet
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Voyagedet voyagedet) {
        voyagedetService.updateById(voyagedet);
        return SUCCESS_TIP;
    }

    /**
     * voyagedet详情
     */
    @RequestMapping(value = "/detail/{voyagedetId}")
    @ResponseBody
    public Object detail(@PathVariable("voyagedetId") Integer voyagedetId) {
        return voyagedetService.selectById(voyagedetId);
    }

    /**
     *  获取所有航次详情号
     */
    @RequestMapping(value = "/getVoyagedetId")
    @ResponseBody
    public Object getVoyagedetId(String condition)
    {
        EntityWrapper<Voyagedet> trainEntityWrapper = new EntityWrapper<>();
        if (ToolUtil.isNotEmpty(condition) ) {
            trainEntityWrapper.like("voyagedetid", condition);
        }
        List<Map<String, Object>> list = this.voyagedetService.selectMaps(trainEntityWrapper);
        List<String> listVoyagedetId = new ArrayList<>();
        for(Map<String, Object> m:list)
        {
            listVoyagedetId.add(m.get("voyagedetid").toString());
        }
        return listVoyagedetId;
    }
}
