package com.stylefeng.guns.modular.system.service;

import com.stylefeng.guns.modular.system.model.Box;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author stylefeng
 * @since 2019-08-15
 */
public interface IBoxService extends IService<Box> {
    Integer getAllBoxesNum();
}
