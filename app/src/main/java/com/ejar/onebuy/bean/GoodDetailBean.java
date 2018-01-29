package com.ejar.onebuy.bean;

import java.util.List;

/**
 * Created by wongxd on 2017/8/14.
 */

public class GoodDetailBean {
    /**
     * code : 200
     * msg : OK
     * data : {"winingNumbers":[{"id":280,"userId":29,"proId":1,"goodsNo":28,"winningNumber":"10000010","sunorder":0,"paytime":1512369358000,"addressId":1,"shopName":null,"num":null,"proImg":null,"expectNum":null,"userName":null,"type":null,"user":null},{"id":279,"userId":29,"proId":1,"goodsNo":28,"winningNumber":"10000009","sunorder":0,"paytime":1512369358000,"addressId":1,"shopName":null,"num":null,"proImg":null,"expectNum":null,"userName":null,"type":null,"user":null},{"id":278,"userId":29,"proId":1,"goodsNo":28,"winningNumber":"10000008","sunorder":0,"paytime":1512369358000,"addressId":1,"shopName":null,"num":null,"proImg":null,"expectNum":null,"userName":null,"type":null,"user":null},{"id":277,"userId":29,"proId":1,"goodsNo":28,"winningNumber":"10000007","sunorder":0,"paytime":1512369358000,"addressId":1,"shopName":null,"num":null,"proImg":null,"expectNum":null,"userName":null,"type":null,"user":null},{"id":276,"userId":29,"proId":1,"goodsNo":28,"winningNumber":"10000006","sunorder":0,"paytime":1512369358000,"addressId":1,"shopName":null,"num":null,"proImg":null,"expectNum":null,"userName":null,"type":null,"user":null},{"id":275,"userId":29,"proId":1,"goodsNo":28,"winningNumber":"10000005","sunorder":0,"paytime":1512369324000,"addressId":1,"shopName":null,"num":null,"proImg":null,"expectNum":null,"userName":null,"type":null,"user":null},{"id":274,"userId":29,"proId":1,"goodsNo":28,"winningNumber":"10000004","sunorder":0,"paytime":1512369324000,"addressId":1,"shopName":null,"num":null,"proImg":null,"expectNum":null,"userName":null,"type":null,"user":null},{"id":273,"userId":29,"proId":1,"goodsNo":28,"winningNumber":"10000003","sunorder":0,"paytime":1512369324000,"addressId":1,"shopName":null,"num":null,"proImg":null,"expectNum":null,"userName":null,"type":null,"user":null},{"id":272,"userId":29,"proId":1,"goodsNo":28,"winningNumber":"10000002","sunorder":0,"paytime":1512369324000,"addressId":1,"shopName":null,"num":null,"proImg":null,"expectNum":null,"userName":null,"type":null,"user":null},{"id":271,"userId":29,"proId":1,"goodsNo":28,"winningNumber":"10000001","sunorder":0,"paytime":1512369324000,"addressId":1,"shopName":null,"num":null,"proImg":null,"expectNum":null,"userName":null,"type":null,"user":null}],"product":{"id":1,"name":"sdfsd","price":10,"picture":"/static/product/picture/1511853104090_1.png","status":1,"productHTML":"<p><img src=\"http://localhost:8081/oneceIndiana/static/product/32239f89-32be-48db-8941-41b6bb63a810.png\" alt=\"14\"><br><\/p><p><br><\/p>","catalogID":1,"type":3,"sort":1,"delFalg":0,"expectNum":10,"alreadyNum":10,"nowNo":28,"isHot":1,"isNew":1,"oneceAmount":1,"stock":73,"isCollection":0,"catalogName":null,"pictures":null},"code":0,"recode":[{"id":2,"userId":29,"goodNo":28,"creatTime":1512369358000,"proId":1,"num":5,"user":{"id":29,"tel":"ofs560oSxazCXqrHPDkniDNna2WY","otherAccounts":null,"gameAccounts":null,"passWord":null,"name":null,"headImg":"/0","sex":0,"niceName":"测试专用","phone":null,"wallet":9610,"integral":0,"addNumber":null,"selActivity":null,"state":1,"hide":1,"bind":1,"unionid":"o_LpCxGmBMlRfJsJDX056wEx2W5E","ip":null,"ipAddr":null,"joinNumber":0,"joinDate":null,"zhuiQi":0}},{"id":1,"userId":29,"goodNo":28,"creatTime":1512369298000,"proId":1,"num":5,"user":{"id":29,"tel":"ofs560oSxazCXqrHPDkniDNna2WY","otherAccounts":null,"gameAccounts":null,"passWord":null,"name":null,"headImg":"/0","sex":0,"niceName":"测试专用","phone":null,"wallet":9610,"integral":0,"addNumber":null,"selActivity":null,"state":1,"hide":1,"bind":1,"unionid":"o_LpCxGmBMlRfJsJDX056wEx2W5E","ip":null,"ipAddr":null,"joinNumber":0,"joinDate":null,"zhuiQi":0}}],"totalPage":1,"nowTime":"2017-12-04"}
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
         * winingNumbers : [{"id":280,"userId":29,"proId":1,"goodsNo":28,"winningNumber":"10000010","sunorder":0,"paytime":1512369358000,"addressId":1,"shopName":null,"num":null,"proImg":null,"expectNum":null,"userName":null,"type":null,"user":null},{"id":279,"userId":29,"proId":1,"goodsNo":28,"winningNumber":"10000009","sunorder":0,"paytime":1512369358000,"addressId":1,"shopName":null,"num":null,"proImg":null,"expectNum":null,"userName":null,"type":null,"user":null},{"id":278,"userId":29,"proId":1,"goodsNo":28,"winningNumber":"10000008","sunorder":0,"paytime":1512369358000,"addressId":1,"shopName":null,"num":null,"proImg":null,"expectNum":null,"userName":null,"type":null,"user":null},{"id":277,"userId":29,"proId":1,"goodsNo":28,"winningNumber":"10000007","sunorder":0,"paytime":1512369358000,"addressId":1,"shopName":null,"num":null,"proImg":null,"expectNum":null,"userName":null,"type":null,"user":null},{"id":276,"userId":29,"proId":1,"goodsNo":28,"winningNumber":"10000006","sunorder":0,"paytime":1512369358000,"addressId":1,"shopName":null,"num":null,"proImg":null,"expectNum":null,"userName":null,"type":null,"user":null},{"id":275,"userId":29,"proId":1,"goodsNo":28,"winningNumber":"10000005","sunorder":0,"paytime":1512369324000,"addressId":1,"shopName":null,"num":null,"proImg":null,"expectNum":null,"userName":null,"type":null,"user":null},{"id":274,"userId":29,"proId":1,"goodsNo":28,"winningNumber":"10000004","sunorder":0,"paytime":1512369324000,"addressId":1,"shopName":null,"num":null,"proImg":null,"expectNum":null,"userName":null,"type":null,"user":null},{"id":273,"userId":29,"proId":1,"goodsNo":28,"winningNumber":"10000003","sunorder":0,"paytime":1512369324000,"addressId":1,"shopName":null,"num":null,"proImg":null,"expectNum":null,"userName":null,"type":null,"user":null},{"id":272,"userId":29,"proId":1,"goodsNo":28,"winningNumber":"10000002","sunorder":0,"paytime":1512369324000,"addressId":1,"shopName":null,"num":null,"proImg":null,"expectNum":null,"userName":null,"type":null,"user":null},{"id":271,"userId":29,"proId":1,"goodsNo":28,"winningNumber":"10000001","sunorder":0,"paytime":1512369324000,"addressId":1,"shopName":null,"num":null,"proImg":null,"expectNum":null,"userName":null,"type":null,"user":null}]
         * product : {"id":1,"name":"sdfsd","price":10,"picture":"/static/product/picture/1511853104090_1.png","status":1,"productHTML":"<p><img src=\"http://localhost:8081/oneceIndiana/static/product/32239f89-32be-48db-8941-41b6bb63a810.png\" alt=\"14\"><br><\/p><p><br><\/p>","catalogID":1,"type":3,"sort":1,"delFalg":0,"expectNum":10,"alreadyNum":10,"nowNo":28,"isHot":1,"isNew":1,"oneceAmount":1,"stock":73,"isCollection":0,"catalogName":null,"pictures":null}
         * code : 0
         * recode : [{"id":2,"userId":29,"goodNo":28,"creatTime":1512369358000,"proId":1,"num":5,"user":{"id":29,"tel":"ofs560oSxazCXqrHPDkniDNna2WY","otherAccounts":null,"gameAccounts":null,"passWord":null,"name":null,"headImg":"/0","sex":0,"niceName":"测试专用","phone":null,"wallet":9610,"integral":0,"addNumber":null,"selActivity":null,"state":1,"hide":1,"bind":1,"unionid":"o_LpCxGmBMlRfJsJDX056wEx2W5E","ip":null,"ipAddr":null,"joinNumber":0,"joinDate":null,"zhuiQi":0}},{"id":1,"userId":29,"goodNo":28,"creatTime":1512369298000,"proId":1,"num":5,"user":{"id":29,"tel":"ofs560oSxazCXqrHPDkniDNna2WY","otherAccounts":null,"gameAccounts":null,"passWord":null,"name":null,"headImg":"/0","sex":0,"niceName":"测试专用","phone":null,"wallet":9610,"integral":0,"addNumber":null,"selActivity":null,"state":1,"hide":1,"bind":1,"unionid":"o_LpCxGmBMlRfJsJDX056wEx2W5E","ip":null,"ipAddr":null,"joinNumber":0,"joinDate":null,"zhuiQi":0}}]
         * totalPage : 1
         * nowTime : 2017-12-04
         */

