package com.demo.wechatpay.entity.pay;

/**
 * TestUnitorderVO
 *
 * @author WuYuxiang
 * @date 2017-06-16
 */
public class TestUnitorderVO {
    //返回码
    private String retcode;
    //返回秒说明
    private String retmsg;
    //原交易流水
    private String oldtrxid;
    //随机字符串
    private String randomstr;
    //签名
    private String sign;

    private TestUnitorder testUnitorder;

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

    public String getOldtrxid() {
        return oldtrxid;
    }

    public void setOldtrxid(String oldtrxid) {
        this.oldtrxid = oldtrxid;
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

    public TestUnitorder getTestUnitorder() {
        return testUnitorder;
    }

    public void setTestUnitorder(TestUnitorder testUnitorder) {
        this.testUnitorder = testUnitorder;
    }
}
