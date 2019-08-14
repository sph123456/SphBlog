package com.soecode.blog.service;

import com.soecode.blog.Untils.DateUtil;
import com.soecode.blog.Untils.JSONUtil;
import com.soecode.blog.Untils.PageUntil;
import com.soecode.blog.dao.redis.RedisDao;
import com.soecode.blog.entity.Image;
import com.soecode.blog.dao.mapper.ImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ImageService {
    @Resource
    private ImageMapper imageMapper;
    @Autowired
    private RedisDao redisDao;

    public static void main(String[] args) {
        Integer a = null;
        long now = System.currentTimeMillis();
        System.out.println(now);

    }

    public PageUntil<Image> imageList(Integer currentPage, Integer pageSize,Integer imageKind) {
        int start = (currentPage-1)*pageSize;
        String  key = "imageList";
        List<Image> list =null;
//        if(imageKind== null){
//            list = imageMapper.imageList(pageSize,start,currentPage,imageKind);
//
//        }else {
//
//            String field="imageList"+ imageKind;
//            String objectList = redisDao.hget(key,field);
//            if(objectList != null){
//                list = JSONUtil.fromJsonList(objectList,Image.class);
//            }else {
//                list = imageMapper.imageList(pageSize,start,currentPage,imageKind);
//                redisDao.hset(key,field,JSONUtil.objToJsonString(list));
//                Long seconds = DateUtil.getSecondsNextEarlyMorning(3,0,0)/1000;
//                int second = seconds.intValue();
//                redisDao.setExpire(key,24*60*60);
//            }
//        }
        PageUntil<Image> pageUntil = new PageUntil<Image>();
        pageUntil.setCurrentPage(currentPage);
        pageUntil.setPageSize(pageSize);
        pageUntil.setInforList(list);
        pageUntil.setRecordCount(imageMapper.totalCount());
        return pageUntil;
//        System.out.println(imageKind.toString());

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
        redisDao.hdel(key,field);
//        redisDao.d
        return result;
    }

    public int updateImage(String imageTitle, String imageKind, String imageUrl, Integer id) {
        int result = this.imageMapper.updateImage(imageTitle,imageKind,imageUrl,id);
        String key = "imageList";
        String field ="imageList"+imageKind;
        redisDao.hdel(key,field);
        return result;
    }

    public int deleteImage(Integer id) {
        int result = this.imageMapper.deleteImage(id);
        String key = "imageList";
        redisDao.del(key);
        return result;
    }

}
