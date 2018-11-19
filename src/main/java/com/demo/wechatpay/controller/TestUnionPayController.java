package com.demo.wechatpay.controller;

import com.demo.wechatpay.common.Constant;
import com.demo.wechatpay.common.ServerConfig;
import com.demo.wechatpay.entity.json.JSONResponse;
import com.demo.wechatpay.entity.pay.TestPayVO;
import com.demo.wechatpay.entity.pay.TestUnitorderVO;
import com.demo.wechatpay.service.TestUnionPayService;
import com.demo.wechatpay.util.AscIISortUtil;
import com.demo.wechatpay.util.BeanToMapUtils;
import com.demo.wechatpay.util.MD5Util;
import com.demo.wechatpay.util.SignUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * TestUnionPayController
 *
 * @author WuYuxiang
 * @date 2017-06-16
 */
@Controller
@RequestMapping("/pay")
public class TestUnionPayController {

    @Autowired
    ServerConfig serverConfig;
    @Autowired
    TestUnionPayService testUnionPayService;

    private JSONResponse jsonResponse;
    private TestPayVO testPayVO;
    private TestUnitorderVO testUnitorderVO;
    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @RequestMapping("unionpay")
    public JSONResponse unionpay() {
        jsonResponse = new JSONResponse();
        testPayVO = new TestPayVO();
        testUnitorderVO = new TestUnitorderVO();
        testPayVO.setCusid(serverConfig.UNIONPAY_CUSID);
        testPayVO.setAppid(serverConfig.UNIONPAY_APPID);
        testPayVO.setVersion("1");
        testPayVO.setTrxamt("1");
        testPayVO.setReqsn(SignUtil.getUUID());//交易单号
        testPayVO.setPaytype(Constant.PAYTYPE_WECHAT);//支付方式-微信扫码支付
        testPayVO.setRandomstr(SignUtil.getUUID());//随机字符串
        testPayVO.setBody(Constant.ORDER_BODY);//订单标题
        testPayVO.setRemark("备注信息XXXXXXX");
        testPayVO.setValidtime("15");//订单有效期，默认为15分钟
        testPayVO.setNotify_url("http://pay.edufe.zgbxdx.cn/demo/pay/returnMessage");//回调地址
        testPayVO.setLimit_pay(Constant.PAY_WECHAT_LIMIT);//微信支付不能使用信用卡
        Map<String,String> paraMap = new HashMap<String,String>();
        paraMap = BeanToMapUtils.toMap(testPayVO);
        paraMap.remove("sign");
        String ascIIstr = AscIISortUtil.formatUrlMap(paraMap,true,true);
        testPayVO.setSign(MD5Util.MD5Encode(ascIIstr, "UTF-8").toUpperCase());
        try{
            testUnitorderVO = testUnionPayService.getUnitorder(testPayVO);
            jsonResponse.setResult(JSONResponse.Result.SUCCESS);
            jsonResponse.setObject(testUnitorderVO);
        }catch (Exception e){
            log.error("ERROR",e);
        }
        return jsonResponse;
    }



}
