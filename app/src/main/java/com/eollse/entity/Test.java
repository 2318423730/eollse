package com.eollse.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/4/6/0006.
 */

public class Test {

    /**
     * Status : 1
     * Message : 消息数据加载成功
     * CountNum : 6
     * Data : [{"id":1,"title":"党员在小区里义务巡逻","site":"http://192.168.1.222:8099/Government/image/9.jpg","shzt":"0"},{"id":2,"title":"党员志愿者带领小区文化队伍开展国防教育宣传活动","site":"http://192.168.1.222:8099/Government/image/10.jpg","shzt":"0"},{"id":3,"title":"社区党员参与\u201d相约星期四，谈谈烦心事\u201c活动，在小区收集群众意见","site":"http://192.168.1.222:8099/Government/image/11.jpg","shzt":"0"},{"id":4,"title":"社区开展廉政文化进小区","site":"http://192.168.1.222:8099/Government/image/12.jpg","shzt":"0"},{"id":5,"title":"社区主题党日\u2014\u2014民主评议党员","site":"http://192.168.1.222:8099/Government/image/13.jpg","shzt":"0"},{"id":6,"title":"主题党日\u2014\u2014党员开展洁净家园活动","site":"http://192.168.1.222:8099/Government/image/14.jpg","shzt":"0"}]
     */

    private int Status;
    private String Message;
    private int CountNum;
    private List<DataBean> Data;

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public int getCountNum() {
        return CountNum;
    }

    public void setCountNum(int CountNum) {
        this.CountNum = CountNum;
    }

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * id : 1
         * title : 党员在小区里义务巡逻
         * site : http://192.168.1.222:8099/Government/image/9.jpg
         * shzt : 0
         */

        private int id;
        private String title;
        private String site;
        private String shzt;

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

        public String getSite() {
            return site;
        }

        public void setSite(String site) {
            this.site = site;
        }

        public String getShzt() {
            return shzt;
        }

        public void setShzt(String shzt) {
            this.shzt = shzt;
        }
    }
}
