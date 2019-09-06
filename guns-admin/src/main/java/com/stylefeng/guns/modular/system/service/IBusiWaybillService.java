package com.stylefeng.guns.modular.system.service;

import com.stylefeng.guns.modular.system.model.BusiWaybill;
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
public interface IBusiWaybillService extends IService<BusiWaybill> {
    Integer getWayBoxNumsStart();
    Integer getWayBoxNumsStartMonth(Date d);
    Integer getWayBoxNumsEnd();
    Integer getWayBoxNumsEndMonth(Date d);
}
