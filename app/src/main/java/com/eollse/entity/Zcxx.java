package com.eollse.entity;

import java.util.List;

/**
 * Created by miliang on 2017/3/15/0015.
 */

public class Zcxx {

    /**
     * Status : 1
     * Message : 消息数据加载成功
     * Page : 1
     * PageCount : 3
     * CountNum : 23
     * NewsShowUrl : /api/Html/news_show.html
     * Data : [{"NewsId":1301,"DeptName":"重庆市","ImageIndex":"","Title":"渝北区水利局工会开展防汛抢险拓展训练","ModuId":1,"ModuName":"国际视野","EditDate":"2016-11-02","EditUserName":"管理员","istop":0},{"NewsId":1300,"DeptName":"重庆市","ImageIndex":"","Title":"渝北区总工会举办2016年工会专职工会干部培训班","ModuId":1,"ModuName":"国际视野","EditDate":"2016-11-02","EditUserName":"管理员","istop":0},{"NewsId":1299,"DeptName":"总工会","ImageIndex":"","Title":"关于推进2016年度职工互助保险 参保工作的通知","ModuId":1,"ModuName":"国际视野","EditDate":"2016-11-02","EditUserName":"12","istop":0},{"NewsId":1272,"DeptName":"区群团活中心","ImageIndex":"","Title":"同步录音录像庭审效率提升","ModuId":1,"ModuName":"国际视野","EditDate":"2016-11-02","EditUserName":"区群团活中心","istop":0},{"NewsId":1271,"DeptName":"区群团活中心","ImageIndex":"","Title":"中华人民共和国归侨侨眷权益保护法实施办法","ModuId":1,"ModuName":"国际视野","EditDate":"2016-11-02","EditUserName":"区群团活中心","istop":0},{"NewsId":1270,"DeptName":"区群团活中心","ImageIndex":"","Title":"国务院印发《\u201c十三五\u201d加快残疾人小康进程规划纲要》","ModuId":1,"ModuName":"国际视野","EditDate":"2016-11-02","EditUserName":"区群团活中心","istop":0},{"NewsId":1268,"DeptName":"区群团活中心","ImageIndex":"","Title":"渝北区副区长唐丽华检查全区残疾人信息数据动态更新工作","ModuId":1,"ModuName":"国际视野","EditDate":"2016-11-02","EditUserName":"区群团活中心","istop":0},{"NewsId":1267,"DeptName":"区群团活中心","ImageIndex":"","Title":"增强忧患意识，抓好国防教育","ModuId":1,"ModuName":"国际视野","EditDate":"2016-11-02","EditUserName":"区群团活中心","istop":0},{"NewsId":1266,"DeptName":"区群团活中心","ImageIndex":"","Title":"渝北群团\u201c一网一微\u201d上线 有奖征集渝北发展良策","ModuId":1,"ModuName":"国际视野","EditDate":"2016-11-02","EditUserName":"区群团活中心","istop":0},{"NewsId":1079,"DeptName":"区工商联","ImageIndex":"","Title":"习近平在庆祝中国共产党成立95周年大会上的讲话","ModuId":1,"ModuName":"国际视野","EditDate":"2016-11-01","EditUserName":"6","istop":0}]
     */

    private int Status;
    private String Message;
    private String Page;
    private String PageCount;
    private String CountNum;
    private String NewsShowUrl;
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

    public String getPage() {
        return Page;
    }

    public void setPage(String Page) {
        this.Page = Page;
    }

    public String getPageCount() {
        return PageCount;
    }

    public void setPageCount(String PageCount) {
        this.PageCount = PageCount;
    }

    public String getCountNum() {
        return CountNum;
    }

    public void setCountNum(String CountNum) {
        this.CountNum = CountNum;
    }

    public String getNewsShowUrl() {
        return NewsShowUrl;
    }

    public void setNewsShowUrl(String NewsShowUrl) {
        this.NewsShowUrl = NewsShowUrl;
    }

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * NewsId : 1301
         * DeptName : 重庆市
         * ImageIndex :
         * Title : 渝北区水利局工会开展防汛抢险拓展训练
         * ModuId : 1
         * ModuName : 国际视野
         * EditDate : 2016-11-02
         * EditUserName : 管理员
         * istop : 0
         */

        private int NewsId;
        private String DeptName;
        private String ImageIndex;
        private String Title;
        private int ModuId;
        private String ModuName;
        private String EditDate;
        private String EditUserName;
        private int istop;

        public int getNewsId() {
            return NewsId;
        }

        public void setNewsId(int NewsId) {
            this.NewsId = NewsId;
        }

        public String getDeptName() {
            return DeptName;
        }

        public void setDeptName(String DeptName) {
            this.DeptName = DeptName;
        }

        public String getImageIndex() {
            return ImageIndex;
        }

        public void setImageIndex(String ImageIndex) {
            this.ImageIndex = ImageIndex;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public int getModuId() {
            return ModuId;
        }

        public void setModuId(int ModuId) {
            this.ModuId = ModuId;
        }

        public String getModuName() {
            return ModuName;
        }

        public void setModuName(String ModuName) {
            this.ModuName = ModuName;
        }

        public String getEditDate() {
            return EditDate;
        }

        public void setEditDate(String EditDate) {
            this.EditDate = EditDate;
        }

        public String getEditUserName() {
            return EditUserName;
        }

        public void setEditUserName(String EditUserName) {
            this.EditUserName = EditUserName;
        }

        public int getIstop() {
            return istop;
        }

        public void setIstop(int istop) {
            this.istop = istop;
        }
    }
}
