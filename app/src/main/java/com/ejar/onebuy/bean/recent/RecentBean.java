package com.ejar.onebuy.bean.recent;

import java.util.List;

/**
 * Created by wongxd on 2017/8/16.
 */

public class RecentBean {
    /**
     * code : 200
     * msg : OK
     * data : {"announces":[{"id":13,"proId":3,"proName":"测试1","img":"/static/product/picture/1512544659884_1.jpg","dateNO":3,"state":2,"overTime":1512545415000,"kjTime":1512545999000,"announceTime":1512545999000,"joinNum":5,"proprice":5,"afterTime":0,"user":{"id":29,"tel":"ofs560oSxazCXqrHPDkniDNna2WY","otherAccounts":null,"gameAccounts":null,"passWord":null,"name":null,"headImg":"/0","sex":0,"niceName":"测试专用","phone":null,"wallet":9845,"integral":0,"addNumber":null,"selActivity":null,"state":1,"hide":1,"bind":1,"unionid":"o_LpCxGmBMlRfJsJDX056wEx2W5E","ip":null,"ipAddr":null,"joinNumber":0,"joinDate":null,"zhuiQi":0}},{"id":11,"proId":3,"proName":"测试1","img":"/static/product/picture/1512544659884_1.jpg","dateNO":2,"state":2,"overTime":1512544928000,"kjTime":1512545399000,"announceTime":1512545399000,"joinNum":5,"proprice":5,"afterTime":0,"user":{"id":36,"tel":null,"otherAccounts":null,"gameAccounts":null,"passWord":null,"name":null,"headImg":"http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLkzOrXd2aQ1yriavLXJFmET5ojepTMkzhibFfs0z1Z4MMa1C5Fm9FeyBwCQ3GGtrG80oSnamKtGfrw/0","sex":0,"niceName":"tite","phone":null,"wallet":9999930,"integral":0,"addNumber":null,"selActivity":null,"state":1,"hide":1,"bind":1,"unionid":"o_LpCxFnqzpWANQMi2jFETdXb1Lw","ip":null,"ipAddr":null,"joinNumber":0,"joinDate":null,"zhuiQi":0}},{"id":12,"proId":2,"proName":"测试","img":"/static/product/picture/1512542851808_1.jpg","dateNO":9,"state":2,"overTime":1512544941000,"kjTime":1512545399000,"announceTime":1512545399000,"joinNum":10,"proprice":10,"afterTime":0,"user":{"id":36,"tel":null,"otherAccounts":null,"gameAccounts":null,"passWord":null,"name":null,"headImg":"http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLkzOrXd2aQ1yriavLXJFmET5ojepTMkzhibFfs0z1Z4MMa1C5Fm9FeyBwCQ3GGtrG80oSnamKtGfrw/0","sex":0,"niceName":"tite","phone":null,"wallet":9999930,"integral":0,"addNumber":null,"selActivity":null,"state":1,"hide":1,"bind":1,"unionid":"o_LpCxFnqzpWANQMi2jFETdXb1Lw","ip":null,"ipAddr":null,"joinNumber":0,"joinDate":null,"zhuiQi":0}},{"id":9,"proId":2,"proName":"测试","img":"/static/product/picture/1512542851808_1.jpg","dateNO":8,"state":2,"overTime":1512544671000,"kjTime":1512544799000,"announceTime":1512544799000,"joinNum":10,"proprice":10,"afterTime":0,"user":{"id":31,"tel":null,"otherAccounts":null,"gameAccounts":null,"passWord":null,"name":null,"headImg":"http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIZ5Fv0cecSvCso31nn50Qzk8kohhNzicacu2q7Pz7javV3poMu3N6bWBleOqNib6BxZ4ibPTOkexJWg/0","sex":1,"niceName":"wy","phone":null,"wallet":19,"integral":3.6,"addNumber":null,"selActivity":null,"state":1,"hide":1,"bind":1,"unionid":"o_LpCxJIDKlIOlO2pCU4u-iyRUtI","ip":null,"ipAddr":null,"joinNumber":0,"joinDate":null,"zhuiQi":0}},{"id":10,"proId":3,"proName":"测试1","img":"/static/product/picture/1512544659884_1.jpg","dateNO":1,"state":2,"overTime":1512544719000,"kjTime":1512544799000,"announceTime":1512544799000,"joinNum":5,"proprice":5,"afterTime":0,"user":{"id":36,"tel":null,"otherAccounts":null,"gameAccounts":null,"passWord":null,"name":null,"headImg":"http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLkzOrXd2aQ1yriavLXJFmET5ojepTMkzhibFfs0z1Z4MMa1C5Fm9FeyBwCQ3GGtrG80oSnamKtGfrw/0","sex":0,"niceName":"tite","phone":null,"wallet":9999930,"integral":0,"addNumber":null,"selActivity":null,"state":1,"hide":1,"bind":1,"unionid":"o_LpCxFnqzpWANQMi2jFETdXb1Lw","ip":null,"ipAddr":null,"joinNumber":0,"joinDate":null,"zhuiQi":0}}],"code":0,"mmpTime":1512546709450,"totalPage":3,"nowTime":"2017-12-06"}
     */

