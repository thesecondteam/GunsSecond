package com.stylefeng.guns.modular.system.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.model.Ship;
import com.stylefeng.guns.modular.system.service.IShipService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/ShipChart")
public class ShipChartController extends BaseController {
    private String PREFIX = "/system/chart/";
    @Resource
    IShipService shipService;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "ship_country.html";
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition){
        EntityWrapper<Ship> shipEntityWrapper = new EntityWrapper<>();
        if (ToolUtil.isNotEmpty(condition) ) {
            shipEntityWrapper.like("imo", condition);
        }
        List<Map<String, Object>> list = this.shipService.selectMaps(shipEntityWrapper);
        Map<String,Integer>shipName=new HashMap<String,Integer>();
        System.out.println(list);
        //if(shipName.containsKey())
        for (Map<String, Object> s:list)
        {
            if(shipName.containsKey(s.get("country").toString()))
            {
                int num=Integer.parseInt(shipName.get(s.get("country")).toString())+1;
                shipName.put(s.get("country").toString(),num);
            }
            else
            {
                shipName.put(s.get("country").toString(),1);
            }

        }
        List<Map<String,String>>listShip=new ArrayList<>();
        for(Map.Entry<String, Integer> entry : shipName.entrySet())
        {
            Map<String,String> m=new HashMap<>();
            m.put("name",entry.getKey());
            m.put("value",entry.getValue().toString());
            listShip.add(m);
        }
        System.out.println(shipName);
        return listShip;
    }
}
