package com.soecode.blog.dao.mapper;

import com.soecode.blog.entity.Thumbs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ThumbsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Thumbs record);

    int insertSelective(Thumbs record);

    Thumbs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Thumbs record);

    int updateByPrimaryKey(Thumbs record);

    List<Thumbs> thumbList(@Param(value = "targetId")String targetId);

    int add(@Param(value = "userId") String userId,@Param(value = "targetId")  String targetId);

}