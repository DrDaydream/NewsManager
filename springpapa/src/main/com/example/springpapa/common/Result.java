package com.example.springpapa.common;

public class Result<T> {
    private String code;//识别码
    private String msg;//返回信息
    private T data;//用于查询不同表

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    public Result(){

    }
    public Result(T data){
        this.data=data;
    }
    public static Result success(){
        Result result=new Result<>();
        result.setCode("0");
        result.setMsg("success");
        return result;
    }
    public static <T> Result<T> success(T data){
        Result<T> result=new Result<>(data);
        result.setCode("0");
        result.setMsg("success");
        return result;
    }
    public static Result error(String code,String msg){
        Result result=new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }


}
