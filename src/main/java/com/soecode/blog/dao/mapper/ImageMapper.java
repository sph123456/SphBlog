package com.soecode.blog.dao.mapper;

import com.soecode.blog.entity.Image;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ImageMapper {
    int deleteImage(@Param(value = "id") Integer id);

    int insert(Image record);

//    int insertSelective(Image record);
//
//    Image selectByPrimaryKey(Integer id);
//
//    int updateByPrimaryKeySelective(Image record);
//
//    int updateByPrimaryKey(Image record);

    List<Image> imageList(@Param(value = "pageSize") Integer pageSize, @Param(value = "start") Integer start, @Param(value = "currentPage") Integer currentPage,@Param(value = "imageKind") Integer imageKind);

    Integer totalCount();

    int select(@Param(value = "imageTitle") String imageTitle);

    int addImage(@Param(value = "imageTitle") String imageTitle, @Param(value = "imageKind") Integer imageKind, @Param(value = "imageUrl") String imageUrl,@Param(value = "createTime") Date createtime);

    int updateImage(@Param(value = "imageTitle") String imageTitle, @Param(value = "imageKind") String imageKind, @Param(value = "imageUrl") String imageUrl, @Param(value = "id") Integer id);

}