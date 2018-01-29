package com.ejar.onebuy.bean.home;

/**
 * Created by wongxd on 2017/8/15.
 * 立即参与
 */

public class JoinNowBean {

    /**
     * code : 200
     * msg : OK
     * data : {"product":{"id":25,"name":"08-10快速夺宝","price":10,"picture":"/static/product/picture/31f34d5f-4124-48a9-93be-fa200e5c9c01.jpg,/static/product/picture/c81fe13d-3fdb-454f-b532-50971288f73e.jpg","status":1,"productHTML":"<p><img src=\"/oneceIndiana/static/product/790b2a8c-befe-401f-808d-417fc1ae681a.jpg\" alt=\"b70510e57b3aa4262667c4ce9f723521\"><br><\/p><p><br><\/p>","catalogID":1,"type":3,"sort":1,"delFalg":0,"expectNum":10,"alreadyNum":0,"nowNo":6,"isHot":1,"isNew":1,"oneceAmount":1,"stock":995,"catalogName":"","pictures":""},"code":0,"wallet":0}
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
         * product : {"id":25,"name":"08-10快速夺宝","price":10,"picture":"/static/product/picture/31f34d5f-4124-48a9-93be-fa200e5c9c01.jpg,/static/product/picture/c81fe13d-3fdb-454f-b532-50971288f73e.jpg","status":1,"productHTML":"<p><img src=\"/oneceIndiana/static/product/790b2a8c-befe-401f-808d-417fc1ae681a.jpg\" alt=\"b70510e57b3aa4262667c4ce9f723521\"><br><\/p><p><br><\/p>","catalogID":1,"type":3,"sort":1,"delFalg":0,"expectNum":10,"alreadyNum":0,"nowNo":6,"isHot":1,"isNew":1,"oneceAmount":1,"stock":995,"catalogName":"","pictures":""}
         * code : 0
         * wallet : 0.0
         */

        private ProductBean product;
        private int code;
//        private double wallet;

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

//        public double getWallet() {
//            return wallet;
//        }
//
//        public void setWallet(double wallet) {
//            this.wallet = wallet;
//        }

        public static class ProductBean {
            /**
             * id : 25
             * name : 08-10快速夺宝
             * price : 10.0
             * picture : /static/product/picture/31f34d5f-4124-48a9-93be-fa200e5c9c01.jpg,/static/product/picture/c81fe13d-3fdb-454f-b532-50971288f73e.jpg
             * status : 1
             * productHTML : <p><img src="/oneceIndiana/static/product/790b2a8c-befe-401f-808d-417fc1ae681a.jpg" alt="b70510e57b3aa4262667c4ce9f723521"><br></p><p><br></p>
             * catalogID : 1
             * type : 3
             * sort : 1
             * delFalg : 0
             * expectNum : 10
             * alreadyNum : 0
             * nowNo : 6
             * isHot : 1
             * isNew : 1
             * oneceAmount : 1
             * stock : 995
             * catalogName :
             * pictures :
             */

            private int id;
            private String name;
//            private double price;
            private String picture;
//            private int status;
//            private String productHTML;
//            private int catalogID;
            private int type;
//            private int sort;
//            private int delFalg;
            private int expectNum;
            private int alreadyNum;
            private int nowNo;
//            private int isHot;
//            private int isNew;
            private double oneceAmount;
//            private int stock;
//            private String catalogName;
//            private String pictures;

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

//            public double getPrice() {
//                return price;
//            }
//
//            public void setPrice(double price) {
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

            public double getOneceAmount() {
                return oneceAmount;
            }

            public void setOneceAmount(double oneceAmount) {
                this.oneceAmount = oneceAmount;
            }

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
        }
    }
}
