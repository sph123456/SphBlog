package com.soecode.blog.service;

import com.soecode.blog.Untils.PageUntil;
import com.soecode.blog.dao.mapper.MessageMapper;
import com.soecode.blog.dao.redis.RedisDao;
import com.soecode.blog.entity.Image;
import com.soecode.blog.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MessageService {


    @Resource
    private MessageMapper messageMapper;

//    @Autowired
//    private RedisDao redisDao;


    public  PageUntil<Image>  messageList(Integer currentPage, Integer pageSize, Integer replay) {
        int start = (currentPage-1)*pageSize;
        List<Image> list =messageMapper.MessageList(replay,start,pageSize,currentPage);
        PageUntil<Image> pageUntil = new PageUntil<>();
        pageUntil.setPageSize(pageSize);
        pageUntil.setCurrentPage(currentPage);
        pageUntil.setInforList(list);
        pageUntil.setRecordCount(messageMapper.count(replay));
        return  pageUntil;

    }
}
