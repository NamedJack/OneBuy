package com.ejar.onebuy.bean.home;

import java.util.List;

/**
 * Created by wongxd on 2017/8/14.
 * 商品分类
 */

public class GoodTypeBean {

    /**
     * code : 200
     * msg : OK
     * data : {"code":0,"catalogs":[{"id":1,"name":"极速专区","pid":0},{"id":2,"name":"优选商品","pid":0},{"id":3,"name":"手机平板","pid":0},{"id":4,"name":"电脑办公","pid":0},{"id":5,"name":"数码影音","pid":0},{"id":6,"name":"女性时尚","pid":0},{"id":7,"name":"美食天地","pid":0},{"id":8,"name":"潮流新品","pid":0},{"id":9,"name":"其他商品","pid":0}]}
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
         * catalogs : [{"id":1,"name":"极速专区","pid":0},{"id":2,"name":"优选商品","pid":0},{"id":3,"name":"手机平板","pid":0},{"id":4,"name":"电脑办公","pid":0},{"id":5,"name":"数码影音","pid":0},{"id":6,"name":"女性时尚","pid":0},{"id":7,"name":"美食天地","pid":0},{"id":8,"name":"潮流新品","pid":0},{"id":9,"name":"其他商品","pid":0}]
         */

        private int code;
        private List<CatalogsBean> catalogs;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public List<CatalogsBean> getCatalogs() {
            return catalogs;
        }

        public void setCatalogs(List<CatalogsBean> catalogs) {
            this.catalogs = catalogs;
        }

        public static class CatalogsBean {
            /**
             * id : 1
             * name : 极速专区
             * pid : 0
             */

            private int id;
            private String name;
            private int pid;

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

            public int getPid() {
                return pid;
            }

            public void setPid(int pid) {
                this.pid = pid;
            }
        }
    }
}
