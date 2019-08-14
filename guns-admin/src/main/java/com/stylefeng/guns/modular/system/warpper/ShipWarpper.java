package com.stylefeng.guns.modular.system.warpper;

import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;

import java.util.List;
import java.util.Map;

public class ShipWarpper extends BaseControllerWarpper {
    public ShipWarpper(List<Map<String, Object>> list) {
        super(list);
    }

    @Override
    public void warpTheMap(Map<String, Object> map) {
        if(map.get("statecode").equals(0))
            map.put("stateName","已禁用");
        else if(map.get("statecode").equals(1))
            map.put("stateName","已启用");
        else
            map.put("stateName","未定义");
    }
}
