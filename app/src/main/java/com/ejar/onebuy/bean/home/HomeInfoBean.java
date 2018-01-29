package com.ejar.onebuy.bean.home;

import java.util.List;

/**
 * Created by wongxd on 2017/8/11.
 */

public class HomeInfoBean {

    /**
     * code : 200
     * msg : OK
     * data : {"hotProduct":[{"id":25,"name":"08-10快速夺宝","price":10,"picture":"/static/product/picture/31f34d5f-4124-48a9-93be-fa200e5c9c01.jpg","status":1,"productHTML":"<p><img src=\"/oneceIndiana/static/product/790b2a8c-befe-401f-808d-417fc1ae681a.jpg\" alt=\"b70510e57b3aa4262667c4ce9f723521\"><br><\/p><p><br><\/p>","catalogID":1,"type":3,"sort":1,"delFalg":0,"expectNum":10,"alreadyNum":0,"nowNo":3,"isHot":1,"isNew":1,"oneceAmount":1,"stock":998,"catalogName":"","pictures":""}],"hotCatalogId":2,"code":0,"commendProduct":{"id":25,"name":"08-10快速夺宝","price":10,"picture":"/static/product/picture/31f34d5f-4124-48a9-93be-fa200e5c9c01.jpg","status":1,"productHTML":"<p><img src=\"/oneceIndiana/static/product/790b2a8c-befe-401f-808d-417fc1ae681a.jpg\" alt=\"b70510e57b3aa4262667c4ce9f723521\"><br><\/p><p><br><\/p>","catalogID":1,"type":3,"sort":1,"delFalg":0,"expectNum":10,"alreadyNum":0,"nowNo":3,"isHot":1,"isNew":1,"oneceAmount":1,"stock":998,"catalogName":"","pictures":""},"totalPage":1,"fastCatalogId":1,"newProduct":[{"id":25,"name":"08-10快速夺宝","price":10,"picture":"/static/product/picture/31f34d5f-4124-48a9-93be-fa200e5c9c01.jpg","status":1,"productHTML":"<p><img src=\"/oneceIndiana/static/product/790b2a8c-befe-401f-808d-417fc1ae681a.jpg\" alt=\"b70510e57b3aa4262667c4ce9f723521\"><br><\/p><p><br><\/p>","catalogID":1,"type":3,"sort":1,"delFalg":0,"expectNum":10,"alreadyNum":0,"nowNo":3,"isHot":1,"isNew":1,"oneceAmount":1,"stock":998,"catalogName":"","pictures":""}],"products":[{"id":26,"name":"8-11十元夺宝","price":100,"picture":"/static/product/picture/b8f26b43-7114-4772-b7df-542f6e84cc15.jpg","status":1,"productHTML":"<p><img src=\"/oneceIndiana/static/product/f4519664-fab5-4643-9f9b-3d7c595f7792.jpg\" alt=\"b70510e57b3aa4262667c4ce9f723521\"><br><\/p><p><br><\/p>","catalogID":2,"type":2,"sort":1,"delFalg":0,"expectNum":10,"alreadyNum":10,"nowNo":1,"isHot":1,"isNew":1,"oneceAmount":10,"stock":1000,"catalogName":"","pictures":""}]}
     */

