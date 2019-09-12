package com.soecode.blog.service;

import com.soecode.blog.Untils.PageUntil;
import com.soecode.blog.dao.mapper.ArticleMapper;
import com.soecode.blog.dao.mapper.CommentMapper;
import com.soecode.blog.dao.mapper.ThumbsMapper;
import com.soecode.blog.dao.redis.RedisDao;
import com.soecode.blog.entity.Comment;
import com.soecode.blog.entity.Thumbs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class CommentService {


    @Autowired
    protected CommentMapper commentMapper;
    @Autowired
    private ThumbsMapper thumbsMapper;
    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private RedisDao redisDao;

    public PageUntil<Comment> commentList(Integer pagesize, Integer currentPage) {
        Integer start = (currentPage-1)*pagesize;
        List<Comment> list = commentMapper.commentList(start,pagesize);
        PageUntil<Comment> pageUntil = new PageUntil<Comment>();
        pageUntil.setCurrentPage(currentPage);
        pageUntil.setPageSize(pagesize);
        pageUntil.setInforList(list);
        pageUntil.setRecordCount(commentMapper.totalCount());
        pageUntil.getPageCount();
        return pageUntil;
    }

    public int addComment(Comment comment) {
//        String commentKey= String.format("UserKey",comment.getUserId());
        comment.setPid("0");
        comment.setStatus(1);
        int result = commentMapper.addComment(comment.getTargetId(),comment.getPid(),comment.getContent(),comment.getUserId(),comment.getUserNickname(),comment.getReplyUserid(),comment.getReplyId(),comment.getStatus(),comment.getReplyUsername());
        return result;
    }

    public int status(String targetId) {
        return articleMapper.status(targetId);
    }

    public int  repaly(Comment comment) {
//        comment.setPid("1");

        comment.setType(1);
        comment.setStatus(1);
        int result = commentMapper.replay(comment.getTargetId(),comment.getPid(),comment.getContent(),comment.getUserId(),comment.getUserNickname(),comment.getReplyUserid(),comment.getReplyUsername(),comment.getReplyId(),comment.getStatus(),comment.getType());
        return result;
    }

    public List<Thumbs> thumbList(String targetId) {
        List<Thumbs> list =thumbsMapper.thumbList(targetId);
        return list;
    }

    public Integer thumbs(String userId, String targetId) {
        int result = thumbsMapper.add(userId,targetId);
        return result;
    }

    public int delete(Integer targetId, Integer userId, Integer commentId) {
    int result = this.commentMapper.deleteByPrimaryKey(targetId,userId,commentId);
    return result;
    }

//    public void changArticleLikeCount(Map<String,Object> map) {
//        commentMapper.changArticleLikeCount(map);
//    }
}
