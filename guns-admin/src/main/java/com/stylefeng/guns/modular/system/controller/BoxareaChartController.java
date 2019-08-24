package com.stylefeng.guns.modular.system.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.model.Boxtype;
import com.stylefeng.guns.modular.system.service.IBoxareaService;
import com.stylefeng.guns.modular.system.service.IBoxtypeService;
import com.stylefeng.guns.modular.system.warpper.BoxtypeWarpper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/BoxareaChart")
public class BoxareaChartController extends BaseController {
    private String PREFIX = "/system/chart/";

    @Autowired
    private IBoxareaService boxareaService;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "box_area.html";
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list() {

        List<Map<String,Object>> listdata = this.boxareaService.list();
        Object[][] array = new Object[listdata.size()][10];
        for(int i=0;i<listdata.size();i++){
            array[i] = listdata.get(i).values().toArray();
        }
        System.out.println(array);
        return array;



    }

}
