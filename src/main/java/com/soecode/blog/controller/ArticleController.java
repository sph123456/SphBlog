package com.soecode.blog.controller;

import com.mysql.jdbc.StringUtils;
import com.soecode.blog.Untils.BaseResponseUtil;
import com.soecode.blog.Untils.PageUntil;
import com.soecode.blog.entity.AddParams;
import com.soecode.blog.entity.Article;
import com.soecode.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping( "article")
@ResponseBody
public class ArticleController {

        @Autowired
        private ArticleService articleService;

    /**
     * 获取用户列表进行分页
     * @param pagesize   限制条数
     * @param currentPage 当前页
     * @return
     */
    @RequestMapping(value = "articleList",produces = "application/json;charset=utf-8")
    @ResponseBody
    public  String articleList (Integer pagesize, Integer currentPage,Integer articleClassify){
        if(pagesize == null || currentPage == null){
            return BaseResponseUtil.getBaseRespToString(-1,"参数为空","erro");
        }
        List<Article> list = articleService.articleList(pagesize,currentPage,articleClassify);
        PageUntil<Article> pageUntil = new PageUntil<Article>();
        pageUntil.setCurrentPage(currentPage);
        pageUntil.setPageSize(pagesize);
        pageUntil.setInforList(list);
        pageUntil.setRecordCount(articleService.totalCount(articleClassify));
        pageUntil.getPageCount();
        return BaseResponseUtil.getBaseRespToString(0,"ok",pageUntil);
    }


    /**
     * 增加分类
     * @param
     * @return
     */
    @RequestMapping(value = "/addArticle",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String addArticle ( AddParams addParams){
        String articleAuthor = addParams.getArticleAuthor();
        Integer articleClassify = addParams.getArticleClassify();
        String articleContent  = addParams.getArticleContent();
        String articleImage =  addParams.getArticleImage();
        Integer articleLikeNum = addParams.getArticleLikeNum();
        Integer articleReadNum = addParams.getArticleReadNum();
        Integer articleStatus = addParams.getArticleStatus();

        String articleTitle = addParams.getArticleTitle();


        if(StringUtils.isNullOrEmpty(articleTitle) ||StringUtils.isNullOrEmpty(articleContent) ||StringUtils.isNullOrEmpty(articleAuthor)
                 || articleClassify == null ){
            return BaseResponseUtil.getBaseRespToString(-1,"参数错误","erro");
        }
        if(articleLikeNum == null) {
            articleLikeNum = 0;

        }else if(articleReadNum == null){
            articleReadNum = 0;
        }
        int ret = articleService.addArticle(articleTitle,articleImage,articleAuthor,articleClassify,articleReadNum,articleLikeNum,articleContent);
        if(ret > 0){
            return BaseResponseUtil.getBaseRespToString(0,"注册成功","ok");
        }
        return BaseResponseUtil.getBaseRespToString(0,"注册成功","ok");
    }

    /**
     * 修改分类
     * @param
     * @param
     * @return
     */
    @RequestMapping(value = "/updateArticle" ,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String updateKind(AddParams addParams){
        Integer id = addParams.getId();
        String articleAuthor = addParams.getArticleAuthor();
        Integer articleClassify = addParams.getArticleClassify();
        String articleContent  = addParams.getArticleContent();
        String articleImage =  addParams.getArticleImage();
        Integer articleLikeNum = addParams.getArticleLikeNum();
        Integer articleReadNum = addParams.getArticleReadNum();
        Integer articleStatus = addParams.getArticleStatus();
        String articleTitle = addParams.getArticleTitle();

        if(id == null){
            return BaseResponseUtil.getBaseRespToString(-1,"参数错误","erro");
        }
//        if(articleLikeNum == null) {
//            articleLikeNum = 0;
//
//        }else if(articleReadNum == null){
//            articleReadNum = 0;
//        }
        int result = articleService.updateArticle(id,articleTitle,articleImage,articleAuthor,articleClassify,articleReadNum,articleLikeNum,articleContent);
        if(result == 1){
            return BaseResponseUtil.getBaseRespToString(0,"修改成功","ok");
        }else {
           return BaseResponseUtil.getBaseRespToString(-2,"修改失败","erro");
        }

    }


    /**
     * 根据id删除用户信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete",produces = "application/json;charset=utf-8")
    @ResponseBody()
    public String delete(  Integer id) {
        if (id == null) {
            return BaseResponseUtil.getBaseRespToString(-1, "参数错误", "erro");
        }
        int result = articleService.delete(id);
        if (result == 0) {
            return  BaseResponseUtil.getBaseRespToString(-2,"删除失败","erro");
        }else {
            return BaseResponseUtil.getBaseRespToString(1, "Success", null);
        }
    }
}
