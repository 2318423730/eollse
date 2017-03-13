package com.eollse.entity;

import java.util.List;

/**
 * Created by miliang on 2017/3/13/0013.
 */

public class Type {

    /**
     * Status : 1
     * Message : 消息数据加载成功
     * CountNum : 5
     * Data : [{"OpinionClassId":1,"OpinionClassName":"建议"},{"OpinionClassId":2,"OpinionClassName":"求助"},{"OpinionClassId":4,"OpinionClassName":"咨询"},{"OpinionClassId":3,"OpinionClassName":"投诉"},{"OpinionClassId":5,"OpinionClassName":"其它"}]
     */

    private int Status;
    private String Message;
    private String CountNum;
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

    public String getCountNum() {
        return CountNum;
    }

    public void setCountNum(String CountNum) {
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
         * OpinionClassId : 1
         * OpinionClassName : 建议
         */

        private int OpinionClassId;
        private String OpinionClassName;

        public int getOpinionClassId() {
            return OpinionClassId;
        }

        public void setOpinionClassId(int OpinionClassId) {
            this.OpinionClassId = OpinionClassId;
        }

        public String getOpinionClassName() {
            return OpinionClassName;
        }

        public void setOpinionClassName(String OpinionClassName) {
            this.OpinionClassName = OpinionClassName;
        }
    }
}
