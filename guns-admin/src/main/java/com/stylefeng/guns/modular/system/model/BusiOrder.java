package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
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
 * @since 2019-08-14
 */
@TableName("busi_order")
public class BusiOrder extends Model<BusiOrder> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 订单编号
     */
    private String ordernumber;
    /**
     * 运输方式
     */
    private Integer trantype;
    /**
     * 货物类型
     */
    private Integer goodstype;
    /**
     * 货物名称
     */
    private String goodsname;
    /**
     * 货物容量
     */
    private BigDecimal goodsvolume;
    /**
     * 起点
     */
    private Integer startpoint;
    /**
     * 收货方电话
     */
    private String recephone;
    /**
     * 收货方
     */
    private String receiver;
    /**
     * 终点
     */
    private Integer endpoint;
    /**
     * 发货方电话
     */
    private String consiphone;
    /**
     * 发货方
     */
    private String consignor;
    /**
     * 创建时间
     */
    private Date creationtime;
    /**
     * 订单码
     */
    private Integer ordercode;
    /**
     * 备注
     */
    private String note;
    private String spare;
    private Integer spare1;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public BigDecimal getGoodsvolume() {
        return goodsvolume;
    }

    public void setGoodsvolume(BigDecimal goodsvolume) {
        this.goodsvolume = goodsvolume;
    }

    public Integer getStartpoint() {
        return startpoint;
    }

    public void setStartpoint(Integer startpoint) {
        this.startpoint = startpoint;
    }

    public String getRecephone() {
        return recephone;
    }

    public void setRecephone(String recephone) {
        this.recephone = recephone;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Integer getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(Integer endpoint) {
        this.endpoint = endpoint;
    }

    public String getConsiphone() {
        return consiphone;
    }

    public void setConsiphone(String consiphone) {
        this.consiphone = consiphone;
    }

    public String getConsignor() {
        return consignor;
    }

    public void setConsignor(String consignor) {
        this.consignor = consignor;
    }

    public Date getCreationtime() {
        return creationtime;
    }

    public void setCreationtime(Date creationtime) {
        this.creationtime = creationtime;
    }

    public Integer getOrdercode() {
        return ordercode;
    }

    public void setOrdercode(Integer ordercode) {
        this.ordercode = ordercode;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getSpare() {
        return spare;
    }

    public void setSpare(String spare) {
        this.spare = spare;
    }

    public Integer getSpare1() {
        return spare1;
    }

    public void setSpare1(Integer spare1) {
        this.spare1 = spare1;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "BusiOrder{" +
        "id=" + id +
        ", ordernumber=" + ordernumber +
        ", trantype=" + trantype +
        ", goodstype=" + goodstype +
        ", goodsname=" + goodsname +
        ", goodsvolume=" + goodsvolume +
        ", startpoint=" + startpoint +
        ", recephone=" + recephone +
        ", receiver=" + receiver +
        ", endpoint=" + endpoint +
        ", consiphone=" + consiphone +
        ", consignor=" + consignor +
        ", creationtime=" + creationtime +
        ", ordercode=" + ordercode +
        ", note=" + note +
        ", spare=" + spare +
        ", spare1=" + spare1 +
        "}";
    }
}
