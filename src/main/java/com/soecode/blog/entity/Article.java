package com.soecode.blog.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class Article {
    private Integer id;

    private String kindName;

    private String articleTitle;

    private String articleImage;

    private String articleAuthor;

    private Integer articleClassify;

    private Integer articleReadNum;

    private Integer articleLikeNum;

    private Integer articleStatus;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date articlePublicTime;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private String articleContent;





    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle == null ? null : articleTitle.trim();
    }

    public String getArticleImage() {
        return articleImage;
    }

    public void setArticleImage(String articleImage) {
        this.articleImage = articleImage == null ? null : articleImage.trim();
    }

    public String getArticleAuthor() {
        return articleAuthor;
    }

    public void setArticleAuthor(String articleAuthor) {
        this.articleAuthor = articleAuthor == null ? null : articleAuthor.trim();
    }

    public Integer getArticleClassify() {
        return articleClassify;
    }

    public void setArticleClassify(Integer articleClassify) {
        this.articleClassify = articleClassify;
    }

    public Integer getArticleReadNum() {
        return articleReadNum;
    }

    public void setArticleReadNum(Integer articleReadNum) {
        this.articleReadNum = articleReadNum;
    }

    public Integer getArticleLikeNum() {
        return articleLikeNum;
    }

    public void setArticleLikeNum(Integer articleLikeNum) {
        this.articleLikeNum = articleLikeNum;
    }

    public Integer getArticleStatus() {
        return articleStatus;
    }

    public void setArticleStatus(Integer articleStatus) {
        this.articleStatus = articleStatus;
    }

    public Date getArticlePublicTime() {
        return articlePublicTime;
    }

    public void setArticlePublicTime(Date articlePublicTime) {
        this.articlePublicTime = articlePublicTime;
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
    public String getKindName() {
        return kindName;
    }

    public void setKindName(String kindName) {
        this.kindName = kindName;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent == null ? null : articleContent.trim();
    }
}