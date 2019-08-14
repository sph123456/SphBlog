package com.soecode.blog.Untils;

import com.soecode.blog.entity.User;

import java.util.ArrayList;
import java.util.List;

public class PageUntil<T> {

    private Integer pageSize ; //每页显示的条数
    private Integer recordCount;//记录数
    private Integer currentPage;//当前页
    private Integer pageCount;//页数
    private List<T> inforList; //记录信息.
    private Long currentTime ;	//返回系统当前时间

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getRecordCount() {
        return this.recordCount;
    }

    public void setRecordCount(Integer recordCount) {
        this.recordCount = recordCount;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public List<T> getInforList() {
        return this.inforList;
    }

    public void setInforList(List<T> inforList) {
        this.inforList = inforList;
    }

    public Long getCurrentTime() {
        return this.currentTime;
    }

    public void setCurrentTime(Long currentTime) {
        this.currentTime = currentTime;
    }
}
