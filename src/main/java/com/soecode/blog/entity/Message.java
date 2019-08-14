package com.soecode.blog.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class Message {
    private Integer id;

    private String messageNickname;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date messageTime;

    private Integer replay;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private String messageContent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessageNickname() {
        return messageNickname;
    }

    public void setMessageNickname(String messageNickname) {
        this.messageNickname = messageNickname == null ? null : messageNickname.trim();
    }

    public Date getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(Date messageTime) {
        this.messageTime = messageTime;
    }

    public Integer getReplay() {
        return replay;
    }

    public void setReplay(Integer replay) {
        this.replay = replay;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent == null ? null : messageContent.trim();
    }
}