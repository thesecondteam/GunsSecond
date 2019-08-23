package com.stylefeng.guns.modular.system.controller;

import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.modular.system.model.Box;
import com.stylefeng.guns.modular.system.service.IBoxService;
import com.stylefeng.guns.modular.system.warpper.AreaMoveWarpper;
import com.stylefeng.guns.modular.system.warpper.BoxWarpper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/areaMove")
public class AreaMoveController {
    private String PREFIX="/system/areamove/";
    @Autowired IBoxService boxService;
    @RequestMapping("")
    public String index(){
        return PREFIX+"areabox.html";
    }
    @RequestMapping("/list")
    @ResponseBody
    public Object list(String condition) {
        List<Map<String, Object>> list;
        list=boxService.selectMaps(null);
        return new AreaMoveWarpper(list).warp() ;
    }
    @RequestMapping("/areamove/{boxId}")
    public String areamove(@PathVariable Integer boxId, Model model){
        Box box = boxService.selectById(boxId);
        model.addAttribute("item",box);
        LogObjectHolder.me().set(box);
        return PREFIX+"areamove.html";
    }
}
