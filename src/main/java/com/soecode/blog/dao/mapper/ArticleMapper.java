package com.soecode.blog.dao.mapper;

import com.soecode.blog.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ArticleMapper {
    int deleteByPrimaryKey(@Param(value = "id")Integer id);

//    int insert(Article record);

//    int insertSelective(Article record);
//
//    Article selectByPrimaryKey(Integer id);
//
//    int updateByPrimaryKeySelective(Article record);
//
//    int updateByPrimaryKeyWithBLOBs(Article record);
//
//    int updateByPrimaryKey(Article record);


    Integer totalCount(@Param(value = "articleClassify") Integer articleClassify);

    List<Article> articleList(@Param(value = "start") Integer start,@Param(value = "pagesize") Integer pagesize,@Param(value = "currentPage") Integer currentPage,@Param(value = "articleClassify") Integer articleClassify);

    int addArticle(@Param(value = "articleTitle") String articleTitle, @Param(value = "articleImage") String articleImage,
                   @Param(value = "articleAuthor") String articleAuthor, @Param(value = "articleClassify")Integer articleClassify,
                   @Param(value = "articleReadNum") Integer articleReadNum, @Param(value = "articleLikeNum") Integer articleLikeNum,
                   @Param(value = "articleContent") String articleContent, @Param(value = "publichTime")Date publichTime,@Param(value = "createTime") Date createTime);

    int updateArticle(@Param(value = "articleTitle") String articleTitle,@Param(value = "articleImage") String articleImage,
                      @Param(value = "articleAuthor") String articleAuthor, @Param(value = "articleClassify") Integer articleClassify,@Param(value = "articleReadNum") Integer articleReadNum,
                      @Param(value = "articleLikeNum")Integer articleLikeNum,  @Param(value = "articleContent") String articleContent ,@Param(value = "id") Integer id);

    int updateStatus(@Param(value = "id") String id, @Param(value = "articleStatus") Integer articleStatus);

    int[] updateStatus(List<Object[]> list);

    void changArticleLikeCount(Map<String,Object> map);

    Article select(@Param(value = "targetId") String targetId);

    int status(@Param(value = "targetId") String targetId);
//
//    int updateArticle(@Param(value = "id") Integer id, @Param(value = "articleTitle") String articleTitle, @Param(value = "articleImage") String articleImage,
//                      @Param(value = "articleAuthor") String articleAuthor, Integer classify, @Param(value = "articleClassify") Integer articleClassify,
//                      @Param(value = "articleReadNum") Integer articleReadNum, @Param(value = "articleLikeNum") Integer articleLikeNum,
//                      @Param(value = "articleContent") String articleContent);
}