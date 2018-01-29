package com.ejar.onebuy.bean.me;

import java.util.List;

/**
 * Created by wongxd on 2017/8/21.
 */

public class JoinRecordBean {

    /**
     * code : 200
     * msg : OK
     * data : {"ulist":[{"id":2328,"userId":4,"proId":33,"goodsNo":4,"winningNumber":"10000001","sunorder":1,"paytime":1502864707000,"addressId":17,"shopName":"8-16（4324532）","num":2,"proImg":"/static/product/picture/5979d2f5-7779-43fb-a928-0d06514ef6d1.png","expectNum":10,"userName":"体力","type":2,"user":""}],"msg":"查询所有夺宝记录成功","code":0,"totalPage":11}
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
         * ulist : [{"id":2328,"userId":4,"proId":33,"goodsNo":4,"winningNumber":"10000001","sunorder":1,"paytime":1502864707000,"addressId":17,"shopName":"8-16（4324532）","num":2,"proImg":"/static/product/picture/5979d2f5-7779-43fb-a928-0d06514ef6d1.png","expectNum":10,"userName":"体力","type":2,"user":""}]
         * msg : 查询所有夺宝记录成功
         * code : 0
         * totalPage : 11
         */

        private String msg;
        private int code;
        private int totalPage;
        private List<UlistBean> ulist;

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

        public List<UlistBean> getUlist() {
            return ulist;
        }

        public void setUlist(List<UlistBean> ulist) {
            this.ulist = ulist;
        }

        public static class UlistBean {
            /**
             * id : 2328
             * userId : 4
             * proId : 33
             * goodsNo : 4
             * winningNumber : 10000001
             * sunorder : 1
             * paytime : 1502864707000
             * addressId : 17
             * shopName : 8-16（4324532）
             * num : 2
             * proImg : /static/product/picture/5979d2f5-7779-43fb-a928-0d06514ef6d1.png
             * expectNum : 10
             * userName : 体力
             * type : 2
             * user :
             */

            private int id;
            private int userId;
            private int proId;
            private int goodsNo;
            private String winningNumber;
            private int sunorder;
            private long paytime;
            private int addressId;
            private String shopName;
            private int num;
            private String proImg;
            private int expectNum;
            private String userName;
            private int type;
            private String user;

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

            public int getGoodsNo() {
                return goodsNo;
            }

            public void setGoodsNo(int goodsNo) {
                this.goodsNo = goodsNo;
            }

            public String getWinningNumber() {
                return winningNumber;
            }

            public void setWinningNumber(String winningNumber) {
                this.winningNumber = winningNumber;
            }

            public int getSunorder() {
                return sunorder;
            }

            public void setSunorder(int sunorder) {
                this.sunorder = sunorder;
            }

            public long getPaytime() {
                return paytime;
            }

            public void setPaytime(long paytime) {
                this.paytime = paytime;
            }

            public int getAddressId() {
                return addressId;
            }

            public void setAddressId(int addressId) {
                this.addressId = addressId;
            }

            public String getShopName() {
                return shopName;
            }

            public void setShopName(String shopName) {
                this.shopName = shopName;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public String getProImg() {
                return proImg;
            }

            public void setProImg(String proImg) {
                this.proImg = proImg;
            }

            public int getExpectNum() {
                return expectNum;
            }

            public void setExpectNum(int expectNum) {
                this.expectNum = expectNum;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getUser() {
                return user;
            }

            public void setUser(String user) {
                this.user = user;
            }
        }
    }
}
