package com.stylefeng.guns.modular.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.modular.system.dao.TrainstationChartMapper;
import com.stylefeng.guns.modular.system.dao.VoyagewillChartMapper;
import com.stylefeng.guns.modular.system.model.BusiWaybill;
import com.stylefeng.guns.modular.system.model.Voyage;
import com.stylefeng.guns.modular.system.service.ITrainstationChartService;
import com.stylefeng.guns.modular.system.service.IVoyagewillChartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2019-08-15
 */
@Service
public class TrainstationChartServiceImpl extends ServiceImpl<TrainstationChartMapper, BusiWaybill> implements ITrainstationChartService {

    @Override
    public List<Map<String, Object>> list() {
        return this.baseMapper.list();
    }

}
