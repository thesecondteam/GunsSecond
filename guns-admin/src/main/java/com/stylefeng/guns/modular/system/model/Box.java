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
 * @since 2019-08-15
 */
@TableName("enti_box")
public class Box extends Model<Box> {

    private static final long serialVersionUID = 1L;

    private String spare1;
    private String spare2;
    /**
     * 订单编号
     */
    private String ordernumber;
    /**
     * 运输方式
     */
    private Integer trantype;
    /**
     * 集装箱编码
     */
    private String boxcode;
    /**
     * 场地
     */
    private Integer areaid;
    /**
     * 添加时间
     */
    private Date addtime;
    /**
     * 箱子类型
     */
    private Integer boxtype;
    /**
     * 箱子尺寸
     */
    private String boxsize;
    /**
     * 货物类型
     */
    private Integer goodstype;
    /**
     * 货物名称
     */
    private String goodsname;
    /**
     * 起点
     */
    private Integer startpoint;
    /**
     * 终点
     */
    private Integer endpoint;
    /**
     * 是否空箱
     */
    private Integer emptycode;
    /**
     * 状态码码
     */
    private Integer statecode;
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;


    public String getSpare1() {
        return spare1;
    }

    public void setSpare1(String spare1) {
        this.spare1 = spare1;
    }

    public String getSpare2() {
        return spare2;
    }

    public void setSpare2(String spare2) {
        this.spare2 = spare2;
    }

    public String getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(String ordernumber) {
        this.ordernumber = ordernumber;
    }

    public Integer getTrantype() {
        return trantype;
    }

    public void setTrantype(Integer trantype) {
        this.trantype = trantype;
    }

    public String getBoxcode() {
        return boxcode;
    }

    public void setBoxcode(String boxcode) {
        this.boxcode = boxcode;
    }

    public Integer getAreaid() {
        return areaid;
    }

    public void setAreaid(Integer areaid) {
        this.areaid = areaid;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Integer getBoxtype() {
        return boxtype;
    }

    public void setBoxtype(Integer boxtype) {
        this.boxtype = boxtype;
    }

    public String getBoxsize() {
        return boxsize;
    }

    public void setBoxsize(String boxsize) {
        this.boxsize = boxsize;
    }

    public Integer getGoodstype() {
        return goodstype;
    }

    public void setGoodstype(Integer goodstype) {
        this.goodstype = goodstype;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public Integer getStartpoint() {
        return startpoint;
    }

    public void setStartpoint(Integer startpoint) {
        this.startpoint = startpoint;
    }

    public Integer getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(Integer endpoint) {
        this.endpoint = endpoint;
    }

    public Integer getEmptycode() {
        return emptycode;
    }

    public void setEmptycode(Integer emptycode) {
        this.emptycode = emptycode;
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
        return "Box{" +
        "spare1=" + spare1 +
        ", spare2=" + spare2 +
        ", ordernumber=" + ordernumber +
        ", trantype=" + trantype +
        ", boxcode=" + boxcode +
        ", areaid=" + areaid +
        ", addtime=" + addtime +
        ", boxtype=" + boxtype +
        ", boxsize=" + boxsize +
        ", goodstype=" + goodstype +
        ", goodsname=" + goodsname +
        ", startpoint=" + startpoint +
        ", endpoint=" + endpoint +
        ", emptycode=" + emptycode +
        ", statecode=" + statecode +
        ", id=" + id +
        "}";
    }
}
