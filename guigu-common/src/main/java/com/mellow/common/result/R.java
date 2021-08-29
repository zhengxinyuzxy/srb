package com.mellow.common.result;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class R {
    private Integer code;
    private String message;
    private Map<String, Object> data = new HashMap<>();

    // 构造私有化
    private R(){}

    // 响应成功
    public static R ok() {
        R r = new R();
        r.setCode(ResponseEnum.SUCCESSS.getCode());
        r.setMessage(ResponseEnum.SUCCESSS.getMessage());
        return r;
    }

    // 响应失败
    public static R error() {
        R r = new R();
        r.setCode(ResponseEnum.ERROR.getCode());
        r.setMessage(ResponseEnum.ERROR.getMessage());
        return r;
    }

    // 给R的map类型的data赋值
    public R data(String key, Object value) {
        this.data.put(key,value);
        return this;
    }

    // 给R的map集合赋值
    public R data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }

    // 响应特定结果
    public static R setResult(ResponseEnum responseEnum) {
        R r = new R();
        r.setCode(responseEnum.getCode());
        r.setMessage(responseEnum.getMessage());
        return r;
    }

    // 返回特定的信息
    public R Message(String message) {
        this.setMessage(message);
        return this;
    }

    // 返回特定的状态码
    public R code(Integer code) {
        this.setCode(code);
        return this;
    }

}
