package edu.jlu.group17.back.utils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class R<T> {
    private Boolean code;//true成功，false失败
    private String msg;
    private T data;
    private Map<Object, Object> map=new HashMap<>();
    public static <T>R<T>success(T obj){
        R<T>r=new R<>();
        r.data=obj;
        r.code=true;
        return r;
    }
    public static <T>R<T>error(String msg){
        R<T>r=new R<>();
        r.msg=msg;
        r.code=false;
        return r;
    }
    public R<T>add(String key,Object value){
        map.put(key,value);
        return this;
    }
}
