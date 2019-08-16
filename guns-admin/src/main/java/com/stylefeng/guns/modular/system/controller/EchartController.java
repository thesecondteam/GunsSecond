package com.stylefeng.guns.modular.system.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.stylefeng.guns.modular.system.model.BusiWaybill;
import com.stylefeng.guns.modular.system.model.DictStation;
import com.stylefeng.guns.modular.system.service.IBusiWaybillService;
import com.stylefeng.guns.modular.system.service.IDictStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@Controller
@RequestMapping("/showEchart")
public class EchartController {
    @Autowired
    private IDictStationService dictStationService;
    @Autowired
    private IBusiWaybillService busiWaybillService;
    private String PREFIX="/system/echart/";
    /**
     * 跳转到首页
     */
    @RequestMapping("/china")
    public String index() {
        return PREFIX + "echart_china.html";
    }
    @RequestMapping("/word")
    public String index_word() {
        return PREFIX + "echart_word.html";
    }
    @RequestMapping("/getChina")
    @ResponseBody
    public Object getChina()
    {
        List<DictStation> list=dictStationService.selectList(null);
        Set<String> point=new HashSet<String>();
        Map<String,float[]> cmap=new HashMap<>();
        for (DictStation l:list) {
            if(l.getProvince()==null)continue;
            point.add(l.getProvince());
        }
        for (String province:point)
        {
            StringBuffer buffer = new StringBuffer();
            try {
                //建立URL，把请求地址给补全，其中urlencode（）方法用于把params里的参数给取出来
                URL url = new URL("http://api.map.baidu.com/geocoder?address="+province+"&output=json&key=37492c0ee6f924cb5e934fa08c6b1676");
                //打开http连接
                HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
                httpUrlConn.setDoInput(true);
                httpUrlConn.setRequestMethod("GET");
                httpUrlConn.connect();

                //获得输入
                InputStream inputStream = httpUrlConn.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                //将bufferReader的值给放到buffer里
                String str = null;
                while ((str = bufferedReader.readLine()) != null) {
                    buffer.append(str);
                }
                //buffer转String
                String res=buffer.toString();
                JSONObject jsonObject=JSONObject.parseObject(res);
                jsonObject=jsonObject.getJSONObject("result");
                jsonObject=jsonObject.getJSONObject("location");
                String lng=jsonObject.getString("lng");
                String lat=jsonObject.getString("lat");
                float [] f_place=new float[2];
                f_place[0]=Float.parseFloat(lng);
                f_place[1]=Float.parseFloat(lat);
                cmap.put(province,f_place);
                System.out.println(JSONObject.toJSON(cmap));


                //关闭bufferReader和输入流
                bufferedReader.close();
                inputStreamReader.close();
                inputStream.close();
                inputStream = null;
                //断开连接
                httpUrlConn.disconnect();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        return  busiWaybillService.selectList(null);
    }

}
