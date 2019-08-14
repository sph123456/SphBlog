package com.soecode.blog.Untils;

import com.soecode.blog.entity.BaseResp;

public class BaseResponseUtil {
    public static String getBaseRespToString(int code, String msg, Object obj) {
        BaseResp resp = new BaseResp();
        resp.code = code;
        resp.msg = msg;
        resp.obj = obj;
        resp.currentTime = System.currentTimeMillis();
        return JSONUtil.objToJsonString(resp);
    }

}
