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
public class BoxWarpper extends BaseControllerWarpper {

    public BoxWarpper(List<Map<String, Object>> list) {
        super(list);
    }

    private DicFactory dicFactory=new DicFactory();
    private Map<Object,Object> maps=dicFactory.getStationNameMap();
    @Override
    public void warpTheMap(Map<String, Object> map) {

        map.put("startpointName",maps.get(map.get("startpoint")));
        map.put("endpointName",maps.get(map.get("endpoint")));
        if(map.get("statecode").equals(0))
            map.put("stateName","已禁用");
        else if(map.get("statecode").equals(1))
            map.put("stateName","已启用");
        else
            map.put("stateName","未定义");
    }

}
