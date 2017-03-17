package com.eollse.entity;

import java.util.List;

/**
 * Created by miliang on 2017/3/17/0017.
 */

public class Jgcx {

    /**
     * Status : 1
     * Message : 消息数据加载成功
     * Page : 1
     * PageCount : 33
     * Action :
     * CountNum : 194
     * NewsShowUrl : /api/Html/news_show.html
     * Data : [{"OpinionId":100606,"TVInfoId":"2","deptName":"德凯物业","deptId":861,"Title":"投诉","UserName":"周琳琳","MobileNo":"18523088885","Address":"","EditDate":"2017-03-15","OpinionClassName":"建议","OpinionClassId":"1","ReDate":"2017-03-16","AuditState":1,"AuditName":"已办结"},{"OpinionId":100605,"TVInfoId":"2","deptName":"鲁能5街区","deptId":851,"Title":"投诉","UserName":"周琳琳","MobileNo":"18523088885","Address":"","EditDate":"2017-03-15","OpinionClassName":"建议","OpinionClassId":"1","ReDate":"2017-03-16","AuditState":1,"AuditName":"已办结"},{"OpinionId":100603,"TVInfoId":"14","deptName":"鲁能5街区","deptId":851,"Title":"gdghh","UserName":"zzfzssvzssgVsafgbg","MobileNo":"18580423366","Address":"","EditDate":"2017-03-14","OpinionClassName":"建议","OpinionClassId":"1","ReDate":"2017-03-15","AuditState":1,"AuditName":"已办结"},{"OpinionId":100602,"TVInfoId":"19","deptName":"德凯物业","deptId":861,"Title":"狗太多了，到处拉屎，中庭好多","UserName":"陈奕","MobileNo":"13586642544","Address":"","EditDate":"2017-03-11","OpinionClassName":"求助","OpinionClassId":"2","ReDate":"2017-03-13","AuditState":1,"AuditName":"已办结"},{"OpinionId":100601,"TVInfoId":"7","deptName":"鲁能西路社区","deptId":854,"Title":"动物预苗","UserName":"游霞","MobileNo":"13183918566","Address":"","EditDate":"2017-03-10","OpinionClassName":"求助","OpinionClassId":"2","ReDate":"2017-03-10","AuditState":1,"AuditName":"已办结"},{"OpinionId":100600,"TVInfoId":"7","deptName":"鲁能西路社区","deptId":854,"Title":"txf","UserName":"txf","MobileNo":"13883318048","Address":"","EditDate":"2017-03-06","OpinionClassName":"求助","OpinionClassId":"2","ReDate":"2017-03-07","AuditState":1,"AuditName":"已办结"}]
     */

    private int Status;
    private String Message;
    private String Page;
    private String PageCount;
    private String Action;
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

    public String getAction() {
        return Action;
    }

    public void setAction(String Action) {
        this.Action = Action;
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
         * OpinionId : 100606
         * TVInfoId : 2
         * deptName : 德凯物业
         * deptId : 861
         * Title : 投诉
         * UserName : 周琳琳
         * MobileNo : 18523088885
         * Address :
         * EditDate : 2017-03-15
         * OpinionClassName : 建议
         * OpinionClassId : 1
         * ReDate : 2017-03-16
         * AuditState : 1
         * AuditName : 已办结
         */

        private int OpinionId;
        private String TVInfoId;
        private String deptName;
        private int deptId;
        private String Title;
        private String UserName;
        private String MobileNo;
        private String Address;
        private String EditDate;
        private String OpinionClassName;
        private String OpinionClassId;
        private String ReDate;
        private int AuditState;
        private String AuditName;

        public int getOpinionId() {
            return OpinionId;
        }

        public void setOpinionId(int OpinionId) {
            this.OpinionId = OpinionId;
        }

        public String getTVInfoId() {
            return TVInfoId;
        }

        public void setTVInfoId(String TVInfoId) {
            this.TVInfoId = TVInfoId;
        }

        public String getDeptName() {
            return deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }

        public int getDeptId() {
            return deptId;
        }

        public void setDeptId(int deptId) {
            this.deptId = deptId;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getMobileNo() {
            return MobileNo;
        }

        public void setMobileNo(String MobileNo) {
            this.MobileNo = MobileNo;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String Address) {
            this.Address = Address;
        }

        public String getEditDate() {
            return EditDate;
        }

        public void setEditDate(String EditDate) {
            this.EditDate = EditDate;
        }

        public String getOpinionClassName() {
            return OpinionClassName;
        }

        public void setOpinionClassName(String OpinionClassName) {
            this.OpinionClassName = OpinionClassName;
        }

        public String getOpinionClassId() {
            return OpinionClassId;
        }

        public void setOpinionClassId(String OpinionClassId) {
            this.OpinionClassId = OpinionClassId;
        }

        public String getReDate() {
            return ReDate;
        }

        public void setReDate(String ReDate) {
            this.ReDate = ReDate;
        }

        public int getAuditState() {
            return AuditState;
        }

        public void setAuditState(int AuditState) {
            this.AuditState = AuditState;
        }

        public String getAuditName() {
            return AuditName;
        }

        public void setAuditName(String AuditName) {
            this.AuditName = AuditName;
        }
    }
}
