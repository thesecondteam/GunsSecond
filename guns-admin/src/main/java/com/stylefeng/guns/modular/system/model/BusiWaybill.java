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
@TableName("busi_waybill")
public class BusiWaybill extends Model<BusiWaybill> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String waybillid;
    private String trainnum;
    private Date starttime;
    private Date endtime;
    private String consigncompany;
    private String recivecompany;
    private Integer boxnumber;
    private Integer startpoint;
    private Integer endpoint;
    private BigDecimal distance;
    private Integer statecode;
    private Integer loadtype;
    private Integer loadcode;
    private Integer unloadcode;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWaybillid() {
        return waybillid;
    }

    public void setWaybillid(String waybillid) {
        this.waybillid = waybillid;
    }

    public String getTrainnum() {
        return trainnum;
    }

    public void setTrainnum(String trainnum) {
        this.trainnum = trainnum;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getConsigncompany() {
        return consigncompany;
    }

    public void setConsigncompany(String consigncompany) {
        this.consigncompany = consigncompany;
    }

    public String getRecivecompany() {
        return recivecompany;
    }

    public void setRecivecompany(String recivecompany) {
        this.recivecompany = recivecompany;
    }

    public Integer getBoxnumber() {
        return boxnumber;
    }

    public void setBoxnumber(Integer boxnumber) {
        this.boxnumber = boxnumber;
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

    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }

    public Integer getStatecode() {
        return statecode;
    }

    public void setStatecode(Integer statecode) {
        this.statecode = statecode;
    }

    public Integer getLoadtype() {
        return loadtype;
    }

    public void setLoadtype(Integer loadtype) {
        this.loadtype = loadtype;
    }

    public Integer getLoadcode() {
        return loadcode;
    }

    public void setLoadcode(Integer loadcode) {
        this.loadcode = loadcode;
    }

    public Integer getUnloadcode() {
        return unloadcode;
    }

    public void setUnloadcode(Integer unloadcode) {
        this.unloadcode = unloadcode;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "BusiWaybill{" +
        "id=" + id +
        ", waybillid=" + waybillid +
        ", trainnum=" + trainnum +
        ", starttime=" + starttime +
        ", endtime=" + endtime +
        ", consigncompany=" + consigncompany +
        ", recivecompany=" + recivecompany +
        ", boxnumber=" + boxnumber +
        ", startpoint=" + startpoint +
        ", endpiont=" + endpoint +
        ", distance=" + distance +
        ", statecode=" + statecode +
        ", loadtype=" + loadtype +
        ", loadcode=" + loadcode +
        ", unloadcode=" + unloadcode +
        "}";
    }
}
