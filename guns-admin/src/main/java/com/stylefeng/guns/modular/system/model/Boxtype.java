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
@TableName("dict_boxtype")
public class Boxtype extends Model<Boxtype> {

    private static final long serialVersionUID = 1L;

    /**
     * 类型编码
     */
    private String typecode;
    /**
     * 类型
     */
    private String type;
    /**
     * 状态码
     */
    private Integer statecode;
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;


    public String getTypecode() {
        return typecode;
    }

    public void setTypecode(String typecode) {
        this.typecode = typecode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Boxtype{" +
        "typecode=" + typecode +
        ", type=" + type +
        ", statecode=" + statecode +
        ", id=" + id +
        "}";
    }
}
