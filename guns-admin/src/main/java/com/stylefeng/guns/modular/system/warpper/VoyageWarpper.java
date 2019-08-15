package com.stylefeng.guns.modular.system.warpper;

import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.util.ToolUtil;

import java.util.Map;
/*
* 包装航次信息
* */

public class VoyageWarpper extends BaseControllerWarpper{
    public VoyageWarpper(Object list){
        super(list);
    }

    @Override
    public void warpTheMap(Map<String,Object> map){
        /*map.put("addFile","warpperName");*/
    }
}
