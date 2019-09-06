package com.stylefeng.guns.modular.webSys.service.impl;

import com.stylefeng.guns.modular.system.model.BusiOrder;
import com.stylefeng.guns.modular.system.dao.BusiOrderMapper;
import com.stylefeng.guns.modular.webSys.service.IBusiOrderService;
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
public class BusiOrderServiceImpl extends ServiceImpl<BusiOrderMapper, BusiOrder> implements IBusiOrderService {
    @Override
    public Integer getOrderNumsStart(){return this.baseMapper.getOrderNumsStart();}
    @Override
    public Integer getOrderNumsStartMonth(Date time){return this.baseMapper.getOrderNumsStartMonth(time);}
}
