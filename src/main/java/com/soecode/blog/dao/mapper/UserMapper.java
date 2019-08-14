package com.soecode.blog.dao.mapper;

import com.soecode.blog.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(@Param(value = "phone") String phone, @Param(value = "password") String password, @Param(value = "nickname") String nickname, @Param(value = "email") String email);

    int updateAdmin(@Param(value = "id") Integer id,@Param(value = "isAdmin") Integer isAdmin);

    List<User> accountList(@Param(value = "pagesize") Integer pagesize, @Param(value = "currentpage") Integer currentpage, @Param(value = "start")Integer start );

    int TotalCount(String phone);

    int ifEnable(@Param(value = "isDelete") Integer isDelete,@Param(value = "phone") String phone);

    int Count(String phone);

    int checklogin(@Param(value = "phone") String phone,@Param(value = "password") String password);
}