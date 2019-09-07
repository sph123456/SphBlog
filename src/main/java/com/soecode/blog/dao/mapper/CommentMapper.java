package com.soecode.blog.dao.mapper;

import com.soecode.blog.entity.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(String id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    List<Comment> commentList(Integer start, Integer pagesize);

    Integer totalCount();

    int addComment(@Param(value = "targetId") String targetId,@Param(value = "content") String content,@Param(value = "userNickname") String userNickname,@Param(value = "userId") String userId,@Param(value = "replyUserid") String replyUserid
    ,@Param(value = "replyId") String replyId,@Param(value = "status") String status);
}