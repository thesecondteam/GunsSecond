package com.stylefeng.guns.modular.system.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.modular.system.service.IVoyageloadChartService;
import com.stylefeng.guns.modular.system.service.IVoyageshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/VoyageloadChart")
public class VoyageloadChartController extends BaseController {
    private String PREFIX = "/system/chart/";

    @Autowired
    private IVoyageloadChartService voyageloadChartService;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "voyage_load.html";
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list() {

        List<Map<String,Object>> listdata = this.voyageloadChartService.list();
        Object[][] array = new Object[listdata.size()+5][10];
        for(int i=0;i<listdata.size();i++){
            array[i] = listdata.get(i).values().toArray();
        }
        int[] array1=new  int[60];

        int num=0;
        for(int i=0;i<12;i++)
        {
            array1[i]=Integer.parseInt(array[i][1].toString());
            num=Integer.parseInt(array[i+12][1].toString());
            array1[i]+=num;
        }
        for(int i=12;i<24;i++)
        {
            array1[i]=Integer.parseInt(array[i+12][1].toString());
            num=Integer.parseInt(array[i+24][1].toString());
            array1[i]+=num;
        }
        return array1;



    }

}
