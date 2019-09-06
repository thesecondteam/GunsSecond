package com.stylefeng.guns.modular.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.modular.system.model.Voyage;

import java.util.List;
import java.util.Map;

/**s
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2019-08-15
 */
public interface VoyageloadChartMapper extends BaseMapper<Voyage> {
    List<Map<String, Object>> list1();
    List<Map<String, Object>> list2();
    List<Map<String, Object>> list3();
    List<Map<String, Object>> list4();
}
