package com.stylefeng.guns.modular.system.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.modular.system.model.Box;
import com.stylefeng.guns.modular.system.model.Voyage;
import com.stylefeng.guns.modular.system.model.Voyagedet;
import com.stylefeng.guns.modular.system.service.IBoxService;
import com.stylefeng.guns.modular.system.service.IBusiWaybillService;
import com.stylefeng.guns.modular.system.service.IVoyageService;
import com.stylefeng.guns.modular.system.warpper.BoxWarpper;
import com.stylefeng.guns.modular.webSys.service.IBusiOrderService;
import io.swagger.models.auth.In;
import net.sf.jsqlparser.statement.select.Distinct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/getInfoHome")
public class HomeInfoController {

    @Autowired
    private IVoyageService voyageService;
    @Autowired
    private IBusiWaybillService busiWaybillService;
    @Autowired
    private IBusiOrderService busiOrderService;
    @Autowired
    private IBoxService boxService;
    @RequestMapping("/homeInfo")
    @ResponseBody
    public Object getHomeInfo()
    {
        List<Integer> list=new ArrayList<>();
        Integer voyageBoxNumsEnd=voyageService.getVoyageBoxNumsEnd();//进
        Integer voyageBoxNumsEndMonth=voyageService.getVoyageBoxNumsEndMonth(new Date());
        Integer voyageBoxNumsStart=voyageService.getVoyageBoxNumsStart();//出
        Integer voyageBoxNumsStartMonth=voyageService.getVoyageBoxNumsStartMonth(new Date());

        Integer wayBoxNumsEnd=busiWaybillService.getWayBoxNumsEnd();
        Integer wayBoxNumsEndMonth=busiWaybillService.getWayBoxNumsEndMonth(new Date());
        Integer wayBoxNumsStart=busiWaybillService.getWayBoxNumsStart();
        Integer wayBoxNumsStartMonth=busiWaybillService.getWayBoxNumsStartMonth(new Date());

        Integer thisMonthIn=voyageBoxNumsEndMonth+wayBoxNumsEndMonth;//当月进
        list.add(thisMonthIn);
        Integer allIn=voyageBoxNumsEnd+wayBoxNumsEnd;//总进
        list.add(allIn);
        Integer thisMonthOut=voyageBoxNumsStartMonth+wayBoxNumsStartMonth;//当月出
        list.add(thisMonthOut);
        Integer allOut=voyageBoxNumsStart+wayBoxNumsStart;//总出
        list.add(allOut);

        Integer boxesNum=boxService.getAllBoxesNum();//当前货箱数
        list.add(boxesNum);
        Integer flue=allIn-allOut;//流量 总进-总出
        list.add(flue);

        Integer orderNumsStartMonth=busiOrderService.getOrderNumsStartMonth(new Date());//当月订单
        list.add(orderNumsStartMonth);
        Integer orderNumsStart=busiOrderService.getOrderNumsStart();//订单总量
        list.add(orderNumsStart);

        return list;
    }
}
