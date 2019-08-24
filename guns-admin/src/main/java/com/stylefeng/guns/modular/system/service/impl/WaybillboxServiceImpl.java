package com.stylefeng.guns.modular.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.modular.system.dao.BoxareaMapper;
import com.stylefeng.guns.modular.system.dao.WaybillboxMapper;
import com.stylefeng.guns.modular.system.model.Box;
import com.stylefeng.guns.modular.system.model.BusiWaybilldet;
import com.stylefeng.guns.modular.system.service.IBoxareaService;
import com.stylefeng.guns.modular.system.service.IWaybillboxService;
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
public class WaybillboxServiceImpl extends ServiceImpl<WaybillboxMapper, BusiWaybilldet> implements IWaybillboxService {

    @Override
    public List<Map<String, Object>> list() {
        return this.baseMapper.list();
    }

}
