package com.soecode.blog.service;

import com.soecode.blog.Untils.PageUntil;
import com.soecode.blog.dao.mapper.ImageMapper;
import com.soecode.blog.dao.redis.RedisDao;
import com.soecode.blog.entity.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PhotoService {
    @Autowired
    private ImageMapper imageMapper;
    @Autowired
    private RedisDao redisDao;

    public PageUntil<Image> imageList(Integer pageSize, Integer currentPage, Integer imageKind) {
        int start = (currentPage-1)*pageSize;
        List<Image> list = imageMapper.imageList(pageSize,start,currentPage,imageKind);
        PageUntil<Image> pageUntil = new PageUntil<Image>();
        pageUntil.setPageSize(pageSize);
        pageUntil.setCurrentPage(currentPage);
        pageUntil.setInforList(list);
        pageUntil.setRecordCount(imageMapper.totalCount(imageKind));
        return  pageUntil;
    }

    public int select(String imageTitle) {
        int result =this.imageMapper.select(imageTitle);
        return  result;
    }

    public int addImage(String imageTitle, Integer imageKind, String imageUrl) {
        Date createTime = new Date();
        int result = this.imageMapper.addImage(imageTitle,imageKind,imageUrl,createTime);
        String key = "imageList";
        String field ="imageList"+imageKind;
//        redisDao.hdel(key,field);
//        redisDao.d
        return result;
    }

    public int updateImage(String imageTitle, String imageKind, String imageUrl, Integer id) {
        int result = this.imageMapper.updateImage(imageTitle,imageKind,imageUrl,id);
//        String key = "imageList";
//        String field ="imageList"+imageKind;
//        redisDao.hdel(key,field);
        return result;
    }

    public int deleteImage(Integer id) {
        int result = this.imageMapper.deleteImage(id);
//        String key = "imageList";
//        redisDao.del(key);
        return result;
    }
}
