package com.ejar.onebuy.bean.me;

import java.util.List;

/**
 * Created by wongxd on 2017/8/21.
 */

public class HaveInHandBean {

    /**
     * code : 200
     * msg : OK
     * data : {"msg":"个人中心进行中查看成功","code":0,"totalPage":2,"rlist":[{"id":8,"userId":4,"proId":28,"frequency":15,"remainingtimes":15,"personNum":1,"proImg":"/static/product/picture/dac88d65-be91-4fb7-9174-4c8f0dc111e5.png,/static/product/picture/af9656da-9b7f-4f17-9558-eb178142558e.png","shopName":"虚拟电话卡","nowNo":7,"num":0,"expectNum":25,"alreadyNum":25,"type":4}]}
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
         * msg : 个人中心进行中查看成功
         * code : 0
         * totalPage : 2
         * rlist : [{"id":8,"userId":4,"proId":28,"frequency":15,"remainingtimes":15,"personNum":1,"proImg":"/static/product/picture/dac88d65-be91-4fb7-9174-4c8f0dc111e5.png,/static/product/picture/af9656da-9b7f-4f17-9558-eb178142558e.png","shopName":"虚拟电话卡","nowNo":7,"num":0,"expectNum":25,"alreadyNum":25,"type":4}]
         */

        private String msg;
        private int code;
        private int totalPage;
        private List<RlistBean> rlist;

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

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public List<RlistBean> getRlist() {
            return rlist;
        }

        public void setRlist(List<RlistBean> rlist) {
            this.rlist = rlist;
        }

        public static class RlistBean {
            /**
             * id : 8
             * userId : 4
             * proId : 28
             * frequency : 15
             * remainingtimes : 15
             * personNum : 1
             * proImg : /static/product/picture/dac88d65-be91-4fb7-9174-4c8f0dc111e5.png,/static/product/picture/af9656da-9b7f-4f17-9558-eb178142558e.png
             * shopName : 虚拟电话卡
             * nowNo : 7
             * num : 0
             * expectNum : 25
             * alreadyNum : 25
             * type : 4
             */

            private int id;
            private int userId;
            private int proId;
            private int frequency;
            private int remainingtimes;
            private int personNum;
            private String proImg;
            private String shopName;
            private int nowNo;
            private int num;
            private int expectNum;
            private int alreadyNum;
            private int type;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getProId() {
                return proId;
            }

            public void setProId(int proId) {
                this.proId = proId;
            }

            public int getFrequency() {
                return frequency;
            }

            public void setFrequency(int frequency) {
                this.frequency = frequency;
            }

            public int getRemainingtimes() {
                return remainingtimes;
            }

            public void setRemainingtimes(int remainingtimes) {
                this.remainingtimes = remainingtimes;
            }

            public int getPersonNum() {
                return personNum;
            }

            public void setPersonNum(int personNum) {
                this.personNum = personNum;
            }

            public String getProImg() {
                return proImg;
            }

            public void setProImg(String proImg) {
                this.proImg = proImg;
            }

            public String getShopName() {
                return shopName;
            }

            public void setShopName(String shopName) {
                this.shopName = shopName;
            }

            public int getNowNo() {
                return nowNo;
            }

            public void setNowNo(int nowNo) {
                this.nowNo = nowNo;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public int getExpectNum() {
                return expectNum;
            }

            public void setExpectNum(int expectNum) {
                this.expectNum = expectNum;
            }

            public int getAlreadyNum() {
                return alreadyNum;
            }

            public void setAlreadyNum(int alreadyNum) {
                this.alreadyNum = alreadyNum;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }
    }
}
