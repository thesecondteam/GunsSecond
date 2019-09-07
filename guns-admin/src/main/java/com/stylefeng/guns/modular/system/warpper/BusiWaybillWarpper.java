package com.stylefeng.guns.modular.system.warpper;

import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;
import com.stylefeng.guns.modular.system.factory.DicFactory;

import java.util.List;
import java.util.Map;

public class BusiWaybillWarpper extends BaseControllerWarpper{
    public BusiWaybillWarpper(List<Map<String,Object>> list){
        super(list);
    }

    private DicFactory dicFactory=new DicFactory();
    private Map<Object,Object> stationmaps=dicFactory.getStationMap();
    @Override
    public void warpTheMap(Map<String,Object> map){
        map.put("startpointName",stationmaps.get(map.get("startpoint")));
        map.put("endpointName",stationmaps.get(map.get("endpoint")));

        if(map.get("statecode").equals(0))
            map.put("statename","未完成");
        else if(map.get("statecode").equals(1))
            map.put("statename","已完成");
        else
            map.put("statename","未定义");
    }
}
