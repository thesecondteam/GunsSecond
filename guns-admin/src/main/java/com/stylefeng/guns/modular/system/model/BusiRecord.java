package com.stylefeng.guns.modular.system.model;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author stylefeng
 * @since 2019-08-29
 */
@TableName("busi_record")
public class BusiRecord extends Model<BusiRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;
    /**
     * 旧内容
     */
    private String oldcontent;
    /**
     * 新内容
     */
    private String newcontent;
    /**
     * 操作人
     */
    private String oprman;
    /**
     * 操作时间
操作时间
     */
    private Date optime;
    /**
     * 操作类型
     */
    private String optype;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOldcontent() {
        return oldcontent;
    }

    public void setOldcontent(String oldcontent) {
        this.oldcontent = oldcontent;
    }

    public String getNewcontent() {
        return newcontent;
    }

    public void setNewcontent(String newcontent) {
        this.newcontent = newcontent;
    }

    public String getOprman() {
        return oprman;
    }

    public void setOprman(String oprman) {
        this.oprman = oprman;
    }

    public Date getOptime() {
        return optime;
    }

    public void setOptime(Date optime) {
        this.optime = optime;
    }

    public String getOptype() {
        return optype;
    }

    public void setOptype(String optype) {
        this.optype = optype;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "BusiRecord{" +
        "id=" + id +
        ", oldcontent=" + oldcontent +
        ", newcontent=" + newcontent +
        ", oprman=" + oprman +
        ", optime=" + optime +
        ", optype=" + optype +
        "}";
    }
}
