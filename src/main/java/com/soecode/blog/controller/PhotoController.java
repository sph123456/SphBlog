package com.soecode.blog.controller;

import com.soecode.blog.Untils.BaseResponseUtil;
import com.soecode.blog.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("photo")
@ResponseBody
public class PhotoController {
    @Autowired
    private PhotoService photoService;


    @RequestMapping(value = "/photoList",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String imageList(Integer pageSize, Integer currentPage,Integer imageKind){
        if(pageSize ==null || currentPage == null || imageKind == null){
            return BaseResponseUtil.getBaseRespToString(-1,"参数错误","erro");
        }
        return  BaseResponseUtil.getBaseRespToString(1,"ok",photoService.imageList(pageSize,currentPage,imageKind));
    }
    @RequestMapping(value = "/addImage")
    @ResponseBody
    public  String addImage(String imageTitle, Integer imageKind, String imageUrl){
        if(StringUtils.isEmpty(imageTitle) || StringUtils.isEmpty(imageUrl) || imageKind == null){
            return BaseResponseUtil.getBaseRespToString(-1,"参数错误","erro");
        }
        int count = photoService.select(imageTitle);
        if(count>0){
            return  BaseResponseUtil.getBaseRespToString(-2,"标题已经存在",null);
        }else {
            int result = photoService.addImage(imageTitle,imageKind,imageUrl);
            if(result >0){
                return  BaseResponseUtil.getBaseRespToString(0,"添加成功","ok");
            }else {
                return  BaseResponseUtil.getBaseRespToString(-3,"添加失败","err0");
            }
        }
    }
    @RequestMapping(value = "/updateImage")
    @ResponseBody
    public  String updateImage(Integer id ,String imageTitle, String imageKind, String imageUrl) {
        if(id == null){
            return BaseResponseUtil.getBaseRespToString(-1,"参数错误","erro");
        }
        int result  = photoService.updateImage(imageTitle,imageKind,imageUrl,id);
//        Date data = new Date();

        if(result==1){
            return BaseResponseUtil.getBaseRespToString(0, "修改成功",null);
        }else {
            return BaseResponseUtil.getBaseRespToString(-2,"修改失败","erro");
        }

    }

    @RequestMapping(value = "/deleteImage")
    @ResponseBody
    public  String  deleteImage(Integer id) {
        if(id == null){
            return BaseResponseUtil.getBaseRespToString(-1,"参数错误","erro");
        }
        int result = photoService.deleteImage(id);
        if(result >1){
            return BaseResponseUtil.getBaseRespToString(0,"删除成功","ok");
        }else {
            return BaseResponseUtil.getBaseRespToString(-2,"删除失败","erro");
        }
    }


}
