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

        List<Map<String,Object>> listdata1 = this.voyageloadChartService.list1();
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



        List<Map<String,Object>> listdata2 = this.voyageloadChartService.list2();
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
        List<Map<String,Object>> listdata3 = this.voyageloadChartService.list3();
        Object[][] array3 = new Object[listdata3.size()][2];
        String[] str3=new String[12];
        int []array33=new int [12];

        for( int i=0;i<listdata3.size();i++){
            array3[i] = listdata3.get(i).values().toArray();
            str3[i]=array1[i][0].toString();
        }
        for(int i=0;i<12;i++)
        {
            for(int j=i;j<12;j++)
            {
                if(date[j].equals(str3[i]))
                {
                    array33[j]=Integer.parseInt(array3[i][1].toString());
                }
                else
                {
                    j++;
                }
            }
        }



        List<Map<String,Object>> listdata4 = this.voyageloadChartService.list4();
        Object[][] array4 = new Object[listdata4.size()][2];
        String[] str4=new String[12];
        int []array44=new int [12];

        for( int i=0;i<listdata4.size();i++){
            array4[i] = listdata4.get(i).values().toArray();
            str4[i]=array1[i][0].toString();
        }
        for(int i=0;i<12;i++)
        {
            for(int j=i;j<12;j++)
            {
                if(date[j].equals(str4[i]))
                {
                    array44[j]=Integer.parseInt(array4[i][1].toString());
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
            array5[i]=array11[i]+array22[i];
            array5[i+12]=array33[i]+array44[i];
        }

//        int[] array1=new  int[60];
//
//
//        int num=0;
//        for(int i=0;i<12;i++)
//        {
//            array1[i]=Integer.parseInt(array[i][1].toString());
//            num=Integer.parseInt(array[i+12][1].toString());
//            array1[i]+=num;
//        }
//        for(int i=12;i<24;i++)
//        {
//            array1[i]=Integer.parseInt(array[i+12][1].toString());
//            num=Integer.parseInt(array[i+24][1].toString());
//            array1[i]+=num;
//        }
        return array5;



    }

}
