package com.stylefeng.guns.modular.system.factory;

import com.stylefeng.guns.core.util.SpringContextHolder;
import com.stylefeng.guns.modular.system.dao.DictStationMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DicFactory {
    private DictStationMapper stationMapper = SpringContextHolder.getBean(DictStationMapper.class);

    public Map<Object,Object> getStationNameMap(){
        Map<Object,Object> map=new HashMap<Object,Object>();
        List<Map<String, Object>> list = stationMapper.selectMaps(null);
        for (Integer i=0;i<list.size();i++) {
            map.put(list.get(i).get("id"),list.get(i).get("name"));
        }
        return map;
    }
}
