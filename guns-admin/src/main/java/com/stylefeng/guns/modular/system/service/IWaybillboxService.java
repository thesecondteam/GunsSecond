package com.stylefeng.guns.modular.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.system.model.Box;
import com.stylefeng.guns.modular.system.model.BusiWaybilldet;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author stylefeng
 * @since 2019-08-15
 */
public interface IWaybillboxService extends IService<BusiWaybilldet> {
    List<Map<String, Object>> list();

}
