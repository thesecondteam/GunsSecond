package com.stylefeng.guns.modular.webSys.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.exception.BizExceptionEnum;
import com.stylefeng.guns.core.exception.GunsException;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.warpper.DictWarpper;
import com.stylefeng.guns.modular.system.warpper.DictGoodstypeWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.DictGoodstype;
import com.stylefeng.guns.modular.webSys.service.IDictGoodstypeService;

import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * goodstype控制器
 *
 * @author fengshuonan
 * @Date 2019-08-13 16:58:45
 */
@Controller
@RequestMapping("/dictGoodstype")
public class DictGoodstypeController extends BaseController {

    private String PREFIX = "/webSys/dictGoodstype/";

    @Autowired
    private IDictGoodstypeService dictGoodstypeService;

    /**
     * 跳转到goodstype首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "dictGoodstype.html";
    }

    /**
     * 跳转到添加goodstype
     */
    @RequestMapping("/dictGoodstype_add")
    public String dictGoodstypeAdd() {
        return PREFIX + "dictGoodstype_add.html";
    }

    /**
     * 跳转到修改goodstype
     */
    @RequestMapping("/dictGoodstype_update/{dictGoodstypeId}")
    public String dictGoodstypeUpdate(@PathVariable Integer dictGoodstypeId, Model model) {
        DictGoodstype dictGoodstype = dictGoodstypeService.selectById(dictGoodstypeId);
        model.addAttribute("dictGoodstype",dictGoodstype);//和js的参数保持一致
        LogObjectHolder.me().set(dictGoodstype);
        return PREFIX + "dictGoodstype_edit.html";
    }

    /**
     * 获取goodstype列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        EntityWrapper<DictGoodstype> dictGoodstypeEntityWrapper = new EntityWrapper<>();

        if (ToolUtil.isEmpty(condition)) {
            dictGoodstypeEntityWrapper.like("goodstype", condition);
        }
            // return dictGoodstypeService.selectList(null);
//      } else{
//         EntityWrapper<DictGoodstype>entityWrapper=new EntityWrapper<>();
//
//          return dictGoodstypeService.selectList(entityWrapper.like("goodstype",condition));
//     }
            List<Map<String, Object>> list = this.dictGoodstypeService.selectMaps(dictGoodstypeEntityWrapper);

            return new DictGoodstypeWrapper(list).warp();
        }

    /**
     * 新增goodstype
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(DictGoodstype dictGoodstype) {
        dictGoodstypeService.insert(dictGoodstype);
        return SUCCESS_TIP;
    }

    /**
     * 删除goodstype
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer dictGoodstypeId) {
        dictGoodstypeService.deleteById(dictGoodstypeId);
        return SUCCESS_TIP;
    }

    /**
     * 修改goodstype
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(DictGoodstype dictGoodstype) {
         if(ToolUtil.isEmpty(dictGoodstype)||dictGoodstype.getId()==null)
         {

             throw new GunsException(BizExceptionEnum.REQUEST_NULL);

         }
        dictGoodstypeService.updateById(dictGoodstype);
        return SUCCESS_TIP;
    }

    /**
     * goodstype详情
     */
    @RequestMapping(value = "/detail/{dictGoodstypeId}")
    @ResponseBody
    public Object detail(@PathVariable("dictGoodstypeId") Integer dictGoodstypeId) {
        return dictGoodstypeService.selectById(dictGoodstypeId);
    }


    /**
     * 禁用
     */
    @RequestMapping(value = "/disable")
    @ResponseBody
    public Object disable(DictGoodstype goodstype) {
        goodstype.setStatecode(0);
       dictGoodstypeService.updateById(goodstype);
        return SUCCESS_TIP;
    }
    /**
     * 启用
     */
    @RequestMapping(value = "/enable")
    @ResponseBody
    public Object enable(DictGoodstype goodstype) {
        goodstype.setStatecode(1);
        dictGoodstypeService.updateById(goodstype);
        return SUCCESS_TIP;
    }


    /**
     * 获取所有货物类型
     */
    @RequestMapping(value="/getGoodsId")
    @ResponseBody
    public Object getGoodsId(String condition)
    {
        EntityWrapper<DictGoodstype> dictGoodstypeEntityWrapper = new EntityWrapper<>();
        if (ToolUtil.isNotEmpty(condition) ) {
            dictGoodstypeEntityWrapper.like("goodstype", condition);
        }
        List<Map<String, Object>> list = this.dictGoodstypeService.selectMaps(dictGoodstypeEntityWrapper);
        return list;
    }
}
