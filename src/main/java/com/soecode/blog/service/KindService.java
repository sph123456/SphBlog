package com.soecode.blog.service;

import com.soecode.blog.dao.mapper.KindMapper;
import com.soecode.blog.entity.Kind;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class KindService {

    @Resource
    private KindMapper kindMapper;


    public int addKind(String kindName) {
        return this.kindMapper.addKind(kindName);
    }

    public int updateKind(Integer id, String kindName) {
        return  this.kindMapper.updateKind(id,kindName);
    }

    public List<Kind> kindList(Integer pagesize, Integer currentPage) {
        int start = (currentPage-1)*pagesize;
        return this.kindMapper.kindList(pagesize,currentPage,start);
    }

    public Integer totalCount() {
        return  this.kindMapper.totalCount();
    }

    public int delete(Integer id) {
        return this.kindMapper.deleteByPrimaryKey(id);
    }
}
