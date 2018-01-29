package com.ejar.onebuy.bean.recent;

import java.util.List;

/**
 * Created by wongxd on 2017/8/17.
 */

public class CaculateDetailBean {

    /**
     * code : 200
     * msg : OK
     * data : {"goodsNo":{"id":148,"datenumber":"3","proId":33,"overtime":"2017-08-16 14:25:06","lotterytime":"2017-08-16 14:25:06","winningUserId":4,"state":2,"konw":2,"winningNumber":"10000001","sunOrder":1,"deleted":1,"numberA":"","numberB":"","isSend":0,"isConfirm":0,"shopName":"","expectNum":"","product":"","address":"","user":""},"userInfo":[{"id":2327,"userId":4,"proId":33,"goodsNo":3,"winningNumber":"10000010","sunorder":0,"paytime":1502864402000,"addressId":17,"shopName":"","num":"","user":{"id":4,"tel":"18683661691","otherAccounts":"","gameAccounts":"","passWord":"","name":"啊实打实的","headImg":"","sex":"","niceName":"撒旦法是","phone":"","wallet":9996957,"integral":"","addNumber":"","selActivity":1,"state":1,"hide":1,"ip":"192.168.100.30","ipAddr":"","joinNumber":0,"joinDate":"","zhuiQi":0}}],"code":0,"nowNo":"3","needPerson":10,"proName":"8-16（4324532）"}
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
         * goodsNo : {"id":148,"datenumber":"3","proId":33,"overtime":"2017-08-16 14:25:06","lotterytime":"2017-08-16 14:25:06","winningUserId":4,"state":2,"konw":2,"winningNumber":"10000001","sunOrder":1,"deleted":1,"numberA":"","numberB":"","isSend":0,"isConfirm":0,"shopName":"","expectNum":"","product":"","address":"","user":""}
         * userInfo : [{"id":2327,"userId":4,"proId":33,"goodsNo":3,"winningNumber":"10000010","sunorder":0,"paytime":1502864402000,"addressId":17,"shopName":"","num":"","user":{"id":4,"tel":"18683661691","otherAccounts":"","gameAccounts":"","passWord":"","name":"啊实打实的","headImg":"","sex":"","niceName":"撒旦法是","phone":"","wallet":9996957,"integral":"","addNumber":"","selActivity":1,"state":1,"hide":1,"ip":"192.168.100.30","ipAddr":"","joinNumber":0,"joinDate":"","zhuiQi":0}}]
         * code : 0
         * nowNo : 3
         * needPerson : 10
         * proName : 8-16（4324532）
         */

        private GoodsNoBean goodsNo;
        private int code;
        private String nowNo;
        private int needPerson;
        private String proName;
        private List<UserInfoBean> userInfo;

        public GoodsNoBean getGoodsNo() {
            return goodsNo;
        }

