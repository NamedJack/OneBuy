package com.ejar.onebuy.bean.home;

import java.util.List;

/**
 * Created by wongxd on 2017/8/14.
 */

public class HomeBannerBean {

    /**
     * code : 200
     * msg : OK
     * data : {"str":["恭喜 吃炸鸡的猪,获得8-11十元夺宝","恭喜 吃炸鸡的猪,获得08-10快速夺宝","恭喜 吃炸鸡的猪,获得08-10快速夺宝","恭喜 吃炸鸡的猪,获得佳能相机","恭喜 吃炸鸡的猪,获得08-10快速夺宝"],"code":0,"list":["/static/product/picture/QQ截图20170807181213.png","static/product/pictureImg/b8cd176e-725d-4353-83cd-77b8222f6b31.png","static/product/pictureImg/383be152-093d-4ef1-8f60-91822b4d30c7.png","static/product/pictureImg/c8d9e594-cfa1-4bd4-9afd-76597c955ede.jpg","static/product/pictureImg/2d3e9f40-4d72-423c-b29a-92abad1d6319.jpg"]}
     */

    public int code;
    public String msg;
    public DataBean data;

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
         * str : ["恭喜 吃炸鸡的猪,获得8-11十元夺宝","恭喜 吃炸鸡的猪,获得08-10快速夺宝","恭喜 吃炸鸡的猪,获得08-10快速夺宝","恭喜 吃炸鸡的猪,获得佳能相机","恭喜 吃炸鸡的猪,获得08-10快速夺宝"]
         * code : 0
         * list : ["/static/product/picture/QQ截图20170807181213.png","static/product/pictureImg/b8cd176e-725d-4353-83cd-77b8222f6b31.png","static/product/pictureImg/383be152-093d-4ef1-8f60-91822b4d30c7.png","static/product/pictureImg/c8d9e594-cfa1-4bd4-9afd-76597c955ede.jpg","static/product/pictureImg/2d3e9f40-4d72-423c-b29a-92abad1d6319.jpg"]
         */

        private int code;
        private List<String> str;
        private List<BannerBean> list;
        private List<String> ad;

        public List<String> getAd() {
            return ad;
        }

        public void setAd(List<String> ad) {
            this.ad = ad;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public List<String> getStr() {
            return str;
        }

        public void setStr(List<String> str) {
            this.str = str;
        }

        public List<BannerBean> getList() {
            return list;
        }

        public void setList(List<BannerBean> list) {
            this.list = list;
        }


        public static  class BannerBean{

            /**
             * id : 6
             * img : /static/product/pictureImg/082cc7cf-893f-4f1b-8ab5-4aa564873b8d.png
             * url :
             * proId : 1
             * isShow : 1
             * sort : 1
             */

            private int id;
            private String img;
            private String url;
            private int proId;
            private int isShow;
            private int sort;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getProId() {
                return proId;
            }

            public void setProId(int proId) {
                this.proId = proId;
            }

            public int getIsShow() {
                return isShow;
            }

            public void setIsShow(int isShow) {
                this.isShow = isShow;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }
        }
    }
}
