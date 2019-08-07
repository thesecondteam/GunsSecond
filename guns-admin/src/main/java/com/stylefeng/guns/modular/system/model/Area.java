package com.stylefeng.guns.modular.system.model;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author stylefeng
 * @since 2019-08-07
 */
public class Area extends Model<Area> {

    private static final long serialVersionUID = 1L;

    private String boxid;
    private String areaid;
    private String areatype;
    private Date addtime;
    private String boxtype;
    private String boxsize;
    private String boxgoods;
    private String ifempty;
    private String state;
    private String boxcode;


    public String getBoxid() {
        return boxid;
    }

    public void setBoxid(String boxid) {
        this.boxid = boxid;
    }

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid;
    }

    public String getAreatype() {
        return areatype;
    }

    public void setAreatype(String areatype) {
        this.areatype = areatype;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
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

    public String getIfempty() {
        return ifempty;
    }

    public void setIfempty(String ifempty) {
        this.ifempty = ifempty;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getBoxcode() {
        return boxcode;
    }

    public void setBoxcode(String boxcode) {
        this.boxcode = boxcode;
    }

    @Override
    protected Serializable pkVal() {
        return this.boxid;
    }

    @Override
    public String toString() {
        return "Area{" +
        "boxid=" + boxid +
        ", areaid=" + areaid +
        ", areatype=" + areatype +
        ", addtime=" + addtime +
        ", boxtype=" + boxtype +
        ", boxsize=" + boxsize +
        ", boxgoods=" + boxgoods +
        ", ifempty=" + ifempty +
        ", state=" + state +
        ", boxcode=" + boxcode +
        "}";
    }
}