    private String code;
    private String msg;
    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
         * announces : [{"id":13,"proId":3,"proName":"测试1","img":"/static/product/picture/1512544659884_1.jpg","dateNO":3,"state":2,"overTime":1512545415000,"kjTime":1512545999000,"announceTime":1512545999000,"joinNum":5,"proprice":5,"afterTime":0,"user":{"id":29,"tel":"ofs560oSxazCXqrHPDkniDNna2WY","otherAccounts":null,"gameAccounts":null,"passWord":null,"name":null,"headImg":"/0","sex":0,"niceName":"测试专用","phone":null,"wallet":9845,"integral":0,"addNumber":null,"selActivity":null,"state":1,"hide":1,"bind":1,"unionid":"o_LpCxGmBMlRfJsJDX056wEx2W5E","ip":null,"ipAddr":null,"joinNumber":0,"joinDate":null,"zhuiQi":0}},{"id":11,"proId":3,"proName":"测试1","img":"/static/product/picture/1512544659884_1.jpg","dateNO":2,"state":2,"overTime":1512544928000,"kjTime":1512545399000,"announceTime":1512545399000,"joinNum":5,"proprice":5,"afterTime":0,"user":{"id":36,"tel":null,"otherAccounts":null,"gameAccounts":null,"passWord":null,"name":null,"headImg":"http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLkzOrXd2aQ1yriavLXJFmET5ojepTMkzhibFfs0z1Z4MMa1C5Fm9FeyBwCQ3GGtrG80oSnamKtGfrw/0","sex":0,"niceName":"tite","phone":null,"wallet":9999930,"integral":0,"addNumber":null,"selActivity":null,"state":1,"hide":1,"bind":1,"unionid":"o_LpCxFnqzpWANQMi2jFETdXb1Lw","ip":null,"ipAddr":null,"joinNumber":0,"joinDate":null,"zhuiQi":0}},{"id":12,"proId":2,"proName":"测试","img":"/static/product/picture/1512542851808_1.jpg","dateNO":9,"state":2,"overTime":1512544941000,"kjTime":1512545399000,"announceTime":1512545399000,"joinNum":10,"proprice":10,"afterTime":0,"user":{"id":36,"tel":null,"otherAccounts":null,"gameAccounts":null,"passWord":null,"name":null,"headImg":"http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLkzOrXd2aQ1yriavLXJFmET5ojepTMkzhibFfs0z1Z4MMa1C5Fm9FeyBwCQ3GGtrG80oSnamKtGfrw/0","sex":0,"niceName":"tite","phone":null,"wallet":9999930,"integral":0,"addNumber":null,"selActivity":null,"state":1,"hide":1,"bind":1,"unionid":"o_LpCxFnqzpWANQMi2jFETdXb1Lw","ip":null,"ipAddr":null,"joinNumber":0,"joinDate":null,"zhuiQi":0}},{"id":9,"proId":2,"proName":"测试","img":"/static/product/picture/1512542851808_1.jpg","dateNO":8,"state":2,"overTime":1512544671000,"kjTime":1512544799000,"announceTime":1512544799000,"joinNum":10,"proprice":10,"afterTime":0,"user":{"id":31,"tel":null,"otherAccounts":null,"gameAccounts":null,"passWord":null,"name":null,"headImg":"http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIZ5Fv0cecSvCso31nn50Qzk8kohhNzicacu2q7Pz7javV3poMu3N6bWBleOqNib6BxZ4ibPTOkexJWg/0","sex":1,"niceName":"wy","phone":null,"wallet":19,"integral":3.6,"addNumber":null,"selActivity":null,"state":1,"hide":1,"bind":1,"unionid":"o_LpCxJIDKlIOlO2pCU4u-iyRUtI","ip":null,"ipAddr":null,"joinNumber":0,"joinDate":null,"zhuiQi":0}},{"id":10,"proId":3,"proName":"测试1","img":"/static/product/picture/1512544659884_1.jpg","dateNO":1,"state":2,"overTime":1512544719000,"kjTime":1512544799000,"announceTime":1512544799000,"joinNum":5,"proprice":5,"afterTime":0,"user":{"id":36,"tel":null,"otherAccounts":null,"gameAccounts":null,"passWord":null,"name":null,"headImg":"http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLkzOrXd2aQ1yriavLXJFmET5ojepTMkzhibFfs0z1Z4MMa1C5Fm9FeyBwCQ3GGtrG80oSnamKtGfrw/0","sex":0,"niceName":"tite","phone":null,"wallet":9999930,"integral":0,"addNumber":null,"selActivity":null,"state":1,"hide":1,"bind":1,"unionid":"o_LpCxFnqzpWANQMi2jFETdXb1Lw","ip":null,"ipAddr":null,"joinNumber":0,"joinDate":null,"zhuiQi":0}}]
         * code : 0
         * mmpTime : 1512546709450
         * totalPage : 3
         * nowTime : 2017-12-06
         */

