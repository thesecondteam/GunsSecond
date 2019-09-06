package com.stylefeng.guns.modular.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.modular.system.dao.VoyageloadChartMapper;

import com.stylefeng.guns.modular.system.model.Voyage;
import com.stylefeng.guns.modular.system.service.IVoyageloadChartService;

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
public class VoyageloadChartServiceImpl extends ServiceImpl<VoyageloadChartMapper, Voyage> implements IVoyageloadChartService {

    @Override
    public List<Map<String, Object>> list1() {
        return this.baseMapper.list1();
    }
    @Override
    public List<Map<String, Object>> list2() {
        return this.baseMapper.list2();
    }
    @Override
    public List<Map<String, Object>> list3() {
        return this.baseMapper.list3();
    }
    @Override
    public List<Map<String, Object>> list4() {
        return this.baseMapper.list4();
    }

}
