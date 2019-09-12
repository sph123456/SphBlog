package com.soecode.blog.dao.mapper;

import com.soecode.blog.entity.Comment;
import com.soecode.blog.entity.Thumbs;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CommentMapper {
    int deleteByPrimaryKey(@Param(value = "targetId") Integer targetId,@Param(value = "userId") Integer userId, @Param(value = "commentId") Integer commentId);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    List<Comment> commentList(@Param(value = "start") Integer start, @Param(value = "pagesize") Integer pagesize);

    Integer totalCount();

    int addComment(@Param(value = "targetId") String targetId, @Param(value = "pid") String pid, @Param(value = "content") String content, @Param(value = "userId") String userId, @Param(value = "userNickname") String userNickname, @Param(value = "replyUserid") String replyUserid
            , @Param(value = "replyId") String replyId, @Param(value = "status") Integer status,@Param(value = "replyUsername") String replyUsername);

    void changArticleLikeCount(@Param(value = "map") Map<String,Object> map);

    int replay(@Param(value = "targetId") String targetId, @Param(value = "pid") String pid, @Param(value = "content") String content, @Param(value = "userId") String userId, @Param(value = "userNickname") String userNickname, @Param(value = "replyUserid") String replyUserid
            , @Param(value = "replyUsername") String replyUsername,@Param(value = "replyId") String replyId, @Param(value = "status") Integer status,@Param(value = "type") Integer type);


//    int addComment(@Param(value = "targetId") String targetId,@Param(value = "content") String content,,@Param(value = "userId") String userId, @Param(value = "userNickname")String userNickname, String pid, Integer status);
}