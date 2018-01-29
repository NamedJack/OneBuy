package com.ejar.onebuy.bean.favorite;

import java.util.List;

/**
 * Created by wongxd on 2017/8/17.
 */

public class FavoriteBean {


    /**
     * code : 200
     * msg : OK
     * data : {"msg":"展示收藏成功","code":100,"slist":[{"id":33,"name":"8-16（4324532）","price":100,"picture":"/static/product/picture/5979d2f5-7779-43fb-a928-0d06514ef6d1.png","status":1,"productHTML":"<p><img src=\"http://localhost:8010/oneceIndiana/static/product/efd9eb10-0893-41f7-8fe3-5cc2f26b74fe.png\" alt=\"QQ截图20170814133651\"><br><\/p><p><br><\/p>","catalogID":2,"type":2,"sort":1,"delFalg":0,"expectNum":10,"alreadyNum":2,"nowNo":4,"isHot":1,"isNew":1,"oneceAmount":10,"stock":9997,"isCollection":0,"catalogName":"","pictures":""}]}
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
         * msg : 展示收藏成功
         * code : 100
         * slist : [{"id":33,"name":"8-16（4324532）","price":100,"picture":"/static/product/picture/5979d2f5-7779-43fb-a928-0d06514ef6d1.png","status":1,"productHTML":"<p><img src=\"http://localhost:8010/oneceIndiana/static/product/efd9eb10-0893-41f7-8fe3-5cc2f26b74fe.png\" alt=\"QQ截图20170814133651\"><br><\/p><p><br><\/p>","catalogID":2,"type":2,"sort":1,"delFalg":0,"expectNum":10,"alreadyNum":2,"nowNo":4,"isHot":1,"isNew":1,"oneceAmount":10,"stock":9997,"isCollection":0,"catalogName":"","pictures":""}]
         */

        private  int totalPage;

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        private String msg;
        private int code;
        private List<SlistBean> slist;

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

        public List<SlistBean> getSlist() {
            return slist;
        }

        public void setSlist(List<SlistBean> slist) {
            this.slist = slist;
        }

        public static class SlistBean {
            /**
             * id : 33
             * name : 8-16（4324532）
             * price : 100.0
             * picture : /static/product/picture/5979d2f5-7779-43fb-a928-0d06514ef6d1.png
             * status : 1
             * productHTML : <p><img src="http://localhost:8010/oneceIndiana/static/product/efd9eb10-0893-41f7-8fe3-5cc2f26b74fe.png" alt="QQ截图20170814133651"><br></p><p><br></p>
             * catalogID : 2
             * type : 2
             * sort : 1
             * delFalg : 0
             * expectNum : 10
             * alreadyNum : 2
             * nowNo : 4
             * isHot : 1
             * isNew : 1
             * oneceAmount : 10
             * stock : 9997
             * isCollection : 0
             * catalogName :
             * pictures :
             */


            private boolean isEdit = false;

            public boolean isEdit() {
                return isEdit;
            }

            public void setEdit(boolean edit) {
                isEdit = edit;
            }

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
            private int isCollection;
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

            public int getIsCollection() {
                return isCollection;
            }

            public void setIsCollection(int isCollection) {
                this.isCollection = isCollection;
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
