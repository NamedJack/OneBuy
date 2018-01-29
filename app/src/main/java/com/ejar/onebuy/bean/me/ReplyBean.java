package com.ejar.onebuy.bean.me;

import java.util.List;

/**
 * Created by wongxd on 2017/8/18.
 */

public class ReplyBean {

    /**
     * code : 200
     * msg : OK
     * data : {"code":0,"totalPage":1,"list":[{"id":4,"replyContent":"666666","userId":4,"adminId":"","faTime":"","replyTime":"","leContent":"奥斯卡溜达了看似简单克拉商家的"}]}
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
         * totalPage : 1
         * list : [{"id":4,"replyContent":"666666","userId":4,"adminId":"","faTime":"","replyTime":"","leContent":"奥斯卡溜达了看似简单克拉商家的"}]
         */

        private int code;
        private int totalPage;
        private List<ListBean> list;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 4
             * replyContent : 666666
             * userId : 4
             * adminId :
             * faTime :
             * replyTime :
             * leContent : 奥斯卡溜达了看似简单克拉商家的
             */

            private int id;
            private String replyContent;
            private int userId;
            private String adminId;
            private String faTime;
            private String replyTime;
            private String leContent;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getReplyContent() {
                return replyContent;
            }

            public void setReplyContent(String replyContent) {
                this.replyContent = replyContent;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getAdminId() {
                return adminId;
            }

            public void setAdminId(String adminId) {
                this.adminId = adminId;
            }

            public String getFaTime() {
                return faTime;
            }

            public void setFaTime(String faTime) {
                this.faTime = faTime;
            }

            public String getReplyTime() {
                return replyTime;
            }

            public void setReplyTime(String replyTime) {
                this.replyTime = replyTime;
            }

            public String getLeContent() {
                return leContent;
            }

            public void setLeContent(String leContent) {
                this.leContent = leContent;
            }
        }
    }
}
