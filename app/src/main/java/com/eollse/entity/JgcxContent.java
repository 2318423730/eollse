package com.eollse.entity;

import java.util.List;

/**
 * Created by miliang on 2017/3/18/0018.
 */

public class JgcxContent {

    /**
     * Status : 1
     * Message : 数据加载成功
     * OpinionId : 100030
     * deptName : 鲁能西路社区
     * UserName : 吴坤
     * MobileNo : 15213263965
     * Title : 孕前检查
     * Address : 鲁能5街区
     * Content : 住在鲁能6街区是否能免费孕前体检，需要带哪些材料
     * OpinionClassName : 建议
     * EditDate : 2016/11/1 14:46:29
     * ReContent : 可以渝北区免费的孕前健康检查，需要带上申请人双方身份证、户口本、结婚证，到社区进行登记，即可开具免费孕前健康检查的服务单。
     * ReUserName : 佘天娟
     * ReDate : 2016/11/9 16:35:03
     * AuditState : 1
     * AuditName : 已办结
     * Score :
     * ScoreMsg :
     * ScoreDate :
     * Data : [{"OpinionLogId":336,"OpinionId":100030,"deptName":"鲁能西路社区","deptId":854,"EditUserName":"佘天娟","EditUserId":"1530","EditDate":"2016/11/9 16:35:03","ReContent":"已办结。","AuditState":""},{"OpinionLogId":231,"OpinionId":100030,"deptName":"鲁能5街区","deptId":851,"EditUserName":"王玲","EditUserId":"1531","EditDate":"2016/11/9 10:19:26","ReContent":"已转到鲁能西路社区处理","AuditState":""}]
     */

    private int Status;
    private String Message;
    private String OpinionId;
    private String deptName;
    private String UserName;
    private String MobileNo;
    private String Title;
    private String Address;
    private String Content;
    private String OpinionClassName;
    private String EditDate;
    private String ReContent;
    private String ReUserName;
    private String ReDate;
    private String AuditState;
    private String AuditName;
    private String Score;
    private String ScoreMsg;
    private String ScoreDate;
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

    public String getOpinionId() {
        return OpinionId;
    }

    public void setOpinionId(String OpinionId) {
        this.OpinionId = OpinionId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
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

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public String getOpinionClassName() {
        return OpinionClassName;
    }

    public void setOpinionClassName(String OpinionClassName) {
        this.OpinionClassName = OpinionClassName;
    }

    public String getEditDate() {
        return EditDate;
    }

    public void setEditDate(String EditDate) {
        this.EditDate = EditDate;
    }

    public String getReContent() {
        return ReContent;
    }

    public void setReContent(String ReContent) {
        this.ReContent = ReContent;
    }

    public String getReUserName() {
        return ReUserName;
    }

    public void setReUserName(String ReUserName) {
        this.ReUserName = ReUserName;
    }

    public String getReDate() {
        return ReDate;
    }

    public void setReDate(String ReDate) {
        this.ReDate = ReDate;
    }

    public String getAuditState() {
        return AuditState;
    }

    public void setAuditState(String AuditState) {
        this.AuditState = AuditState;
    }

    public String getAuditName() {
        return AuditName;
    }

    public void setAuditName(String AuditName) {
        this.AuditName = AuditName;
    }

    public String getScore() {
        return Score;
    }

    public void setScore(String Score) {
        this.Score = Score;
    }

    public String getScoreMsg() {
        return ScoreMsg;
    }

    public void setScoreMsg(String ScoreMsg) {
        this.ScoreMsg = ScoreMsg;
    }

    public String getScoreDate() {
        return ScoreDate;
    }

    public void setScoreDate(String ScoreDate) {
        this.ScoreDate = ScoreDate;
    }

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * OpinionLogId : 336
         * OpinionId : 100030
         * deptName : 鲁能西路社区
         * deptId : 854
         * EditUserName : 佘天娟
         * EditUserId : 1530
         * EditDate : 2016/11/9 16:35:03
         * ReContent : 已办结。
         * AuditState :
         */

        private int OpinionLogId;
        private int OpinionId;
        private String deptName;
        private int deptId;
        private String EditUserName;
        private String EditUserId;
        private String EditDate;
        private String ReContent;
        private String AuditState;

        public int getOpinionLogId() {
            return OpinionLogId;
        }

        public void setOpinionLogId(int OpinionLogId) {
            this.OpinionLogId = OpinionLogId;
        }

        public int getOpinionId() {
            return OpinionId;
        }

        public void setOpinionId(int OpinionId) {
            this.OpinionId = OpinionId;
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

        public String getEditUserName() {
            return EditUserName;
        }

        public void setEditUserName(String EditUserName) {
            this.EditUserName = EditUserName;
        }

        public String getEditUserId() {
            return EditUserId;
        }

        public void setEditUserId(String EditUserId) {
            this.EditUserId = EditUserId;
        }

        public String getEditDate() {
            return EditDate;
        }

        public void setEditDate(String EditDate) {
            this.EditDate = EditDate;
        }

        public String getReContent() {
            return ReContent;
        }

        public void setReContent(String ReContent) {
            this.ReContent = ReContent;
        }

        public String getAuditState() {
            return AuditState;
        }

        public void setAuditState(String AuditState) {
            this.AuditState = AuditState;
        }
    }
}
