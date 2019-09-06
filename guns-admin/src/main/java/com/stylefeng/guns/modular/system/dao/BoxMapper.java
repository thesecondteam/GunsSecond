package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.modular.system.model.Box;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2019-08-15
 */
public interface BoxMapper extends BaseMapper<Box> {
    Integer getAllBoxesNum();
}