        private ProductBean product;
        private int code;
        private int totalPage;
        private String nowTime;
        private List<WiningNumbersBean> winingNumbers;
        private List<RecodeBean> recode;

        public ProductBean getProduct() {
            return product;
        }

        public void setProduct(ProductBean product) {
            this.product = product;
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

        public String getNowTime() {
            return nowTime;
        }

        public void setNowTime(String nowTime) {
            this.nowTime = nowTime;
        }

        public List<WiningNumbersBean> getWiningNumbers() {
            return winingNumbers;
        }

        public void setWiningNumbers(List<WiningNumbersBean> winingNumbers) {
            this.winingNumbers = winingNumbers;
        }

        public List<RecodeBean> getRecode() {
            return recode;
        }

        public void setRecode(List<RecodeBean> recode) {
            this.recode = recode;
        }

        public static class ProductBean {
            /**
             * id : 1
             * name : sdfsd
             * price : 10
             * picture : /static/product/picture/1511853104090_1.png
             * status : 1
             * productHTML : <p><img src="http://localhost:8081/oneceIndiana/static/product/32239f89-32be-48db-8941-41b6bb63a810.png" alt="14"><br></p><p><br></p>
             * catalogID : 1
             * type : 3
             * sort : 1
             * delFalg : 0
             * expectNum : 10
             * alreadyNum : 10
             * nowNo : 28
             * isHot : 1
             * isNew : 1
             * oneceAmount : 1
             * stock : 73
             * isCollection : 0
             * catalogName : null
             * pictures : null
             */

            private int id;
            private String name;
//            private int price;
            private String picture;
//            private int status;
            private String productHTML;
//            private int catalogID;
            private int type;
//            private int sort;
//            private int delFalg;
            private int expectNum;
            private int alreadyNum;
            private int nowNo;
//            private int isHot;
//            private int isNew;
//            private int oneceAmount;
//            private int stock;
            private int isCollection;
//            private Object catalogName;
//            private Object pictures;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

//            public int getPrice() {
//                return price;
//            }
//
//            public void setPrice(int price) {
//                this.price = price;
//            }

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

//            public int getStatus() {
//                return status;
//            }
//
//            public void setStatus(int status) {
//                this.status = status;
//            }

            public String getProductHTML() {
                return productHTML;
            }

            public void setProductHTML(String productHTML) {
                this.productHTML = productHTML;
            }

//            public int getCatalogID() {
//                return catalogID;
//            }
//
//            public void setCatalogID(int catalogID) {
//                this.catalogID = catalogID;
//            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

//            public int getSort() {
//                return sort;
//            }
//
//            public void setSort(int sort) {
//                this.sort = sort;
//            }
//
//            public int getDelFalg() {
//                return delFalg;
//            }
//
//            public void setDelFalg(int delFalg) {
//                this.delFalg = delFalg;
//            }

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

            public int getNowNo() {
                return nowNo;
            }

            public void setNowNo(int nowNo) {
                this.nowNo = nowNo;
            }

//            public int getIsHot() {
//                return isHot;
//            }
//
//            public void setIsHot(int isHot) {
//                this.isHot = isHot;
//            }
//
//            public int getIsNew() {
//                return isNew;
//            }
//
//            public void setIsNew(int isNew) {
//                this.isNew = isNew;
//            }
//
//            public int getOneceAmount() {
//                return oneceAmount;
//            }
//
//            public void setOneceAmount(int oneceAmount) {
//                this.oneceAmount = oneceAmount;
//            }
//
//            public int getStock() {
//                return stock;
//            }
//
//            public void setStock(int stock) {
//                this.stock = stock;
//            }

            public int getIsCollection() {
                return isCollection;
            }

            public void setIsCollection(int isCollection) {
                this.isCollection = isCollection;
            }

//            public Object getCatalogName() {
//                return catalogName;
//            }
//
//            public void setCatalogName(Object catalogName) {
//                this.catalogName = catalogName;
//            }
//
//            public Object getPictures() {
//                return pictures;
//            }
//
//            public void setPictures(Object pictures) {
//                this.pictures = pictures;
//            }
        }

        public static class WiningNumbersBean {
            /**
             * id : 280
             * userId : 29
             * proId : 1
             * goodsNo : 28
             * winningNumber : 10000010
             * sunorder : 0
             * paytime : 1512369358000
             * addressId : 1
             * shopName : null
             * num : null
             * proImg : null
             * expectNum : null
             * userName : null
             * type : null
             * user : null
             */

            private int id;
            private int userId;
            private int proId;
            private int goodsNo;
            private String winningNumber;
//            private int sunorder;
//            private long paytime;
//            private int addressId;
            private Object shopName;
            private Object num;
            private Object proImg;
            private Object expectNum;
            private Object userName;
            private Object type;
            private Object user;

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

//            public int getSunorder() {
//                return sunorder;
//            }
//
//            public void setSunorder(int sunorder) {
//                this.sunorder = sunorder;
//            }
//
//            public long getPaytime() {
//                return paytime;
//            }
//
//            public void setPaytime(long paytime) {
//                this.paytime = paytime;
//            }
//
//            public int getAddressId() {
//                return addressId;
//            }
//
//            public void setAddressId(int addressId) {
//                this.addressId = addressId;
//            }

            public Object getShopName() {
                return shopName;
            }

            public void setShopName(Object shopName) {
                this.shopName = shopName;
            }

            public Object getNum() {
                return num;
            }

            public void setNum(Object num) {
                this.num = num;
            }

            public Object getProImg() {
                return proImg;
            }

            public void setProImg(Object proImg) {
                this.proImg = proImg;
            }

            public Object getExpectNum() {
                return expectNum;
            }

            public void setExpectNum(Object expectNum) {
                this.expectNum = expectNum;
            }

            public Object getUserName() {
                return userName;
            }

            public void setUserName(Object userName) {
                this.userName = userName;
            }

            public Object getType() {
                return type;
            }

            public void setType(Object type) {
                this.type = type;
            }

            public Object getUser() {
                return user;
            }

            public void setUser(Object user) {
                this.user = user;
            }
        }

        public static class RecodeBean {
            /**
             * id : 2
             * userId : 29
             * goodNo : 28
             * creatTime : 1512369358000
             * proId : 1
             * num : 5
             * user : {"id":29,"tel":"ofs560oSxazCXqrHPDkniDNna2WY","otherAccounts":null,"gameAccounts":null,"passWord":null,"name":null,"headImg":"/0","sex":0,"niceName":"测试专用","phone":null,"wallet":9610,"integral":0,"addNumber":null,"selActivity":null,"state":1,"hide":1,"bind":1,"unionid":"o_LpCxGmBMlRfJsJDX056wEx2W5E","ip":null,"ipAddr":null,"joinNumber":0,"joinDate":null,"zhuiQi":0}
             */

            private int id;
            private int userId;
//            private int goodNo;
            private long creatTime;
            private int proId;
            private int num;
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

//            public int getGoodNo() {
//                return goodNo;
//            }
//
//            public void setGoodNo(int goodNo) {
//                this.goodNo = goodNo;
//            }

            public long getCreatTime() {
                return creatTime;
            }

            public void setCreatTime(long creatTime) {
                this.creatTime = creatTime;
            }

            public int getProId() {
                return proId;
            }

            public void setProId(int proId) {
                this.proId = proId;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
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
                 * id : 29
                 * tel : ofs560oSxazCXqrHPDkniDNna2WY
                 * otherAccounts : null
                 * gameAccounts : null
                 * passWord : null
                 * name : null
                 * headImg : /0
                 * sex : 0
                 * niceName : 测试专用
                 * phone : null
                 * wallet : 9610
                 * integral : 0
                 * addNumber : null
                 * selActivity : null
                 * state : 1
                 * hide : 1
                 * bind : 1
                 * unionid : o_LpCxGmBMlRfJsJDX056wEx2W5E
                 * ip : null
                 * ipAddr : null
                 * joinNumber : 0
                 * joinDate : null
                 * zhuiQi : 0
                 */

                private int id;
                private String tel;
//                private Object otherAccounts;
//                private Object gameAccounts;
//                private Object passWord;
                private Object name;
                private String headImg;
//                private int sex;
                private String niceName;
                private Object phone;
//                private int wallet;
//                private int integral;
//                private Object addNumber;
//                private Object selActivity;
//                private int state;
//                private int hide;
                private int bind;
//                private String unionid;
//                private Object ip;
//                private Object ipAddr;
//                private int joinNumber;
//                private Object joinDate;
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

//                public Object getOtherAccounts() {
//                    return otherAccounts;
//                }
//
//                public void setOtherAccounts(Object otherAccounts) {
//                    this.otherAccounts = otherAccounts;
//                }
//
//                public Object getGameAccounts() {
//                    return gameAccounts;
//                }
//
//                public void setGameAccounts(Object gameAccounts) {
//                    this.gameAccounts = gameAccounts;
//                }

//                public Object getPassWord() {
//                    return passWord;
//                }
//
//                public void setPassWord(Object passWord) {
//                    this.passWord = passWord;
//                }

                public Object getName() {
                    return name;
                }

                public void setName(Object name) {
                    this.name = name;
                }

                public String getHeadImg() {
                    return headImg;
                }

                public void setHeadImg(String headImg) {
                    this.headImg = headImg;
                }

//                public int getSex() {
//                    return sex;
//                }
//
//                public void setSex(int sex) {
//                    this.sex = sex;
//                }

                public String getNiceName() {
                    return niceName;
                }

                public void setNiceName(String niceName) {
                    this.niceName = niceName;
                }

                public Object getPhone() {
                    return phone;
                }

                public void setPhone(Object phone) {
                    this.phone = phone;
                }

//                public int getWallet() {
//                    return wallet;
//                }
//
//                public void setWallet(int wallet) {
//                    this.wallet = wallet;
//                }
//
//                public int getIntegral() {
//                    return integral;
//                }
//
//                public void setIntegral(int integral) {
//                    this.integral = integral;
//                }
//
//                public Object getAddNumber() {
//                    return addNumber;
//                }
//
//                public void setAddNumber(Object addNumber) {
//                    this.addNumber = addNumber;
//                }
//
//                public Object getSelActivity() {
//                    return selActivity;
//                }
//
//                public void setSelActivity(Object selActivity) {
//                    this.selActivity = selActivity;
//                }
//
//                public int getState() {
//                    return state;
//                }
//
//                public void setState(int state) {
//                    this.state = state;
//                }
//
//                public int getHide() {
//                    return hide;
//                }
//
//                public void setHide(int hide) {
//                    this.hide = hide;
//                }

                public int getBind() {
                    return bind;
                }

                public void setBind(int bind) {
                    this.bind = bind;
                }

//                public String getUnionid() {
//                    return unionid;
//                }
//
//                public void setUnionid(String unionid) {
//                    this.unionid = unionid;
//                }
//
//                public Object getIp() {
//                    return ip;
//                }
//
//                public void setIp(Object ip) {
//                    this.ip = ip;
//                }
//
//                public Object getIpAddr() {
//                    return ipAddr;
//                }
//
//                public void setIpAddr(Object ipAddr) {
//                    this.ipAddr = ipAddr;
//                }
//
//                public int getJoinNumber() {
//                    return joinNumber;
//                }
//
//                public void setJoinNumber(int joinNumber) {
//                    this.joinNumber = joinNumber;
//                }
//
//                public Object getJoinDate() {
//                    return joinDate;
//                }
//
//                public void setJoinDate(Object joinDate) {
//                    this.joinDate = joinDate;
//                }

                public int getZhuiQi() {
                    return zhuiQi;
                }

                public void setZhuiQi(int zhuiQi) {
                    this.zhuiQi = zhuiQi;
                }
            }
        }
    }


//
//    /**
//     * code : 200
//     * msg : OK
//     * data : {"product":{"id":25,"name":"08-10快速夺宝","price":10,"picture":"/static/product/picture/31f34d5f-4124-48a9-93be-fa200e5c9c01.jpg,/static/product/picture/c81fe13d-3fdb-454f-b532-50971288f73e.jpg","status":1,"productHTML":"<p><img src=\"/oneceIndiana/static/product/790b2a8c-befe-401f-808d-417fc1ae681a.jpg\" alt=\"b70510e57b3aa4262667c4ce9f723521\"><br><\/p><p><br><\/p>","catalogID":1,"type":3,"sort":1,"delFalg":0,"expectNum":10,"alreadyNum":6,"nowNo":5,"isHot":1,"isNew":1,"oneceAmount":1,"stock":996,"catalogName":"","pictures":""},"code":0,"recode":[{"id":1,"tel":"18281568081","otherAccounts":"","gameAccounts":"","passWord":"","name":"张三","headImg":"","sex":1,"niceName":"吃炸鸡的猪","phone":"","wallet":714,"integral":1000,"addNumber":"","selActivity":1,"state":1,"hide":1,"ip":"192.168.100.14","ipAddr":"爱神的箭","joinNumber":44,"joinDate":1502678159000}],"totalPage":1,"winingNumber":"暂无信息","nowTime":"2017-08-14"}
//     */
//
//    private int code;
//    private String msg;
//    private DataBean data;
//
//    public int getCode() {
//        return code;
//    }
//
//    public void setCode(int code) {
//        this.code = code;
//    }
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }
//
//    public DataBean getData() {
//        return data;
//    }
//
//    public void setData(DataBean data) {
//        this.data = data;
//    }
//
//    public static class DataBean {
//        /**
//         * product : {"id":25,"name":"08-10快速夺宝","price":10,"picture":"/static/product/picture/31f34d5f-4124-48a9-93be-fa200e5c9c01.jpg,/static/product/picture/c81fe13d-3fdb-454f-b532-50971288f73e.jpg","status":1,"productHTML":"<p><img src=\"/oneceIndiana/static/product/790b2a8c-befe-401f-808d-417fc1ae681a.jpg\" alt=\"b70510e57b3aa4262667c4ce9f723521\"><br><\/p><p><br><\/p>","catalogID":1,"type":3,"sort":1,"delFalg":0,"expectNum":10,"alreadyNum":6,"nowNo":5,"isHot":1,"isNew":1,"oneceAmount":1,"stock":996,"catalogName":"","pictures":""}
//         * code : 0
//         * recode : [{"id":1,"tel":"18281568081","otherAccounts":"","gameAccounts":"","passWord":"","name":"张三","headImg":"","sex":1,"niceName":"吃炸鸡的猪","phone":"","wallet":714,"integral":1000,"addNumber":"","selActivity":1,"state":1,"hide":1,"ip":"192.168.100.14","ipAddr":"爱神的箭","joinNumber":44,"joinDate":1502678159000}]
//         * totalPage : 1
//         * winingNumber : 暂无信息
//         * nowTime : 2017-08-14
//         */
//
//        private ProductBean product;
//        private int code;
//        private int totalPage;
//        private List<WinNumber> winingNumbers;
//        private String nowTime;
//        private List<RecodeBean> recode;
//
//        public ProductBean getProduct() {
//            return product;
//        }
//
//        public void setProduct(ProductBean product) {
//            this.product = product;
//        }
//
//        public int getCode() {
//            return code;
//        }
//
//        public void setCode(int code) {
//            this.code = code;
//        }
//
//        public int getTotalPage() {
//            return totalPage;
//        }
//
//        public void setTotalPage(int totalPage) {
//            this.totalPage = totalPage;
//        }
//
//        public List<WinNumber> getWiningNumber() {
//            return winingNumbers;
//        }
//
//        public void setWiningNumber(List<WinNumber> winingNumber) {
//            this.winingNumbers = winingNumber;
//        }
//
//        public String getNowTime() {
//            return nowTime;
//        }
//
//        public void setNowTime(String nowTime) {
//            this.nowTime = nowTime;
//        }
//
//        public List<RecodeBean> getRecode() {
//            return recode;
//        }
//
//        public void setRecode(List<RecodeBean> recode) {
//            this.recode = recode;
//        }
//
//        public static class ProductBean {
//            /**
//             * id : 25
//             * name : 08-10快速夺宝
//             * price : 10.0
//             * picture : /static/product/picture/31f34d5f-4124-48a9-93be-fa200e5c9c01.jpg,/static/product/picture/c81fe13d-3fdb-454f-b532-50971288f73e.jpg
//             * status : 1
//             * productHTML : <p><img src="/oneceIndiana/static/product/790b2a8c-befe-401f-808d-417fc1ae681a.jpg" alt="b70510e57b3aa4262667c4ce9f723521"><br></p><p><br></p>
//             * catalogID : 1
//             * type : 3
//             * sort : 1
//             * delFalg : 0
//             * expectNum : 10
//             * alreadyNum : 6
//             * nowNo : 5
//             * isHot : 1
//             * isNew : 1
//             * oneceAmount : 1
//             * stock : 996
//             * catalogName :
//             * pictures :
//             */
//
//            private int isCollection;
//
//            public int getIsCollection() {
//                return isCollection;
//            }
//
//            public void setIsCollection(int isCollection) {
//                this.isCollection = isCollection;
//            }
//
//            private int id;
//            private String name;
//            private double price;
//            private String picture;
//            private int status;
//            private String productHTML;
//            private int catalogID;
//            private int type;
//            private int sort;
//            private int delFalg;
//            private int expectNum;
//            private int alreadyNum;
//            private int nowNo;
//            private int isHot;
//            private int isNew;
//            private int oneceAmount;
//            private int stock;
//            private String catalogName;
//            private String pictures;
//
//            public int getId() {
//                return id;
//            }
//
//            public void setId(int id) {
//                this.id = id;
//            }
//
//            public String getName() {
//                return name;
//            }
//
//            public void setName(String name) {
//                this.name = name;
//            }
//
//            public double getPrice() {
//                return price;
//            }
//
//            public void setPrice(double price) {
//                this.price = price;
//            }
//
//            public String getPicture() {
//                return picture;
//            }
//
//            public void setPicture(String picture) {
//                this.picture = picture;
//            }
//
//            public int getStatus() {
//                return status;
//            }
//
//            public void setStatus(int status) {
//                this.status = status;
//            }
//
//            public String getProductHTML() {
//                return productHTML;
//            }
//
//            public void setProductHTML(String productHTML) {
//                this.productHTML = productHTML;
//            }
//
//            public int getCatalogID() {
//                return catalogID;
//            }
//
//            public void setCatalogID(int catalogID) {
//                this.catalogID = catalogID;
//            }
//
//            public int getType() {
//                return type;
//            }
//
//            public void setType(int type) {
//                this.type = type;
//            }
//
//            public int getSort() {
//                return sort;
//            }
//
//            public void setSort(int sort) {
//                this.sort = sort;
//            }
//
//            public int getDelFalg() {
//                return delFalg;
//            }
//
//            public void setDelFalg(int delFalg) {
//                this.delFalg = delFalg;
//            }
//
//            public int getExpectNum() {
//                return expectNum;
//            }
//
//            public void setExpectNum(int expectNum) {
//                this.expectNum = expectNum;
//            }
//
//            public int getAlreadyNum() {
//                return alreadyNum;
//            }
//
//            public void setAlreadyNum(int alreadyNum) {
//                this.alreadyNum = alreadyNum;
//            }
//
//            public int getNowNo() {
//                return nowNo;
//            }
//
//            public void setNowNo(int nowNo) {
//                this.nowNo = nowNo;
//            }
//
//            public int getIsHot() {
//                return isHot;
//            }
//
//            public void setIsHot(int isHot) {
//                this.isHot = isHot;
//            }
//
//            public int getIsNew() {
//                return isNew;
//            }
//
//            public void setIsNew(int isNew) {
//                this.isNew = isNew;
//            }
//
//            public int getOneceAmount() {
//                return oneceAmount;
//            }
//
//            public void setOneceAmount(int oneceAmount) {
//                this.oneceAmount = oneceAmount;
//            }
//
//            public int getStock() {
//                return stock;
//            }
//
//            public void setStock(int stock) {
//                this.stock = stock;
//            }
//
//            public String getCatalogName() {
//                return catalogName;
//            }
//
//            public void setCatalogName(String catalogName) {
//                this.catalogName = catalogName;
//            }
//
//            public String getPictures() {
//                return pictures;
//            }
//
//            public void setPictures(String pictures) {
//                this.pictures = pictures;
//            }
//        }
//
//        public static class RecodeBean {
//            /**
//             * id : 1
//             * tel : 18281568081
//             * otherAccounts :
//             * gameAccounts :
//             * passWord :
//             * name : 张三
//             * headImg :
//             * sex : 1
//             * niceName : 吃炸鸡的猪
//             * phone :
//             * wallet : 714
//             * integral : 1000
//             * addNumber :
//             * selActivity : 1
//             * state : 1
//             * hide : 1
//             * ip : 192.168.100.14
//             * ipAddr : 爱神的箭
//             * joinNumber : 44
//             * joinDate : 1502678159000
//             */
//
//            private int zhuiQi;
//
//            public int getZhuiQi() {
//                return zhuiQi;
//            }
//
//            public void setZhuiQi(int zhuiQi) {
//                this.zhuiQi = zhuiQi;
//            }
//
//            private int id;
//            private String tel;
//            private String otherAccounts;
//            private String gameAccounts;
//            private String passWord;
//            private String name;
//            private String headImg;
//            private int sex;
//            private String niceName;
//            private String phone;
//            private double wallet;
//            private int integral;
//            private String addNumber;
//            private int selActivity;
//            private int state;
//            private int hide;
//            private String ip;
//            private String ipAddr;
//            private int joinNumber;
//            private long joinDate;
//
//            public int getId() {
//                return id;
//            }
//
//            public void setId(int id) {
//                this.id = id;
//            }
//
//            public String getTel() {
//                return tel;
//            }
//
//            public void setTel(String tel) {
//                this.tel = tel;
//            }
//
//            public String getOtherAccounts() {
//                return otherAccounts;
//            }
//
//            public void setOtherAccounts(String otherAccounts) {
//                this.otherAccounts = otherAccounts;
//            }
//
//            public String getGameAccounts() {
//                return gameAccounts;
//            }
//
//            public void setGameAccounts(String gameAccounts) {
//                this.gameAccounts = gameAccounts;
//            }
//
//            public String getPassWord() {
//                return passWord;
//            }
//
//            public void setPassWord(String passWord) {
//                this.passWord = passWord;
//            }
//
//            public String getName() {
//                return name;
//            }
//
//            public void setName(String name) {
//                this.name = name;
//            }
//
//            public String getHeadImg() {
//                return headImg;
//            }
//
//            public void setHeadImg(String headImg) {
//                this.headImg = headImg;
//            }
//
//            public int getSex() {
//                return sex;
//            }
//
//            public void setSex(int sex) {
//                this.sex = sex;
//            }
//
//            public String getNiceName() {
//                return niceName;
//            }
//
//            public void setNiceName(String niceName) {
//                this.niceName = niceName;
//            }
//
//            public String getPhone() {
//                return phone;
//            }
//
//            public void setPhone(String phone) {
//                this.phone = phone;
//            }
//
//            public double getWallet() {
//                return wallet;
//            }
//
//            public void setWallet(double wallet) {
//                this.wallet = wallet;
//            }
//
//            public int getIntegral() {
//                return integral;
//            }
//
//            public void setIntegral(int integral) {
//                this.integral = integral;
//            }
//
//            public String getAddNumber() {
//                return addNumber;
//            }
//
//            public void setAddNumber(String addNumber) {
//                this.addNumber = addNumber;
//            }
//
//            public int getSelActivity() {
//                return selActivity;
//            }
//
//            public void setSelActivity(int selActivity) {
//                this.selActivity = selActivity;
//            }
//
//            public int getState() {
//                return state;
//            }
//
//            public void setState(int state) {
//                this.state = state;
//            }
//
//            public int getHide() {
//                return hide;
//            }
//
//            public void setHide(int hide) {
//                this.hide = hide;
//            }
//
//            public String getIp() {
//                return ip;
//            }
//
//            public void setIp(String ip) {
//                this.ip = ip;
//            }
//
//            public String getIpAddr() {
//                return ipAddr;
//            }
//
//            public void setIpAddr(String ipAddr) {
//                this.ipAddr = ipAddr;
//            }
//
//            public int getJoinNumber() {
//                return joinNumber;
//            }
//
//            public void setJoinNumber(int joinNumber) {
//                this.joinNumber = joinNumber;
//            }
//
//            public long getJoinDate() {
//                return joinDate;
//            }
//
//            public void setJoinDate(long joinDate) {
//                this.joinDate = joinDate;
//            }
//
//
//        }
//
//
//        public static class WinNumber {
//
//            /**
//             * id : 2273
//             * userId : 4
//             * proId : 33
//             * goodsNo : 1
//             * winningNumber : 10000001
//             * sunorder : 0
//             * paytime : 1502847910000
//             * addressId : 17
//             * shopName :
//             * num :
//             * user :
//             */
//
//            private int id;
//            private int userId;
//            private int proId;
//            private int goodsNo;
//            private String winningNumber;
//            private int sunorder;
//            private long paytime;
//            private int addressId;
//            private String shopName;
//            private String num;
//            private String user;
//
//            public int getId() {
//                return id;
//            }
//
//            public void setId(int id) {
//                this.id = id;
//            }
//
//            public int getUserId() {
//                return userId;
//            }
//
//            public void setUserId(int userId) {
//                this.userId = userId;
//            }
//
//            public int getProId() {
//                return proId;
//            }
//
//            public void setProId(int proId) {
//                this.proId = proId;
//            }
//
//            public int getGoodsNo() {
//                return goodsNo;
//            }
//
//            public void setGoodsNo(int goodsNo) {
//                this.goodsNo = goodsNo;
//            }
//
//            public String getWinningNumber() {
//                return winningNumber;
//            }
//
//            public void setWinningNumber(String winningNumber) {
//                this.winningNumber = winningNumber;
//            }
//
//            public int getSunorder() {
//                return sunorder;
//            }
//
//            public void setSunorder(int sunorder) {
//                this.sunorder = sunorder;
//            }
//
//            public long getPaytime() {
//                return paytime;
//            }
//
//            public void setPaytime(long paytime) {
//                this.paytime = paytime;
//            }
//
//            public int getAddressId() {
//                return addressId;
//            }
//
//            public void setAddressId(int addressId) {
//                this.addressId = addressId;
//            }
//
//            public String getShopName() {
//                return shopName;
//            }
//
//            public void setShopName(String shopName) {
//                this.shopName = shopName;
//            }
//
//            public String getNum() {
//                return num;
//            }
//
//            public void setNum(String num) {
//                this.num = num;
//            }
//
//            public String getUser() {
//                return user;
//            }
//
//            public void setUser(String user) {
//                this.user = user;
//            }
//        }
//    }


}
