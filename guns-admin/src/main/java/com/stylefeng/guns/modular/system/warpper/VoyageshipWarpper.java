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
public class VoyageshipWarpper extends BaseControllerWarpper {

    public VoyageshipWarpper(List<Map<String, Object>> list) {
        super(list);
    }

//    private DicFactory dicFactory=new DicFactory();
//    private Map<Object,Object> shipnamemaps=dicFactory.getShipNameMap();

    @Override
    protected void warpTheMap(Map<String, Object> map) {

//        map.put("shipName1",shipnamemaps.get(map.get("imo")));
//
//        System.out.print("========>"+map.get("imo"));

    }



}
