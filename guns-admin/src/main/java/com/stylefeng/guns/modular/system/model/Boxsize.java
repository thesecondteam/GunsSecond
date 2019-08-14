package com.stylefeng.guns.modular.system.model;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.enums.IdType;
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
 * @since 2019-08-13
 */
@TableName("dict_boxsize")
public class Boxsize extends Model<Boxsize> {

    private static final long serialVersionUID = 1L;

    private String sizecode;
    private String sizetype;
    private BigDecimal inlength;
    private BigDecimal inwidth;
    private BigDecimal inheight;
    private BigDecimal outheight;
    private BigDecimal outwidth;
    private BigDecimal volume;
    private BigDecimal capacity;
    private Integer statecode;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;


    public String getSizecode() {
        return sizecode;
    }

    public void setSizecode(String sizecode) {
        this.sizecode = sizecode;
    }

    public String getSizetype() {
        return sizetype;
    }

    public void setSizetype(String sizetype) {
        this.sizetype = sizetype;
    }

    public BigDecimal getInlength() {
        return inlength;
    }

    public void setInlength(BigDecimal inlength) {
        this.inlength = inlength;
    }

    public BigDecimal getInwidth() {
        return inwidth;
    }

    public void setInwidth(BigDecimal inwidth) {
        this.inwidth = inwidth;
    }

    public BigDecimal getInheight() {
        return inheight;
    }

    public void setInheight(BigDecimal inheight) {
        this.inheight = inheight;
    }

    public BigDecimal getOutheight() {
        return outheight;
    }

    public void setOutheight(BigDecimal outheight) {
        this.outheight = outheight;
    }

    public BigDecimal getOutwidth() {
        return outwidth;
    }

    public void setOutwidth(BigDecimal outwidth) {
        this.outwidth = outwidth;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public BigDecimal getCapacity() {
        return capacity;
    }

    public void setCapacity(BigDecimal capacity) {
        this.capacity = capacity;
    }

    public Integer getStatecode() {
        return statecode;
    }

    public void setStatecode(Integer statecode) {
        this.statecode = statecode;
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
        return "Boxsize{" +
        "sizecode=" + sizecode +
        ", sizetype=" + sizetype +
        ", inlength=" + inlength +
        ", inwidth=" + inwidth +
        ", inheight=" + inheight +
        ", outheight=" + outheight +
        ", outwidth=" + outwidth +
        ", volume=" + volume +
        ", capacity=" + capacity +
        ", statecode=" + statecode +
        ", id=" + id +
        "}";
    }
}
