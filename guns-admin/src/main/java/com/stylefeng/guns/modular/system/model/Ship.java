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
 * @since 2019-08-14
 */
@TableName("enti_ship")
public class Ship extends Model<Ship> {

    private static final long serialVersionUID = 1L;

    /**
     * 船舶IMO编号
     */
    private String imo;
    /**
     * 船舶中文名
     */
    private String shipcname;
    /**
     * 船舶英文名
     */
    private String shipename;
    /**
     * 所属国
     */
    private String country;
    /**
     * 长度
     */
    private BigDecimal shiplength;
    /**
     * 宽度
     */
    private BigDecimal shipwidth;
    /**
     * 载重吨位
     */
    private BigDecimal tonnage;
    /**
     * MMSI
     */
    private String mmsi;
    /**
     * 联系人
     */
    private String contacts;
    /**
     * 电话
     */
    private String telphone;
    /**
     * 状态码
     */
    private Integer statecode;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String spare1;
    private String spare2;


    public String getImo() {
        return imo;
    }

    public void setImo(String imo) {
        this.imo = imo;
    }

    public String getShipcname() {
        return shipcname;
    }

    public void setShipcname(String shipcname) {
        this.shipcname = shipcname;
    }

    public String getShipename() {
        return shipename;
    }

    public void setShipename(String shipename) {
        this.shipename = shipename;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public BigDecimal getShiplength() {
        return shiplength;
    }

    public void setShiplength(BigDecimal shiplength) {
        this.shiplength = shiplength;
    }

    public BigDecimal getShipwidth() {
        return shipwidth;
    }

    public void setShipwidth(BigDecimal shipwidth) {
        this.shipwidth = shipwidth;
    }

    public BigDecimal getTonnage() {
        return tonnage;
    }

    public void setTonnage(BigDecimal tonnage) {
        this.tonnage = tonnage;
    }

    public String getMmsi() {
        return mmsi;
    }

    public void setMmsi(String mmsi) {
        this.mmsi = mmsi;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Ship{" +
        "imo=" + imo +
        ", shipcname=" + shipcname +
        ", shipename=" + shipename +
        ", country=" + country +
        ", shiplength=" + shiplength +
        ", shipwidth=" + shipwidth +
        ", tonnage=" + tonnage +
        ", mmsi=" + mmsi +
        ", contacts=" + contacts +
        ", telphone=" + telphone +
        ", statecode=" + statecode +
        ", id=" + id +
        ", spare1=" + spare1 +
        ", spare2=" + spare2 +
        "}";
    }
}
