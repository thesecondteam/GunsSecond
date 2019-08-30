package com.stylefeng.guns.modular.system.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.modular.system.service.IVoyageloadChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/Loadmonth")
public class LoadmonthChartController extends BaseController {
    private String PREFIX = "/system/chart/";

    @Autowired
    private IVoyageloadChartService voyageloadChartService;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "load_month.html";
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String month) {
        System.out.print("====================================");
        int monthN;
        if(month==""||month==null)
            monthN=8;
        else
            monthN=Integer.parseInt(month.trim());
        System.out.println("==============>>>>>>>>>"+monthN);
        List<Map<String,Object>> listdata = this.voyageloadChartService.list();
        Object[][] array = new Object[listdata.size()][5];
        for(int i=0;i<listdata.size();i++){
            array[i] = listdata.get(i).values().toArray();
        }
        int[] array1=new  int[60];

        int num=monthN;
        for(int i=0;i<48;i++)
        {
            array1[i]=Integer.parseInt(array[i][1].toString());
        }
        int[][] array2=new  int[12][4];
        for(int j=0;j<12;j++)
        {
            int i=j;
            array2[i][0]=array1[i];
            array2[i][1]=array1[i+12];
            array2[i][2]=array1[i+24];
            array2[i][3]=array1[i+36];
        }

        return array2[monthN-1];


    }

}
