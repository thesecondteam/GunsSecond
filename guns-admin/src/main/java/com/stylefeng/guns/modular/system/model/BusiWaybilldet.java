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
 * @since 2019-08-14
 */
@TableName("busi_waybilldet")
public class BusiWaybilldet extends Model<BusiWaybilldet> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String waybilldetid;
    private String waybillid;
    private String boxid;
    private String boxtype;
    private String boxsize;
    private String boxgoods;
    private String operation;
    private Date createtime;
    private Integer statecode;
    private String finish;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWaybilldetid() {
        return waybilldetid;
    }

    public void setWaybilldetid(String waybilldetid) {
        this.waybilldetid = waybilldetid;
    }

    public String getWaybillid() {
        return waybillid;
    }

    public void setWaybillid(String waybillid) {
        this.waybillid = waybillid;
    }

    public String getBoxid() {
        return boxid;
    }

    public void setBoxid(String boxid) {
        this.boxid = boxid;
    }

    public String getBoxtype() {
        return boxtype;
    }

    public void setBoxtype(String boxtype) {
        this.boxtype = boxtype;
    }

    public String getBoxsize() {
        return boxsize;
    }

    public void setBoxsize(String boxsize) {
        this.boxsize = boxsize;
    }

    public String getBoxgoods() {
        return boxgoods;
    }

    public void setBoxgoods(String boxgoods) {
        this.boxgoods = boxgoods;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getStatecode() {
        return statecode;
    }

    public void setStatecode(Integer statecode) {
        this.statecode = statecode;
    }

    public String getFinish() {
        return finish;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "BusiWaybilldet{" +
        "id=" + id +
        ", waybilldetid=" + waybilldetid +
        ", waybillid=" + waybillid +
        ", boxid=" + boxid +
        ", boxtype=" + boxtype +
        ", boxsize=" + boxsize +
        ", boxgoods=" + boxgoods +
        ", operation=" + operation +
        ", createtime=" + createtime +
        ", statecode=" + statecode +
        ", finish=" + finish +
        "}";
    }
}
