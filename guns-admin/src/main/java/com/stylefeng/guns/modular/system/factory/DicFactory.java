package com.stylefeng.guns.modular.system.factory;

import com.stylefeng.guns.core.util.SpringContextHolder;
import com.stylefeng.guns.modular.system.dao.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DicFactory {
    private DictStationMapper stationMapper = SpringContextHolder.getBean(DictStationMapper.class);
    private DictGoodstypeMapper goodstypeMapper = SpringContextHolder.getBean(DictGoodstypeMapper.class);
    private BoxtypeMapper boxtypeMapper=SpringContextHolder.getBean(BoxtypeMapper.class);
    private AreaMapper areatypeMapper=SpringContextHolder.getBean(AreaMapper.class);
    private ShipMapper shipMapper=SpringContextHolder.getBean(ShipMapper.class);

    /*
     * 站点名称封装
     */
    public Map<Object,Object> getStationMap(){
        Map<Object,Object> map=new HashMap<Object,Object>();
        List<Map<String, Object>> list = stationMapper.selectMaps(null);
        for (Integer i=0;i<list.size();i++) {

            map.put(list.get(i).get("id"),list.get(i).get("name"));
        }
        return map;
    }

    /*
     * 集装箱类型封装
     */
    public Map<Object,Object> getBoxTypeMap(){
        Map<Object,Object> map=new HashMap<Object,Object>();
        List<Map<String, Object>> list = boxtypeMapper.selectMaps(null);
        for (Integer i=0;i<list.size();i++) {
            map.put(list.get(i).get("id"),list.get(i).get("type"));
        }
        return map;
    }

    /*
     * 货物类型封装
     */
    public Map<Object,Object> getGoodsTypeMap(){
        Map<Object,Object> map=new HashMap<Object,Object>();
        List<Map<String, Object>> list = goodstypeMapper.selectMaps(null);

        for (Integer i=0;i<list.size();i++) {
            map.put(list.get(i).get("id"),list.get(i).get("goodstype"));
        }
        System.out.println("===========qqqqqqqqqq===>"+map);
        return map;
    }

    /*
     * 场地类型封装
     */
    public Map<Object,Object> getAreaTypeMap(){
        Map<Object,Object> map=new HashMap<Object,Object>();
        List<Map<String, Object>> list = areatypeMapper.selectMaps(null);
        for (Integer i=0;i<list.size();i++) {
            map.put(list.get(i).get("id"),list.get(i).get("areatype"));
        }
        return map;
    }

    /*
     * 场地类型封装
     */
    public Map<Object,Object> getShipNameMap(){
        Map<Object,Object> map=new HashMap<Object,Object>();
        List<Map<String, Object>> list = shipMapper.selectMaps(null);
        for (Integer i=0;i<list.size();i++) {
            map.put(list.get(i).get("imo"),list.get(i).get("shipcname"));
        }
        return map;
    }
}
