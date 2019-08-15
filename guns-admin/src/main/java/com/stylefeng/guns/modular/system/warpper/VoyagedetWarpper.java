package com.stylefeng.guns.modular.system.warpper;

import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.util.ToolUtil;

import java.util.Map;
/*
 * 包装航次详情信息
 * */
public class VoyagedetWarpper extends BaseControllerWarpper{
    public VoyagedetWarpper(Object list){
        super(list);
    }

    @Override
    public void warpTheMap(Map<String,Object> map){
        /*map.put("addFile","warpperName");*/
    }
}
