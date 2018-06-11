package com.iflytek.processfile.dto.base;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: shuaihu2
 * @Date: 2018/5/7
 * @Interface: BaseModel
 * @Description:
 */
public abstract class BaseModel implements Serializable {

    private static final long serialVersionUID = -1L;

    private Integer page;

    private Integer limit;

    private String creator;

    private Date createTime;

    private String modificator;

    private Date updateTime;
    /*有可能会用到的操作类型字段*/
    private String type;

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setModificator(String modificator) {
        this.modificator = modificator;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getCreator() {
        return creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public String getModificator() {
        return modificator;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getLimit() {
        return limit;
    }
}
