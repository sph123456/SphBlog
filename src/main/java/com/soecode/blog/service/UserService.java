package com.soecode.blog.service;

import com.soecode.blog.dao.mapper.UserMapper;
import com.soecode.blog.entity.User;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService  {
    @Resource
     private UserMapper userMapper;
    public int register(String phone, String password, String nickname, String email) {
        int result = userMapper.insert(phone,password,nickname,email);
        return  result;
    }

    public int updateAdmin(Integer id, Integer isAdmin) {
        int result = userMapper.updateAdmin(id,isAdmin);
        return  result;
    }

    public List<User> accountList(Integer pagesize, Integer currentPage) {
        int start = (currentPage-1)*pagesize;
        return userMapper.accountList(pagesize,currentPage,start);
    }

    public Integer TotalCount(String phone) {
        int result = userMapper.TotalCount(phone);
        return  result;
    }

    public int delete(Integer id) {
        int result = userMapper.deleteByPrimaryKey(id);
        return result;
    }

    public int ifEnable(Integer isDelete,String phone) {
        int result = userMapper.ifEnable(isDelete,phone);
        return result;
    }

    public int Count(String phone) {
       int result = this.userMapper.Count(phone);
        return  result;
    }

    public int checklogin(String phone, String password) {
        return this.userMapper.checklogin(phone,password);
    }
}
