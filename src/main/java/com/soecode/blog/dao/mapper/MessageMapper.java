package com.soecode.blog.dao.mapper;

import com.soecode.blog.entity.Message;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageMapper {
    int deleteByPrimaryKey(Integer id);

//    int insert(Message record);
//
//    int insertSelective(Message record);
//
//    Message selectByPrimaryKey(Integer id);
//
//    int updateByPrimaryKeySelective(Message record);
//
//    int updateByPrimaryKeyWithBLOBs(Message record);
//
//    int updateByPrimaryKey(Message record);

    List<Message> MessageList(@Param(value = "replay") Integer replay, @Param(value = "start")int start, @Param(value = "pageSize") Integer pageSize, @Param(value = "currentPage")Integer currentPage);

    Integer count(@Param(value = "replay")Integer replay);
}