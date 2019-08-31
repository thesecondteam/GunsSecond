package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author stylefeng
 * @since 2019-08-28
 */
@TableName("busi_updown")
public class Updown extends Model<Updown> {

    private static final long serialVersionUID = 1L;

    /**
     * 订单编号
     */
    private String ordernumber;
    /**
     * 操作时间
     */
    private Date optime;
    /**
     * 操作人
     */
    private String oppeople;
    /**
     * 操作类型
     */
    private Integer optype;
    /**
     * 联系方式
     */
    private String recphone;
    /**
     * 接箱场地
     */
    private Integer areaid;
    /**
     * 接货人
     */
    private String recpeople;
    /**
     * 列表
     */

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;


    public String getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(String ordernumber) {
        this.ordernumber = ordernumber;
    }

    public Date getOptime() {
        return optime;
    }

    public void setOptime(Date optime) {
        this.optime = optime;
    }

    public String getOppeople() {
        return oppeople;
    }

    public void setOppeople(String oppeople) {
        this.oppeople = oppeople;
    }

    public Integer getOptype() {
        return optype;
    }

    public void setOptype(Integer optype) {
        this.optype = optype;
    }

    public String getRecphone() {
        return recphone;
    }

    public void setRecphone(String recphone) {
        this.recphone = recphone;
    }

    public Integer getAreaid() {
        return areaid;
    }

    public void setAreaid(Integer areaid) {
        this.areaid = areaid;
    }

    public String getRecpeople() {
        return recpeople;
    }

    public void setRecpeople(String recpeople) {
        this.recpeople = recpeople;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Updown{" +
        "ordernumber=" + ordernumber +
        ", optime=" + optime +
        ", oppeople=" + oppeople +
        ", optype=" + optype +
        ", recphone=" + recphone +
        ", areaid=" + areaid +
        ", recpeople=" + recpeople +
        ", id=" + id +
        "}";
    }
}
