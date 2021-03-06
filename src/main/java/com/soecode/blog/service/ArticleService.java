package com.soecode.blog.service;

import com.soecode.blog.Untils.JSONUtil;
import com.soecode.blog.dao.mapper.ArticleMapper;
import com.soecode.blog.dao.mapper.ThumbsMapper;
import com.soecode.blog.entity.Article;
import com.soecode.blog.dao.redis.RedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ThumbsMapper thumbsMapper;
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

    /**
     * 批量修改文章状态
     * @param id
     * @param articleStatus
     * @return
     */
    public int updateStatus(String id, Integer articleStatus) {
        String[] ids = id.split(",");
        List<Object[]> list = new ArrayList<Object[]>();
        if(ids != null && ids.length>0){
            for(int i=0;i<ids.length;i++){
                list.add(new Object[]{articleStatus,ids[i],ids[i]});
            }
            int[] result = articleMapper.updateStatus(list);
            if(result.length == 0){
                return -1;
            }
            return 1;
        }
        return -1;

    }

    public void changArticleLikeCount(Map<String,Object> map) {
        articleMapper.changArticleLikeCount(map);

    }

    public Article select(String targetId) {

        return this.articleMapper.select(targetId);
    }
}
