package wang.wongxd.wquicklib.base.retrofit.model;

import java.util.List;

/**
 * Created by wongxd on 2017/8/15.
 */

public class LuckyBean {

    private List<ProductListBean> productList;

    public List<ProductListBean> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductListBean> productList) {
        this.productList = productList;
    }

    public static class ProductListBean {
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
         * nowNo : 72
         * isHot : 1
         * isNew : 1
         * oneceAmount : 10
         * stock : 929
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

    @Override
    public String toString() {
        return productList.size() +productList.get(0).getName();
    }
}
