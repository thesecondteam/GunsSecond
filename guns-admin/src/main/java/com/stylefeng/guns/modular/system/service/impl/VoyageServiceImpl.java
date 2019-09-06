package com.stylefeng.guns.modular.system.service.impl;

import com.stylefeng.guns.modular.system.model.Voyage;
import com.stylefeng.guns.modular.system.dao.VoyageMapper;
import com.stylefeng.guns.modular.system.service.IVoyageService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
public class VoyageServiceImpl extends ServiceImpl<VoyageMapper, Voyage> implements IVoyageService {
    @Override
    public Integer getVoyageBoxNumsStart(){return this.baseMapper.getVoyageBoxNumsStart();}
    @Override
    public Integer getVoyageBoxNumsStartMonth(Date time){return this.baseMapper.getVoyageBoxNumsStartMonth(time);}
    @Override
    public Integer getVoyageBoxNumsEnd(){return this.baseMapper.getVoyageBoxNumsEnd();}
    @Override
    public Integer getVoyageBoxNumsEndMonth(Date time){return this.baseMapper.getVoyageBoxNumsEndMonth(time);}
}
