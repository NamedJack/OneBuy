package com.ejar.onebuy.bean.me;

import java.util.List;

/**
 * Created by wongxd on 2017/8/21.
 * 夺宝记录 已揭晓
 */

public class HaveBeenBean {

    /**
     * code : 200
     * msg : OK
     * data : {"msg":"个人中心已揭晓查看成功","code":0,"totalPage":1,"list":[{"id":144,"datenumber":5,"proId":33,"overtime":"2017-08-16 14:08:23","lotterytime":"2017-08-16 14:08:23","winningUserId":4,"state":2,"konw":2,"winningNumber":"10000009","sunOrder":1,"deleted":1,"numberA":20170816140823607,"numberB":26511,"isSend":0,"isConfirm":0,"shopName":"8-16（4324532）","num":10,"proImg":"/static/product/picture/5979d2f5-7779-43fb-a928-0d06514ef6d1.png","userName":"体力","type":2,"product":"","address":"","user":""}]}
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
         * msg : 个人中心已揭晓查看成功
         * code : 0
         * totalPage : 1
         * list : [{"id":144,"datenumber":5,"proId":33,"overtime":"2017-08-16 14:08:23","lotterytime":"2017-08-16 14:08:23","winningUserId":4,"state":2,"konw":2,"winningNumber":"10000009","sunOrder":1,"deleted":1,"numberA":20170816140823607,"numberB":26511,"isSend":0,"isConfirm":0,"shopName":"8-16（4324532）","num":10,"proImg":"/static/product/picture/5979d2f5-7779-43fb-a928-0d06514ef6d1.png","userName":"体力","type":2,"product":"","address":"","user":""}]
         */

        private String msg;
        private int code;
        private int totalPage;
        private List<ListBean> list;

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

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 144
             * datenumber : 5
             * proId : 33
             * overtime : 2017-08-16 14:08:23
             * lotterytime : 2017-08-16 14:08:23
             * winningUserId : 4
             * state : 2
             * konw : 2
             * winningNumber : 10000009
             * sunOrder : 1
             * deleted : 1
             * numberA : 20170816140823607
             * numberB : 26511
             * isSend : 0
             * isConfirm : 0
             * shopName : 8-16（4324532）
             * num : 10
             * proImg : /static/product/picture/5979d2f5-7779-43fb-a928-0d06514ef6d1.png
             * userName : 体力
             * type : 2
             * product :
             * address :
             * user :
             */

            private int id;
            private int datenumber;
            private int proId;
            private String overtime;
            private String lotterytime;
            private int winningUserId;
            private int state;
            private int konw;
            private String winningNumber;
            private int sunOrder;
            private int deleted;
            private long numberA;
            private int numberB;
            private int isSend;
            private int isConfirm;
            private String shopName;
            private int num;
            private String proImg;
            private String userName;
            private int type;
            private String product;
            private String address;
            private String user;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getDatenumber() {
                return datenumber;
            }

            public void setDatenumber(int datenumber) {
                this.datenumber = datenumber;
            }

            public int getProId() {
                return proId;
            }

            public void setProId(int proId) {
                this.proId = proId;
            }

            public String getOvertime() {
                return overtime;
            }

            public void setOvertime(String overtime) {
                this.overtime = overtime;
            }

            public String getLotterytime() {
                return lotterytime;
            }

            public void setLotterytime(String lotterytime) {
                this.lotterytime = lotterytime;
            }

            public int getWinningUserId() {
                return winningUserId;
            }

            public void setWinningUserId(int winningUserId) {
                this.winningUserId = winningUserId;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public int getKonw() {
                return konw;
            }

            public void setKonw(int konw) {
                this.konw = konw;
            }

            public String getWinningNumber() {
                return winningNumber;
            }

            public void setWinningNumber(String winningNumber) {
                this.winningNumber = winningNumber;
            }

            public int getSunOrder() {
                return sunOrder;
            }

            public void setSunOrder(int sunOrder) {
                this.sunOrder = sunOrder;
            }

            public int getDeleted() {
                return deleted;
            }

            public void setDeleted(int deleted) {
                this.deleted = deleted;
            }

            public long getNumberA() {
                return numberA;
            }

            public void setNumberA(long numberA) {
                this.numberA = numberA;
            }

            public int getNumberB() {
                return numberB;
            }

            public void setNumberB(int numberB) {
                this.numberB = numberB;
            }

            public int getIsSend() {
                return isSend;
            }

            public void setIsSend(int isSend) {
                this.isSend = isSend;
            }

            public int getIsConfirm() {
                return isConfirm;
            }

            public void setIsConfirm(int isConfirm) {
                this.isConfirm = isConfirm;
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

            public String getProduct() {
                return product;
            }

            public void setProduct(String product) {
                this.product = product;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
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
