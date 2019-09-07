package com.stylefeng.guns.modular.system.warpper;

import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.factory.DicFactory;

import java.util.List;
import java.util.Map;
/*
* 包装航次信息
* */

public class VoyageWarpper extends BaseControllerWarpper{
    public VoyageWarpper(List<Map<String,Object>> list){
        super(list);
    }

    private DicFactory dicFactory=new DicFactory();
    private Map<Object,Object> harbourmaps=dicFactory.getHarbourMap();
    private Map<Object,Object> getHarbourmaps=dicFactory.getHarbourMap();

    @Override
    public void warpTheMap(Map<String,Object> map){

        Integer startpoint = null;
        if(ToolUtil.isNotEmpty(map.get("startpoint"))) {
             startpoint = Integer.parseInt(map.get("startpoint").toString());
        }
/*        System.out.println(startpoint);
        System.out.println(getHarbourmaps);
        System.out.println(getHarbourmaps.get(startpoint));*/
        map.put("startpointName",getHarbourmaps.get(startpoint));
        Integer endpoint = null;
        if(ToolUtil.isNotEmpty((map.get("endpoint")))){
            endpoint = Integer.parseInt(map.get("startpoint").toString());
        }
        map.put("endpointName",harbourmaps.get(endpoint));

        if(map.get("statecode").equals(0))
            map.put("statename","未完成");
        else if(map.get("statecode").equals(1))
            map.put("statename","已完成");
        else if(map.get("statecode").equals(""))
            map.put("statename","未定义");

    }
}