    public int code;
    public String msg;
    public DataBean data;

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
         * hotProduct : [{"id":25,"name":"08-10快速夺宝","price":10,"picture":"/static/product/picture/31f34d5f-4124-48a9-93be-fa200e5c9c01.jpg","status":1,"productHTML":"<p><img src=\"/oneceIndiana/static/product/790b2a8c-befe-401f-808d-417fc1ae681a.jpg\" alt=\"b70510e57b3aa4262667c4ce9f723521\"><br><\/p><p><br><\/p>","catalogID":1,"type":3,"sort":1,"delFalg":0,"expectNum":10,"alreadyNum":0,"nowNo":3,"isHot":1,"isNew":1,"oneceAmount":1,"stock":998,"catalogName":"","pictures":""}]
         * hotCatalogId : 2
         * code : 0
         * commendProduct : {"id":25,"name":"08-10快速夺宝","price":10,"picture":"/static/product/picture/31f34d5f-4124-48a9-93be-fa200e5c9c01.jpg","status":1,"productHTML":"<p><img src=\"/oneceIndiana/static/product/790b2a8c-befe-401f-808d-417fc1ae681a.jpg\" alt=\"b70510e57b3aa4262667c4ce9f723521\"><br><\/p><p><br><\/p>","catalogID":1,"type":3,"sort":1,"delFalg":0,"expectNum":10,"alreadyNum":0,"nowNo":3,"isHot":1,"isNew":1,"oneceAmount":1,"stock":998,"catalogName":"","pictures":""}
         * totalPage : 1
         * fastCatalogId : 1
         * newProduct : [{"id":25,"name":"08-10快速夺宝","price":10,"picture":"/static/product/picture/31f34d5f-4124-48a9-93be-fa200e5c9c01.jpg","status":1,"productHTML":"<p><img src=\"/oneceIndiana/static/product/790b2a8c-befe-401f-808d-417fc1ae681a.jpg\" alt=\"b70510e57b3aa4262667c4ce9f723521\"><br><\/p><p><br><\/p>","catalogID":1,"type":3,"sort":1,"delFalg":0,"expectNum":10,"alreadyNum":0,"nowNo":3,"isHot":1,"isNew":1,"oneceAmount":1,"stock":998,"catalogName":"","pictures":""}]
         * products : [{"id":26,"name":"8-11十元夺宝","price":100,"picture":"/static/product/picture/b8f26b43-7114-4772-b7df-542f6e84cc15.jpg","status":1,"productHTML":"<p><img src=\"/oneceIndiana/static/product/f4519664-fab5-4643-9f9b-3d7c595f7792.jpg\" alt=\"b70510e57b3aa4262667c4ce9f723521\"><br><\/p><p><br><\/p>","catalogID":2,"type":2,"sort":1,"delFalg":0,"expectNum":10,"alreadyNum":10,"nowNo":1,"isHot":1,"isNew":1,"oneceAmount":10,"stock":1000,"catalogName":"","pictures":""}]
         */

        private int hotCatalogId;
        private int code;
        private CommendProductBean commendProduct;
        private int totalPage;
        private int fastCatalogId;
        private List<HotProductBean> hotProduct;
        private List<NewProductBean> newProduct;
        private List<ProductsBean> products;

        public int getHotCatalogId() {
            return hotCatalogId;
        }

