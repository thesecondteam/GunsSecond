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
public class OrderWarpper extends BaseControllerWarpper {

    public OrderWarpper(List<Map<String, Object>> list) {
        super(list);
    }

    private DicFactory dicFactory=new DicFactory();
    private Map<Object,Object> stationmaps=dicFactory.getStationMap();
    private Map<Object,Object>harbourmaps=dicFactory.getHarbourMap();
    private Map<Object,Object> boxtypemaps=dicFactory.getBoxTypeMap();
    private Map<Object,Object> goodstypemaps=dicFactory.getGoodsTypeMap();
    private Map<Object,Object> areatypemaps=dicFactory.getAreaTypeMap();
    @Override
    public void warpTheMap(Map<String, Object> map) {

//        map.put("startpointName",stationmaps.get(map.get("startpoint")));
          map.put("endpointName",stationmaps.get(map.get("endpoint")));

//        map.put("boxtypeName",boxtypemaps.get(map.get("boxtype")));

        map.put("goodstypeName",goodstypemaps.get(map.get("goodstype")));
//        map.put("areatypeName",areatypemaps.get(map.get("areaid")));


        if(map.get("trantype").equals(0))
            map.put("transName","海运");
        else if(map.get("trantype").equals(1))
            map.put("transName","陆运");

        if(map.get("ordercode").equals(0))
            map.put("stateName","未处理");
        else if(map.get("ordercode").equals(1))
            map.put("stateName","已装箱");
        else
                map.put("stateName","已完成");

    }

}
