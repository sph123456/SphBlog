package com.soecode.blog.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class AddParams {
    private Integer id;

    private String articleTitle;

    private String articleImage;

    private String articleAuthor;

    private Integer articleClassify;

    private Integer articleReadNum;

    private Integer articleLikeNum;

    private Integer articleStatus;


    private String articleContent;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date articlePublicTime;

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
        this.articleTitle = articleTitle;
    }

    public String getArticleImage() {
        return articleImage;
    }

    public void setArticleImage(String articleImage) {
        this.articleImage = articleImage;
    }

    public String getArticleAuthor() {
        return articleAuthor;
    }

    public void setArticleAuthor(String articleAuthor) {
        this.articleAuthor = articleAuthor;
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

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public Date getArticlePublicTime() {
        return articlePublicTime;
    }

    public void setArticlePublicTime(Date articlePublicTime) {
        this.articlePublicTime = articlePublicTime;
    }
}
