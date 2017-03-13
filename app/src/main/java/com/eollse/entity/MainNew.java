package com.eollse.entity;

import java.util.List;

/**
 * Created by miliang on 2017/3/8/0008.
 */

public class MainNew {

    /**
     * Status : 1
     * Message : 消息数据加载成功
     * Page : 1
     * PageCount : 49
     * CountNum : 293
     * NewsShowUrl : /api/Html/news_show.html
     * Data : [{"NewsId":1419,"DeptName":"重庆市","ImageIndex":"","Title":"联手送温暖 慰问暖人心","ModuId":3,"ModuName":"惠民政策","EditDate":"2017-02-08","EditUserName":"管理员","istop":0},{"NewsId":1436,"DeptName":"区妇联","ImageIndex":"","Title":"渝北区大盛镇妇联\u201c妇女实践课堂\u201d开讲","ModuId":3,"ModuName":"惠民政策","EditDate":"2017-02-06","EditUserName":"4","istop":0},{"NewsId":1430,"DeptName":"重庆市","ImageIndex":"","Title":"市四届人大五次会议闭幕","ModuId":3,"ModuName":"惠民政策","EditDate":"2017-02-06","EditUserName":"管理员","istop":0},{"NewsId":1447,"DeptName":"区残联","ImageIndex":"","Title":"渝北区残联机关支部召开2016年度组织生活会","ModuId":3,"ModuName":"惠民政策","EditDate":"2017-01-25","EditUserName":"14","istop":0},{"NewsId":1446,"DeptName":"区残联","ImageIndex":"","Title":"渝北区残联为贫困残疾人免费安装假肢","ModuId":3,"ModuName":"惠民政策","EditDate":"2017-01-24","EditUserName":"14","istop":0},{"NewsId":1445,"DeptName":"区残联","ImageIndex":"","Title":"渝北区委副书记指导区残联领导班子民主生活会","ModuId":3,"ModuName":"惠民政策","EditDate":"2017-01-24","EditUserName":"14","istop":0}]
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
         * NewsId : 1419
         * DeptName : 重庆市
         * ImageIndex :
         * Title : 联手送温暖 慰问暖人心
         * ModuId : 3
         * ModuName : 惠民政策
         * EditDate : 2017-02-08
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
