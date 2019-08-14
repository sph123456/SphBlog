package com.soecode.blog.Untils;

import com.google.gson.*;
import com.alibaba.fastjson.JSONObject;

import org.springframework.util.StringUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class JSONUtil {
    private static final Gson gs = new GsonBuilder().disableHtmlEscaping().create();
    private static final Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    /**
     * 将对象转换为json数据
     */
    public static String objToJsonString(Object obj) {
        return JSONObject.toJSONString(obj);
    }
    public static String objToJsonStringEx(Object obj){
        return gs.toJson(obj);
    }
    /**
     * 将String转换为对象
//     */
//    @SuppressWarnings("unchecked")
    public static <T> T objFromJsonString(String jsonString, Type type) {
        if (StringUtils.isEmpty(jsonString)) {
            return (T) gson.fromJson(jsonString, type);
        }
        return null;
    }
    public static <T> ArrayList<T> fromJsonList(String json, Type cls) {
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        ArrayList<T> mList = new ArrayList<T>(array.size());
        for(final JsonElement elem : array){
            mList.add(gson.fromJson(elem, cls));
        }
        return mList;
    }
    public static <superType, T> ArrayList<superType> fromJsonList(String json, Class<T> cls, Type superType) {
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        ArrayList<superType> mList = new ArrayList<superType>(array.size());
        for(final JsonElement elem : array){
            mList.add((superType) gson.fromJson(elem, cls));
        }
        return mList;
    }

}
