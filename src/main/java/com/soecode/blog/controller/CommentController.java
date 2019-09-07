package com.soecode.blog.controller;

import com.soecode.blog.Untils.BaseResponseUtil;
import com.soecode.blog.Untils.PageUntil;
import com.soecode.blog.entity.Article;
import com.soecode.blog.entity.Comment;
import com.soecode.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping( "comment")
@ResponseBody
public class CommentController {

    @Autowired
    private CommentService commentService;


    /**
     * 获取评论列表进行分页
     * @param pagesize   限制条数
     * @param currentPage 当前页
     * @return
     */
    @RequestMapping(value = "commentList",produces = "application/json;charset=utf-8")
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

        int result = commentService.addComment(comment);
        if(result>0){

        }
            return BaseResponseUtil.getBaseRespToString(-3,"评论失败","");

    }
}
