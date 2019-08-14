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
@TableName("enti_train")
public class Train extends Model<Train> {

    private static final long serialVersionUID = 1L;

    /**
     * 火车编号
     */
    private String traincode;
    /**
     * 车厢数
     */
    private Integer carriagenum;
    /**
     * 车厢长度
     */
    private BigDecimal carriagelen;
    /**
     * 状态
     */
    private Integer statecode;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String spare1;
    private String spare2;


    public String getTraincode() {
        return traincode;
    }

    public void setTraincode(String traincode) {
        this.traincode = traincode;
    }

    public Integer getCarriagenum() {
        return carriagenum;
    }

    public void setCarriagenum(Integer carriagenum) {
        this.carriagenum = carriagenum;
    }

    public BigDecimal getCarriagelen() {
        return carriagelen;
    }

    public void setCarriagelen(BigDecimal carriagelen) {
        this.carriagelen = carriagelen;
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
        return "Train{" +
        "traincode=" + traincode +
        ", carriagenum=" + carriagenum +
        ", carriagelen=" + carriagelen +
        ", statecode=" + statecode +
        ", id=" + id +
        ", spare1=" + spare1 +
        ", spare2=" + spare2 +
        "}";
    }
}
