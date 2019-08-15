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
 * @author tuchengli
 * @since 2019-08-14
 */
@TableName("busi_voyage")
public class Voyage extends Model<Voyage> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String voyagenum;
    private String imo;
    private Date starttime;
    private Date endtime;
    private String consigncompany;
    private String recivecompany;
    private Integer boxnumber;
    private BigDecimal distance;
    private Integer statecode;
    private Integer loadcode;
    private Integer unloadcode;
    private Integer loadtype;
    private Integer endpoint;
    private Integer startpiont;
    private String spareone;
    private String sparetwo;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVoyagenum() {
        return voyagenum;
    }

    public void setVoyagenum(String voyagenum) {
        this.voyagenum = voyagenum;
    }

    public String getImo() {
        return imo;
    }

    public void setImo(String imo) {
        this.imo = imo;
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

    public Integer getLoadtype() {
        return loadtype;
    }

    public void setLoadtype(Integer loadtype) {
        this.loadtype = loadtype;
    }

    public Integer getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(Integer endpoint) {
        this.endpoint = endpoint;
    }

    public Integer getStartpiont() {
        return startpiont;
    }

    public void setStartpiont(Integer startpiont) {
        this.startpiont = startpiont;
    }

    public String getSpareone() {
        return spareone;
    }

    public void setSpareone(String spareone) {
        this.spareone = spareone;
    }

    public String getSparetwo() {
        return sparetwo;
    }

    public void setSparetwo(String sparetwo) {
        this.sparetwo = sparetwo;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Voyage{" +
        "id=" + id +
        ", voyagenum=" + voyagenum +
        ", imo=" + imo +
        ", starttime=" + starttime +
        ", endtime=" + endtime +
        ", consigncompany=" + consigncompany +
        ", recivecompany=" + recivecompany +
        ", boxnumber=" + boxnumber +
        ", distance=" + distance +
        ", statecode=" + statecode +
        ", loadcode=" + loadcode +
        ", unloadcode=" + unloadcode +
        ", loadtype=" + loadtype +
        ", endpoint=" + endpoint +
        ", startpiont=" + startpiont +
        ", spareone=" + spareone +
        ", sparetwo=" + sparetwo +
        "}";
    }
}
