package com.ejar.onebuy.bean.me;

import java.io.Serializable;

/**
 * Created by wongxd on 2017/8/21.
 */

public class PersonBean implements Serializable {

    /**
     * code : 200
     * msg : OK
     * data : {"msg":"查询个人信息成功","code":0,"user":{"id":11,"tel":"ofs560ssZrA-jOboNhmimguNcWbk","otherAccounts":"","gameAccounts":"","passWord":"","name":"","headImg":"http://wx.qlogo.cn/mmopen/tLkR4VRzFibA2qSavAsicaUic39rEelokuwfnRchiaQQPCnftffKjvQ3whl66VAnDoVMjcCBrg8CGmdXy1KcNsNPwLY8p6zQ6K49/0","sex":1,"niceName":"至东至西至清溪","phone":null,"wallet":0,"integral":"","addNumber":"","selActivity":"","state":"","hide":1,"ip":"","ipAddr":"","joinNumber":0,"joinDate":"","zhuiQi":0}}
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

    public static class DataBean implements Serializable {
        /**
         * msg : 查询个人信息成功
         * code : 0
         * user : {"id":11,"tel":"ofs560ssZrA-jOboNhmimguNcWbk","otherAccounts":"","gameAccounts":"","passWord":"","name":"","headImg":"http://wx.qlogo.cn/mmopen/tLkR4VRzFibA2qSavAsicaUic39rEelokuwfnRchiaQQPCnftffKjvQ3whl66VAnDoVMjcCBrg8CGmdXy1KcNsNPwLY8p6zQ6K49/0","sex":1,"niceName":"至东至西至清溪","phone":null,"wallet":0,"integral":"","addNumber":"","selActivity":"","state":"","hide":1,"ip":"","ipAddr":"","joinNumber":0,"joinDate":"","zhuiQi":0}
         */

        private String msg;
        private int code;
        private UserBean user;

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

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean implements Serializable, Cloneable {
            /**
             * id : 11
             * tel : ofs560ssZrA-jOboNhmimguNcWbk
             * otherAccounts :
             * gameAccounts :
             * passWord :
             * name :
             * headImg : http://wx.qlogo.cn/mmopen/tLkR4VRzFibA2qSavAsicaUic39rEelokuwfnRchiaQQPCnftffKjvQ3whl66VAnDoVMjcCBrg8CGmdXy1KcNsNPwLY8p6zQ6K49/0
             * sex : 1
             * niceName : 至东至西至清溪
             * phone : null
             * wallet : 0.0
             * integral :
             * addNumber :
             * selActivity :
             * state :
             * hide : 1
             * ip :
             * ipAddr :
             * joinNumber : 0
             * joinDate :
             * zhuiQi : 0
             */

            @Override
            public Object clone() {
                UserBean user = null;
                try {
                    user = (UserBean) super.clone();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
                return user;
            }

            private int id;
            private String tel;
            private String otherAccounts;
            private String gameAccounts;
            private String passWord;
            private String name;
            private String headImg;
            private int sex;
            private String niceName;
            private Object phone;
            private double wallet;
            private String integral;
            private String addNumber;
            private String selActivity;
            private String state;
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

            public Object getPhone() {
                return phone;
            }

            public void setPhone(Object phone) {
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

            public String getSelActivity() {
                return selActivity;
            }

            public void setSelActivity(String selActivity) {
                this.selActivity = selActivity;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
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
