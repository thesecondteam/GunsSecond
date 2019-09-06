package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.modular.system.model.Voyage;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tuchengli
 * @since 2019-08-14
 */
public interface VoyageMapper extends BaseMapper<Voyage> {
    Integer getVoyageBoxNumsStart();
    Integer getVoyageBoxNumsStartMonth(Date time);
    Integer getVoyageBoxNumsEnd();
    Integer getVoyageBoxNumsEndMonth(Date time);
}
