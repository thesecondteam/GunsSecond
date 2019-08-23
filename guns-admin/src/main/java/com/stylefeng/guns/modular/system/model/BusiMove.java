package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author stylefeng
 * @since 2019-08-22
 */
@TableName("busi_move")
public class BusiMove extends Model<BusiMove> {

    private static final long serialVersionUID = 1L;

    /**
     * 自增ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 原 火车 轮船 场地 编号
     */
    private String oldArea;
    /**
     * 目的场地
     */
    private String newArea;
    /**
     * 箱子编码
     */
    private String boxCode;
    /**
     * 操作时间
     */
    private Date operTime;
    /**
     * 操作人
     */
    private String operPerson;
    /**
     * 操作类型
     */
    private String opeType;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOldArea() {
        return oldArea;
    }

    public void setOldArea(String oldArea) {
        this.oldArea = oldArea;
    }

    public String getNewArea() {
        return newArea;
    }

    public void setNewArea(String newArea) {
        this.newArea = newArea;
    }

    public String getBoxCode() {
        return boxCode;
    }

    public void setBoxCode(String boxCode) {
        this.boxCode = boxCode;
    }

    public Date getOperTime() {
        return operTime;
    }

    public void setOperTime(Date operTime) {
        this.operTime = operTime;
    }

    public String getOperPerson() {
        return operPerson;
    }

    public void setOperPerson(String operPerson) {
        this.operPerson = operPerson;
    }

    public String getOpeType() {
        return opeType;
    }

    public void setOpeType(String opeType) {
        this.opeType = opeType;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "BusiMove{" +
        "id=" + id +
        ", oldArea=" + oldArea +
        ", newArea=" + newArea +
        ", boxCode=" + boxCode +
        ", operTime=" + operTime +
        ", operPerson=" + operPerson +
        ", opeType=" + opeType +
        "}";
    }
}
