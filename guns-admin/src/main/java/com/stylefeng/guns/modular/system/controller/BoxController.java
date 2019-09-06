package com.stylefeng.guns.modular.system.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.annotion.BussinessLog;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.support.DateTime;
import com.stylefeng.guns.modular.system.factory.DicFactory;

import com.stylefeng.guns.modular.system.model.BusiMove;
import com.stylefeng.guns.modular.system.service.IBusiMoveService;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.warpper.BoxWarpper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.Box;
import com.stylefeng.guns.modular.system.service.IBoxService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2019-08-15 09:19:26
 */
@Controller
@RequestMapping("/box")
public class BoxController extends BaseController {

    private String PREFIX = "/system/box/";


    @Autowired
    private IBoxService boxService;
    /**************业务记录*************/
    private DicFactory dicFactory=new DicFactory();
    private Map<Object,Object> areaMaps=dicFactory.getAreaTypeMap();

    @Autowired
    private IBusiMoveService busiMoveService;
    /**************业务记录*************/

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "box.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/box_add")
    public String boxAdd() {
        return PREFIX + "box_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/box_update/{boxId}")
    public String boxUpdate(@PathVariable Integer boxId, Model model) {
        Box box = boxService.selectById(boxId);
        model.addAttribute("item",box);
        LogObjectHolder.me().set(box);
        return PREFIX + "box_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition,Integer viewtype) {
        List<Map<String, Object>> list;

        if (ToolUtil.isNotEmpty(condition)&&ToolUtil.isNotEmpty(viewtype)) {
            EntityWrapper<Box> boxwrapper=new EntityWrapper<Box>();
            boxwrapper.like("boxcode",condition).and().eq("emptycode",viewtype);
            list=boxService.selectMaps(boxwrapper);
        }
        else if(ToolUtil.isNotEmpty(viewtype))
        {
            EntityWrapper<Box> boxwrapper=new EntityWrapper<Box>();
            boxwrapper.eq("emptycode",viewtype);
            list=boxService.selectMaps(boxwrapper);
        }
        else
        {
            EntityWrapper<Box> boxwrapper=new EntityWrapper<Box>();
            boxwrapper.eq("emptycode",0);
            list=boxService.selectMaps(boxwrapper);
        }
        return new BoxWarpper(list).warp() ;
    }

    /**
     * 新增
     */
    @BussinessLog(value = "添加箱子", key = "boxId")
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Box box) {
        box.setAreaid(1);
        boxService.insert(box);
        return SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @BussinessLog(value = "删除箱子", key = "boxId")
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer boxId) {
        boxService.deleteById(boxId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @BussinessLog(value = "修改箱子", key = "boxId")
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Box box) {

        /**************业务记录*************/
        BusiMove busiMove=new BusiMove();
        Map<String,Object>m=new HashMap<>();
        m.put("id",box.getId());
        List<Box> bList=boxService.selectByMap(m);//只有一条
        for(Box b:bList)
        {
            busiMove.setOldArea(areaMaps.get(b.getAreaid()).toString());//旧地址
        }
        busiMove.setNewArea(areaMaps.get(box.getAreaid()).toString());//新地址
        busiMove.setBoxCode(box.getBoxcode());//箱子编码
        busiMove.setOperTime(new DateTime());//当前时间
        busiMove.setOperPerson(ShiroKit.getUser().getName());
        if(busiMove.getOldArea()!=busiMove.getNewArea())//新旧地址一样就不更新业务日志
        {
            busiMove.setOpeType("修改地址");
            busiMoveService.insert(busiMove);
        }
        /**************业务记录*************/


        boxService.updateById(box);
        return SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{boxId}")
    @ResponseBody
    public Object detail(@PathVariable("boxId") Integer boxId) {
        return boxService.selectById(boxId);
    }
    /**
     * 获取所有集装箱箱号
     */
    @RequestMapping(value = "/getBoxCode")
    @ResponseBody
    public Object geBoxCode(String condition)
    {
        EntityWrapper<Box> boxEntityWrapper = new EntityWrapper<>();
        if(ToolUtil.isNotEmpty(condition)){
            boxEntityWrapper.like("boxcode",condition);
        }
        List<Map<String,Object>> list = this.boxService.selectMaps(boxEntityWrapper);
        List<String> listBoxCode = new ArrayList<>();
        for(Map<String,Object> m:list){
            listBoxCode.add(m.get("boxcode").toString());
        }
        return listBoxCode;
    }

    /**
     * 获取特定集装箱
     */
    @RequestMapping(value = "/getBoxType")
    @ResponseBody
    public Object getBoxType(@RequestParam(value = "BoxCode")String BoxCode){
        EntityWrapper<Box> boxEntityWrapper = new EntityWrapper<>();
        if(ToolUtil.isNotEmpty(BoxCode)){
            boxEntityWrapper.eq("boxcode",BoxCode);
        }
        Object boxType = this.boxService.selectById(boxEntityWrapper);
        return boxType;
    }
}
