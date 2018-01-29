package com.ejar.onebuy.bean;

import java.util.List;

/**
 * Created by wongxd on 2017/8/14.
 * 往期揭晓bean
 */

public class PreviousLuckyBean {

    /**
     * code : 200
     * msg : OK
     * data : {"code":0,"personNum":10,"winBefore":[{"id":14,"datenumber":"1","proId":26,"overtime":"2017-08-14 14:14:34","lotterytime":"2017-08-14 14:14:34","winningUserId":1,"state":2,"konw":2,"winningNumber":"10000007","sunOrder":2,"numberA":0,"numberB":0,"user":{"id":1,"tel":"18281568081","otherAccounts":"","gameAccounts":"","passWord":"","name":"张三","headImg":"","sex":1,"niceName":"吃炸鸡的猪","phone":"","wallet":714,"integral":1000,"addNumber":"","selActivity":1,"state":1,"hide":1,"ip":"192.168.100.14","ipAddr":"爱神的箭","joinNumber":20,"joinDate":""}}]}
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
         * code : 0
         * personNum : 10
         * winBefore : [{"id":14,"datenumber":"1","proId":26,"overtime":"2017-08-14 14:14:34","lotterytime":"2017-08-14 14:14:34","winningUserId":1,"state":2,"konw":2,"winningNumber":"10000007","sunOrder":2,"numberA":0,"numberB":0,"user":{"id":1,"tel":"18281568081","otherAccounts":"","gameAccounts":"","passWord":"","name":"张三","headImg":"","sex":1,"niceName":"吃炸鸡的猪","phone":"","wallet":714,"integral":1000,"addNumber":"","selActivity":1,"state":1,"hide":1,"ip":"192.168.100.14","ipAddr":"爱神的箭","joinNumber":20,"joinDate":""}}]
         */

        private int code;
        private int personNum;
        private List<WinBeforeBean> winBefore;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public int getPersonNum() {
            return personNum;
        }

        public void setPersonNum(int personNum) {
            this.personNum = personNum;
        }

        public List<WinBeforeBean> getWinBefore() {
            return winBefore;
        }

        public void setWinBefore(List<WinBeforeBean> winBefore) {
            this.winBefore = winBefore;
        }

        public static class WinBeforeBean {
            /**
             * id : 14
             * datenumber : 1
             * proId : 26
             * overtime : 2017-08-14 14:14:34
             * lotterytime : 2017-08-14 14:14:34
             * winningUserId : 1
             * state : 2
             * konw : 2
             * winningNumber : 10000007
             * sunOrder : 2
             * numberA : 0
             * numberB : 0
             * user : {"id":1,"tel":"18281568081","otherAccounts":"","gameAccounts":"","passWord":"","name":"张三","headImg":"","sex":1,"niceName":"吃炸鸡的猪","phone":"","wallet":714,"integral":1000,"addNumber":"","selActivity":1,"state":1,"hide":1,"ip":"192.168.100.14","ipAddr":"爱神的箭","joinNumber":20,"joinDate":""}
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
            private String numberA;
            private String numberB;
            private UserBean user;

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

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
            }

            public static class UserBean {
                /**
                 * id : 1
                 * tel : 18281568081
                 * otherAccounts :
                 * gameAccounts :
                 * passWord :
                 * name : 张三
                 * headImg :
                 * sex : 1
                 * niceName : 吃炸鸡的猪
                 * phone :
                 * wallet : 714.0
                 * integral : 1000.0
                 * addNumber :
                 * selActivity : 1
                 * state : 1
                 * hide : 1
                 * ip : 192.168.100.14
                 * ipAddr : 爱神的箭
                 * joinNumber : 20
                 * joinDate :
                 */

                private int id;
                private String tel;
                private String otherAccounts;
                private String gameAccounts;
                private String passWord;
                private String name;
                private String headImg;
                private int sex;
                private String niceName;
                private String phone;
                private double wallet;
                private double integral;
                private String addNumber;
                private int selActivity;
                private int state;
                private int hide;
                private String ip;
                private String ipAddr;
                private int joinNumber;
                private String joinDate;

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

                public int getSex() {
                    return sex;
                }

                public void setSex(int sex) {
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

                public double getIntegral() {
                    return integral;
                }

                public void setIntegral(double integral) {
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
            }
        }
    }
}
