package com.stylefeng.guns.modular.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.system.model.Voyage;

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
public interface IVoyageloadChartService extends IService<Voyage> {
    List<Map<String, Object>> list1();
    List<Map<String, Object>> list2();
    List<Map<String, Object>> list3();
    List<Map<String, Object>> list4();

}
