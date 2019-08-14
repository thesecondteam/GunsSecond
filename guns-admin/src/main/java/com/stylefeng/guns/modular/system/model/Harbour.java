package com.stylefeng.guns.modular.system.model;

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
@TableName("dict_harbour")
public class Harbour extends Model<Harbour> {

    private static final long serialVersionUID = 1L;

    /**
     * 港口号
     */
    private String harbourcode;
    /**
     * 港口名称
     */
    private String harbourname;
    /**
     * 国家
     */
    private String country;
    /**
     * 省份
     */
    private String province;
    /**
     * 城市
     */
    private String city;
    /**
     * 轮船数量
     */
    private Integer shipnum;
    /**
     * 场地容量
     */
    private Integer volume;
    /**
     * 状态
     */
    private Integer statecode;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String spare1;
    private String spare2;


    public String getHarbourcode() {
        return harbourcode;
    }

    public void setHarbourcode(String harbourcode) {
        this.harbourcode = harbourcode;
    }

    public String getHarbourname() {
        return harbourname;
    }

    public void setHarbourname(String harbourname) {
        this.harbourname = harbourname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getShipnum() {
        return shipnum;
    }

    public void setShipnum(Integer shipnum) {
        this.shipnum = shipnum;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
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
        return "Harbour{" +
        "harbourcode=" + harbourcode +
        ", harbourname=" + harbourname +
        ", country=" + country +
        ", province=" + province +
        ", city=" + city +
        ", shipnum=" + shipnum +
        ", volume=" + volume +
        ", statecode=" + statecode +
        ", id=" + id +
        ", spare1=" + spare1 +
        ", spare2=" + spare2 +
        "}";
    }
}
