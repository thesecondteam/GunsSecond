package com.stylefeng.guns.modular.system.warpper;

import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;
import com.stylefeng.guns.modular.system.factory.DicFactory;

import java.util.List;
import java.util.Map;

/**
 * 用户管理的包装类
 *
 * @author fengshuonan
 * @date 2017年2月13日 下午10:47:03
 */
public class UpdownWarpper extends BaseControllerWarpper {

    public UpdownWarpper(List<Map<String, Object>> list) {
        super(list);
    }

    private DicFactory dicFactory=new DicFactory();
    private Map<Object,Object> stationmaps=dicFactory.getStationMap();
    private Map<Object,Object> boxtypemaps=dicFactory.getBoxTypeMap();
    private Map<Object,Object> goodstypemaps=dicFactory.getGoodsTypeMap();
    private Map<Object,Object> areatypemaps=dicFactory.getAreaTypeMap();
    private Map<Object,Object>harbourmaps=dicFactory.getHarbourMap();
    @Override
    public void warpTheMap(Map<String, Object> map) {

        Integer Zero=0; Integer  One=1;

        if(Zero.equals(map.get("optype")))
        {
            map.put("opName","拆箱");
        }
        else if(One.equals(map.get("optype"))) {
            map.put("opName", "装箱");
        }
        map.put("areaName",areatypemaps.get(map.get("areaid")));

    }

}
