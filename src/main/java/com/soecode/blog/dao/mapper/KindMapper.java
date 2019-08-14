package com.soecode.blog.dao.mapper;

import com.soecode.blog.entity.Kind;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface KindMapper {
    int deleteByPrimaryKey(@Param(value = "id") Integer id);

    int addKind(@Param(value = "kindName") String kindName);

//    Kind selectByPrimaryKey(Integer id);

    int updateKind(@Param(value = "id")Integer id,@Param(value = "kindName") String kindName);

    List<Kind> kindList(@Param(value = "pagesize") Integer pagesize,@Param(value = "currentPage") Integer currentPage, @Param(value = "start") int start);

    Integer totalCount();
}