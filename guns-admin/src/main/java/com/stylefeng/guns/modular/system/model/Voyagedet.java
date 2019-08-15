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
 * @author tuchengli
 * @since 2019-08-14
 */
@TableName("busi_voyagedet")
public class Voyagedet extends Model<Voyagedet> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String voyagedetid;
    private String voyagenum;
    private String boxid;
    private String boxtype;
    private String boxsize;
    private String boxgoods;
    private Integer statecode;
    private Date createtime;
    private String operation;
    private String finish;
    private String spareone;
    private String sparetwo;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVoyagedetid() {
        return voyagedetid;
    }

    public void setVoyagedetid(String voyagedetid) {
        this.voyagedetid = voyagedetid;
    }

    public String getVoyagenum() {
        return voyagenum;
    }

    public void setVoyagenum(String voyagenum) {
        this.voyagenum = voyagenum;
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

    public Integer getStatecode() {
        return statecode;
    }

    public void setStatecode(Integer statecode) {
        this.statecode = statecode;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getFinish() {
        return finish;
    }

    public void setFinish(String finish) {
        this.finish = finish;
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
        return "Voyagedet{" +
        "id=" + id +
        ", voyagedetid=" + voyagedetid +
        ", voyagenum=" + voyagenum +
        ", boxid=" + boxid +
        ", boxtype=" + boxtype +
        ", boxsize=" + boxsize +
        ", boxgoods=" + boxgoods +
        ", statecode=" + statecode +
        ", createtime=" + createtime +
        ", operation=" + operation +
        ", finish=" + finish +
        ", spareone=" + spareone +
        ", sparetwo=" + sparetwo +
        "}";
    }
}
