package com.soecode.blog.service;

import com.nimbusds.jose.JOSEException;
import com.soecode.blog.Untils.TokenUtil;
import com.soecode.blog.dao.mapper.UserMapper;
import com.soecode.blog.dao.redis.RedisDao;
import com.soecode.blog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService  {
    public static Object redisUser ;
    @Resource
     private UserMapper userMapper;

    @Autowired
    private RedisDao redisDao;
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

    public Integer checklogin(String phone, String password) {
        Integer result = this.userMapper.checklogin(phone,password);
        String token = null;
        if(result > 0 ){
            User user = userMapper.userInfo(phone,password);
            String userInfor= user.getId().toString()+user.getNickname();
            Map<String, Object> map = new HashMap<>();
            //建立载荷。
            map.put("userInfor", userInfor);
            //生成时间
            map.put("sta", new Date().getTime());
            //过期时间
            map.put("exp", new Date().getTime()+600);
            try {
                 token = TokenUtil.creatToken(map);
                 String key = token;
                 if(redisDao.existKey(key)){
                     redisUser =redisDao.hgetAll(key);
                 }else {
                     Map <String,String> hmap = new HashMap<String,String>();
                     hmap.put("userID",user.getId().toString());
                     hmap.put("nickName",user.getNickname());
                     redisDao.setMap(key,hmap);
                 }
            } catch (JOSEException e) {
                e.printStackTrace();
            }
        }
            return  result;
    }
}
