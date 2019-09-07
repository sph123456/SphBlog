package com.soecode.blog.service;

import com.soecode.blog.Untils.PageUntil;
import com.soecode.blog.dao.mapper.CommentMapper;
import com.soecode.blog.dao.redis.RedisDao;
import com.soecode.blog.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommentService {


    @Autowired
    protected CommentMapper commentMapper;

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
//        int result = commentMapper.addComment(comment);
        return Integer.parseInt(null);
    }
}
