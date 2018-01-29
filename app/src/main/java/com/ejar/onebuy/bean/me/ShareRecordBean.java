package com.ejar.onebuy.bean.me;

import java.util.List;

/**
 * Created by wongxd on 2017/8/22.
 * 晒单记录
 */

public class ShareRecordBean {

    /**
     * code : 200
     * msg : OK
     * data : {"msg":"展示商品晒单详情成功","code":100,"olist":[{"id":1,"gid":143,"content":"测试内容","surl":"/static/product/picture/QQ截图20170807181213.png,/static/product/picture/rBACE1bHu06g6ZRsAAEagrk8V7s15_200x200_3.jpg,/static/product/picture/rBACFFX_bELS8zrBAAFms3U0o8c386_200x200_3.png","username":"吃炸鸡的猪","suntime":"2017-08-14 14:50:15","shopname":"08-10快速夺宝","datenumber":"1","expectNum":10,"winningNumber":"10000004","lotterytime":"2017-08-11 09:27:10","img":""}]}
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
         * msg : 展示商品晒单详情成功
         * code : 100
         * olist : [{"id":1,"gid":143,"content":"测试内容","surl":"/static/product/picture/QQ截图20170807181213.png,/static/product/picture/rBACE1bHu06g6ZRsAAEagrk8V7s15_200x200_3.jpg,/static/product/picture/rBACFFX_bELS8zrBAAFms3U0o8c386_200x200_3.png","username":"吃炸鸡的猪","suntime":"2017-08-14 14:50:15","shopname":"08-10快速夺宝","datenumber":"1","expectNum":10,"winningNumber":"10000004","lotterytime":"2017-08-11 09:27:10","img":""}]
         */

        private int totalPage;

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        private String msg;
        private int code;
        private List<OlistBean> olist;

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

        public List<OlistBean> getOlist() {
            return olist;
        }

        public void setOlist(List<OlistBean> olist) {
            this.olist = olist;
        }

        public static class OlistBean {
            /**
             * id : 1
             * gid : 143
             * content : 测试内容
             * surl : /static/product/picture/QQ截图20170807181213.png,/static/product/picture/rBACE1bHu06g6ZRsAAEagrk8V7s15_200x200_3.jpg,/static/product/picture/rBACFFX_bELS8zrBAAFms3U0o8c386_200x200_3.png
             * username : 吃炸鸡的猪
             * suntime : 2017-08-14 14:50:15
             * shopname : 08-10快速夺宝
             * datenumber : 1
             * expectNum : 10
             * winningNumber : 10000004
             * lotterytime : 2017-08-11 09:27:10
             * img :
             */

            private String headImg;

            public String getHeadImg() {
                return headImg;
            }

            public void setHeadImg(String headImg) {
                this.headImg = headImg;
            }

            private int id;
            private int gid;
            private String content;
            private String surl;
            private String username;
            private String suntime;
            private String shopname;
            private String datenumber;
            private int expectNum;
            private String winningNumber;
            private String lotterytime;
            private String img;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getGid() {
                return gid;
            }

            public void setGid(int gid) {
                this.gid = gid;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getSurl() {
                return surl;
            }

            public void setSurl(String surl) {
                this.surl = surl;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getSuntime() {
                return suntime;
            }

            public void setSuntime(String suntime) {
                this.suntime = suntime;
            }

            public String getShopname() {
                return shopname;
            }

            public void setShopname(String shopname) {
                this.shopname = shopname;
            }

            public String getDatenumber() {
                return datenumber;
            }

            public void setDatenumber(String datenumber) {
                this.datenumber = datenumber;
            }

            public int getExpectNum() {
                return expectNum;
            }

            public void setExpectNum(int expectNum) {
                this.expectNum = expectNum;
            }

            public String getWinningNumber() {
                return winningNumber;
            }

            public void setWinningNumber(String winningNumber) {
                this.winningNumber = winningNumber;
            }

            public String getLotterytime() {
                return lotterytime;
            }

            public void setLotterytime(String lotterytime) {
                this.lotterytime = lotterytime;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }
        }
    }
}
