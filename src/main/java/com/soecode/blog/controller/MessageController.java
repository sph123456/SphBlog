package com.soecode.blog.controller;

import com.soecode.blog.Untils.BaseResponseUtil;
import com.soecode.blog.entity.Image;
import com.soecode.blog.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping( "message")
@ResponseBody
public class MessageController {
    @Resource
    private MessageService messageService;

    @RequestMapping(value = "/messageList")
    @ResponseBody
    public  String messageList(Integer currentPage, Integer pageSize, Integer replay) {
        if (currentPage == null || pageSize == null) {
            return BaseResponseUtil.getBaseRespToString(-1, "参数错误", "erro");
        }
        return BaseResponseUtil.getBaseRespToString(0,"ok",messageService.messageList(currentPage,pageSize,replay));
    }
}
