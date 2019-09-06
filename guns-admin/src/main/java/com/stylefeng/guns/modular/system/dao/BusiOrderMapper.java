package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.modular.system.model.BusiOrder;
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
public interface BusiOrderMapper extends BaseMapper<BusiOrder> {
    Integer getOrderNumsStart();
    Integer getOrderNumsStartMonth(Date time);
}
