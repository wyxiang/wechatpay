package com.demo.wechatpay.controller;

import com.demo.wechatpay.util.AscIISortUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Test
 *
 * @author WuYuxiang
 * @date 2017-06-19
 */
public class Test {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //字典序列排序
        Map<String,String> paraMap = new HashMap<String,String>();
        paraMap.put("total_fee","200");
        paraMap.put("appid", "wxd678efh567hg6787");
        paraMap.put("body", "腾讯充值中心-QQ会员充值");
        paraMap.put("out_trade_no","20150806125346");
        String url = AscIISortUtil.formatUrlMap(paraMap, true, true);
        System.out.println(url);
    }
}
