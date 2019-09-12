package com.soecode.blog.controller;

import com.soecode.blog.Untils.BaseResponseUtil;
import com.soecode.blog.Untils.PageUntil;
import com.soecode.blog.entity.Article;
import com.soecode.blog.entity.Comment;
import com.soecode.blog.entity.Thumbs;
import com.soecode.blog.service.ArticleService;
import com.soecode.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping( "comment")
@ResponseBody
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ArticleService articleService;


    /**
     * 获取评论列表进行分页
     * @param pagesize   限制条数
     * @param currentPage 当前页
     * @return
     */
    @RequestMapping(value = "commentList")
    @ResponseBody
    public  String commentList (Integer pagesize, Integer currentPage){
        if(pagesize == null || currentPage == null){
            return BaseResponseUtil.getBaseRespToString(-1,"参数为空","erro");
        }
        return BaseResponseUtil.getBaseRespToString(0,"ok",commentService.commentList(pagesize,currentPage));
    }


    @RequestMapping(value = "addComment")
    public  String addComment(Comment comment){
//        comment.setStatus();
        if(StringUtils.isEmpty(comment.getTargetId())|| StringUtils.isEmpty(comment.getContent())|| StringUtils.isEmpty(comment.getUserId())
                || StringUtils.isEmpty(comment.getUserNickname()) || comment.getContent().length()>200){
            return BaseResponseUtil.getBaseRespToString(-1,"参数错误","");
        }
        int status = commentService.status(comment.getTargetId());
        if(status > 0){
            int result = commentService.addComment(comment);
            if(result>0){
                return  BaseResponseUtil.getBaseRespToString(0,"评论成功",null);
            }
            return BaseResponseUtil.getBaseRespToString(-3,"评论失败","");

        }else {
            return BaseResponseUtil.getBaseRespToString(-4,"该文章不在状态",null);
        }

    }
    @RequestMapping(value = "replay")
    public String replay(Comment comment){
        if(StringUtils.isEmpty(comment.getTargetId())|| StringUtils.isEmpty(comment.getContent())|| StringUtils.isEmpty(comment.getUserId())
                || StringUtils.isEmpty(comment.getUserNickname()) || comment.getContent().length()>200
                || StringUtils.isEmpty(comment.getPid())){
            return BaseResponseUtil.getBaseRespToString(-1,"参数错误","");
        }
        int result = commentService.repaly(comment);
        if(result>0){
            return  BaseResponseUtil.getBaseRespToString(0,"回复成功",null);
        }
        return BaseResponseUtil.getBaseRespToString(-3,"回复失败","");
    }

    @RequestMapping(value = "thumb")
    public String thumb(String targetId,String userId){
        if(StringUtils.isEmpty(targetId) || StringUtils.isEmpty(userId)){
            return BaseResponseUtil.getBaseRespToString(-1,"参数错误",null);
        }
        boolean isThumbs= false;
        List<Thumbs> list = commentService.thumbList(targetId);
        for(int i=0; i<list.size(); i++){
            if(userId.equals(list.get(i).getUserId())) {
                isThumbs = true;
                break;
            }
        }
        if (isThumbs){
            return BaseResponseUtil.getBaseRespToString(-2,"已经点过赞了",null);
        }else{
                Article article=  articleService.select(targetId);
                int oldcount = article.getArticleLikeNum();
                Map<String,Object> map = new HashMap<>();
                map.put("targetId",targetId);
                map.put("articleLikeCount",oldcount+1);
                commentService.thumbs(userId,targetId);
                articleService.changArticleLikeCount(map);
        }
        return BaseResponseUtil.getBaseRespToString(0,"点赞成功",null);
    }

    /**
     * 删除评论
     */
    @RequestMapping(value = "delete")
    public String delete(Integer targetId,Integer userId,Integer commentId){
        if(commentId == null){
            return BaseResponseUtil.getBaseRespToString(-1,"参数错误",null);
        }
        int result = commentService.delete(targetId,userId,commentId);
        if (result>0){
            return BaseResponseUtil.getBaseRespToString(0,"删除成功",result);
        }else{
            return  BaseResponseUtil.getBaseRespToString(-2,"删除失败",result);
        }
    }
}
