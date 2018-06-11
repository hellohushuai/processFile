package com.iflytek.processfile.dto;

import com.iflytek.processfile.dto.base.BaseModel;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;


import java.util.Date;
/**
 * @class_name: CyProcessFile
 * @package: com.iflytek.processfile.dto
 * @describe: 流程文件实体类
 * @author: shuaihu2
 * @creat_date: 2018/5/7
 * @creat_time: 18:54
 **/
@Component
public class CyProcessFile extends BaseModel {
    private Long id;
    /**创建者id*/
    private Long uid;
    /**创建者真实姓名*/
    private String realName;
    /** 文件标题*/
    private String title;
    /** 创建时间*/
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startTime;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private String startTimeString;
    /** 修改人姓名*/
    private String updateUser;
    /** 修改时间*/
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updateTime;
    @DateTimeFormat(pattern="yyyy-MM-dd")    private String updateTimeString;
    /** 不存域名的url*/
    private String url;
    /** 类型 1代表子文件*/
    private String type;
    /** 浏览次数*/
    private Integer pageView;
    /** 点赞数*/
    private Integer praise;
    /** 删除标记，0-正常，1-已删除，2-锁定*/
    private String delFlag;
    /** 内容*/
    private String description;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getStartTimeString() {
        return startTimeString;
    }

    public void setStartTimeString(String startTimeString) {
        this.startTimeString = startTimeString;
    }

    public String getUpdateTimeString() {
        return updateTimeString;
    }

    public void setUpdateTimeString(String updateTimeString) {
        this.updateTimeString = updateTimeString;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPageView() {
        return pageView;
    }

    public void setPageView(Integer pageView) {
        this.pageView = pageView;
    }

    public Integer getPraise() {
        return praise;
    }

    public void setPraise(Integer praise) {
        this.praise = praise;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}