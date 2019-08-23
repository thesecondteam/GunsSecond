package com.stylefeng.guns.modular.system.warpper;


import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;
import com.stylefeng.guns.modular.system.factory.DicFactory;

import java.util.List;
import java.util.Map;

public class AreaMoveWarpper extends BaseControllerWarpper {
    public AreaMoveWarpper(List<Map<String, Object>> list) {
        super(list);
    }
    private DicFactory dicFactory=new DicFactory();
    private Map<Object,Object> areaMaps=dicFactory.getAreaTypeMap();
    @Override
    public void warpTheMap(Map<String, Object> map) {

        map.put("areatypeName",areaMaps.get(map.get("areaid")));
    }
}
