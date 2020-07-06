/**
 * User: zsquirrel
 * Date: 2020/4/28
 * Time: 5:16 下午
 */
package com.cskaoyan.mall.model;

import java.util.List;

public class Result {

    private Integer code;

    private Object data;

    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Result() {
    }

    public Result(List data) {
        if(data==null){
            setCode(500);
            setData(data);
            setMessage("500：服务器错误！");
        }else if(data.isEmpty()){
            setCode(404);
            setData(data);
            setMessage("404：没有数据");
        }else{
            setCode(0);
            setData(data);
        }
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", data=" + data +
                '}';
    }
}