        public void setHotCatalogId(int hotCatalogId) {
            this.hotCatalogId = hotCatalogId;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public CommendProductBean getCommendProduct() {
            return commendProduct;
        }

        public void setCommendProduct(CommendProductBean commendProduct) {
            this.commendProduct = commendProduct;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getFastCatalogId() {
            return fastCatalogId;
        }

        public void setFastCatalogId(int fastCatalogId) {
            this.fastCatalogId = fastCatalogId;
        }

        public List<HotProductBean> getHotProduct() {
            return hotProduct;
        }

        public void setHotProduct(List<HotProductBean> hotProduct) {
            this.hotProduct = hotProduct;
        }

        public List<NewProductBean> getNewProduct() {
            return newProduct;
        }

        public void setNewProduct(List<NewProductBean> newProduct) {
            this.newProduct = newProduct;
        }

        public List<ProductsBean> getProducts() {
            return products;
        }

        public void setProducts(List<ProductsBean> products) {
            this.products = products;
        }

        public static class CommendProductBean {
            /**
             * id : 25
             * name : 08-10快速夺宝
             * price : 10
             * picture : /static/product/picture/31f34d5f-4124-48a9-93be-fa200e5c9c01.jpg
             * status : 1
             * productHTML : <p><img src="/oneceIndiana/static/product/790b2a8c-befe-401f-808d-417fc1ae681a.jpg" alt="b70510e57b3aa4262667c4ce9f723521"><br></p><p><br></p>
             * catalogID : 1
             * type : 3
             * sort : 1
             * delFalg : 0
             * expectNum : 10
             * alreadyNum : 0
             * nowNo : 3
             * isHot : 1
             * isNew : 1
             * oneceAmount : 1
             * stock : 998
             * catalogName :
             * pictures :
             */

            private int id;
            private String name;
            private int price;
            private String picture;
            private int status;
            private String productHTML;
            private int catalogID;
            private int type;
            private int sort;
            private int delFalg;
            private int expectNum;
            private int alreadyNum;
            private int nowNo;
            private int isHot;
            private int isNew;
            private int oneceAmount;
            private int stock;
            private String catalogName;
            private String pictures;

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

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getProductHTML() {
                return productHTML;
            }

            public void setProductHTML(String productHTML) {
                this.productHTML = productHTML;
            }

            public int getCatalogID() {
                return catalogID;
            }

            public void setCatalogID(int catalogID) {
                this.catalogID = catalogID;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public int getDelFalg() {
                return delFalg;
            }

            public void setDelFalg(int delFalg) {
                this.delFalg = delFalg;
            }

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

            public int getIsHot() {
                return isHot;
            }

            public void setIsHot(int isHot) {
                this.isHot = isHot;
            }

            public int getIsNew() {
                return isNew;
            }

            public void setIsNew(int isNew) {
                this.isNew = isNew;
            }

            public int getOneceAmount() {
                return oneceAmount;
            }

            public void setOneceAmount(int oneceAmount) {
                this.oneceAmount = oneceAmount;
            }

            public int getStock() {
                return stock;
            }

            public void setStock(int stock) {
                this.stock = stock;
            }

            public String getCatalogName() {
                return catalogName;
            }

            public void setCatalogName(String catalogName) {
                this.catalogName = catalogName;
            }

            public String getPictures() {
                return pictures;
            }

            public void setPictures(String pictures) {
                this.pictures = pictures;
            }
        }

        public static class HotProductBean {
            /**
             * id : 25
             * name : 08-10快速夺宝
             * price : 10
             * picture : /static/product/picture/31f34d5f-4124-48a9-93be-fa200e5c9c01.jpg
             * status : 1
             * productHTML : <p><img src="/oneceIndiana/static/product/790b2a8c-befe-401f-808d-417fc1ae681a.jpg" alt="b70510e57b3aa4262667c4ce9f723521"><br></p><p><br></p>
             * catalogID : 1
             * type : 3
             * sort : 1
             * delFalg : 0
             * expectNum : 10
             * alreadyNum : 0
             * nowNo : 3
             * isHot : 1
             * isNew : 1
             * oneceAmount : 1
             * stock : 998
             * catalogName :
             * pictures :
             */

            private int id;
            private String name;
            private double price;
            private String picture;
            private int status;
            private String productHTML;
            private int catalogID;
            private int type;
            private int sort;
            private int delFalg;
            private int expectNum;
            private int alreadyNum;
            private int nowNo;
            private int isHot;
            private int isNew;
            private int oneceAmount;
            private int stock;
            private String catalogName;
            private String pictures;

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

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getProductHTML() {
                return productHTML;
            }

            public void setProductHTML(String productHTML) {
                this.productHTML = productHTML;
            }

            public int getCatalogID() {
                return catalogID;
            }

            public void setCatalogID(int catalogID) {
                this.catalogID = catalogID;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public int getDelFalg() {
                return delFalg;
            }

            public void setDelFalg(int delFalg) {
                this.delFalg = delFalg;
            }

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

            public int getIsHot() {
                return isHot;
            }

            public void setIsHot(int isHot) {
                this.isHot = isHot;
            }

            public int getIsNew() {
                return isNew;
            }

            public void setIsNew(int isNew) {
                this.isNew = isNew;
            }

            public int getOneceAmount() {
                return oneceAmount;
            }

            public void setOneceAmount(int oneceAmount) {
                this.oneceAmount = oneceAmount;
            }

            public int getStock() {
                return stock;
            }

            public void setStock(int stock) {
                this.stock = stock;
            }

            public String getCatalogName() {
                return catalogName;
            }

            public void setCatalogName(String catalogName) {
                this.catalogName = catalogName;
            }

            public String getPictures() {
                return pictures;
            }

            public void setPictures(String pictures) {
                this.pictures = pictures;
            }
        }

        public static class NewProductBean {
            /**
             * id : 25
             * name : 08-10快速夺宝
             * price : 10
             * picture : /static/product/picture/31f34d5f-4124-48a9-93be-fa200e5c9c01.jpg
             * status : 1
             * productHTML : <p><img src="/oneceIndiana/static/product/790b2a8c-befe-401f-808d-417fc1ae681a.jpg" alt="b70510e57b3aa4262667c4ce9f723521"><br></p><p><br></p>
             * catalogID : 1
             * type : 3
             * sort : 1
             * delFalg : 0
             * expectNum : 10
             * alreadyNum : 0
             * nowNo : 3
             * isHot : 1
             * isNew : 1
             * oneceAmount : 1
             * stock : 998
             * catalogName :
             * pictures :
             */

            private int id;
            private String name;
            private double price;
            private String picture;
            private int status;
            private String productHTML;
            private int catalogID;
            private int type;
            private int sort;
            private int delFalg;
            private int expectNum;
            private int alreadyNum;
            private int nowNo;
            private int isHot;
            private int isNew;
            private int oneceAmount;
            private int stock;
            private String catalogName;
            private String pictures;

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

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getProductHTML() {
                return productHTML;
            }

            public void setProductHTML(String productHTML) {
                this.productHTML = productHTML;
            }

            public int getCatalogID() {
                return catalogID;
            }

            public void setCatalogID(int catalogID) {
                this.catalogID = catalogID;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public int getDelFalg() {
                return delFalg;
            }

            public void setDelFalg(int delFalg) {
                this.delFalg = delFalg;
            }

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

            public int getIsHot() {
                return isHot;
            }

            public void setIsHot(int isHot) {
                this.isHot = isHot;
            }

            public int getIsNew() {
                return isNew;
            }

            public void setIsNew(int isNew) {
                this.isNew = isNew;
            }

            public int getOneceAmount() {
                return oneceAmount;
            }

            public void setOneceAmount(int oneceAmount) {
                this.oneceAmount = oneceAmount;
            }

            public int getStock() {
                return stock;
            }

            public void setStock(int stock) {
                this.stock = stock;
            }

            public String getCatalogName() {
                return catalogName;
            }

            public void setCatalogName(String catalogName) {
                this.catalogName = catalogName;
            }

            public String getPictures() {
                return pictures;
            }

            public void setPictures(String pictures) {
                this.pictures = pictures;
            }
        }

        public static class ProductsBean {
            /**
             * id : 26
             * name : 8-11十元夺宝
             * price : 100
             * picture : /static/product/picture/b8f26b43-7114-4772-b7df-542f6e84cc15.jpg
             * status : 1
             * productHTML : <p><img src="/oneceIndiana/static/product/f4519664-fab5-4643-9f9b-3d7c595f7792.jpg" alt="b70510e57b3aa4262667c4ce9f723521"><br></p><p><br></p>
             * catalogID : 2
             * type : 2
             * sort : 1
             * delFalg : 0
             * expectNum : 10
             * alreadyNum : 10
             * nowNo : 1
             * isHot : 1
             * isNew : 1
             * oneceAmount : 10
             * stock : 1000
             * catalogName :
             * pictures :
             */

            private int id;
            private String name;
            private double price;
            private String picture;
            private int status;
            private String productHTML;
            private int catalogID;
            private int type;
            private int sort;
            private int delFalg;
            private int expectNum;
            private int alreadyNum;
            private int nowNo;
            private int isHot;
            private int isNew;
            private int oneceAmount;
            private int stock;
            private String catalogName;
            private String pictures;

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

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getProductHTML() {
                return productHTML;
            }

            public void setProductHTML(String productHTML) {
                this.productHTML = productHTML;
            }

            public int getCatalogID() {
                return catalogID;
            }

            public void setCatalogID(int catalogID) {
                this.catalogID = catalogID;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public int getDelFalg() {
                return delFalg;
            }

            public void setDelFalg(int delFalg) {
                this.delFalg = delFalg;
            }

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

            public int getIsHot() {
                return isHot;
            }

            public void setIsHot(int isHot) {
                this.isHot = isHot;
            }

            public int getIsNew() {
                return isNew;
            }

            public void setIsNew(int isNew) {
                this.isNew = isNew;
            }

            public int getOneceAmount() {
                return oneceAmount;
            }

            public void setOneceAmount(int oneceAmount) {
                this.oneceAmount = oneceAmount;
            }

            public int getStock() {
                return stock;
            }

            public void setStock(int stock) {
                this.stock = stock;
            }

            public String getCatalogName() {
                return catalogName;
            }

            public void setCatalogName(String catalogName) {
                this.catalogName = catalogName;
            }

            public String getPictures() {
                return pictures;
            }

            public void setPictures(String pictures) {
                this.pictures = pictures;
            }
        }
    }
}
