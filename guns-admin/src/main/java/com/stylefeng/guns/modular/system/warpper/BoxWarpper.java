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
    private Map<Object,Object> stationmaps=dicFactory.getStationMap();
    private Map<Object,Object> boxtypemaps=dicFactory.getBoxTypeMap();
    private Map<Object,Object> goodstypemaps=dicFactory.getGoodsTypeMap();
    private Map<Object,Object> areatypemaps=dicFactory.getAreaTypeMap();
    private Map<Object,Object>harbourmaps=dicFactory.getHarbourMap();
    @Override
    public void warpTheMap(Map<String, Object> map) {

        Integer H=0; Integer L=1;

        if(H.equals(map.get("trantype")))
        {  map.put("transName","海运");
            map.put("startpointName",harbourmaps.get(map.get("startpoint")));
            map.put("endpointName",harbourmaps.get(map.get("endpoint")));}
        else if(L.equals(map.get("trantype"))) {
            map.put("transName", "陆运");
            map.put("startpointName",stationmaps.get(map.get("startpoint")));
            map.put("endpointName",stationmaps.get(map.get("endpoint")));
        }
        map.put("boxtypeName",boxtypemaps.get(map.get("boxtype")));
        map.put("goodstypeName",goodstypemaps.get(map.get("goodstype")));
        map.put("areatypeName",areatypemaps.get(map.get("areaid")));
        if(map.get("emptycode").equals(0))
            map.put("emptyName","是");
        else if(map.get("emptycode").equals(1))
            map.put("emptyName","否");
        else
            map.put("emptyName","未定义");
        if(map.get("statecode").equals(0))
            map.put("stateName","已禁用");
        else if(map.get("statecode").equals(1))
            map.put("stateName","已启用");
        else
                map.put("stateName","未定义");

    }

}
