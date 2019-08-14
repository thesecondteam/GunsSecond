package com.stylefeng.guns.modular.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/showEchart")
public class EchartController {
    private String PREFIX="/system/echart/";
    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "echart.html";
    }


}
