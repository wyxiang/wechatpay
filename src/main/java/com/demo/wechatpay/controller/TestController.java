package com.demo.wechatpay.controller;

import com.demo.wechatpay.entity.json.JSONResponse;
import com.demo.wechatpay.entity.wechat.UnifiedorderDataVO;
import com.demo.wechatpay.util.*;
import com.demo.wechatpay.util.TextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * TestController
 *
 * @author WuYuxiang
 * @date 2016/10/31
 */
@Controller
@RequestMapping("/")
public class TestController {
    private JSONResponse jsonResponse;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * forward index.jsp
     *
     * @return
     */
    @RequestMapping("index")
    public String index() {
        return "index";
    }


    @RequestMapping("wechatpay")
    @ResponseBody
    public JSONResponse wechatpay() {
        jsonResponse = new JSONResponse();
        jsonResponse.setResult(JSONResponse.Result.SUCCESS);
        jsonResponse.setQrCode("weixin://wxpay/bizpayurl?pr=CqaIqk5");
        UnifiedorderDataVO unifiedorderDataVO = new UnifiedorderDataVO();
        unifiedorderDataVO = this.unifiedorder();
        TextMessage textMessage = new TextMessage(unifiedorderDataVO);
        String respMessage = MessageUtil.textMessageToXml(textMessage);


        try {
            String result = HttpUtil.httpsRequest("https://api.mch.weixin.qq.com/pay/unifiedorder",
                    "POST", respMessage.replaceAll("__", "_"));
            Map<String, String> resultmap = XMLUtil.doXMLParse(result);
            if ("SUCCESS".equals(resultmap.get("result_code")) && "SUCCESS".equals(resultmap.get("return_code"))) {

                jsonResponse.setQrCode(resultmap.get("code_url"));
            }
        } catch (Exception e1) {
            log.error("错误：扫码支付 -调用统一支付接口获取预支付订单出现异常！" + textMessage, e1);
        }

        return jsonResponse;
    }


    /**
     * 封装统一下单订单参数
     *
     * @return
     */

    public UnifiedorderDataVO unifiedorder() {
        UnifiedorderDataVO unifiedorderDataVO = new UnifiedorderDataVO();
        //公众号id--必填
        unifiedorderDataVO.setAppid("wxc7f1f00a7e6d9165");
        //商品备注--必填
        unifiedorderDataVO.setAttach("certcodexxxxxxxxxxxxx");
        //商户号（微信支付分配的商户号）--必填
        unifiedorderDataVO.setMch_id("1270224101");
        //随即字符串--必填
        unifiedorderDataVO.setNonce_str(SignUtil.getUUID());
        //商品描述--必填
        unifiedorderDataVO.setBody("测试支付");
        String orderId = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((new Date())).
                replaceAll("(?:-| |:)", "") + SignUtil.getUUID().substring(0, 18);
        //商户订单号--必填
        unifiedorderDataVO.setOut_trade_no(orderId);
        //总金额--必填
        unifiedorderDataVO.setTotal_fee(1);
        //终端ip
        unifiedorderDataVO.setSpbill_create_ip("123.103.13.184");
        //通知地址--必填
//        unifiedorderDataVO.setNotify_url("http://pay.edufe.zgbxdx.cn/demo/returnMessage");
        unifiedorderDataVO.setNotify_url("http://zgrz.daxue.iachina.cn/demo/returnMessage");

        //交易类型--必填-NATIVE二维码支付
        unifiedorderDataVO.setTrade_type("NATIVE");
        unifiedorderDataVO.setProduct_id(orderId);
        //商户key -- secret
        unifiedorderDataVO.setKey("5fb57d35446a4264810766ef50f581ad");

        //拼接字符串 --签名用
        String paySign = "appid=" + unifiedorderDataVO.getAppid() +
                "&attach=" + unifiedorderDataVO.getAttach() +
                "&body=" + unifiedorderDataVO.getBody() +
                "&mch_id=" + unifiedorderDataVO.getMch_id() +
                "&nonce_str=" + unifiedorderDataVO.getNonce_str() +
                "&notify_url=" + unifiedorderDataVO.getNotify_url() +
                "&out_trade_no=" + unifiedorderDataVO.getOut_trade_no() +
                "&product_id=" + unifiedorderDataVO.getProduct_id() +
                "&spbill_create_ip=" + unifiedorderDataVO.getSpbill_create_ip() +
                "&total_fee=" + unifiedorderDataVO.getTotal_fee() +
                "&trade_type=" + unifiedorderDataVO.getTrade_type() +
                "&key=" + unifiedorderDataVO.getKey();
        //签名
        unifiedorderDataVO.setSign(MD5Util.MD5Encode(paySign.toString(), "UTF-8").toUpperCase());
        return unifiedorderDataVO;
    }

    /**
     * 支付成功后回调处理
     */
    @RequestMapping("returnMessage")
    public void returnMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader((ServletInputStream) request.getInputStream()));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            String resXml = "";
            String wcXml = sb.toString();
            Map<String, String> m;
            UnifiedorderDataVO unifiedorderDataVO = new UnifiedorderDataVO();
            try {
                m = XMLUtil.doXMLParse(wcXml);
                unifiedorderDataVO.setAppid(m.get("appid").toString());
                unifiedorderDataVO.setAttach(m.get("attach").toString());
                unifiedorderDataVO.setFee_type(m.get("fee_type").toString());
                unifiedorderDataVO.setMch_id(m.get("mch_id").toString());
                unifiedorderDataVO.setOpenid(m.get("openid").toString());
                unifiedorderDataVO.setOut_trade_no(m.get("out_trade_no").toString());
                unifiedorderDataVO.setResultCode(m.get("result_code").toString());
                unifiedorderDataVO.setReturnCode(m.get("return_code").toString());
                unifiedorderDataVO.setSign(m.get("sign").toString());
                unifiedorderDataVO.setTimeEnd(m.get("time_end").toString());
                unifiedorderDataVO.setTotal_fee(Integer.parseInt(m.get("total_fee")));
                unifiedorderDataVO.setTrade_type(m.get("trade_type").toString());
                unifiedorderDataVO.setTransactionId(m.get("transaction_id").toString());
                System.out.println(unifiedorderDataVO.getMch_id());
                System.out.println(unifiedorderDataVO.getTotal_fee());
                System.out.println(unifiedorderDataVO.getAttach());
                System.out.println(unifiedorderDataVO.getBody());
            } catch (Exception e) {
                e.printStackTrace();
                log.info("解析回掉xml文件失败" + e);
            }
            if ("SUCCESS".equals(unifiedorderDataVO.getResultCode())) {

                log.info("回调成功！！回调成功！！回调成功！！回调成功！！回调成功！！回调成功！！");
                System.out.println("回调成功！！回调成功！！回调成功！！回调成功！！回调成功！！回调成功！！");


                log.info("===========支付成功===========");
                resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                        + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
                log.info("===========修改订单状态===========");
            } else {
                resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                        + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
            }
            log.info("微信支付回调数据结束");
            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
            out.write(resXml.getBytes());
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("支付确认异常" + e);
        }
    }
}
