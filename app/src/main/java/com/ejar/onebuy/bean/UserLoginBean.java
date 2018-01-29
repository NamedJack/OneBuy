package com.ejar.onebuy.bean;

/**
 * Created by Administrator on 2017\12\8 0008.
 */

public class UserLoginBean {
    /**
     * code : 200
     * msg : OK
     * data : {"msg":"三方登录请求成功","code":200,"userId":29,"cowUserId":100134,"token":"27257a2b611b80f798a52d5648753fb3"}
     */

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * msg : 三方登录请求成功
         * code : 200
         * userId : 29
         * cowUserId : 100134
         * token : 27257a2b611b80f798a52d5648753fb3
         */

        private String msg;
        private int code;
        private int userId;
        private String cowUserId;
        private String token;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getCowUserId() {
            return cowUserId;
        }

        public void setCowUserId(String cowUserId) {
            this.cowUserId = cowUserId;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
