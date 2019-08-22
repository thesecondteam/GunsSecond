package com.stylefeng.guns.modular.system.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.model.Ship;
import com.stylefeng.guns.modular.system.service.IShipService;
import com.stylefeng.guns.modular.system.warpper.VoyageWarpper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.Voyage;
import com.stylefeng.guns.modular.system.service.IVoyageService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * voyage控制器
 *
 * @author fengshuonan
 * @Date 2019-08-14 17:03:52
 */
@Controller
@RequestMapping("/voyage")
public class VoyageController extends BaseController {

    private String PREFIX = "/system/voyage/";

    @Autowired
    private IVoyageService voyageService;
    @Autowired
    private IShipService shipService;

    /**
     * 跳转到voyage首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "voyage.html";
    }

    /**
     * 跳转到添加voyage
     */
    @RequestMapping("/voyage_add")
    public String voyageAdd() {
        return PREFIX + "voyage_add.html";
    }

    /**
     * 跳转到修改voyage
     */
    @RequestMapping("/voyage_update/{voyageId}")
    public String voyageUpdate(@PathVariable Integer voyageId, Model model) {
        Voyage voyage = voyageService.selectById(voyageId);
        model.addAttribute("item",voyage);
        LogObjectHolder.me().set(voyage);
        return PREFIX + "voyage_edit.html";
    }

    /**
     * 获取voyage列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        /**
        * 声明条件构造器
        */
        EntityWrapper<Voyage> voyageEntityWrapper = new EntityWrapper<>();

        if(ToolUtil.isNotEmpty(condition)){
            voyageEntityWrapper.like("voyagenum",condition);
        }
        List<Map<String,Object>>list = this.voyageService.selectMaps(voyageEntityWrapper);
        return super.warpObject(new VoyageWarpper(list));
    }

    /**
     * 新增voyage
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Voyage voyage) {
        voyageService.insert(voyage);
        return SUCCESS_TIP;
    }

    /**
     * 删除voyage
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer voyageId) {
        voyageService.deleteById(voyageId);
        return SUCCESS_TIP;
    }

    /**
     * 修改voyage
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Voyage voyage) {
        voyageService.updateById(voyage);
        return SUCCESS_TIP;
    }

    /**
     * voyage详情
     */
    @RequestMapping(value = "/detail/{voyageId}")
    @ResponseBody
    public Object detail(@PathVariable("voyageId") Integer voyageId) {
        return voyageService.selectById(voyageId);
    }

    /**
     * 获取所有轮船船号
     */
    @RequestMapping(value="/getImo")
    @ResponseBody
    public Object getVoyageNum(String condition)
    {
        EntityWrapper<Ship> shipEntityWrapper = new EntityWrapper<>();
        if (ToolUtil.isNotEmpty(condition) ) {
            shipEntityWrapper.like("imo", condition);
        }
        List<Map<String, Object>> list = this.shipService.selectMaps(shipEntityWrapper);
        List<String> listVoyageNum = new ArrayList<>();
        for(Map<String, Object> m:list)
        {
            listVoyageNum.add(m.get("imo").toString());
        }
        return listVoyageNum;
    }
}
