package com.stylefeng.guns.modular.system.service.impl;

import com.stylefeng.guns.modular.system.model.Voyagedet;
import com.stylefeng.guns.modular.system.dao.VoyagedetMapper;
import com.stylefeng.guns.modular.system.service.IVoyagedetService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tuchengli
 * @since 2019-08-14
 */
@Service
@Transactional
public class VoyagedetServiceImpl extends ServiceImpl<VoyagedetMapper, Voyagedet> implements IVoyagedetService {
    @Override
    public List<Map<String, Object>> list(String condition) {
        return this.baseMapper.list(condition);
    }
}
