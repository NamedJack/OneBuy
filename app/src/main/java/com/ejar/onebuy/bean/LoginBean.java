package com.ejar.onebuy.bean;

import java.io.Serializable;

public class LoginBean implements Serializable {

    /**
     * code : 100
     * data : {"user":{"id":4,"tel":"18683661691","otherAccounts":"","gameAccounts":"","passWord":"","name":"","headImg":"","sex":"","niceName":"","phone":"","wallet":"","integral":"","addNumber":"","selActivity":1,"state":1,"ip":"192.168.100.30","ipAddr":"","joinNumber":0,"joinDate":""},"token":"267b0a1735313c7e0aba95eab98c1b1e"}
     */

    private int code;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * user : {"id":4,"tel":"18683661691","otherAccounts":"","gameAccounts":"","passWord":"","name":"","headImg":"","sex":"","niceName":"","phone":"","wallet":"","integral":"","addNumber":"","selActivity":1,"state":1,"ip":"192.168.100.30","ipAddr":"","joinNumber":0,"joinDate":""}
         * token : 267b0a1735313c7e0aba95eab98c1b1e
         */

        private UserBean user;
        private String token;

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public static class UserBean implements Serializable, Cloneable {
            /**
             * id : 4
             * tel : 18683661691
             * otherAccounts :
             * gameAccounts :
             * passWord :
             * name :
             * headImg :
             * sex :
             * niceName :
             * phone :
             * wallet :
             * integral :
             * addNumber :
             * selActivity : 1
             * state : 1
             * ip : 192.168.100.30
             * ipAddr :
             * joinNumber : 0
             * joinDate :
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
            private String wallet;
            private String integral;
            private String addNumber;
            private int selActivity;
            private int state;
            private String ip;
            private String ipAddr;
            private int joinNumber;
            private String joinDate;


            @Override
            public Object clone()  {
                UserBean user = null;
                try {
                    user = (UserBean) super.clone();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
                return user;
            }

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

            public String getWallet() {
                return wallet;
            }

            public void setWallet(String wallet) {
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