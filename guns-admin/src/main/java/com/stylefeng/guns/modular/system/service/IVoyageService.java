package com.stylefeng.guns.modular.system.service;

import com.stylefeng.guns.modular.system.model.Voyage;
import com.baomidou.mybatisplus.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tuchengli
 * @since 2019-08-14
 */
public interface IVoyageService extends IService<Voyage> {
    /**
     * 获取所有部门列表
     */
    List<Map<String, Object>> list(@Param("condition") String condition);
}
