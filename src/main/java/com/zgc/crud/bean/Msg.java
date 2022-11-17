package com.zgc.crud.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 耿
 * @version 1.0
 * @Date 2022/11/12 14:30
 * description:
 */
public class Msg {

    //状态码 100:成功    200:失败
    private int code;

    //提示信息
    private String msg;

    //用户要返回的数据
    private Map<String, Object> extend = new HashMap<>();

    //返回成功信息
    public static Msg success(){
        Msg result = new Msg();
        result.setCode(100);
        result.setMsg("处理成功！");
        return result;
    }

    //返回失败信息
    public static Msg fail(){
        Msg result = new Msg();
        result.setCode(200);
        result.setMsg("处理失败！");
        return result;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    //配置数据
    public Msg add(String key, Object value){
        this.getExtend().put(key, value);
        return this;
    }


    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }
}
