package com.demo.wechatpay.entity.pay;

/**
 * TestUnitorder
 *
 * @author WuYuxiang
 * @date 2017-06-16
 */
public class TestUnitorder {

    //返回码
    private String retcode;
    //返回码说明
    private String retmsg;
    //-----以下信息只有当retcode为SUCCESS时有返回
    //商户号
    private String cusid;
    //应用ID
    private String appid;
    //交易单号
    private String trxid;
    //商户订单号
    private String reqsn;
    //交易状态
    private String trxstatus;
    //交易完成时间
    private String fintime;
    //错误原因
    private String errmsg;
    //随机字符串
    private String randomstr;
    //签名
    private String sign;

    public String getRetcode() {
        return retcode;
    }

    public void setRetcode(String retcode) {
        this.retcode = retcode;
    }

    public String getRetmsg() {
        return retmsg;
    }

    public void setRetmsg(String retmsg) {
        this.retmsg = retmsg;
    }

    public String getCusid() {
        return cusid;
    }

    public void setCusid(String cusid) {
        this.cusid = cusid;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getTrxid() {
        return trxid;
    }

    public void setTrxid(String trxid) {
        this.trxid = trxid;
    }

    public String getReqsn() {
        return reqsn;
    }

    public void setReqsn(String reqsn) {
        this.reqsn = reqsn;
    }

    public String getTrxstatus() {
        return trxstatus;
    }

    public void setTrxstatus(String trxstatus) {
        this.trxstatus = trxstatus;
    }

    public String getFintime() {
        return fintime;
    }

    public void setFintime(String fintime) {
        this.fintime = fintime;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getRandomstr() {
        return randomstr;
    }

    public void setRandomstr(String randomstr) {
        this.randomstr = randomstr;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
