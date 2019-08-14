package com.soecode.blog.controller;

import com.mysql.jdbc.StringUtils;
import com.soecode.blog.Untils.BaseResponseUtil;
import com.soecode.blog.Untils.PageUntil;
import com.soecode.blog.entity.Kind;
import com.soecode.blog.service.KindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping( "kind")
@ResponseBody
public class KindController {
    @Autowired
    private KindService kindService;


    /**
     * 获取用户列表进行分页
     * @param pagesize   限制条数
     * @param currentPage 当前页
     * @return
     */
    @RequestMapping(value = "kindList",produces = "application/json;charset=utf-8")
    @ResponseBody
    public  String kindList (Integer pagesize, Integer currentPage){
        if(pagesize == null || currentPage == null){
            return BaseResponseUtil.getBaseRespToString(-1,"参数为空","erro");
        }
        List<Kind> list = kindService.kindList(pagesize,currentPage);
        PageUntil<Kind> pageUntil = new PageUntil<Kind>();
        pageUntil.setCurrentPage(currentPage);
        pageUntil.setPageSize(pagesize);
        pageUntil.setInforList(list);
        pageUntil.setRecordCount(kindService.totalCount());
        pageUntil.getPageCount();
        return BaseResponseUtil.getBaseRespToString(0,"ok",pageUntil);
    }


    /**
     * 增加分类
     * @param
     * @return
     */
    @RequestMapping(value = "/addKind",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String addKind ( String kindName){

        if(StringUtils.isNullOrEmpty(kindName)){
            return BaseResponseUtil.getBaseRespToString(-1,"参数错误","erro");
        }

        int ret = kindService.addKind(kindName);
        if(ret > 0){
            return BaseResponseUtil.getBaseRespToString(0,"注册成功","ok");
        }
        return BaseResponseUtil.getBaseRespToString(0,"注册成功","ok");
    }

    /**
     * 修改分类
     * @param id
     * @param
     * @return
     */
    @RequestMapping(value = "/updateKind" ,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String updateKind(Integer id,String kindName){
        if(id == null){
            return BaseResponseUtil.getBaseRespToString(-2,"参数为空","erro");
        }
        int result = kindService.updateKind(id,kindName);
        if(result == 1){
            return BaseResponseUtil.getBaseRespToString(0,"修改成功",null);
        }else {
            return BaseResponseUtil.getBaseRespToString(-2,"修改失败",null);
        }

    }


    /**
     * 根据id删除用户信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete",produces = "application/json;charset=utf-8")
    @ResponseBody()
    public String delete(  Integer id){
        if(id == null){
            return BaseResponseUtil.getBaseRespToString(-1,"参数为空","erro");
        }
        int result = kindService.delete(id);
        if(result == 0)
        {
            return BaseResponseUtil.getBaseRespToString(-2,"删除失败",null);
        }else
            return BaseResponseUtil.getBaseRespToString(1,"Success",null);
    }
}

