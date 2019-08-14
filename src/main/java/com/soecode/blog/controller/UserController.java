package com.soecode.blog.controller;


import com.mysql.jdbc.StringUtils;
import com.soecode.blog.Untils.BaseResponseUtil;
import com.soecode.blog.Untils.PageUntil;
import com.soecode.blog.entity.RegisterParam;
import com.soecode.blog.entity.User;
import com.soecode.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;


@Controller
@RequestMapping( "user")
@ResponseBody
public class UserController {
    @Autowired
    private UserService userService;
    /**
     * 手机号码正则表达式
     */
    private String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
            + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
            + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
            + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
    /**
     * 邮箱正则表达
     */
   private String regex1 = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
    /**
     * 用户登录
     * @param phone 手机号码
     * @param password 密码
     * @return
     */
    @RequestMapping(value = "/login",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String login(String phone, String password) {
        if ( StringUtils.isNullOrEmpty(phone) || password == null) {
           return BaseResponseUtil.getBaseRespToString(-1,"参数错误","erro");
        }
        if(!phone.matches(regex)){
            return BaseResponseUtil.getBaseRespToString(-2,"手机号码格式不对","erro");
        }

        int count = userService.Count(phone);
        if(count>0) {
            return BaseResponseUtil.getBaseRespToString(-3,"该手机号已经注册","erro");
        }else {
            int result = userService.checklogin(phone,password);
            if(result > 0) {
                return BaseResponseUtil.getBaseRespToString(0,"登录成功",null);
            }else {
                return BaseResponseUtil.getBaseRespToString(-1,"该手机号尚未注册或者密码错误",null);
            }
        }
//        if(count >0){
//            return BaseResponseUtil.getBaseRespToString(0,"登录成功",null);
//        }else {
//            return BaseResponseUtil.getBaseRespToString(-1,"该手机号尚未注册或者密码错误",null);
//        }

    }

    /**
     * 注册
     * @param params 注册参数
     * @return
     */
    @RequestMapping(value = "/register",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String register ( RegisterParam params){
        String phone = params.getPhone();
        String password = params.getPassword();
        String nickname =  params.getNickname();
        String email = params.getEmail();
        if(phone ==null || password == null || nickname ==null || email ==null){
           return BaseResponseUtil.getBaseRespToString(-1,"参数错误","erro");
        }
        if(phone.matches(regex)){
            return BaseResponseUtil.getBaseRespToString(-2,"手机号码格式错误","erro");
        }
        if(!email.matches(regex1)){
            return BaseResponseUtil.getBaseRespToString(-3,"邮箱格式错误","erro");
        }
        if(password.length()<6 || password.length()>16){
            return  BaseResponseUtil.getBaseRespToString(-4,"密码长度不对","erro");
        }
        int ret = userService.register(phone,password,nickname,email);
        if(ret > 0){
            return BaseResponseUtil.getBaseRespToString(0,"注册成功","ok");
        }
        return BaseResponseUtil.getBaseRespToString(0,"注册成功","ok");
    }

    /**
     * 修改管理员权限
     * @param id
     * @param
     * @return
     */
    @RequestMapping(value = "/updateAdmin" ,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String updateAdmin(Integer id,Integer isAdmin){
        if(id == null){
            return BaseResponseUtil.getBaseRespToString(-1,"参数为空","erro");
        }
        int result = userService.updateAdmin(id,isAdmin);
        if(result == 1){
            return BaseResponseUtil.getBaseRespToString(-1,"修改成功",null);
        }
        return BaseResponseUtil.getBaseRespToString(-2,"修改失败",null);
    }

    /**
     * 获取用户列表进行分页
     * @param pagesize   限制条数
     * @param currentPage 当前页
     * @return
     */
    @RequestMapping(value = "accountList",produces = "application/json;charset=utf-8")
    @ResponseBody
    public  String accountList (Integer pagesize, Integer currentPage,String phone){
        if(pagesize == null || currentPage == null){
            return BaseResponseUtil.getBaseRespToString(-1,"参数为空","erro");
        }
        List<User> list = userService.accountList(pagesize,currentPage);
        PageUntil<User> pageUntil = new PageUntil<User>();
        pageUntil.setCurrentPage(currentPage);
        pageUntil.setPageSize(pagesize);
        pageUntil.setInforList(list);
        pageUntil.setRecordCount(userService.TotalCount(phone));
        pageUntil.getPageCount();
        return BaseResponseUtil.getBaseRespToString(0,"ok",pageUntil);
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
       int result = userService.delete(id);
       if(result == 0)
       {
           return BaseResponseUtil.getBaseRespToString(-2,"删除失败",null);
       }else
       return BaseResponseUtil.getBaseRespToString(1,"Success",null);
    }

    @RequestMapping(value = "ifEnable",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ifEnable(Integer isDelete,String phone){
        if(phone == null || isDelete == null){
            return  BaseResponseUtil.getBaseRespToString(-1,"参数错误","erro");
        }
        int result = userService.ifEnable(isDelete,phone);
        if(result == 0){
            return  BaseResponseUtil.getBaseRespToString(-1,"禁用失败","erro");
        }else
         return  BaseResponseUtil.getBaseRespToString(0,"Success",0);
    }
}
