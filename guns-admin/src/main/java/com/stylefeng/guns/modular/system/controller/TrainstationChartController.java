package com.stylefeng.guns.modular.system.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.modular.system.service.ITrainstationChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/TrainstationChart")
public class TrainstationChartController extends BaseController {
    private String PREFIX = "/system/chart/";

    @Autowired
    private ITrainstationChartService trainstationService;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "train_station.html";
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list() {

        List<Map<String,Object>> listdata = this.trainstationService.list();
        Object[][] array = new Object[listdata.size()][10];
        for(int i=0;i<listdata.size();i++){
            array[i] = listdata.get(i).values().toArray();
        }
        System.out.println(array);
        return array;

    }

}
