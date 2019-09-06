package com.stylefeng.guns.modular.system.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.modular.system.service.IVoyagewillChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/VoyagewillChart")
public class VoyagewillChartController extends BaseController {
    private String PREFIX = "/system/chart/";

    @Autowired
    private IVoyagewillChartService voyagewillChartService;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "voyage_will.html";
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list() {

//        List<Map<String,Object>> listdata = voyagewillChartService.list1();
//        Object[][] array = new Object[listdata.size()][2];
//        for(int i=0;i<listdata.size();i++){
//            array[i] = listdata.get(i).values().toArray();
//            String str=array[i][0].toString();
//            System.out.println(str);
//        }
////        for(int i=0;i<listdata.size();i++){
////            array[i][0]
////        }
//        int[] array1=new  int[25];
//        String[] date={"201901","201902","201903","201904","201905","201906","201907","201908",
//                "201909","201910","201911","201912"};
//
//        int num=0;
//        for(int i=0;i<24;i++)
//        {
//            array1[i]=Integer.parseInt(array[i][1].toString());
//        }
        List<Map<String,Object>> listdata1 = this.voyagewillChartService.list1();
        Object[][] array1 = new Object[listdata1.size()][2];
        String[] date={"201901","201902","201903","201904","201905","201906","201907","201908",
                "201909","201910","201911","201912"};
        String[] str1=new String[12];
        int []array11=new int [12];

        for( int i=0;i<listdata1.size();i++){
            array1[i] = listdata1.get(i).values().toArray();
            str1[i]=array1[i][0].toString();
        }
        for(int i=0;i<12;i++)
        {
            for(int j=i;j<12;j++)
            {
                if(date[j].equals(str1[i]))
                {
                    array11[j]=Integer.parseInt(array1[i][1].toString());
                }
                else
                {
                    j++;
                }
            }
        }



        List<Map<String,Object>> listdata2 = this.voyagewillChartService.list2();
        Object[][] array2 = new Object[listdata2.size()][2];
        String[] str2=new String[12];
        int []array22=new int [12];

        for( int i=0;i<listdata2.size();i++){
            array2[i] = listdata2.get(i).values().toArray();
            str2[i]=array2[i][0].toString();
        }
        for(int i=0;i<12;i++)
        {
            for(int j=i;j<12;j++)
            {
                if(date[j].equals(str2[i]))
                {
                    array22[j]=Integer.parseInt(array2[i][1].toString());
                }
                else
                {
                    j++;
                }
            }
        }
        int []array5=new int [24];
        for(int i=0;i<12;i++)
        {
            array5[i]=array11[i];
            array5[i+12]=array22[i];
        }
        return array5;




    }

}
