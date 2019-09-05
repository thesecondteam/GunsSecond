
package com.stylefeng.guns.modular.system.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.stylefeng.guns.modular.system.model.*;
import com.stylefeng.guns.modular.system.service.IBusiWaybillService;
import com.stylefeng.guns.modular.system.service.IDictStationService;
import com.stylefeng.guns.modular.system.service.IHarbourService;
import com.stylefeng.guns.modular.system.service.IVoyageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.schema.Entry;

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
    @Autowired
    private IHarbourService harbourService;
    @Autowired
    private IVoyageService voyageService;
    private String PREFIX="/system/echart/";
    /**
     * 跳转到首页
     */
    @RequestMapping("/china")
    public String index() {
        return PREFIX + "echart_china.html";
    }
    @RequestMapping("/world")
    public String index_word() {
        return PREFIX + "echart_world.html";
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
                //URL url = new URL("http://api.map.baidu.com/geocoder?address=秦皇岛&output=json&key=37492c0ee6f924cb5e934fa08c6b1676");
                //打开http连接
                URL url = new URL("http://api.map.baidu.com/geocoder?address="+province+"&output=json&key=37492c0ee6f924cb5e934fa08c6b1676");
                //打开http连接*/
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

                //地点经纬度
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
        if(!cmap.containsKey("秦皇岛"))
        {   float [] _place=new float[2];
            _place[0]=(float)119.608531;
            _place[1]=(float) 39.941748;
            cmap.put("秦皇岛",_place);
        }

        //返回秦皇岛到各地的箱数
        List<BusiWaybill> listBW=busiWaybillService.selectList(null);
        List<BusiWaybill> listGetBW=new ArrayList<>();
        System.out.println(listBW);
        Map<String,Object>mapB=new HashMap<>();
        //得到 秦皇岛——其他地方 的对象
        for(BusiWaybill B:listBW)
        {   if(B.getStartpoint()==null||B.getEndpoint()==null)
            continue;
            if(B.getStartpoint()==7&&B.getEndpoint()!=null)//7代表秦皇岛本地
                listGetBW.add(B);
        }
        //给各地累加箱数

        for(BusiWaybill bwb:listGetBW)
        {
            DictStation listStation = this.dictStationService.selectById(bwb.getEndpoint());

            if(mapB.containsKey(listStation.getProvince()))
            {

                mapB.put(listStation.getProvince(),bwb.getBoxnumber()+Integer.parseInt(mapB.get(listStation.getProvince()).toString()));
            }
            else
            {
                mapB.put(listStation.getProvince(),bwb.getBoxnumber());
            }
        }
        //返回markpoint使用
        List<Map<String,Object>> list2=new ArrayList<>();
        for(Map.Entry<String,Object> me:mapB.entrySet()) {
            Map<String,Object> mf=new HashMap<>();
            mf.put("name", me.getKey());
            mf.put("value", me.getValue());
            list2.add(mf);
        }
        //

        List<List<Map<String,Object>>> list4=new ArrayList<>();
        for(Map<String,Object> mp2:list2)
        {   List<Map<String,Object>> list3=new ArrayList<>();
            Map<String,Object> mf=new HashMap<>();
            mf.put("name","秦皇岛");
            list3.add(mf);
            Map<String,Object> mf2=new HashMap<>();
            mf2.put("name", mp2.get("name"));
            mf2.put("value",mp2.get("value"));
            list3.add(mf2);
            list4.add(list3);
        }



        List<Object> reList=new ArrayList<>();
        reList.add(cmap);//返回各地经纬度
        System.out.println("====================>"+cmap);
        reList.add(list2);//返回箱数
        System.out.println("====================>"+list2);
        System.out.println("====================>"+list4);
                                                        /*
                                                        {
                                                            "name":"江苏省",
                                                            "value":205
                                                        }*/
        reList.add(list4);//返回这样的格式 [{name:'天津'}, {name:'秦皇岛',value:95}],
                        //[{name:'河北省'}, {name:'北京',value:90}]
        return  reList;//一个接口返回三组数据
    }
    @RequestMapping("/getWorld")
    @ResponseBody
    public Object getWorld()
    {
        List<Harbour> list=harbourService.selectList(null);
        Set<String> point=new HashSet<String>();
        Map<String,float[]> cmap=new HashMap<>();
        for (Harbour l:list) {
            if(l.getProvince()==null)continue;
            point.add(l.getProvince());
        }
        for (String province:point)
        {
            StringBuffer buffer = new StringBuffer();
            try {
                //建立URL，把请求地址给补全，其中urlencode（）方法用于把params里的参数给取出来
                //URL url = new URL("http://api.map.baidu.com/geocoder?address=秦皇岛&output=json&key=37492c0ee6f924cb5e934fa08c6b1676");
                //打开http连接
                URL url = new URL("http://api.map.baidu.com/geocoder?address="+province+"&output=json&key=37492c0ee6f924cb5e934fa08c6b1676");
                //打开http连接*/
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

                //地点经纬度
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
        if(!cmap.containsKey("秦皇岛"))
        {   float [] _place=new float[2];
            _place[0]=(float)119.608531;
            _place[1]=(float) 39.941748;
            cmap.put("秦皇岛",_place);
        }

        //返回秦皇岛到各地的箱数
        List<Voyage> listBW=voyageService.selectList(null);
        List<Voyage> listGetBW=new ArrayList<>();
        System.out.println(listBW);
        Map<String,Object>mapB=new HashMap<>();
        //得到 秦皇岛——其他地方 的对象
        for(Voyage B:listBW)
        {   if(B.getStartpoint()==null||B.getEndpoint()==null)
            continue;
            if(B.getStartpoint()==7&&B.getEndpoint()!=null)//7代表秦皇岛本地
                listGetBW.add(B);
        }
        //给各地累加箱数

        for(Voyage bwb:listGetBW)
        {
            Harbour listStation = this.harbourService.selectById(bwb.getEndpoint());

            if(mapB.containsKey(listStation.getProvince()))
            {

                mapB.put(listStation.getProvince(),bwb.getBoxnumber()+Integer.parseInt(mapB.get(listStation.getProvince()).toString()));
            }
            else
            {
                mapB.put(listStation.getProvince(),bwb.getBoxnumber());
            }
        }
        //返回markpoint使用
        List<Map<String,Object>> list2=new ArrayList<>();
        for(Map.Entry<String,Object> me:mapB.entrySet()) {
            Map<String,Object> mf=new HashMap<>();
            mf.put("name", me.getKey());
            mf.put("value", me.getValue());
            list2.add(mf);
        }
        //

        List<List<Map<String,Object>>> list4=new ArrayList<>();
        for(Map<String,Object> mp2:list2)
        {   List<Map<String,Object>> list3=new ArrayList<>();
            Map<String,Object> mf=new HashMap<>();
            mf.put("name","秦皇岛");
            list3.add(mf);
            Map<String,Object> mf2=new HashMap<>();
            mf2.put("name", mp2.get("name"));
            mf2.put("value",mp2.get("value"));
            list3.add(mf2);
            list4.add(list3);
        }



        List<Object> reList=new ArrayList<>();
        reList.add(cmap);//返回各地经纬度
        System.out.println("====================>"+cmap);
        reList.add(list2);//返回箱数
        System.out.println("====================>"+list2);
        System.out.println("====================>"+list4);
                                                        /*
                                                        {
                                                            "name":"江苏省",
                                                            "value":205
                                                        }*/
        reList.add(list4);//返回这样的格式 [{name:'天津'}, {name:'秦皇岛',value:95}],
        //[{name:'河北省'}, {name:'北京',value:90}]
        return  reList;//一个接口返回三组数据
    }
}