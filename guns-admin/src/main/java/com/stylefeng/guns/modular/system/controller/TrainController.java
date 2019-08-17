package com.stylefeng.guns.modular.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.warpper.TrainWarpper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.Train;
import com.stylefeng.guns.modular.system.service.ITrainService;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 火车控制器
 *
 * @author fengshuonan
 * @Date 2019-08-14 14:51:26
 */
@Controller
@RequestMapping("/train")
public class TrainController extends BaseController {

    private String PREFIX = "/system/train/";

    @Autowired
    private ITrainService trainService;

    /**
     * 跳转到火车首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "train.html";
    }

    /**
     * 跳转到添加火车
     */
    @RequestMapping("/train_add")
    public String trainAdd() {
        return PREFIX + "train_add.html";
    }

    /**
     * 跳转到修改火车
     */
    @RequestMapping("/train_update/{trainId}")
    public String trainUpdate(@PathVariable Integer trainId, Model model) {
        Train train = trainService.selectById(trainId);
        model.addAttribute("item",train);
        LogObjectHolder.me().set(train);
        return PREFIX + "train_edit.html";
    }

    /**
     * 获取火车列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        EntityWrapper<Train> trainEntityWrapper = new EntityWrapper<>();
        if (ToolUtil.isNotEmpty(condition) ) {
            trainEntityWrapper.like("traincode", condition);
        }
        List<Map<String, Object>> list = this.trainService.selectMaps(trainEntityWrapper);
        return new TrainWarpper(list).warp();
    }

    /**
     * 新增火车
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Train train) {
        List<Train>l=trainService.selectList(null);
        Set<String> s=new HashSet<String>();
        for(Train d:l) {
            s.add(d.getTraincode());
        }
        if (s.contains(train.getTraincode()))
        {
            String str="{code:2335,message:\"添加错误，火车重复！\"}";
            System.out.println(str);
            JSONObject jsonObject=JSONObject.parseObject(str);
            return jsonObject;
        }
        trainService.insert(train);
        return SUCCESS_TIP;
    }

    /**
     * 删除火车
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer trainId) {
        trainService.deleteById(trainId);
        return SUCCESS_TIP;
    }

    /**
     * 修改火车
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Train train) {
        List<Train>l=trainService.selectList(null);
        Set<String> s=new HashSet<String>();
        for(Train d:l) {
            s.add(d.getTraincode());
        }
        if (s.contains(train.getTraincode()))
        {
            String str="{code:2335,message:\"修改错误，火车重复！\"}";
            System.out.println(str);
            JSONObject jsonObject=JSONObject.parseObject(str);
            return jsonObject;
        }
        trainService.updateById(train);
        return SUCCESS_TIP;
    }

    /**
     * 禁用
     */
    @RequestMapping(value = "/disable")
    @ResponseBody
    public Object disable(Train train) {
        train.setStatecode(0);
        trainService.updateById(train);
        return SUCCESS_TIP;
    }
    /**
     * 启用
     */
    @RequestMapping(value = "/enable")
    @ResponseBody
    public Object enable(Train train) {
        train.setStatecode(1);
        trainService.updateById(train);
        return SUCCESS_TIP;
    }

    /**
     * 火车详情
     */
    @RequestMapping(value = "/detail/{trainId}")
    @ResponseBody
    public Object detail(@PathVariable("trainId") Integer trainId) {
        return trainService.selectById(trainId);
    }
}
