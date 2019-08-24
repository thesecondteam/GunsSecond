package com.stylefeng.guns.modular.system.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.modular.system.service.IBoxareaService;
import com.stylefeng.guns.modular.system.service.IVoyageshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/VoyageshipChart")
public class VoyageshipChartController extends BaseController {
    private String PREFIX = "/system/chart/";

    @Autowired
    private IVoyageshipService  voyageshipService;

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

        List<Map<String,Object>> listdata = this.voyageshipService.list();
        
        Object[][] array = new Object[listdata.size()][10];
        for(int i=0;i<listdata.size();i++){
            array[i] = listdata.get(i).values().toArray();
        }
        System.out.println(array);
        return array;



    }

}
