package com.stylefeng.guns.modular.system.service.impl;

import com.stylefeng.guns.modular.system.model.Box;
import com.stylefeng.guns.modular.system.dao.BoxMapper;
import com.stylefeng.guns.modular.system.service.IBoxService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2019-08-15
 */
@Service
public class BoxServiceImpl extends ServiceImpl<BoxMapper, Box> implements IBoxService {
    @Override
    public Integer getAllBoxesNum(){return this.baseMapper.getAllBoxesNum();}
}
