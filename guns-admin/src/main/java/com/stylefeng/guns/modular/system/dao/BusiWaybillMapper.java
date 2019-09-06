package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.modular.system.model.BusiWaybill;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.Date;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2019-08-14
 */
public interface BusiWaybillMapper extends BaseMapper<BusiWaybill> {
    Integer getWayBoxNumsStart();
    Integer getWayBoxNumsStartMonth(Date time);
    Integer getWayBoxNumsEnd();
    Integer getWayBoxNumsEndMonth(Date time);
}
