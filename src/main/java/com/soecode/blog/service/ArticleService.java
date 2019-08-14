package com.soecode.blog.service;

import com.soecode.blog.Untils.JSONUtil;
import com.soecode.blog.dao.mapper.ArticleMapper;
import com.soecode.blog.entity.Article;
import com.soecode.blog.dao.redis.RedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Resource
    private RedisDao redisDao;

    public List<Article> articleList(Integer pagesize, Integer currentPage,Integer articleClassify) {
        Integer start = (currentPage-1)*pagesize;
        String key = "articleList";
        String field = pagesize+"_"+currentPage+"_"+articleClassify;
        String value = redisDao.hget(key,field);
        List<Article> list ;
        if (!StringUtils.isEmpty(value)){
            list = JSONUtil.fromJsonList(value,Article.class);
            return  list;
        }else  {
            list = this.articleMapper.articleList(start,pagesize,currentPage, articleClassify);
            redisDao.hset(key,field,JSONUtil.objToJsonString(list));
            redisDao.setExpire(key,2*60);
            return list;
        }

}

    public Integer totalCount(Integer articleClassify) {
        return this.articleMapper.totalCount(articleClassify);
    }

    public int updateArticle(Integer id,String articleTitle, String articleImage, String articleAuthor,Integer articleClassify, Integer articleReadNum, Integer articleLikeNum, String articleContent) {
        return this.articleMapper.updateArticle(articleTitle,articleImage,articleAuthor,articleClassify,articleReadNum,articleLikeNum,articleContent,id);
    }

    public int delete(Integer id) {
        return this.articleMapper.deleteByPrimaryKey(id);
    }

    public int addArticle(String articleTitle, String articleImage, String articleAuthor, Integer articleClassify, Integer articleReadNum, Integer articleLikeNum, String articleContent) {
        Date publicTime = new Date();
        Date createTime = new Date();
        return this.articleMapper.addArticle(articleTitle,articleImage,articleAuthor,articleClassify,articleReadNum,articleLikeNum,articleContent,publicTime,createTime);
    }
}
