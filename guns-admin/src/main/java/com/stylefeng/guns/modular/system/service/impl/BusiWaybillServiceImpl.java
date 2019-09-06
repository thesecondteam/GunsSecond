package com.stylefeng.guns.modular.system.service.impl;

import com.stylefeng.guns.modular.system.model.BusiWaybill;
import com.stylefeng.guns.modular.system.dao.BusiWaybillMapper;
import com.stylefeng.guns.modular.system.service.IBusiWaybillService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2019-08-14
 */
@Service
public class BusiWaybillServiceImpl extends ServiceImpl<BusiWaybillMapper, BusiWaybill> implements IBusiWaybillService {
    @Override
    public Integer getWayBoxNumsStart(){return this.baseMapper.getWayBoxNumsStart();}
    @Override
    public Integer getWayBoxNumsStartMonth(Date time){return this.baseMapper.getWayBoxNumsStartMonth(time);}
    @Override
    public Integer getWayBoxNumsEnd(){return this.baseMapper.getWayBoxNumsEnd();}
    @Override
    public Integer getWayBoxNumsEndMonth(Date time){return this.baseMapper.getWayBoxNumsEndMonth(time);}
}
