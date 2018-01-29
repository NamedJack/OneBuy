package com.ejar.onebuy.bean.me;

import java.util.List;

/**
 * Created by wongxd on 2017/8/17.
 * 客服
 */

public class ServiceBean {

    /**
     * code : 200
     * msg : OK
     * data : {"code":0,"totalPage":2,"list":[{"id":1,"title":"一元夺宝规则","content":"wefsdf若是噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢哦浪费的空间刚发的离开过很多咖啡馆里的咖啡馆领导纷纷赶来大夫给剪了短发离开的发过来的客房价格来到桂林的 结果来看的更可怜的风景离开对方就够了对方就够了对方就够了极度分裂国家都浪费国家的立法机关领导开发机构"},{"id":2,"title":"二元抢宝规则","content":"66666666666666666666666666666666666666666666666666666666666666"},{"id":3,"title":"三元夺宝规则","content":"按时间大洛杉矶大厦将颠拉阿拉圣诞节案例是空间大洛杉矶阿拉斯加的拉开始大家家爱看卡上面即可垃圾是"},{"id":4,"title":"四元夺宝规则","content":"萨拉萨角大厦建档立卡商家的拉斯科技的拉丝机杀菌灯按时建档立卡时间到了按时是两科的简历"},{"id":5,"title":"五元夺宝规则","content":"代收款玫琳凯登录码昆明路口莱卡棉电闪雷鸣付了款收到没开始懂了的什么菲利克斯代码"},{"id":6,"title":"六元夺宝规则","content":"施蒂利克老师大家水电费家里耍可点击老师大家三德科技老师的讲课的数据看到监控 "},{"id":7,"title":"七元夺宝规则","content":"数据库都你上课达康书记了的数据打卡机大家看到测控技术的科技圣诞快乐斯柯达解放路萨达"},{"id":8,"title":"八元夺宝规则","content":"撒大家看到卡刷机大师建档立卡按时间大卡司李俊达看似简单卡萨就对了奥斯卡的简历"},{"id":9,"title":"九元夺宝规则","content":"萨达是了解到拉萨打扫家里的看见爱上看来大家安利少看点奥圣诞节离开三队 "},{"id":10,"title":"十元夺宝规则","content":"撒开了多久啊开始了解奥斯卡的吉安市科技的按时间按时吉拉斯科技大洛杉矶 肯定；las啥看了看爱上看到"}]}
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
         * totalPage : 2
         * list : [{"id":1,"title":"一元夺宝规则","content":"wefsdf若是噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢哦浪费的空间刚发的离开过很多咖啡馆里的咖啡馆领导纷纷赶来大夫给剪了短发离开的发过来的客房价格来到桂林的 结果来看的更可怜的风景离开对方就够了对方就够了对方就够了极度分裂国家都浪费国家的立法机关领导开发机构"},{"id":2,"title":"二元抢宝规则","content":"66666666666666666666666666666666666666666666666666666666666666"},{"id":3,"title":"三元夺宝规则","content":"按时间大洛杉矶大厦将颠拉阿拉圣诞节案例是空间大洛杉矶阿拉斯加的拉开始大家家爱看卡上面即可垃圾是"},{"id":4,"title":"四元夺宝规则","content":"萨拉萨角大厦建档立卡商家的拉斯科技的拉丝机杀菌灯按时建档立卡时间到了按时是两科的简历"},{"id":5,"title":"五元夺宝规则","content":"代收款玫琳凯登录码昆明路口莱卡棉电闪雷鸣付了款收到没开始懂了的什么菲利克斯代码"},{"id":6,"title":"六元夺宝规则","content":"施蒂利克老师大家水电费家里耍可点击老师大家三德科技老师的讲课的数据看到监控 "},{"id":7,"title":"七元夺宝规则","content":"数据库都你上课达康书记了的数据打卡机大家看到测控技术的科技圣诞快乐斯柯达解放路萨达"},{"id":8,"title":"八元夺宝规则","content":"撒大家看到卡刷机大师建档立卡按时间大卡司李俊达看似简单卡萨就对了奥斯卡的简历"},{"id":9,"title":"九元夺宝规则","content":"萨达是了解到拉萨打扫家里的看见爱上看来大家安利少看点奥圣诞节离开三队 "},{"id":10,"title":"十元夺宝规则","content":"撒开了多久啊开始了解奥斯卡的吉安市科技的按时间按时吉拉斯科技大洛杉矶 肯定；las啥看了看爱上看到"}]
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
             * id : 1
             * title : 一元夺宝规则
             * content : wefsdf若是噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢哦浪费的空间刚发的离开过很多咖啡馆里的咖啡馆领导纷纷赶来大夫给剪了短发离开的发过来的客房价格来到桂林的 结果来看的更可怜的风景离开对方就够了对方就够了对方就够了极度分裂国家都浪费国家的立法机关领导开发机构
             */

            private int id;
            private String title;
            private String content;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }
    }
}