        public void setGoodsNo(GoodsNoBean goodsNo) {
            this.goodsNo = goodsNo;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getNowNo() {
            return nowNo;
        }

        public void setNowNo(String nowNo) {
            this.nowNo = nowNo;
        }

        public int getNeedPerson() {
            return needPerson;
        }

        public void setNeedPerson(int needPerson) {
            this.needPerson = needPerson;
        }

        public String getProName() {
            return proName;
        }

        public void setProName(String proName) {
            this.proName = proName;
        }

        public List<UserInfoBean> getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(List<UserInfoBean> userInfo) {
            this.userInfo = userInfo;
        }

        public static class GoodsNoBean {
            /**
             * id : 148
             * datenumber : 3
             * proId : 33
             * overtime : 2017-08-16 14:25:06
             * lotterytime : 2017-08-16 14:25:06
             * winningUserId : 4
             * state : 2
             * konw : 2
             * winningNumber : 10000001
             * sunOrder : 1
             * deleted : 1
             * numberA :
             * numberB :
             * isSend : 0
             * isConfirm : 0
             * shopName :
             * expectNum :
             * product :
             * address :
             * user :
             */

            private int id;
            private String datenumber;
            private int proId;
            private String overtime;
            private String lotterytime;
            private int winningUserId;
            private int state;
            private int konw;
            private String winningNumber;
            private int sunOrder;
            private int deleted;
            private String numberA;
            private String numberB;
            private int isSend;
            private int isConfirm;
            private String shopName;
            private String expectNum;
            private String product;
            private String address;
            private String user;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getDatenumber() {
                return datenumber;
            }

            public void setDatenumber(String datenumber) {
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

            public String getNumberA() {
                return numberA;
            }

            public void setNumberA(String numberA) {
                this.numberA = numberA;
            }

            public String getNumberB() {
                return numberB;
            }

            public void setNumberB(String numberB) {
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

            public String getExpectNum() {
                return expectNum;
            }

            public void setExpectNum(String expectNum) {
                this.expectNum = expectNum;
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

        public static class UserInfoBean {
            /**
             * id : 2327
             * userId : 4
             * proId : 33
             * goodsNo : 3
             * winningNumber : 10000010
             * sunorder : 0
             * paytime : 1502864402000
             * addressId : 17
             * shopName :
             * num :
             * user : {"id":4,"tel":"18683661691","otherAccounts":"","gameAccounts":"","passWord":"","name":"啊实打实的","headImg":"","sex":"","niceName":"撒旦法是","phone":"","wallet":9996957,"integral":"","addNumber":"","selActivity":1,"state":1,"hide":1,"ip":"192.168.100.30","ipAddr":"","joinNumber":0,"joinDate":"","zhuiQi":0}
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
            private String num;
            private UserBean user;

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

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
            }

            public static class UserBean {
                /**
                 * id : 4
                 * tel : 18683661691
                 * otherAccounts :
                 * gameAccounts :
                 * passWord :
                 * name : 啊实打实的
                 * headImg :
                 * sex :
                 * niceName : 撒旦法是
                 * phone :
                 * wallet : 9996957.0
                 * integral :
                 * addNumber :
                 * selActivity : 1
                 * state : 1
                 * hide : 1
                 * ip : 192.168.100.30
                 * ipAddr :
                 * joinNumber : 0
                 * joinDate :
                 * zhuiQi : 0
                 */

                private int id;
                private String tel;
                private String otherAccounts;
                private String gameAccounts;
                private String passWord;
                private String name;
                private String headImg;
                private String sex;
                private String niceName;
                private String phone;
                private double wallet;
                private String integral;
                private String addNumber;
                private int selActivity;
                private int state;
                private int hide;
                private String ip;
                private String ipAddr;
                private int joinNumber;
                private String joinDate;
                private int zhuiQi;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getTel() {
                    return tel;
                }

                public void setTel(String tel) {
                    this.tel = tel;
                }

                public String getOtherAccounts() {
                    return otherAccounts;
                }

                public void setOtherAccounts(String otherAccounts) {
                    this.otherAccounts = otherAccounts;
                }

                public String getGameAccounts() {
                    return gameAccounts;
                }

                public void setGameAccounts(String gameAccounts) {
                    this.gameAccounts = gameAccounts;
                }

                public String getPassWord() {
                    return passWord;
                }

                public void setPassWord(String passWord) {
                    this.passWord = passWord;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getHeadImg() {
                    return headImg;
                }

                public void setHeadImg(String headImg) {
                    this.headImg = headImg;
                }

                public String getSex() {
                    return sex;
                }

                public void setSex(String sex) {
                    this.sex = sex;
                }

                public String getNiceName() {
                    return niceName;
                }

                public void setNiceName(String niceName) {
                    this.niceName = niceName;
                }

                public String getPhone() {
                    return phone;
                }

                public void setPhone(String phone) {
                    this.phone = phone;
                }

                public double getWallet() {
                    return wallet;
                }

                public void setWallet(double wallet) {
                    this.wallet = wallet;
                }

                public String getIntegral() {
                    return integral;
                }

                public void setIntegral(String integral) {
                    this.integral = integral;
                }

                public String getAddNumber() {
                    return addNumber;
                }

                public void setAddNumber(String addNumber) {
                    this.addNumber = addNumber;
                }

                public int getSelActivity() {
                    return selActivity;
                }

                public void setSelActivity(int selActivity) {
                    this.selActivity = selActivity;
                }

                public int getState() {
                    return state;
                }

                public void setState(int state) {
                    this.state = state;
                }

                public int getHide() {
                    return hide;
                }

                public void setHide(int hide) {
                    this.hide = hide;
                }

                public String getIp() {
                    return ip;
                }

                public void setIp(String ip) {
                    this.ip = ip;
                }

                public String getIpAddr() {
                    return ipAddr;
                }

                public void setIpAddr(String ipAddr) {
                    this.ipAddr = ipAddr;
                }

                public int getJoinNumber() {
                    return joinNumber;
                }

                public void setJoinNumber(int joinNumber) {
                    this.joinNumber = joinNumber;
                }

                public String getJoinDate() {
                    return joinDate;
                }

                public void setJoinDate(String joinDate) {
                    this.joinDate = joinDate;
                }

                public int getZhuiQi() {
                    return zhuiQi;
                }

                public void setZhuiQi(int zhuiQi) {
                    this.zhuiQi = zhuiQi;
                }
            }
        }
    }
}
