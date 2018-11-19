package com.demo.wechatpay.common;

/**
 * Constant
 *
 * @author WuYuxiang
 * @date 2017/06/16
 */
public class Constant {

    //指令分割符
    public static final String CMD_STR = "\\+";
    public static final String CMD_COM = ",";
    public static final String CMD_COL = ":";
    public static final String CMD_NEL = "\n";
    public static final String CMD_SFE = ";";
    public static final String CMD_AND = "&";
    public static final String ZERO = "0";
    public static final String ONE = "1";

    //通联支付 测试URL
    public static final String UNIONPAY_URL_TEST = "https://vsp.allinpay.com/apiweb/unitorder/pay";
    //交易方式-微信扫码支付
    public static final String PAYTYPE_WECHAT ="W01";
    //交易方式-支付宝扫码支付
    public static final String PAYTYPE_ALI ="A01";
    //微信支付不能使用信用卡
    public static final String PAY_WECHAT_LIMIT="no_credit";
    //订单标题
    public static final String ORDER_BODY ="通联支付测试";
}
