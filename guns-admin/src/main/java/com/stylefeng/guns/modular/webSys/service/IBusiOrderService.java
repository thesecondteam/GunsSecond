package com.stylefeng.guns.modular.webSys.service;

import com.stylefeng.guns.modular.system.model.BusiOrder;
import com.baomidou.mybatisplus.service.IService;

import java.util.Date;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author stylefeng
 * @since 2019-08-14
 */
public interface IBusiOrderService extends IService<BusiOrder> {
    Integer getOrderNumsStart();
    Integer getOrderNumsStartMonth(Date d);
}
