package com.ly.bs.modules.accountcenter.entity;

import java.io.Serializable;
import java.util.Date;

/**
* @ClassName: BusBussinessEntityEntity
* @Description: 系统用户日志
* @author wangyu
* @date 2017-06-12 13:59:37
*/
public class BusBussinessEntityEntity implements Serializable {

    /**
    * 主键
    **/
    private Long bussinessid;
    /**
    * 业务名称
    **/
    private String bizname;
    /**
    * 业务标识
    **/
    private Integer bizflag;
    /**
    * 业务单价
    **/
    private Integer bizamount;
    /**
    * 按订单或者按车票
    **/
    private Long biztype;
    /**
    * 费用规则
    **/
    private Long bizchargingrules;

    public BusBussinessEntityEntity() {
        super();
    }

    public BusBussinessEntityEntity(Long bussinessid, String bizname, Integer bizflag, Integer bizamount, Long biztype, Long bizchargingrules) {
        this.bussinessid = bussinessid;
        this.bizname = bizname;
        this.bizflag = bizflag;
        this.bizamount = bizamount;
        this.biztype = biztype;
        this.bizchargingrules = bizchargingrules;
    }

    public Long getBussinessid() {
        return bussinessid;
    }

    public void setBussinessid(Long bussinessid) {
        this.bussinessid = bussinessid;
    }

    public String getBizname() {
        return bizname;
    }

    public void setBizname(String bizname) {
        this.bizname = bizname == null ? null : bizname.trim();
    }

    public Integer getBizflag() {
        return bizflag;
    }

    public void setBizflag(Integer bizflag) {
        this.bizflag = bizflag;
    }

    public Integer getBizamount() {
        return bizamount;
    }

    public void setBizamount(Integer bizamount) {
        this.bizamount = bizamount;
    }

    public Long getBiztype() {
        return biztype;
    }

    public void setBiztype(Long biztype) {
        this.biztype = biztype;
    }

    public Long getBizchargingrules() {
        return bizchargingrules;
    }

    public void setBizchargingrules(Long bizchargingrules) {
        this.bizchargingrules = bizchargingrules;
    }

}