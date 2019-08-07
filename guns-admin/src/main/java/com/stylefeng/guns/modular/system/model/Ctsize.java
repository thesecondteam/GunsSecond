package com.stylefeng.guns.modular.system.model;

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
public class Ctsize extends Model<Ctsize> {

    private static final long serialVersionUID = 1L;

    private String sizeid;
    private String cttype;
    private Float inlength;
    private Float inwidth;
    private Float inheight;
    private Float outheight;
    private Float outwidth;
    private Float volume;
    private Float capacity;
    private String state;
    private String boxcode;


    public String getSizeid() {
        return sizeid;
    }

    public void setSizeid(String sizeid) {
        this.sizeid = sizeid;
    }

    public String getCttype() {
        return cttype;
    }

    public void setCttype(String cttype) {
        this.cttype = cttype;
    }

    public Float getInlength() {
        return inlength;
    }

    public void setInlength(Float inlength) {
        this.inlength = inlength;
    }

    public Float getInwidth() {
        return inwidth;
    }

    public void setInwidth(Float inwidth) {
        this.inwidth = inwidth;
    }

    public Float getInheight() {
        return inheight;
    }

    public void setInheight(Float inheight) {
        this.inheight = inheight;
    }

    public Float getOutheight() {
        return outheight;
    }

    public void setOutheight(Float outheight) {
        this.outheight = outheight;
    }

    public Float getOutwidth() {
        return outwidth;
    }

    public void setOutwidth(Float outwidth) {
        this.outwidth = outwidth;
    }

    public Float getVolume() {
        return volume;
    }

    public void setVolume(Float volume) {
        this.volume = volume;
    }

    public Float getCapacity() {
        return capacity;
    }

    public void setCapacity(Float capacity) {
        this.capacity = capacity;
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
        return this.sizeid;
    }

    @Override
    public String toString() {
        return "Ctsize{" +
        "sizeid=" + sizeid +
        ", cttype=" + cttype +
        ", inlength=" + inlength +
        ", inwidth=" + inwidth +
        ", inheight=" + inheight +
        ", outheight=" + outheight +
        ", outwidth=" + outwidth +
        ", volume=" + volume +
        ", capacity=" + capacity +
        ", state=" + state +
        ", boxcode=" + boxcode +
        "}";
    }
}
