package entity;

import java.io.Serializable;

/**
 * @program: Courseware-Backend-Java-2022
 * @description: 返回结果类
 * @author: YxYL
 * @create: 2022-11-22 09:32
 **/
public class Result<T> implements Serializable {
    /**
     * 状态码
     */
    private Integer code;

    /**
     * 消息
     */
    private String msg;

    /**
     * 返回数据
     */
    private T data;
    
    public Result() {
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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
}