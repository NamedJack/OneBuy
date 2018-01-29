package com.ejar.onebuy.bean.me;

import java.util.List;

/**
 * Created by wongxd on 2017/8/15.
 * 收货地址
 */

public class AdressBean {

    /**
     * code : 200
     * msg : OK
     * data : {"code":0,"addressList":[{"id":17,"account":"4","name":"好的好的吧大概效果好","address":"不懂八点半","zip":"","tel":"12135548959","mobile":"","isdefault":1,"province":"天津","city":"天津市","area":"","email":""}]}
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
         * addressList : [{"id":17,"account":"4","name":"好的好的吧大概效果好","address":"不懂八点半","zip":"","tel":"12135548959","mobile":"","isdefault":1,"province":"天津","city":"天津市","area":"","email":""}]
         */

        private int code;
        private List<AddressListBean> addressList;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public List<AddressListBean> getAddressList() {
            return addressList;
        }

        public void setAddressList(List<AddressListBean> addressList) {
            this.addressList = addressList;
        }

        public static class AddressListBean {
            /**
             * id : 17
             * account : 4
             * name : 好的好的吧大概效果好
             * address : 不懂八点半
             * zip :
             * tel : 12135548959
             * mobile :
             * isdefault : 1
             * province : 天津
             * city : 天津市
             * area :
             * email :
             */

            private int id;
            private String account;
            private String name;
            private String address;
            private String zip;
            private String tel;
            private String mobile;
            private int isdefault;
            private String province;
            private String city;
            private String area;
            private String email="";

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getAccount() {
                return account;
            }

            public void setAccount(String account) {
                this.account = account;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getZip() {
                return zip;
            }

            public void setZip(String zip) {
                this.zip = zip;
            }

            public String getTel() {
                return tel;
            }

            public void setTel(String tel) {
                this.tel = tel;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public int getIsdefault() {
                return isdefault;
            }

            public void setIsdefault(int isdefault) {
                this.isdefault = isdefault;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }
        }
    }
}
