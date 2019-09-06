package com.stylefeng.guns.modular.system.service;

import com.stylefeng.guns.modular.system.model.Voyage;
import com.baomidou.mybatisplus.service.IService;
import org.apache.ibatis.annotations.Param;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tuchengli
 * @since 2019-08-14
 */
public interface IVoyageService extends IService<Voyage> {
    Integer getVoyageBoxNumsStart();
    Integer getVoyageBoxNumsStartMonth(Date d);
    Integer getVoyageBoxNumsEnd();
    Integer getVoyageBoxNumsEndMonth(Date d);
}
