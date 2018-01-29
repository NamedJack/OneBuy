package wang.wongxd.wquicklib.base.retrofit.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by wongxd on 2017/8/2.
 *
 * 响应基类
 */
public class BaseResponse<T> implements Serializable {
    @SerializedName("code")
    public int code;
    @SerializedName("msg")
    public String msg;
    @SerializedName("data")
    public T data;
}