        private String code;
        private long mmpTime;
        private int totalPage;
//        private String nowTime;
        private List<AnnouncesBean> announces;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public long getMmpTime() {
            return mmpTime;
        }

        public void setMmpTime(long mmpTime) {
            this.mmpTime = mmpTime;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

//        public String getNowTime() {
//            return nowTime;
//        }
//
//        public void setNowTime(String nowTime) {
//            this.nowTime = nowTime;
//        }

        public List<AnnouncesBean> getAnnounces() {
            return announces;
        }

        public void setAnnounces(List<AnnouncesBean> announces) {
            this.announces = announces;
        }

        public static class AnnouncesBean {
            /**
             * id : 13
             * proId : 3
             * proName : 测试1
             * img : /static/product/picture/1512544659884_1.jpg
             * dateNO : 3
             * state : 2
             * overTime : 1512545415000
             * kjTime : 1512545999000
             * announceTime : 1512545999000
             * joinNum : 5
             * proprice : 5
             * afterTime : 0
             * user : {"id":29,"tel":"ofs560oSxazCXqrHPDkniDNna2WY","otherAccounts":null,"gameAccounts":null,"passWord":null,"name":null,"headImg":"/0","sex":0,"niceName":"测试专用","phone":null,"wallet":9845,"integral":0,"addNumber":null,"selActivity":null,"state":1,"hide":1,"bind":1,"unionid":"o_LpCxGmBMlRfJsJDX056wEx2W5E","ip":null,"ipAddr":null,"joinNumber":0,"joinDate":null,"zhuiQi":0}
             */

            private int id;
            private int proId;
            private String proName;
            private String img;
            private int dateNO;
            private int state;
//            private long overTime;
            private long kjTime;
            private long announceTime;
            private int joinNum;

//            private int proprice;
//            private int afterTime;
            private UserBean user;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getProId() {
                return proId;
            }

            public void setProId(int proId) {
                this.proId = proId;
            }

            public String getProName() {
                return proName;
            }

            public void setProName(String proName) {
                this.proName = proName;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public int getDateNO() {
                return dateNO;
            }

            public void setDateNO(int dateNO) {
                this.dateNO = dateNO;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

//            public long getOverTime() {
//                return overTime;
//            }
//
//            public void setOverTime(long overTime) {
//                this.overTime = overTime;
//            }

            public long getKjTime() {
                return kjTime;
            }

            public void setKjTime(long kjTime) {
                this.kjTime = kjTime;
            }

            public long getAnnounceTime() {
                return announceTime;
            }

            public void setAnnounceTime(long announceTime) {
                this.announceTime = announceTime;
            }

            public int getJoinNum() {
                return joinNum;
            }

            public void setJoinNum(int joinNum) {
                this.joinNum = joinNum;
            }
//
//            public int getProprice() {
//                return proprice;
//            }
//
//            public void setProprice(int proprice) {
//                this.proprice = proprice;
//            }
//
//            public int getAfterTime() {
//                return afterTime;
//            }
//
//            public void setAfterTime(int afterTime) {
//                this.afterTime = afterTime;
//            }

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
                 * wallet : 9845
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
//                private String tel;
//                private Object otherAccounts;
//                private Object gameAccounts;
//                private Object passWord;
//                private Object name;
                private String headImg;
//                private int sex;
                private String niceName;
//                private Object phone;
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
//                private int zhuiQi;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

//                public String getTel() {
//                    return tel;
//                }
//
//                public void setTel(String tel) {
//                    this.tel = tel;
//                }

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
//
//                public Object getPassWord() {
//                    return passWord;
//                }
//
//                public void setPassWord(Object passWord) {
//                    this.passWord = passWord;
//                }

//                public Object getName() {
//                    return name;
//                }
//
//                public void setName(Object name) {
//                    this.name = name;
//                }

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

//                public Object getPhone() {
//                    return phone;
//                }
//
//                public void setPhone(Object phone) {
//                    this.phone = phone;
//                }
//
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

//                public Object getAddNumber() {
//                    return addNumber;
//                }
//
//                public void setAddNumber(Object addNumber) {
//                    this.addNumber = addNumber;
//                }

//                public Object getSelActivity() {
//                    return selActivity;
//                }
//
//                public void setSelActivity(Object selActivity) {
//                    this.selActivity = selActivity;
//                }

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

//                public Object getJoinDate() {
//                    return joinDate;
//                }
//
//                public void setJoinDate(Object joinDate) {
//                    this.joinDate = joinDate;
//                }

//                public int getZhuiQi() {
//                    return zhuiQi;
//                }
//
//                public void setZhuiQi(int zhuiQi) {
//                    this.zhuiQi = zhuiQi;
//                }
            }
        }
    }


//
//    /**
//     * code : 200
//     * msg : OK
//     * data : {"announces":[{"id":124,"proId":32,"proName":"8-16(测试)","img":"/static/product/picture/dd0ce08b-5567-46e8-9542-c6c4988c2ee6.png","dateNO":2,"state":2,"overTime":1502854301000,"announceTime":1502854650000,"joinNum":10,"proprice":100,"afterTime":0,"user":{"id":1,"tel":"18281568081","otherAccounts":"","gameAccounts":"","passWord":"","name":"张三","headImg":"","sex":1,"niceName":"吃炸鸡的猪","phone":"13965651818","wallet":9982357,"integral":1000,"addNumber":"","selActivity":1,"state":1,"hide":1,"ip":"192.168.100.14","ipAddr":"爱神的箭","joinNumber":0,"joinDate":"","zhuiQi":0}}],"code":0,"totalPage":1}
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
//         * announces : [{"id":124,"proId":32,"proName":"8-16(测试)","img":"/static/product/picture/dd0ce08b-5567-46e8-9542-c6c4988c2ee6.png","dateNO":2,"state":2,"overTime":1502854301000,"announceTime":1502854650000,"joinNum":10,"proprice":100,"afterTime":0,"user":{"id":1,"tel":"18281568081","otherAccounts":"","gameAccounts":"","passWord":"","name":"张三","headImg":"","sex":1,"niceName":"吃炸鸡的猪","phone":"13965651818","wallet":9982357,"integral":1000,"addNumber":"","selActivity":1,"state":1,"hide":1,"ip":"192.168.100.14","ipAddr":"爱神的箭","joinNumber":0,"joinDate":"","zhuiQi":0}}]
//         * code : 0
//         * totalPage : 1
//         */
//
//        private int code;
//        private int totalPage;
//        private long mmpTime;
//
//        public long getMmpTime() {
//            return mmpTime;
//        }
//
//        public void setMmpTime(long mmpTime) {
//            this.mmpTime = mmpTime;
//        }
//
//        private List<AnnouncesBean> announces;
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
//        public List<AnnouncesBean> getAnnounces() {
//            return announces;
//        }
//
//        public void setAnnounces(List<AnnouncesBean> announces) {
//            this.announces = announces;
//        }
//
//        public static class AnnouncesBean {
//            /**
//             * id : 124
//             * proId : 32
//             * proName : 8-16(测试)
//             * img : /static/product/picture/dd0ce08b-5567-46e8-9542-c6c4988c2ee6.png
//             * dateNO : 2
//             * state : 2
//             * overTime : 1502854301000
//             * announceTime : 1502854650000
//             * joinNum : 10
//             * proprice : 100.0
//             * afterTime : 0
//             * user : {"id":1,"tel":"18281568081","otherAccounts":"","gameAccounts":"","passWord":"","name":"张三","headImg":"","sex":1,"niceName":"吃炸鸡的猪","phone":"13965651818","wallet":9982357,"integral":1000,"addNumber":"","selActivity":1,"state":1,"hide":1,"ip":"192.168.100.14","ipAddr":"爱神的箭","joinNumber":0,"joinDate":"","zhuiQi":0}
//             */
//
//            private long mmpTime;
//
//            public long getMmpTime() {
//                return mmpTime;
//            }
//
//            public void setMmpTime(long mmpTime) {
//                this.mmpTime = mmpTime;
//            }
//
//            private long endTime = 0;
//
//            private long kjTime;
//
//            public long getEndTime() {
//                return endTime;
//            }
//
//            public void setEndTime(long endTime) {
//                this.endTime = endTime;
//            }
//
//            private int id;
//            private int proId;
//            private String proName;
//            private String img;
//            private int dateNO;
//            private int state;
//            private long overTime;
//            private long announceTime;
//            private int joinNum;
//            private double proprice;
//            private long afterTime;
//            private UserBean user;
//
//            public long getKjTime() {
//                return kjTime;
//            }
//
//            public void setKjTime(long kjTime) {
//                this.kjTime = kjTime;
//            }
//
//            public int getId() {
//                return id;
//            }
//
//            public void setId(int id) {
//                this.id = id;
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
//            public String getProName() {
//                return proName;
//            }
//
//            public void setProName(String proName) {
//                this.proName = proName;
//            }
//
//            public String getImg() {
//                return img;
//            }
//
//            public void setImg(String img) {
//                this.img = img;
//            }
//
//            public int getDateNO() {
//                return dateNO;
//            }
//
//            public void setDateNO(int dateNO) {
//                this.dateNO = dateNO;
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
//            public long getOverTime() {
//                return overTime;
//            }
//
//            public void setOverTime(long overTime) {
//                this.overTime = overTime;
//            }
//
//            public long getAnnounceTime() {
//                return announceTime;
//            }
//
//            public void setAnnounceTime(long announceTime) {
//                this.announceTime = announceTime;
//            }
//
//            public int getJoinNum() {
//                return joinNum;
//            }
//
//            public void setJoinNum(int joinNum) {
//                this.joinNum = joinNum;
//            }
//
//            public double getProprice() {
//                return proprice;
//            }
//
//            public void setProprice(double proprice) {
//                this.proprice = proprice;
//            }
//
//            public long getAfterTime() {
//                return afterTime;
//            }
//
//            public void setAfterTime(long afterTime) {
//                this.afterTime = afterTime;
//            }
//
//            public UserBean getUser() {
//                return user;
//            }
//
//            public void setUser(UserBean user) {
//                this.user = user;
//            }
//
//            public static class UserBean {
//                /**
//                 * id : 1
//                 * tel : 18281568081
//                 * otherAccounts :
//                 * gameAccounts :
//                 * passWord :
//                 * name : 张三
//                 * headImg :
//                 * sex : 1
//                 * niceName : 吃炸鸡的猪
//                 * phone : 13965651818
//                 * wallet : 9982357
//                 * integral : 1000
//                 * addNumber :
//                 * selActivity : 1
//                 * state : 1
//                 * hide : 1
//                 * ip : 192.168.100.14
//                 * ipAddr : 爱神的箭
//                 * joinNumber : 0
//                 * joinDate :
//                 * zhuiQi : 0
//                 */
//
//                private int id;
//                private String tel;
//                private String otherAccounts;
//                private String gameAccounts;
//                private String passWord;
//                private String name;
//                private String headImg;
//                private int sex;
//                private String niceName;
//                private String phone;
//                private int wallet;
//                private int integral;
//                private String addNumber;
//                private int selActivity;
//                private int state;
//                private int hide;
//                private String ip;
//                private String ipAddr;
//                private int joinNumber;
//                private String joinDate;
//                private int zhuiQi;
//
//                public int getId() {
//                    return id;
//                }
//
//                public void setId(int id) {
//                    this.id = id;
//                }
//
//                public String getTel() {
//                    return tel;
//                }
//
//                public void setTel(String tel) {
//                    this.tel = tel;
//                }
//
//                public String getOtherAccounts() {
//                    return otherAccounts;
//                }
//
//                public void setOtherAccounts(String otherAccounts) {
//                    this.otherAccounts = otherAccounts;
//                }
//
//                public String getGameAccounts() {
//                    return gameAccounts;
//                }
//
//                public void setGameAccounts(String gameAccounts) {
//                    this.gameAccounts = gameAccounts;
//                }
//
//                public String getPassWord() {
//                    return passWord;
//                }
//
//                public void setPassWord(String passWord) {
//                    this.passWord = passWord;
//                }
//
//                public String getName() {
//                    return name;
//                }
//
//                public void setName(String name) {
//                    this.name = name;
//                }
//
//                public String getHeadImg() {
//                    return headImg;
//                }
//
//                public void setHeadImg(String headImg) {
//                    this.headImg = headImg;
//                }
//
//                public int getSex() {
//                    return sex;
//                }
//
//                public void setSex(int sex) {
//                    this.sex = sex;
//                }
//
//                public String getNiceName() {
//                    return niceName;
//                }
//
//                public void setNiceName(String niceName) {
//                    this.niceName = niceName;
//                }
//
//                public String getPhone() {
//                    return phone;
//                }
//
//                public void setPhone(String phone) {
//                    this.phone = phone;
//                }
//
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
//                public String getAddNumber() {
//                    return addNumber;
//                }
//
//                public void setAddNumber(String addNumber) {
//                    this.addNumber = addNumber;
//                }
//
//                public int getSelActivity() {
//                    return selActivity;
//                }
//
//                public void setSelActivity(int selActivity) {
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
//
//                public String getIp() {
//                    return ip;
//                }
//
//                public void setIp(String ip) {
//                    this.ip = ip;
//                }
//
//                public String getIpAddr() {
//                    return ipAddr;
//                }
//
//                public void setIpAddr(String ipAddr) {
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
//                public String getJoinDate() {
//                    return joinDate;
//                }
//
//                public void setJoinDate(String joinDate) {
//                    this.joinDate = joinDate;
//                }
//
//                public int getZhuiQi() {
//                    return zhuiQi;
//                }
//
//                public void setZhuiQi(int zhuiQi) {
//                    this.zhuiQi = zhuiQi;
//                }
//            }
//        }
//    }
}
